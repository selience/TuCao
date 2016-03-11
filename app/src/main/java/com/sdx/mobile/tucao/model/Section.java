package com.sdx.mobile.tucao.model;

/**
 * Name: Section
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/8 15:08
 * Desc:
 */
public class Section {
    public static final String SECTION_TOPIC = "topic";
    public static final String SECTION_COMMENT = "comment";
    public static final String SECTION_LOAD_MORE = "loadMore";

    private String name;
    private Object value;

    public Section() {
    }

    public Section(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
