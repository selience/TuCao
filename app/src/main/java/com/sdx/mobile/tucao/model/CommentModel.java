package com.sdx.mobile.tucao.model;

/**
 * Name: CommentModel
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/7 17:12
 * Desc:
 */
public class CommentModel {

    private int id;
    private String text;
    private String user_face;
    private String nick_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser_face() {
        return user_face;
    }

    public void setUser_face(String user_face) {
        this.user_face = user_face;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }
}
