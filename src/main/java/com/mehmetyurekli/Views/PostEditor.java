package com.mehmetyurekli.Views;

import com.formdev.flatlaf.FlatClientProperties;
import com.mehmetyurekli.Builders.PostBuilder;
import com.mehmetyurekli.Login.UserManager;
import com.mehmetyurekli.Models.Post;
import com.mehmetyurekli.Mongo.MongoRepository;
import com.mehmetyurekli.Util.ContentListener;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class PostEditor extends JPanel {

    private JTextField titleField;
    private JTextArea contentArea;
    private final MongoRepository<Post> posts;

    public PostEditor() {
        posts = new MongoRepository<>("Social", "Posts", Post.class);
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap, fillx", "[center]", "[center]"));
        this.setBackground(new Color(69, 69, 69));
        this.putClientProperty(FlatClientProperties.STYLE, "arc: 15");

        JPanel panel = new JPanel(new MigLayout("wrap, fillx", "[left]", "[center]"));
        panel.setPreferredSize(new Dimension(900, 500));

        titleField = new JTextField();
        contentArea = new JTextArea();
        Dimension d = contentArea.getPreferredSize();
        d.height = 100;
        contentArea.setPreferredSize(d);
        contentArea.setLineWrap(true);
        JButton shareBtn = new JButton("Share");


        JLabel pageTitle = new JLabel("Create a new post");
        pageTitle.setFont(new Font("Public Sans", Font.BOLD, 24));

        JLabel title = new JLabel("Title");
        title.setFont(new Font("Public Sans", Font.BOLD, 13));

        JLabel content = new JLabel("Content");
        content.setFont(new Font("Public Sans", Font.BOLD, 13));

        panel.add(pageTitle, "gapbottom 15");

        panel.add(title, "wrap");
        panel.add(titleField, "gapbottom 10, wrap, grow");

        panel.add(content, "wrap");
        panel.add(contentArea, "wrap, grow");

        panel.add(shareBtn, "grow");

        this.add(panel);

        shareBtn.addActionListener(e -> {
            Post post = new PostBuilder().withTitle(titleField.getText()).withContent(contentArea.getText()).withOwner(UserManager.getCurrentUser().getId()).build();

            posts.insertOne(post);
            JOptionPane.showMessageDialog(this, "Post shared successfully.");
        });


    }

}
