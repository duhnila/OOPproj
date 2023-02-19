import java.util.Scanner;
import java.io.IOException;

import hasher.Hasher;
import static logger.LogUser.logTXT;

public class Authorizer {
    protected Database database;

    public Authorizer() {
        database = new Database();
    }

    public void loginUser() throws IOException {
        for (int i = 3; i >= 1; i-- ) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter login or id: ");
            String log = sc.nextLine();
            System.out.print("Enter password: ");
            String pass = sc.nextLine();
            if (authorizeUser(log, pass)) {
                System.out.println("\nSuccessfully logged in!\n");
                return;
            } else {
                if (database.isUserExist(log)) {
                    System.out.println("Wrong login, id or password. Tries remaining: " + (i - 1) + "\n");
                }
                else {
                    System.out.println("Such user doesn't exist. Tries remaining: " + (i - 1) + "\n");
                }
            }
        }
    }

    public boolean authorizeUser(String login, String password) throws IOException {
        Hasher hash = new Hasher();
        AuthViaLogin loginAuth = new AuthViaLogin(login);
        AuthViaId idAuth = new AuthViaId(login);
        if (loginAuth.isUserValid(password) || idAuth.isUserValid(password)) {
            User currentUser = database.getUser(login);
            logTXT(currentUser.getLogin(), currentUser.getId(), password, true);
            return true;
        }
        else {
            if (database.isUserExist(login)) {
                User currentUser = database.getUser(login);
                logTXT(currentUser.getLogin(), currentUser.getId(), password, false);
            }
            return false;
        }
    }
}
