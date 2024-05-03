package com.mehmetyurekli.Login;

import com.formdev.flatlaf.FlatClientProperties;
import com.mehmetyurekli.Models.User;
import com.mehmetyurekli.Services.DatabaseService;
import com.mehmetyurekli.Services.MyDbService;
import com.mehmetyurekli.Builders.UserBuilder;
import com.mehmetyurekli.Util.PasswordUtility;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class RegisterPage extends JPanel {
    private JTextField name;
    private JTextField surname;
    private JComboBox<String> gender;
    private JTextField username;
    private JTextField email;
    private JPasswordField password;
    private JPasswordField confirmPassword;
    private JButton registerBtn;

    private DatabaseService service;


    public RegisterPage() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
        name = new JTextField();
        surname = new JTextField();
        gender = new JComboBox<>(new String[]{"Optimus Prime", "Male", "Female"});
        username = new JTextField();
        email = new JTextField();
        password = new JPasswordField();
        confirmPassword = new JPasswordField();
        registerBtn = new JButton("Sign Up");
        service = new MyDbService().load("Social");


        JPanel panel = new JPanel(new MigLayout("wrap, fillx", "[fill, 250][fill, 250]"));
        panel.setBackground(new Color(69, 69, 69));
        panel.putClientProperty(FlatClientProperties.STYLE, "arc: 15");

        JLabel title = new JLabel("Sign Up");
        title.setFont(new Font("Public Sans", Font.BOLD, 24));
        JLabel description = new JLabel("Please enter your information.");
        description.setFont(new Font("Public Sans", Font.PLAIN, 15));

        panel.add(title, "wrap");
        panel.add(description, "gapbottom 5, wrap");

        panel.add(new JLabel("First Name"));
        panel.add(new JLabel("Last Name"));
        panel.add(name);
        panel.add(surname);


        panel.add(new JLabel("Username"));
        panel.add(new JLabel("Gender"), "wrap");
        panel.add(username);
        panel.add(gender, "wrap");

        panel.add(new JLabel("Email address"), "wrap");
        panel.add(email, "span 2");


        panel.add(new JLabel("Password"));
        panel.add(new JLabel("Confirm password"), "wrap");
        panel.add(password);
        panel.add(confirmPassword);

        panel.add(registerBtn, "span 2");
        registerBtn.setBackground(new Color(45, 110, 0));

        JButton loginBtn = new JButton("<html><u><a style=\"color:#00B5DDFF;\">Try signing in.</a></u></html>");
        loginBtn.setContentAreaFilled(false);
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginBtn.addActionListener(e -> {
            LoginManager.getInstance().showPage(new LoginPage());
        });

        panel.add(loginBtn, "span 2, align center");
        this.add(panel);


        registerBtn.addActionListener(e -> {

            if (isPasswordSame()) {

                if (name.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "You must enter a name!");
                }
                else if (surname.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "You must enter a surname!");
                }
                else if (username.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "You must enter an username!");
                }
                else if (email.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "You must enter an email!");
                }
                else if (password.getPassword() == null || password.getPassword().length < 8) {
                    JOptionPane.showMessageDialog(this, "Minimum password length is 8!");
                }
                else if (service.getUser(username.getText()) != null) {
                    JOptionPane.showMessageDialog(this, "Username is already taken.");
                }
                else {
                    User u = new UserBuilder()
                            .withName(name.getText()).withSurname(surname.getText())
                            .withGender((String) gender.getSelectedItem())
                            .withUsername(username.getText())
                            .withEmail(email.getText())
                            .withPassword(PasswordUtility.hashPassword(password.getPassword()))
                            .build();
                    service.insertUser(u);
                    JOptionPane.showMessageDialog(this, "Successful! You can log in now.");
                }

            }
            else {
                JOptionPane.showMessageDialog(this, "Passwords must match!");
            }
        });
    }

    private boolean isPasswordSame() {
        char[] password = this.password.getPassword();
        char[] confirmPassword = this.confirmPassword.getPassword();
        return (Arrays.equals(password, confirmPassword));
    }

}
