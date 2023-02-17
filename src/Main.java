import java.io.IOException;
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
    }
}