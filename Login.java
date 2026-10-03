import javax.swing.*;
import java.awt.*;

public class Login {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Login - Fake News Detection");
        frame.setSize(450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel(
                "FAKE NEWS DETECTION",
                SwingConstants.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();

        JButton loginButton = new JButton("LOGIN");
        JButton clearButton = new JButton("CLEAR");

        panel.add(userLabel);
        panel.add(userField);

        panel.add(passLabel);
        panel.add(passField);

        panel.add(loginButton);
        panel.add(clearButton);

        frame.add(panel, BorderLayout.CENTER);

        loginButton.addActionListener(e -> {

            String username = userField.getText();
            String password = new String(
                    passField.getPassword()
            );

            if (username.equals("admin")
                    && password.equals("1234")) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Login Successful!"
                );

                frame.dispose();

                FakeNewsGUI.main(new String[]{});

            } else {

                JOptionPane.showMessageDialog(
                        frame,
                        "Invalid Username or Password!"
                );
            }
        });

        clearButton.addActionListener(e -> {

            userField.setText("");
            passField.setText("");

        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}