import org.mindrot.jbcrypt.BCrypt;
import java.util.Objects;

public class AuthViaLogin extends Authorizer {
    String login;

    public AuthViaLogin(String login) {
        super();
        this.login = login;
    }

    public boolean isUserValid(String pass) {
        for (int i = 0; i < database.countLines(); i++) {
            if (Objects.equals(Database.database[i].getLogin(), login) && BCrypt.checkpw(pass, Database.database[i].getPassword())) {
                return true;
            }
        }
        return false;
    }

}
