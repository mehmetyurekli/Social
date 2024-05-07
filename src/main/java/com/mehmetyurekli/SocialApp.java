package com.mehmetyurekli;

import com.mehmetyurekli.Util.ContentChange;
import com.mehmetyurekli.Views.TopBar;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class SocialApp extends JPanel implements ContentListener {

    private TopBar bar;
    private JPanel mainPanel;
    private SettingsPanel settingsPanel;
    private FriendsPanel friendsPanel;
    private JPanel profilePanel;

    public SocialApp(){
        init();
    }

    private void init(){
        settingsPanel = new SettingsPanel();
        settingsPanel.setListener(this);
        settingsPanel.setVisible(false);

        friendsPanel = new FriendsPanel();

        profilePanel = new JPanel();

        bar = new TopBar();
        bar.setListener(this);

        mainPanel = new JPanel(new MigLayout("insets 0 0 0 0", "[][]", "[][500]"));
        mainPanel.add(bar, "span 2, grow, wrap");

        mainPanel.add(settingsPanel);
        mainPanel.add(friendsPanel);

        profilePanel.add(new JLabel("test"));

        this.add(mainPanel);

    }

    @Override
    public void onContentChange(ContentChange changeType) {
        switch(changeType){
            case ContentChange.SETTINGS_ENTER:
                SwingUtilities.invokeLater(() -> {
                    if(settingsPanel.isVisible()){
                        settingsPanel.setVisible(false);
                        profilePanel.setVisible(true);
                    }
                    else{
                        settingsPanel.setVisible(true);
                        profilePanel.setVisible(false);
                    }
                });
                break;


            case ContentChange.SETTINGS_EXIT:
                SwingUtilities.invokeLater(() -> {
                    settingsPanel.setVisible(false);
                    profilePanel.setVisible(true);
                });
                break;

        }

    }
}
