import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectTest {

    private static String username = "root";
    private static String password = "maikal13998";

    private static DatabaseConnect db;


    static {

        try {
            db = new DatabaseConnect(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @BeforeAll
    static void prepare() throws IOException {
        try {
            db.cleanDatabase();
        }catch (Exception ignored){

        }
        db.init();

    }

    @Test
    void getTop10() throws IOException, SQLException {

        var result = db.getTop10(25, false);
        assertEquals(result.size(), 10);

    }

    @Test
    void getAreas() throws IOException, SQLException {

        var res = db.getAreas(false);
        assertEquals(res.size(), 44);

    }

    @Test
    void insertLogs() throws SQLException {

        var exampleIp = getRandomString();
        var exampleTime = "2014-04-28 12:12:12";
        var exmapleQuery = getRandomString();
        var exampleSize = 12;
        var exampleStatus = 200;
        var exampleBrowser = getRandomString();

        db.insertLogs(exampleIp, exampleTime, exmapleQuery, exampleSize, exampleStatus, exampleBrowser);

        String query = "SELECT * FROM logsLM WHERE  ip='" + exampleIp + "'";
        assertEquals(db.select(query, "ip").get(0), exampleIp);


    }

    @Test
    void changeDomens() throws SQLException {

        var myQueryBegin = "SELECT * FROM logsLM WHERE timeQuery BETWEEN '2014-04-25 00:00:00' AND '2014-04-26 00:00:00'";
        var beginState  = db.select(myQueryBegin, "query");

        db.changeDomens(".ru", ".com", 25);


        var myQueryEnd = "SELECT * FROM logsLM WHERE timeQuery BETWEEN '2014-04-25 00:00:00' AND '2014-04-26 00:00:00'";
        var endState = db.select(myQueryEnd, "query");

        assertEquals(beginState.get(beginState.size()-1).replace(".ru", ".com"), endState.get(endState.size()-1));

        db.changeDomens(".com", ".ru", 25);


    }

    @Test
    public String getRandomString() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);

        return new String(array, Charset.forName("UTF-8"));
    }
}