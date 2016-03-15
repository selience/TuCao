package com.sdx.mobile.tucao.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.sdx.mobile.tucao.R;
import com.sdx.mobile.tucao.activity.PublishActivity;
import com.sdx.mobile.tucao.base.BaseListAdapter;
import com.sdx.mobile.tucao.constant.IntentConstants;
import com.sdx.mobile.tucao.model.TopicWord;
import com.sdx.mobile.tucao.util.UIUtils;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Name: SearchTopicAdapter
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/11 15:59
 * Desc:
 */
public class SearchTopicAdapter extends BaseListAdapter<TopicWord, SearchTopicAdapter.ViewHolder> implements Filterable {
    private static final int ITEM_NORMAL = 0;
    private static final int ITEM_CREATE = 1;

    private static final int ITEM_SIZE = ITEM_CREATE + 1;

    private String words;
    private Context context;

    public SearchTopicAdapter(Context context) {
        super(context);
        this.context = context;
    }

    public void setWords(String words) {
        this.words = words;
    }

    @Override
    public int getCount() {
        return super.getCount() + 1;
    }

    @Override
    public TopicWord getItem(int position) {
        if (getItemViewType(position) == ITEM_NORMAL) {
            return super.getItem(position - 1);
        }
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return ITEM_SIZE;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_CREATE;
        }
        return ITEM_NORMAL;
    }

    @Override
    public ViewHolder onCreateViewHolder(View convertView, int itemType) {
        if (itemType == ITEM_NORMAL) {
            return new ViewHolder(convertView);
        }
        return null;
    }

    @Override
    public View newView(LayoutInflater inflater, ViewGroup parent, int itemType) {
        if (itemType == ITEM_NORMAL) {
            return inflater.inflate(R.layout.adapter_homepage_topic_word_item_view, parent, false);
        } else {
            View convertView = inflater.inflate(R.layout.adapter_homepage_topic_create_item_view, parent, false);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PublishActivity.class);
                    intent.putExtra(IntentConstants.INTENT_TOPIC_SUBJECT, words);
                    context.startActivity(intent);
                }
            });
            return convertView;
        }
    }

    @Override
    public void onBindViewHolder(View convertView, ViewHolder viewHolder, int position, int itemType) {
        if (itemType == ITEM_NORMAL) {
            TopicWord topicWord = getItem(position);

            String wordCount = UIUtils.getString(context,
                    R.string.string_homepage_search_word_count_text,
                    topicWord.getTc_count());
            viewHolder.mCountTextView.setText(UIUtils.formatText(wordCount,
                    topicWord.getTc_count() + "", R.color.color_yellow));
            viewHolder.mTitleTextView.setText(topicWord.getTitle());
        }
    }

    @Override
    public Filter getFilter() {
        return new TopicFilter();
    }

    static class ViewHolder {
        @Bind(R.id.id_search_topic_title)
        TextView mTitleTextView;
        @Bind(R.id.id_search_topic_count)
        TextView mCountTextView;

        public ViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }

    class TopicFilter extends Filter {

        @Override
        protected FilterResults performFiltering(final CharSequence prefix) {
            FilterResults results = new FilterResults();
            results.values = getItems();
            results.count = getItems().size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count > 0) {
                setItems((List<TopicWord>) results.values);
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}
