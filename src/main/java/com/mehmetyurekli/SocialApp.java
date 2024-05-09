package com.mehmetyurekli;

import com.mehmetyurekli.Util.ContentChange;
import com.mehmetyurekli.Util.ContentListener;
import com.mehmetyurekli.Util.SearchEngine;
import com.mehmetyurekli.Views.FriendsPanel;
import com.mehmetyurekli.Views.QueryPanel;
import com.mehmetyurekli.Views.SettingsPanel;
import com.mehmetyurekli.Views.TopBar;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class SocialApp extends JPanel implements ContentListener {

    private TopBar bar;
    private JPanel mainPanel;
    private JPanel contentPanel;
    private SettingsPanel settingsPanel;
    private QueryPanel queryPanel;
    private FriendsPanel friendsPanel;
    private String lastIndex;
    public SocialApp(){
        init();
    }

    private void init(){

        lastIndex = "0";

        contentPanel = new JPanel(new CardLayout());
        contentPanel.setPreferredSize(new Dimension(900, 500));

        settingsPanel = new SettingsPanel();
        settingsPanel.setListener(this);

        queryPanel = new QueryPanel();
        friendsPanel = new FriendsPanel();

        contentPanel.add(settingsPanel, "0");
        contentPanel.add(queryPanel, "1");


        bar = new TopBar();
        bar.setListener(this);

        mainPanel = new JPanel(new MigLayout("fill, insets 0 0 0 0", "[900][250]", "[][500]"));
        mainPanel.add(bar, "span 2, wrap");
        mainPanel.add(contentPanel, "cell 0 1, grow");
        mainPanel.add(friendsPanel, "cell 1 1, grow");
        this.add(mainPanel);

    }

    @Override
    public void onContentChange(ContentChange changeType) {
        switch(changeType){
            case ContentChange.SETTINGS_ENTER:
                SwingUtilities.invokeLater(() -> {
                    CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                    cardLayout.show(contentPanel, "0");
                });
                break;


            case ContentChange.SETTINGS_EXIT:
                SwingUtilities.invokeLater(() -> {
                   ((CardLayout) contentPanel.getLayout()).show(contentPanel, lastIndex);
                });
                break;

            case ContentChange.QUERY_ENTER:
                SwingUtilities.invokeLater(() -> {
                    lastIndex = "1";
                    CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                    queryPanel = new QueryPanel(SearchEngine.searchUsers(bar.getSearchBar().getText()));
                    contentPanel.remove(1);
                    contentPanel.add(queryPanel, "1");
                    cardLayout.show(contentPanel, "1");

                });
                break;

        }

    }
}
