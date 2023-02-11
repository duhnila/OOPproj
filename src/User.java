public class User {
    private String login;
    private String password;
    private String Id;

    User(String log, String id, String pass) {
        login = log;
        Id = id;
        password = pass;
        boolean access = false;
    }

    public void setData(String login, String id, String password) {
        this.login = login;
        this.password = password;
        this.Id = id;
//        this.access = access;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

/*   public void setAccess(boolean accessStatus) {
        this.access = accessStatus;
    }
    public boolean getAccess() {
        return access;
    }
 */
    public String getId() {
        return Id;
    }
    public void setId(String id) {
        this.Id = id;
    }
}
