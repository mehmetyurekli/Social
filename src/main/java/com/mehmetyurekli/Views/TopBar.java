package com.mehmetyurekli.Views;

import com.formdev.flatlaf.FlatClientProperties;
import com.mehmetyurekli.Util.ContentListener;
import com.mehmetyurekli.Util.ContentChange;
import com.mehmetyurekli.Util.Icons;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TopBar extends JPanel {

    private ContentListener listener;
    private SocialButton socialBtn;
    private Button settingsBtn;
    private Button profileBtn;
    private Button notificationsBtn;
    private SearchBar searchBar;

    public TopBar(){
        init();
    }

    private void init(){
        socialBtn = new SocialButton();
        settingsBtn = new Button(Icons.SETTINGS);
        profileBtn = new Button(Icons.PROFILE);
        notificationsBtn = new Button(Icons.NOTIFICATION);
        searchBar = new SearchBar();


        JPanel panel = new JPanel(new MigLayout("fill", "[fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60]", "[align center]"));
        panel.setBackground(new Color(69, 69, 69));
        panel.putClientProperty(FlatClientProperties.STYLE, "arc: 15");
        panel.add(socialBtn, "span 3");
        panel.add(searchBar, "span 11, align center, gapx 15");
        panel.add(settingsBtn, "gapx 15");
        panel.add(profileBtn, "gapx 15");
        panel.add(notificationsBtn, "gapx 15");
        this.add(panel);

        socialBtn.addActionListener(e -> {
            //TODO
        });

        searchBar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    SwingUtilities.invokeLater(() -> {
                        listener.onContentChange(ContentChange.QUERY_ENTER);
                    });
                }
            }
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





    }

    public void setListener(ContentListener listener) {
        this.listener = listener;
    }

    public SearchBar getSearchBar() {
        return searchBar;
    }

}
