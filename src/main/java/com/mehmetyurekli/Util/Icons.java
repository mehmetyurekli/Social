package com.mehmetyurekli.Util;

import java.net.URL;

public enum Icons {

    HOME(Icons.class.getResource("/icons/home.png")),
    ADD(Icons.class.getResource("/icons/plus.png")),
    SETTINGS(Icons.class.getResource("/icons/settings.png")),
    PROFILE(Icons.class.getResource("/icons/user.png")),
    NOTIFICATION(Icons.class.getResource("/icons/notification.png")),
    SEARCH(Icons.class.getResource("/icons/search.png"));

    private URL path;

    Icons(URL path) {
        this.path = path;
    }

    public URL getPath() {
        return path;
    }
}

