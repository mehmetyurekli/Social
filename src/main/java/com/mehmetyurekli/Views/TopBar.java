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
    private Button addBtn;
    private SearchBar searchBar;

    public TopBar(){
        init();
    }

    private void init(){
        socialBtn = new SocialButton();
        settingsBtn = new Button(Icons.SETTINGS);
        profileBtn = new Button(Icons.PROFILE);
        addBtn = new Button(Icons.ADD);
        searchBar = new SearchBar();


        JPanel panel = new JPanel(new MigLayout("fill", "[fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60][fill, 60]", "[align center]"));
        panel.setBackground(new Color(69, 69, 69));
        panel.putClientProperty(FlatClientProperties.STYLE, "arc: 15");
        panel.add(socialBtn, "span 3");
        panel.add(searchBar, "span 11, align center, gapx 15");
        panel.add(settingsBtn, "gapx 15");
        panel.add(profileBtn, "gapx 15");
        panel.add(addBtn, "gapx 15");
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





    }

    public void setListener(ContentListener listener) {
        this.listener = listener;
    }

    public SearchBar getSearchBar() {
        return searchBar;
    }

}
