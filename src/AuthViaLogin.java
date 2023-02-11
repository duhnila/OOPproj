import java.util.Objects;

public class AuthViaLogin extends Authorizer {
    String login;

    public AuthViaLogin(String login) {
        super();
        this.login = login;
    }

    public boolean isUserValid(String pass) {
        for (int i = 0; i < database.countLines(); i++) {
            if (Objects.equals(Database.database[i].getLogin(), login) && Objects.equals(Database.database[i].getPassword(), pass)) {
                return true;
            }
        }
        return false;
    }

}
