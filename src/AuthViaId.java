import org.mindrot.jbcrypt.BCrypt;
import java.util.Objects;

public class AuthViaId extends Authorizer{
    private String id;
    private Database database;

    public AuthViaId(String id) {
        super();
        this.id = id;
    }

    public boolean isUserValid(String pass) {
        for (int i = 0; i < database.countLines(); i++) {
            if (Objects.equals(Database.database[i].getId(), id) && BCrypt.checkpw(pass, Database.database[i].getPassword())) {
                return true;
            }
        }
        return false;
    }
}
