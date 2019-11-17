package com.apps.talkit.classes;

import java.io.Serializable;

public class UserInfo implements Serializable {
    String nickname;

    public String getNickname() {
        return nickname;
    }

    public UserInfo(String nickname) {
        this.nickname = nickname;
    }
}
