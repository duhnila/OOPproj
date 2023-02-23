import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static checkValid.inputValid.isIdValid;
import static checkValid.inputValid.isPassValid;

public class RegisterForm extends JDialog {
    private JPanel registerPanel;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JPasswordField conPasswordField;
    private JButton registerButton;
    private JButton loginButton;
    private JTextField idField;
    private static Database database = new Database();
    static boolean isAdded = false;

    public RegisterForm(JFrame parent) {
        super(parent);
        setTitle("Register user");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(500, 550));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
                LoginForm login = new LoginForm(null);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginForm login = new LoginForm(null);
            }
        });
        setVisible(true);
    }

    private void registerUser() {
        String login = loginField.getText();
        String id = idField.getText();
        String password = String.valueOf(passwordField.getPassword());
        String confirmPassword = String.valueOf(conPasswordField.getPassword());

        if (login.isEmpty() || id.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(RegisterForm.this, "Fill the all fields", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(RegisterForm.this, "Passwords doesn't match", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isPassValid(password)) {
            JOptionPane.showMessageDialog(RegisterForm.this, "Wrong password type", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isIdValid(id)) {
            JOptionPane.showMessageDialog(RegisterForm.this, "Wrong ID type", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (database.isUserExist(login) || database.isUserExist(id)) {
            JOptionPane.showMessageDialog(RegisterForm.this, "Such user already exist", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        database.addToDatabase(login, id, password);
        isAdded = true;
        JOptionPane.showMessageDialog(RegisterForm.this, "Successful registration", "Welcome", JOptionPane.DEFAULT_OPTION);
        dispose();
    }

    public static void main(String[] args) {
        RegisterForm myForm = new RegisterForm(null);
        if (isAdded) {
            System.out.println("Successful registration");
        }
        else {
            System.out.println("Registration canceled");
        }
    }
}
