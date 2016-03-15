package com.sdx.mobile.tucao.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sdx.mobile.tucao.R;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 图库显示适配器
 * <p/>
 * Created by yi on 2015/7/6.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ItemHolder> {
    private Activity mActivity;
    private List<String> dataList;
    private List<String> mCheckList;
    private SparseBooleanArray mStatusList;

    public GalleryAdapter(Activity activity) {
        this.mActivity = activity;
        this.dataList = new ArrayList<String>();
        this.mCheckList = new ArrayList<String>();
        this.mStatusList = new SparseBooleanArray();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public String getItem(int position) {
        if (position >= 0 && position < dataList.size()) {
            return dataList.get(position);
        }
        return null;
    }

    public void setDataList(List<String> dataList) {
        this.dataList.addAll(dataList);
    }


    public ArrayList<String> getCheckList() {
        return (ArrayList<String>) mCheckList;
    }


    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mActivity).inflate(
                R.layout.adapter_gallery_list_item_view, parent, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, final int position) {
        final String uriString = getItem(position);
        if (!TextUtils.isEmpty(uriString)) {
            Glide.with(mActivity).load(new File(uriString))
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imageView);
            holder.convertView.setVisibility(View.VISIBLE);
        } else {
            holder.convertView.setVisibility(View.INVISIBLE);
        }

        // 设置选中状态
        holder.convertView.setSelected(mStatusList.get(position));
        holder.convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!view.isSelected()) {
                    mCheckList.add(uriString);
                    mStatusList.put(position, true);
                    holder.convertView.setSelected(true);
                } else {
                    mCheckList.remove(uriString);
                    mStatusList.put(position, false);
                    holder.convertView.setSelected(false);
                }
            }
        });
    }


    static class ItemHolder extends RecyclerView.ViewHolder {
        private View convertView;
        private CheckBox checkBox;
        private ImageView imageView;

        public ItemHolder(View itemView) {
            super(itemView);
            convertView = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.item_image);
            checkBox = (CheckBox) itemView.findViewById(R.id.item_checkbox);
        }
    }
}
