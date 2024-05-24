package com.mehmetyurekli.Views;

import com.formdev.flatlaf.FlatClientProperties;
import com.mehmetyurekli.Models.User;
import com.mehmetyurekli.Util.ContentChange;
import com.mehmetyurekli.Util.ContentListener;
import com.mehmetyurekli.Util.SearchEngine;
import net.miginfocom.swing.MigLayout;
import org.bson.types.ObjectId;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QueryPanel extends JPanel {

    /* Panel that contains a user's username-Name Surname */

    private User[] users;
    private boolean init;
    private ContentListener listener;
    private ObjectId clickedUser;

    public QueryPanel(String query) {
        if (query != null) {
            this.users = SearchEngine.searchUsers(query);
        }
        init();
    }

    public QueryPanel() {
        init = true;
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap", "[center]", "[center]"));
        this.setBackground(new Color(69, 69, 69));
        this.putClientProperty(FlatClientProperties.STYLE, "arc: 15");

        JPanel panel = new JPanel(new MigLayout("wrap", "[left]", "[center]"));

        if (users == null) {
            if (!init) {
                JOptionPane.showMessageDialog(this, "No matches are found!");
            }
            init = false;
        } else {
            for (User u : users) {
                QueryResultPanel queryResultPanel = new QueryResultPanel(u);
                queryResultPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        clickedUser = u.getId();
                        listener.onContentChange(ContentChange.OTHER_PROFILE_ENTER_QUERY);
                    }
                });
                queryResultPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                panel.add(queryResultPanel);
            }
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBackground(new Color(69, 69, 69));
        scrollPane.setPreferredSize(new Dimension(900, 500));
        scrollPane.setForeground(Color.WHITE);

        this.add(scrollPane);
    }


    public ObjectId getClickedUser() {
        return clickedUser;
    }

    public void setListener(ContentListener listener) {
        this.listener = listener;
    }
}
