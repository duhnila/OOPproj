import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginForm extends JDialog {
    private JPanel loginPanel;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private int triesCounter = 3;
    private static Database database = new Database();
    private static boolean isLogged = false;

    public LoginForm(JFrame parent) {
        super(parent);
        setTitle("Sign in");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (triesCounter > 0) {
                    try {
                        if (!loginUser(triesCounter)) {
                            triesCounter -= 1;
                        }
                        else {
                            dispose();
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(LoginForm.this, "No attempts remaining", "ERROR", JOptionPane.WARNING_MESSAGE);
                    dispose();
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                RegisterForm register = new RegisterForm(null);

            }
        });
        setVisible(true);
    }

    private boolean loginUser(int attempts) throws IOException {
        String login = loginField.getText();
        String password = String.valueOf(passwordField.getPassword());
        Authorizer myAuth = new Authorizer();
        if (myAuth.authorizeUser(login, password)) {
            JOptionPane.showMessageDialog(LoginForm.this, String.format("Hello, %s", login), "SUCCESSFUL", JOptionPane.DEFAULT_OPTION);
            isLogged = true;
            return true;
        }
        else {
            JOptionPane.showMessageDialog(LoginForm.this, String.format("WRONG LOGIN, ID OR PASSWORD. TRIES REAMANING:%s", attempts - 1), "FAILED", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    public static void main(String[] args) {
        LoginForm myForm = new LoginForm(null);
        if (isLogged) {
            System.out.println("User logged in");
        }
    }
}
