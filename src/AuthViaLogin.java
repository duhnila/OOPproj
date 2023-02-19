import org.mindrot.jbcrypt.BCrypt;
import java.util.Objects;

public class AuthViaLogin extends Authorizer {
    private String login;

    public AuthViaLogin(String log) {
        super();
        this.login = log;
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
