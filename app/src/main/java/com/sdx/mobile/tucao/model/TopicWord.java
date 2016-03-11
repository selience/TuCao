package com.sdx.mobile.tucao.model;

/**
 * Name: TopicWord
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/11 15:56
 * Desc:
 */
public class TopicWord {
    private int id;
    private int tc_count;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTc_count() {
        return tc_count;
    }

    public void setTc_count(int tc_count) {
        this.tc_count = tc_count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
