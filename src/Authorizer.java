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
        Hasher hash = new Hasher();
        for (int i = 3; i >= 1; i-- ) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter login or id: ");
            String log = sc.nextLine();
            System.out.print("Enter password: ");
            String pass = sc.nextLine();
            AuthViaLogin loginAuth = new AuthViaLogin(log);
            AuthViaId idAuth = new AuthViaId(log);
            if (idAuth.isUserValid(pass) || loginAuth.isUserValid(pass)) {
                User currentUser = database.getUser(log);
                logTXT(currentUser.getLogin(), currentUser.getId(), pass, true);
                System.out.println("\nSuccessfully logged in!\n");
                break;
            } else {
                if (database.isUserExist(log)) {
                    User currentUser = database.getUser(log);
                    logTXT(currentUser.getLogin(), currentUser.getId(), pass, false);
                    System.out.println("Wrong login, id or password. Tries remaining: " + (i - 1) + "\n");
                }else {
                    System.out.println("Such user doesn't exist. Tries remaining: " + (i - 1) + "\n");
                }
            }
        }
    }
}
