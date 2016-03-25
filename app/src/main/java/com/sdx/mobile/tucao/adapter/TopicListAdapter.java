package com.sdx.mobile.tucao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sdx.mobile.tucao.R;
import com.sdx.mobile.tucao.base.BaseListAdapter;
import com.sdx.mobile.tucao.model.CommentModel;
import com.sdx.mobile.tucao.model.Section;
import com.sdx.mobile.tucao.model.TopicModel;
import com.sdx.mobile.tucao.util.DeviceUtils;
import com.sdx.mobile.tucao.util.JumpUtils;
import com.sdx.mobile.tucao.util.UIUtils;
import com.sdx.mobile.tucao.widget.UITopicImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Name: TopicListAdapter
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/8 15:16
 * Desc:
 */
public class TopicListAdapter extends BaseListAdapter<Section, TopicListAdapter.ViewHolder> {
    private static final int ITEM_TOPIC = 0;
    private static final int ITEM_COMMENT = 1;
    private static final int ITEM_LOAD_MORE = 2;
    private static final int ITEM_SIZE = ITEM_LOAD_MORE + 1;

    private Context context;
    private boolean showTitle = true;

    public TopicListAdapter(Context context) {
        super(context);
        this.context = context;
    }

    public void setShowTitle(boolean showTitle) {
        this.showTitle = showTitle;
    }

    @Override
    public int getViewTypeCount() {
        return ITEM_SIZE;
    }

    @Override
    public int getItemViewType(int position) {
        Section section = getItem(position);
        switch (section.getName()) {
            case Section.SECTION_TOPIC:
                return ITEM_TOPIC;
            case Section.SECTION_COMMENT:
                return ITEM_COMMENT;
            case Section.SECTION_LOAD_MORE:
                return ITEM_LOAD_MORE;
            default:
                return ITEM_TOPIC;
        }
    }

    @Override
    public View newView(LayoutInflater inflater, ViewGroup parent, int itemType) {
        if (itemType == ITEM_TOPIC) {
            return inflater.inflate(R.layout.adapter_homepage_list_item_view, parent, false);
        } else if (itemType == ITEM_COMMENT) {
            return inflater.inflate(R.layout.adapter_homepage_comment_list_item_view, parent, false);
        } else if (itemType == ITEM_LOAD_MORE) {
            return inflater.inflate(R.layout.adapter_homepage_comment_load_item_view, parent, false);
        }
        return null;
    }

