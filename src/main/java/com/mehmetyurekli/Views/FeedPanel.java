package com.mehmetyurekli.Views;

import com.formdev.flatlaf.FlatClientProperties;
import com.mehmetyurekli.Login.UserManager;
import com.mehmetyurekli.Models.Post;
import com.mehmetyurekli.Models.User;
import com.mehmetyurekli.Mongo.MongoRepository;
import com.mehmetyurekli.Util.ContentChange;
import com.mehmetyurekli.Util.ContentListener;
import net.miginfocom.swing.MigLayout;
import org.bson.types.ObjectId;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class FeedPanel extends JPanel {

    private final MongoRepository<Post> postRepository;
    private final MongoRepository<User> userRepository;
    private final ArrayList<Post> posts;
    private ContentListener listener;

    public FeedPanel() {
        postRepository = new MongoRepository<>("Social", "Posts", Post.class);
        userRepository = new MongoRepository<>("Social", "Users", User.class);
        posts = new ArrayList<>();
        init();
    }

    private void init() {
        setLayout(new MigLayout("", "[center]", "[center][center]"));
        this.setBackground(new Color(69, 69, 69));
        this.putClientProperty(FlatClientProperties.STYLE, "arc: 15");

        JPanel panel = new JPanel(new MigLayout("wrap", "[left]", "[center]"));

        JButton refreshBtn = new JButton("Refresh feed");
        refreshBtn.setBackground(new Color(31, 31, 31));
        refreshBtn.setFocusable(false);

        for (ObjectId id : UserManager.getCurrentUser().getFriends()) {
            posts.addAll(postRepository.getAll("owner", id));
        }

        posts.sort(new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });

        int limit = 0;
        for (Post p : posts) {
            limit++;
            PostView view = new PostView(p);
            panel.add(view);
            if(limit>30){
                break;
            }
        }
        JLabel title = new JLabel("Your feed");
        title.setFont(new Font("Public Sans", Font.ITALIC, 35));
        title.setForeground(Color.WHITE);
        this.add(title, "align left, gapleft 15");
        this.add(refreshBtn, "align right, gapright 15, wrap");

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(900, 500));
        scrollPane.setForeground(Color.WHITE);

        SwingUtilities.invokeLater(() -> {
            scrollPane.getVerticalScrollBar().setValue(0);
        });

        refreshBtn.addActionListener(e -> {
            listener.onContentChange(ContentChange.FEED_ENTER);
        });

        this.add(scrollPane, "span 2");

    }

    public void setListener(ContentListener listener) {
        this.listener = listener;
    }
}
