package com.mehmetyurekli.Util;

public enum Icons {

    HOME("src/main/java/com/mehmetyurekli/icons/home.png"),
    FRIENDS("src/main/java/com/mehmetyurekli/icons/friends.png"),
    ADD("src/main/java/com/mehmetyurekli/icons/plus.png"),
    SETTINGS("src/main/java/com/mehmetyurekli/icons/settings.png"),
    PROFILE("src/main/java/com/mehmetyurekli/icons/user.png");

    private String path;

    Icons(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

