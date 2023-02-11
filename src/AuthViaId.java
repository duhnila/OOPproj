import java.util.Objects;

public class AuthViaId extends Authorizer{
    String id;

    public AuthViaId(String id) {
        super();
        this.id = id;
    }

    public boolean isUserValid(String pass) {
        for (int i = 0; i < database.countLines(); i++) {
            if (Objects.equals(Database.database[i].getId(), id) && Objects.equals(Database.database[i].getPassword(), pass)) {
                return true;
            }
        }
        return false;
    }
}
