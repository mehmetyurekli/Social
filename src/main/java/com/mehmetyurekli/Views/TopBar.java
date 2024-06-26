package com.mehmetyurekli.Views;

import com.formdev.flatlaf.FlatClientProperties;
import com.mehmetyurekli.Util.ContentChange;
import com.mehmetyurekli.Util.ContentListener;
import com.mehmetyurekli.Util.Icons;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class TopBar extends JPanel {

    //This panel lets user navigate through the app. Notifies the SocialApp when a page changes.

    private ContentListener listener;
    private SearchBar searchBar;

    public TopBar() {
        init();
    }

    private void init() {
        SocialButton socialBtn = new SocialButton();
        Button settingsBtn = new Button(Icons.SETTINGS);
        Button profileBtn = new Button(Icons.PROFILE);
        Button notificationsBtn = new Button(Icons.NOTIFICATION);
        Button createBtn = new Button(Icons.ADD);
        searchBar = new SearchBar();


        JPanel panel = new JPanel(new MigLayout("fill", "[fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60]", "[align center]"));
        panel.setBackground(new Color(69, 69, 69));
        panel.putClientProperty(FlatClientProperties.STYLE, "arc: 15");
        panel.add(socialBtn, "span 3");
        panel.add(searchBar, "span 10, align center, gapx 15");
        panel.add(createBtn, "gapx 15");
        panel.add(settingsBtn, "gapx 15");
        panel.add(profileBtn, "gapx 15");
        panel.add(notificationsBtn, "gapx 15");
        this.add(panel);

        socialBtn.addActionListener(e -> {
            listener.onContentChange(ContentChange.FEED_ENTER);
        });


        settingsBtn.addActionListener(e -> {
            listener.onContentChange(ContentChange.SETTINGS_ENTER);
        });

        profileBtn.addActionListener(e -> {
            listener.onContentChange(ContentChange.PROFILE_ENTER);
        });

        notificationsBtn.addActionListener(e -> {
            listener.onContentChange(ContentChange.NOTIFICATIONS_ENTER);
        });

        createBtn.addActionListener(e -> {
            listener.onContentChange(ContentChange.POST_CREATOR_ENTER);
        });

    }

    public void setListener(ContentListener listener) {
        this.listener = listener;
        searchBar.setListener(this.listener);
    }

    public SearchBar getSearchBar() {
        return searchBar;
    }

}
