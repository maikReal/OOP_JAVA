import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException, ParseException {

        DatabaseConnect db = new DatabaseConnect("root", "maikal13998");
        db.init();

    }
}
