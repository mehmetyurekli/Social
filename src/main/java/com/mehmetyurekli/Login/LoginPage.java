package com.mehmetyurekli.Login;

import com.formdev.flatlaf.FlatClientProperties;
import com.mehmetyurekli.Models.User;
import com.mehmetyurekli.Services.DatabaseService;
import com.mehmetyurekli.Services.MyDbService;
import com.mehmetyurekli.Util.PasswordUtility;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class LoginPage extends JPanel {

    private JTextField username;
    private JPasswordField password;
    private JButton loginBtn;
    //private JCheckBox remember;
    private DatabaseService service;

    public LoginPage() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));
        username = new JTextField();
        password = new JPasswordField();
        loginBtn = new JButton("Login");
        //remember = new JCheckBox("Remember me");
        service = new MyDbService().load("Social");

        JPanel panel = new JPanel(new MigLayout("wrap, fillx", "[fill,300]"));
        panel.setBackground(new Color(69, 69, 69));
        panel.putClientProperty(FlatClientProperties.STYLE, "arc: 15");

        JLabel title = new JLabel("Welcome to Social!");
        title.setFont(new Font("Public Sans", Font.BOLD, 24));
        JLabel description = new JLabel("Enter your username and password.");

        panel.add(title);
        panel.add(description, "gapbottom 5, wrap");

        panel.add(new JLabel("Username"), "gapy 5");
        panel.add(username);
        panel.add(new JLabel("Password"), "gapy 5");
        panel.add(password);
        //panel.add(remember);
        password.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    SwingUtilities.invokeLater(() -> {
                        loginBtn.doClick();
                    });
                }
            }
        });

        loginBtn.setBackground(new Color(45, 110, 0));
        panel.add(loginBtn, "gaptop 15");
        loginBtn.addActionListener(e -> {
            User user = service.getUser(username.getText());
            if (username.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "You must enter your username!");
            }
            else if (password.getPassword().length == 0){
                JOptionPane.showMessageDialog(this, "You must enter your password!");
            }
            else if (user == null) {
                JOptionPane.showMessageDialog(this, "User doesn't exist.");
            }
            else {
                if (PasswordUtility.verifyPassword(password.getPassword(), user)) {
                    JOptionPane.showMessageDialog(this, "Login successful.");
                } else {
                    JOptionPane.showMessageDialog(this, "Password is wrong.");
                }
            }
        });


        JButton registerBtn = new JButton("<html><u><a style=\"color:#00B5DDFF;\">Don't have an account? Sign up here.</a></u></html>");
        registerBtn.setContentAreaFilled(false);
        registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        registerBtn.addActionListener(e -> {
            LoginManager.getInstance().showPage(new RegisterPage());
        });

        panel.add(registerBtn, "gapbottom 0, gaptop 0, align center");
        this.add(panel);

        registerBtn.addActionListener(e -> {
            LoginManager.getInstance().showPage(new RegisterPage());
        });

    }
}