    @Override
    public ViewHolder onCreateViewHolder(View convertView, int itemType) {
        if (itemType == ITEM_TOPIC) {
            return new TopicHolder(convertView);
        } else if (itemType == ITEM_COMMENT) {
            return new CommentHolder(convertView);
        } else if (itemType == ITEM_LOAD_MORE) {
            return new LoadMoreHolder(convertView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(View convertView, ViewHolder viewHolder, int position, int itemType) {
        if (itemType == ITEM_TOPIC) {
            bindTopicView((TopicHolder) viewHolder, position);
        } else if (itemType == ITEM_COMMENT) {
            bindCommentView((CommentHolder) viewHolder, position);
        } else if (itemType == ITEM_LOAD_MORE) {
            bindLoadMoreView((LoadMoreHolder) viewHolder, position);
        }
    }

    private void bindTopicView(TopicHolder viewHolder, int position) {
        Section section = getItem(position);
        TopicModel topicModel = (TopicModel) section.getValue();

        viewHolder.mTitleTextView.setTag(topicModel.getSid());
        viewHolder.mTitleTextView.setText(topicModel.getTitle());
        viewHolder.mTitleTextView.setVisibility(showTitle ? View.VISIBLE : View.GONE);

        int minHeight = (int) DeviceUtils.dp2px(context, 80);
        viewHolder.mContentTextView.setMinHeight(topicModel.hasImageList() ? 0 : minHeight);
        viewHolder.mContentTextView.setText(topicModel.getText());

        viewHolder.mUpCountTextView.setText(UIUtils.getString(context,
                R.string.string_homepage_list_item_upcount_text, topicModel.getUp_count()));
        viewHolder.mTopicImageView.updateView(topicModel.getImgs());
        viewHolder.mTimeTextView.setText(topicModel.getAdd_time());
        viewHolder.mCommentCountTextView.setText(UIUtils.getString(context,
                R.string.string_homepage_list_item_comment_count_text, topicModel.getComment_count()));

        viewHolder.mUpDownImageView.setTag(new EventData(position, topicModel,
                viewHolder.mUpDownImageView, EventData.TYPE_UP_DOWN, context));
        viewHolder.mCommentTextView.setTag(new EventData(position, topicModel,
                viewHolder.mCommentTextView, EventData.TYPE_ADD_COMMENT, context));
        viewHolder.mCommentCountTextView.setTag(new EventData(position, topicModel,
                viewHolder.mCommentCountTextView, EventData.TYPE_ADD_COMMENT, context));

        Glide.with(context).load(topicModel.getUser_face())
                .placeholder(R.drawable.color_placeholder_drawable)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.mAvatarImageView);
    }

    private void bindCommentView(CommentHolder viewHolder, int position) {
        Section section = getItem(position);
        CommentModel commentModel = (CommentModel) section.getValue();

        viewHolder.mTitleTextView.setText(commentModel.getNick_name());
        viewHolder.mContentTextView.setText(commentModel.getText());

        //  判断是否添加评论分隔符
        viewHolder.mItemDivideView1.setVisibility(section.isFlag() ? View.GONE : View.VISIBLE);
        viewHolder.mItemDivideView2.setVisibility(section.isFlag() ? View.VISIBLE : View.GONE);

        Glide.with(context).load(commentModel.getUser_face())
                .placeholder(R.drawable.color_placeholder_drawable)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.mAvatarImageView);
    }

    private void bindLoadMoreView(LoadMoreHolder viewHolder, int position) {
        Section section = getItem(position);
        TopicModel topicModel = (TopicModel) section.getValue();
        viewHolder.mLoadTextView.setTag(new EventData(position, topicModel,
                viewHolder.mLoadTextView, EventData.TYPE_LOAD_MORE, context));
    }


    static class TopicHolder extends ViewHolder {
        @Bind(R.id.id_topic_title)
        TextView mTitleTextView;
        @Bind(R.id.id_topic_avatar)
        ImageView mAvatarImageView;
        @Bind(R.id.id_topic_text)
        TextView mContentTextView;
        @Bind(R.id.id_topic_upcount)
        TextView mUpCountTextView;
        @Bind(R.id.id_topic_images)
        UITopicImageView mTopicImageView;
        @Bind(R.id.id_topic_time)
        TextView mTimeTextView;
        @Bind(R.id.id_topic_comment_count)
        TextView mCommentCountTextView;
        @Bind(R.id.id_topic_comment_count_view)
        TextView mCommentTextView;
        @Bind(R.id.id_topic_up_view)
        ImageView mUpDownImageView;

        public TopicHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }

        @OnClick({
                R.id.id_topic_title,
                R.id.id_topic_up_view,
                R.id.id_topic_comment_count,
                R.id.id_topic_comment_count_view
        })
        public void onClick(View view) {
            if (view.getId() == R.id.id_topic_title) {
                int topicId = Integer.valueOf(view.getTag().toString());
                JumpUtils.startTopicDetailAction(view.getContext(), topicId);
            } else {
                EventData eventData = (EventData) view.getTag();
                EventBus.getDefault().post(eventData);
            }
        }
    }

    static class CommentHolder extends ViewHolder {
        @Bind(R.id.id_comment_avatar)
        ImageView mAvatarImageView;
        @Bind(R.id.id_comment_title)
        TextView mTitleTextView;
        @Bind(R.id.id_comment_text)
        TextView mContentTextView;
        @Bind(R.id.id_item_divide_view1)
        View mItemDivideView1;
        @Bind(R.id.id_item_divide_view2)
        View mItemDivideView2;

        public CommentHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }

    static class LoadMoreHolder extends ViewHolder {
        @Bind(R.id.id_comment_load_view)
        TextView mLoadTextView;

        public LoadMoreHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.id_comment_load_view)
        public void onClick(View view) {
            EventData eventData = (EventData) view.getTag();
            EventBus.getDefault().post(eventData);
        }
    }

    static class ViewHolder {
    }


    public static class EventData {
        public static final String TYPE_UP_DOWN = "upDown";
        public static final String TYPE_LOAD_MORE = "loadMore";
        public static final String TYPE_ADD_COMMENT = "comment";

        public String type;
        public View target;
        public int position;
        public Context context;
        public TopicModel topicModel;

        public EventData(int position, TopicModel topicModel,
                         View target, String type, Context context) {
            this.position = position;
            this.type = type;
            this.target = target;
            this.context = context;
            this.topicModel = topicModel;
        }
    }
}