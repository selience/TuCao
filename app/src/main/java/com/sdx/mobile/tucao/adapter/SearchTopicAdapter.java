package com.sdx.mobile.tucao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.sdx.mobile.tucao.R;
import com.sdx.mobile.tucao.base.BaseListAdapter;
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
    private Context context;

    public SearchTopicAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(View convertView, int itemType) {
        return new ViewHolder(convertView);
    }

    @Override
    public View newView(LayoutInflater inflater, ViewGroup parent, int itemType) {
        return inflater.inflate(R.layout.adapter_homepage_topic_word_item_view, parent, false);
    }

    @Override
    public void onBindViewHolder(View convertView, ViewHolder viewHolder, int position, int itemType) {
        TopicWord topicWord = getItem(position);

        String wordCount = UIUtils.getString(context,
                R.string.string_homepage_search_word_count_text,
                topicWord.getTc_count());
        viewHolder.mCountTextView.setText(UIUtils.formatText(wordCount,
                topicWord.getTc_count() + "", R.color.color_yellow));
        viewHolder.mTitleTextView.setText(topicWord.getTitle());
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
