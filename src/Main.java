import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner myObj = new Scanner(System.in);
        Database struct = new Database();

        while (true) {
            System.out.print("Choose an action > 1 - login\n" +
                    "                 > 2 - register\n" +
                    "                 > 3 - terminate the program\n> ");
            int action = myObj.nextInt();
            if (action == 2) {
                struct.registerUser();
            }
            else if (action == 3) {
                break;
            }
            else if (action == 1) {
                Authorizer auth = new Authorizer();
                auth.loginUser();
            }
        }









        /*
        String ConnectionURL = "jdbc:postgresql://localhost:5432/database";
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(ConnectionURL, "postgres", "fu99kkkopl");
            st = con.createStatement();
            rs = st.executeQuery("select * from users");
            while (rs.next()) {
                System.out.println(rs.getString("id"));
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Exception occurred!");
            }
        }

         */
    }
}