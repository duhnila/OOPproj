import com.slack.api.methods.SlackApiException;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void runThruGUI() {
        LoginForm login = new LoginForm(null);
        LoginForm.main(null);
    }
    public static void runThruCmd() throws Exception {
        Scanner myObj = new Scanner(System.in);
        while (true) {
            System.out.print("Choose an action > 1 - login\n" +
                    "                 > 2 - register\n" +
                    "                 > 3 - terminate the program\n> ");
            int action = myObj.nextInt();
            if (action == 2) {
                Database struct = new Database();
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
    public static void main(String[] args) throws Exception {
        Scanner myObj = new Scanner(System.in);
        System.out.print("> 1 - to run through CMD\n> 2 - to run through GUI\n> ");
        int choice = myObj.nextInt();
        switch (choice) {
            case 1: runThruCmd(); break;
            case 2: runThruGUI(); break;
            default: System.out.println("Bad input"); break;
        }
    }
}