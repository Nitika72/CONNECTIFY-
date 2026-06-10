import java.sql.Connection;
import java.sql.DriverManager;

public class TestDB {

    public static void main(String[] args) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn =
                DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/connectify_db",
                    "root",
                    "nitika2004"
                );

            System.out.println(
                "✅ Connected Successfully!"
            );

            conn.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}