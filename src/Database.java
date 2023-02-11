import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

import static checkValid.inputValid.isIdValid;
import static checkValid.inputValid.isPassValid;

public class Database {
    static public User[] database;

    public Database() {
        Database.database = new User[1];
        Database.database[0] = new User("0", "0", "0");
        updateData();
    }

    public boolean isUserExist(String tempLogin) {
        for (int i = 0; i < database.length; i++) {
            if (Objects.equals(tempLogin, database[i].getLogin()) || Objects.equals(database[i].getId(), tempLogin)) {
                return true;
            }
        }
        return false;
    }

    public void registerUser() {
        updateData();
        Scanner myObj = new Scanner(System.in);
        System.out.print("Enter login: ");
        String log = myObj.nextLine();
        String id;
        while (true) {
            System.out.print("Enter id (must contain 12 digits): ");
            id = myObj.nextLine();

            if (!isIdValid(id)) {
                System.out.println("Wrong ID format. Try again...");
            } else break;
        }

        if (isUserExist(log)) {
            System.out.println(String.format("\nUser %s with this login or id already exists\n", log));
            return;
        }

        while (true) {
            System.out.print("Enter password: ");
            String pass = myObj.nextLine();
            if (isPassValid(pass)) {
                String ConnectionURL = "jdbc:postgresql://localhost:5432/database";
                Connection con = null;
                ResultSet rs = null;
                Statement st = null;
                try {
                    Class.forName("org.postgresql.Driver");
                    con = DriverManager.getConnection(ConnectionURL, "postgres", "fu99kkkopl");
                    PreparedStatement ps = con.prepareStatement("insert into users (id, login, password) values (?,?,?)");
                    ps.setString(1, id);
                    ps.setString(2, log);
                    ps.setString(3, pass);
                    System.out.println(String.format("\nUser %s[%s] successfully registered.\n", log, id));
                    int row = ps.executeUpdate();
                    updateData();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    try {
                        con.close();
                        break;
                    } catch (Exception e) {
                        System.out.println("Exception occurred!");
                    }
                }
            }
        }
    }

    public User getUser(String login) {
        for (int i = 0; i < database.length; i++) {
            if (Objects.equals(database[i].getLogin(), login) || Objects.equals(database[i].getId(), login)) {
                return database[i];
            }
        }
        return database[0];
    }

    private void updateData() {
        int linesAmount = countLines();
        database = new User[linesAmount];
        int i = 0;
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
                database[i] = new User(rs.getString("login"), rs.getString("id"), rs.getString("password"));
                i++;
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
    }

    public int countLines() {
        int count = 0;
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
                count += 1;
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
        return count;
    }
}
