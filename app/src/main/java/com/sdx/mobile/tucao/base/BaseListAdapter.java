package com.sdx.mobile.tucao.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Name: BaseListAdapter
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/7 17:53
 * Desc:
 */
public abstract class BaseListAdapter<Model, T> extends BaseAdapter {
    private List<Model> dataList = new ArrayList();
    private LayoutInflater inflater;

    public BaseListAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return this.dataList.size();
    }

    public boolean isEmpty() {
        return this.dataList.isEmpty();
    }

    public Model getItem(int position) {
        return this.dataList.size() > position ? this.dataList.get(position) : null;
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public final void clearAll() {
        this.dataList.clear();
    }

    public final int getListSize() {
        return this.dataList.size();
    }

    public final List<Model> getItems() {
        return this.dataList;
    }

    public final void addItem(Model item) {
        if (item != null) {
            this.dataList.add(item);
        }
    }

    public final void addItem(int location, Model item) {
        if (item != null) {
            this.dataList.add(location, item);
        }
    }

    public final void addItems(List<Model> items) {
        if (items != null) {
            this.dataList.addAll(items);
        }
    }

    public final void removeItem(int location) {
        if (location >= 0 && location < dataList.size()) {
            this.dataList.remove(location);
        }
    }

    public final void removeItem(Model item) {
        if (item != null) {
            this.dataList.remove(item);
        }
    }

    public final void setItems(List<Model> items) {
        if (items != null) {
            this.dataList.clear();
            this.dataList.addAll(items);
        }

    }

    public final View getView(int position, View convertView, ViewGroup parent) {
        T viewHolder = null;
        int itemType = this.getItemViewType(position);
        if (convertView == null) {
            convertView = this.newView(this.inflater, parent, itemType);
            viewHolder = this.onCreateViewHolder(convertView, itemType);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (T) convertView.getTag();
        }

        this.onBindViewHolder(convertView, viewHolder, position, itemType);
        return convertView;
    }

    public abstract T onCreateViewHolder(View convertView, int itemType);

    public abstract View newView(LayoutInflater inflater, ViewGroup parent, int itemType);

    public abstract void onBindViewHolder(View convertView, T viewHolder, int position, int itemType);
}
