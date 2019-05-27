import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class DatabaseConnect {

    private String username;
    private String password;
    private Connection connection;
    private PreparedStatement prepStatement;
    private String URL = "jdbc:mysql://localhost:3306/yandexDiskData?serverTimezone=UTC&useSSL=false";

    private String[] tablesName;

    private static String mainPath = "src/main/resources/";

    public DatabaseConnect(String username, String password) throws SQLException {
        this.username = username;
        this.password = password;
        this.tablesName = new String[]{"ipDataM", "logsLM", "userDataM"};
        try {
            this.connection = DriverManager.getConnection(URL, this.username, this.password);
        }catch (Exception e){
            System.out.println(e);
        }

        this.prepStatement = null;
    }

    public void init() throws IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/yandex-l_p.properties"));

        DiskParcer parcer = new DiskParcer(properties.get("login").toString(),
                properties.get("password").toString());

        parcer.parceDisk();
        System.out.println("Yandex disk was parsed");

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/yandexDiskData?serverTimezone=UTC&useSSL=false",this.username,this.password);

            System.out.println("Initialization of database and tables...");

            Statement statement = this.connection.createStatement();
            statement.executeUpdate(String.format("create table if not exists %s (\n" +
                                        "ip text," +
                                        "area text);", this.tablesName[0]));

            statement.executeUpdate(String.format("create table if not exists %s (\n" +
                                        "ip text," +
                                        "timeQuery TIMESTAMP," +
                                        "query text," +
                                        "sizePage int," +
                                        "status int," +
                                        "info text);", this.tablesName[1]));

            statement.executeUpdate(String.format("create table if not exists %s (\n" +
                                        "ip text," +
                                        "browser text," +
                                        "gender boolean," +
                                        "age int);", this.tablesName[2]));

            for (var file : this.getTextData()){
//                PreparedStatement ps = null;
                for (var line : file){
                    String query;
                    if (line.length == 2){

                        query = "insert into ipDataM (ip, area) " +
                                "values (?,?)";
                        this.prepStatement = this.connection.prepareStatement(query);
                        this.prepStatement.setString(1, line[0]);
                        this.prepStatement.setString(2, line[1]);

                    }else if(line.length == 6){

                        SimpleDateFormat timeFormatter = new SimpleDateFormat(
                                "yyyyMMddhhmmss");

                        var raw_time = timeFormatter.parse(line[1]).toInstant().toString();
                        raw_time = raw_time.replace('T', ' ');
                        raw_time = raw_time.replace('Z', ' ');
                        Timestamp time = Timestamp.valueOf(raw_time);

                        query = "insert into logsLM (ip, timeQuery, query, sizePage, status, info) " +
                                "values (?, ?, ?, ?, ?, ?)";

                        this.prepStatement = this.connection.prepareStatement(query);
                        this.prepStatement.setString(1, line[0]);
                        this.prepStatement.setTimestamp(2, time);
                        this.prepStatement.setString(3,line[2]);
                        this.prepStatement.setInt(4, Integer.parseInt(line[3]));
                        this.prepStatement.setInt(5, Integer.parseInt(line[4]));
                        this.prepStatement.setString(6,line[5]);

                    }else{
                        query = "insert into userDataM (ip, browser, gender, age) " +
                                "values (?, ?, ?, ?);";

                        this.prepStatement = this.connection.prepareStatement(query);
                        this.prepStatement.setString(1, line[0]);
                        this.prepStatement.setString(2, line[1]);
                        this.prepStatement.setString(3,line[2]);
                        this.prepStatement.setInt(4,Integer.parseInt(line[3]));

                    }
                    this.prepStatement.executeUpdate();
                }
//                ps.close();
            }

        }catch(Exception e){
            System.out.println(e);

        }
        System.out.println("Ending of initialization");

    }

    public ArrayList<String> getTop10 (int date, boolean save) throws SQLException, IOException {

        ArrayList<String> result = new ArrayList<>();

//        Connection connection = DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/yandexDiskData?serverTimezone=UTC&useSSL=false",this.username,this.password);

        String query = String.format("SELECT DISTINCT ip from logsLM WHERE timeQuery BETWEEN '2014-04-%s 00:00:00' AND '2014-04-%s 00:00:00' " +
                "GROUP BY ip ORDER BY count(ip) DESC LIMIT 10", String.valueOf(date), String.valueOf(date+1));


        this.prepStatement = this.connection.prepareStatement(query);

        ResultSet rs = this.prepStatement.executeQuery(query);

        while (rs.next()){
            var res = rs.getString("ip");
//            System.out.println(res);
            result.add(res);
        }

        if (save) {
            writeToExcel("Top-10.xls", result);
            System.out.println("Excel file successfully created!");
        }

        return result;

    }

    public ArrayList<String> getAreas (boolean save) throws SQLException, IOException {
        ArrayList<String> areas = new ArrayList<>();


        String query = "SELECT area FROM ipDataM GROUP BY area, ip HAVING COUNT(ip) > AVG(ip)";

        this.prepStatement = this.connection.prepareStatement(query);

        ResultSet rs = this.prepStatement.executeQuery(query);

        while (rs.next()){
            var res = rs.getString("area");
//            System.out.println(res);
            areas.add(res);
        }

        if (save) {
            writeToExcel("Areas.xls", areas);
            System.out.println("Excel file successfully created!");
        }

        return areas;

    }

    public ArrayList<String> getVisiting(String date, boolean save) throws SQLException, IOException {

        ArrayList<String> visitings = new ArrayList<>();

        String query = "select area, count(*) as c from logsLM inner join (select max(area) as area, ipDataM.ip" +
                " from ipDataM group by ip) as a on a.ip = logsLM.ip" +
                " where timeQuery > '" + date + "' group by area";

        this.prepStatement = this.connection.prepareStatement(query);

        ResultSet res = this.prepStatement.executeQuery(query);

        while (res.next()){
            var rs = res.getString("area");
            visitings.add(rs);
        }


        if (save) {
            writeToExcel("Areas.xls", visitings);
            System.out.println("Excel file successfully created!");
        }

        return visitings;

    }

    public void insertLogs(String ip, String time, String queryUser, int sizePage, int status, String browser) throws SQLException {

        String query = "INSERT INTO logsLM VALUES (?, ?, ?, ?, ?, ?)";

//        PreparedStatement prepStatement = null;

        Timestamp date = null;
        try {
            date = Timestamp.valueOf(time);
        }catch (IllegalArgumentException e){
            throw e;
        }

        this.prepStatement = this.connection.prepareStatement(query);


        this.prepStatement.setString(1, ip);
        this.prepStatement.setTimestamp(2, date);
        this.prepStatement.setString(3,queryUser);
        this.prepStatement.setInt(4, sizePage);
        this.prepStatement.setInt(5, status);
        this.prepStatement.setString(6,browser);

        this.prepStatement.execute();

        System.out.println("Success!");


    }

    public void changeDomens(String oldDomen, String newDomen, int day) throws SQLException {

        var new_day = day+1;

        String query = "UPDATE logsLM SET query = REPLACE(query, '" + oldDomen + "', '" + newDomen + "')" +
                " WHERE query LIKE '%" + oldDomen + "%' AND " +
                "timeQuery BETWEEN '2014-04-" + day + " 00:00:00' AND '2014-04-" + new_day + " 00:00:00'";


        this.prepStatement = this.connection.prepareStatement(query);

        this.prepStatement.execute();

        System.out.println(String.format("Successful change domen %s to domen %s on date: 2014-04-%s", oldDomen, newDomen, String.valueOf(day)));



    }


    private ArrayList<ArrayList<String[]>> getTextData() throws IOException {
        ArrayList<ArrayList<String[]>> allTextFiles = new ArrayList<>();

        for (var filename : this.tablesName){
            String path;
            if (!filename.equals("userDataM")){
                path = mainPath + filename + ".txt";
            }else{
                path = mainPath + filename;
            }

            allTextFiles.add(this.openTextFile(path));
        }

        return allTextFiles;
    }

    public void deleteOldRows(int date) throws SQLException {

        var queryLastDate = "SELECT max(timeQuery) as timeQuery FROM logsLM";

        this.prepStatement = this.connection.prepareStatement(queryLastDate);

        ResultSet rs = this.prepStatement.executeQuery(queryLastDate);

        String lastDate = null;
        while (rs.next()){
            var res = rs.getString("timeQuery");
            lastDate = res;
        }


        var dateLast = lastDate.split(" ")[0].split("-");
        var lastYear = dateLast[0];
        var lastMonth = dateLast[1];
        var lastDay = dateLast[2];
        var timeLast = "00:00:00";

        var newDate = String.format("%s-%s-%s " + timeLast, lastYear, lastMonth, Integer.parseInt(lastDay)-date);

        String query = "DELETE FROM logsLM WHERE timeQuery>='" + newDate + "'";

        this.prepStatement = this.connection.prepareStatement(query);

        this.prepStatement.execute();
        System.out.println("Success!");


    }


    private ArrayList<String[]> openTextFile(String fileName) throws IOException {
        ArrayList<String[]> data = new ArrayList<>();

        String line;

        FileReader fileReader =
                new FileReader(fileName);

        // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader =
                new BufferedReader(fileReader);

        while((line = bufferedReader.readLine()) != null) {
            var splitedData = line.split("\t");
            if (Arrays.asList(splitedData).get(1).equals("")
                    && Arrays.asList(splitedData).get(2).equals("")){
                var newData = new String[]{splitedData[0], splitedData[3], splitedData[4], splitedData[5],
                        splitedData[6], splitedData[7]};
                data.add(newData);
            }else {
                if (splitedData.length == 4){
//                    System.out.println(splitedData[2]);
                    if (splitedData[2].equals("male")){
                        splitedData[2] = String.valueOf(0);
                    }else {
                        splitedData[2] = String.valueOf(1);
                    }

                }
                data.add(splitedData);
            }
        }

        return data;
    }

    private void writeToExcel(String filename, ArrayList<String> result) throws IOException {

        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("Result");

        Font font = book.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        CellStyle style = book.createCellStyle();
        style.setFont(font);

        Row row = sheet.createRow(0);
        Cell name = row.createCell(0);
        name.setCellValue("Title");
        name.setCellStyle(style);
        sheet.createFreezePane(1,1);


        for (var i = 1; i < result.size(); i++){

            Row new_row = sheet.createRow(i);
            Cell new_name = new_row.createCell(0);
            new_name.setCellValue(result.get(i));

        }
        sheet.autoSizeColumn(1);

        book.write(new FileOutputStream(filename));
        book.close();

    }


    public ArrayList<String> select(String query, String column) throws SQLException {
        ArrayList<String> result = new ArrayList<>();

        this.prepStatement = this.connection.prepareStatement(query);

        ResultSet rs = this.prepStatement.executeQuery(query);

        while (rs.next()){
            var res = rs.getString(column);
            result.add(res);
        }

        return result;
    }


    public void cleanDatabase() throws SQLException {

        for (var i : this.tablesName){
            var query = "DROP TABLE " + i;

            this.prepStatement = this.connection.prepareStatement(query);
            this.prepStatement.execute();

        }

        System.out.println("Database was cleaned!");

    }
}

