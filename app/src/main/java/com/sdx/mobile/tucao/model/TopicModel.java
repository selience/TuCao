package com.sdx.mobile.tucao.model;

import java.util.List;

/**
 * Name: TopicModel
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/7 17:12
 * Desc:
 */
public class TopicModel {

    private int id;
    private int sid;
    private String title;
    private int uid;
    private String user_face;
    private String nick_name;
    private int up_count;
    private String text;
    private List<String> imgs;
    private String add_time;
    private String last_comment_time;
    private int comment_count;
    private List<CommentModel> comment_list;
    private int comment_max_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public int getUp_count() {
        return up_count;
    }

    public void setUp_count(int up_count) {
        this.up_count = up_count;
    }

    public void updateCount(int increase) {
        this.up_count += increase;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean hasImageList() {
        return (imgs != null && imgs.size() > 0);
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public void updateCommentCount(int increase) {
        this.comment_count += increase;
    }

    public List<CommentModel> getComment_list() {
        return comment_list;
    }

    public void setComment_list(List<CommentModel> comment_list) {
        this.comment_list = comment_list;
    }

    public int getComment_max_id() {
        return comment_max_id;
    }

    public void setComment_max_id(int comment_max_id) {
        this.comment_max_id = comment_max_id;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getLast_comment_time() {
        return last_comment_time;
    }

    public void setLast_comment_time(String last_comment_time) {
        this.last_comment_time = last_comment_time;
    }
}
