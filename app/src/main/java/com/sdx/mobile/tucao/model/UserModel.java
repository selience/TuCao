package com.sdx.mobile.tucao.model;

/**
 * Name: UserBean
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/8 18:20
 * Desc:
 */
public class UserModel {
    private String auth;
    private String nick_name;
    private String phone;
    private String user_face;

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser_face() {
        return user_face;
    }

    public void setUser_face(String user_face) {
        this.user_face = user_face;
    }
}
