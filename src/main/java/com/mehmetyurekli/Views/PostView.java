package com.mehmetyurekli.Views;

import com.formdev.flatlaf.FlatClientProperties;
import com.mehmetyurekli.Models.Post;
import com.mehmetyurekli.Models.User;
import com.mehmetyurekli.Mongo.MongoRepository;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class PostView extends JPanel {

    private final Post post;

    public PostView(Post post) {
        this.post = post;
        init();
    }

    private void init() {
        JPanel panel = new JPanel(new MigLayout("fill, insets 10 10 10 10", "[left, 800]", "[center]"));
        panel.setBackground(new Color(69, 69, 69));
        panel.putClientProperty(FlatClientProperties.STYLE, "arc: 40");

        MongoRepository<User> users = new MongoRepository<>("Social", "Users", User.class);

        JLabel title = new JLabel(post.getTitle());
        title.setFont(new Font("Public Sans", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        JLabel user = new JLabel("by " + users.getSingle("_id", post.getOwner()).getUsername());
        user.setFont(new Font("Public Sans", Font.ITALIC, 13));
        user.setForeground(Color.WHITE);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'at' HH.mm");
        JLabel date = new JLabel(formatter.format(post.getDate()));
        date.setFont(new Font("Public Sans", Font.ITALIC, 11));
        date.setForeground(Color.WHITE);

        JTextArea content = new JTextArea(post.getContent());
        content.setBackground(new Color(69, 69, 69));
        content.setEditable(false);
        content.setForeground(Color.WHITE);
        content.setLineWrap(true);
        Dimension d = content.getPreferredSize();
        d.height = 80;
        d.width = 800;
        content.setPreferredSize(d);

        panel.add(title, "wrap, gaptop 5, gapleft 5");
        panel.add(user, "align left, split 2, gapbottom 5, gapleft 5, wrap");
        panel.add(content, "wrap");
        panel.add(date, "align right, gapbottom 5, gapright 10, wrap");

        this.add(panel);

    }
}
