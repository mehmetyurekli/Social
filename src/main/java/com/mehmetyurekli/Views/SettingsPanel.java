package com.mehmetyurekli.Views;

import com.formdev.flatlaf.FlatClientProperties;
import com.mehmetyurekli.Login.LoginManager;
import com.mehmetyurekli.Login.LoginPage;
import com.mehmetyurekli.Login.UserManager;
import com.mehmetyurekli.Models.User;
import com.mehmetyurekli.Mongo.MongoRepository;
import com.mehmetyurekli.Util.ContentChange;
import com.mehmetyurekli.Util.ContentListener;
import com.mehmetyurekli.Util.PasswordUtility;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class SettingsPanel extends JPanel {

    /* Settings panel. lets user change password/username or visibility */

    private MongoRepository<User> users;
    private User user;
    private JTextField newUsername;
    private JPasswordField oldPassword;
    private JPasswordField newPassword;
    private JPasswordField confirmNewPassword;
    private JCheckBox visibility;

    private ContentListener listener;

    public SettingsPanel() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("fill"));
        users = new MongoRepository<>("Social", "Users", User.class);
        user = UserManager.getCurrentUser();
        assert this.user != null;

        newUsername = new JTextField();
        oldPassword = new JPasswordField();
        newPassword = new JPasswordField();
        confirmNewPassword = new JPasswordField();
        visibility = new JCheckBox();
        visibility.setSelected(user.isVisible());
        JButton saveBtn = new JButton("Save");
        saveBtn.setMaximumSize(new Dimension(400, 40));
        saveBtn.setBackground(new Color(45, 110, 0));
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setMaximumSize(new Dimension(400, 40));
        cancelBtn.setBackground(new Color(161, 9, 9));

        JPanel panel = new JPanel(new MigLayout("fill, wrap", "[450][450]", "[500]"));
        panel.setBackground(new Color(69, 69, 69));
        panel.putClientProperty(FlatClientProperties.STYLE, "arc: 15");

        JLabel changeUsername = new JLabel("Change username");
        changeUsername.setFont(new Font("Public Sans", Font.PLAIN, 20));
        panel.add(changeUsername, "wrap");

        panel.add(new JLabel("New username"));
        panel.add(newUsername, "wrap, grow");


        JLabel setVisibility = new JLabel("Set visibility");
        setVisibility.setFont(new Font("Public Sans", Font.PLAIN, 20));
        panel.add(setVisibility, "gaptop 15");
        panel.add(visibility, "gaptop 15, wrap");

        JLabel changePassword = new JLabel("Change password");
        changePassword.setFont(new Font("Public Sans", Font.PLAIN, 20));
        panel.add(changePassword, "wrap, gaptop 15");

        panel.add(new JLabel("Old password"));
        panel.add(oldPassword, "wrap, grow");

        panel.add(new JLabel("New password"), "gaptop 10");
        panel.add(new JLabel("Confirm new password"), "wrap, gaptop 10");
        panel.add(newPassword, "grow");
        panel.add(confirmNewPassword, "wrap, grow");

        panel.add(cancelBtn, "align center, grow");
        panel.add(saveBtn, "align center, grow");


        JButton logOut = new JButton("<html><u><a style=\"color:#FFFFFF;\">You can log out here.</a></u></html>");
        logOut.setContentAreaFilled(false);
        logOut.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(logOut, "gapbottom 0, gaptop 0, align center, span 2");

        saveBtn.addActionListener(e -> {
            if (newUsername.getText().isEmpty() && visibility.isSelected() == user.isVisible() && oldPassword.getPassword().length == 0 && newPassword.getPassword().length == 0 && confirmNewPassword.getPassword().length == 0) { // no changes
                listener.onContentChange(ContentChange.SETTINGS_EXIT);
            } else if (oldPassword.getPassword().length == 0) {
                JOptionPane.showMessageDialog(this, "You must enter your password to make changes.");
            } else {
                if (PasswordUtility.verifyPassword(oldPassword.getPassword(), user.getPassword())) {
                    if (!newUsername.getText().isEmpty()) {
                        if (users.getSingle("username", newUsername.getText()) == null) {
                            users.replace("username", user.getUsername(), "username", newUsername.getText());
                            JOptionPane.showMessageDialog(this, "Username changed successfully.");
                            user = UserManager.getCurrentUser();
                            newUsername.setText("");
                            oldPassword.setText("");
                            newPassword.setText("");
                            confirmNewPassword.setText("");
                        } else {
                            JOptionPane.showMessageDialog(this, "Username is already taken.");
                        }
                    }
                    if (newPassword.getPassword().length != 0 || confirmNewPassword.getPassword().length != 0) {
                        if (!Arrays.equals(newPassword.getPassword(), confirmNewPassword.getPassword())) {
                            JOptionPane.showMessageDialog(this, "Passwords doesn't match!");
                        } else {
                            if (newPassword.getPassword().length < 8) {
                                JOptionPane.showMessageDialog(this, "Minimum password length is 8!");
                            } else {
                                users.replace("username", user.getUsername(), "password", PasswordUtility.hashPassword(newPassword.getPassword()));
                                JOptionPane.showMessageDialog(this, "Password changed successfully.");
                                user = UserManager.getCurrentUser();
                                newUsername.setText("");
                                oldPassword.setText("");
                                newPassword.setText("");
                                confirmNewPassword.setText("");
                            }
                        }
                    }
                    if (user.isVisible() != visibility.isSelected()) {
                        users.replace("username", user.getUsername(), "visible", visibility.isSelected());
                        JOptionPane.showMessageDialog(this, "Visibility is set to " + (user.isVisible() ? "'Not visible'" : "'Visible'"));
                        user = UserManager.getCurrentUser();
                        newUsername.setText("");
                        oldPassword.setText("");
                        newPassword.setText("");
                        confirmNewPassword.setText("");
                    }
                    listener.onContentChange(ContentChange.SETTINGS_EXIT);
                } else {
                    JOptionPane.showMessageDialog(this, "Password incorrect!");
                }
            }

            SwingUtilities.invokeLater(() -> {
                revalidate();
                repaint();
            });
        });

        cancelBtn.addActionListener(e -> {
            listener.onContentChange(ContentChange.SETTINGS_EXIT);
        });


        logOut.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Logged out successfully.");
            UserManager.setCurrentUser(null);
            LoginManager.getInstance().showPage(new LoginPage());
        });


        this.add(panel);

    }

    public void setListener(ContentListener listener) {
        this.listener = listener;
    }
}
