package com.sdx.mobile.tucao.model;

import java.util.List;

/**
 * Name: TopicDetail
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/11 13:52
 * Desc:
 */
public class TopicDetail {
    private String title;
    private int tc_count;
    private String sort_type;
    private List<TopicModel> list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTc_count() {
        return tc_count;
    }

    public void setTc_count(int tc_count) {
        this.tc_count = tc_count;
    }

    public String getSort_type() {
        return sort_type;
    }

    public void setSort_type(String sort_type) {
        this.sort_type = sort_type;
    }

    public List<TopicModel> getList() {
        return list;
    }

    public void setList(List<TopicModel> list) {
        this.list = list;
    }
}
