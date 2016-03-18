package com.sdx.mobile.tucao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sdx.mobile.tucao.R;
import com.sdx.mobile.tucao.adapter.TopicListAdapter;
import com.sdx.mobile.tucao.app.GlobalContext;
import com.sdx.mobile.tucao.base.BaseToolBarActivity;
import com.sdx.mobile.tucao.model.CommentModel;
import com.sdx.mobile.tucao.model.HttpResult;
import com.sdx.mobile.tucao.model.RequestParams;
import com.sdx.mobile.tucao.model.Section;
import com.sdx.mobile.tucao.model.TopicDetail;
import com.sdx.mobile.tucao.model.TopicModel;
import com.sdx.mobile.tucao.util.DebugLog;
import com.sdx.mobile.tucao.util.JumpUtils;
import com.sdx.mobile.tucao.util.Toaster;
import com.sdx.mobile.tucao.util.UIUtils;
import com.sdx.mobile.tucao.widget.CommentPopupWindow;
import com.sdx.mobile.tucao.widget.CommentPopupWindow.EventCommentData;
import com.sdx.mobile.tucao.widget.EndlessScrollListener;
import com.sdx.mobile.tucao.widget.TopicPopupWindow;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Name: DetailActivity
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/11 12:15
 * Desc:
 */
public class DetailActivity extends BaseToolBarActivity implements
        EndlessScrollListener.OnLoadMoreListener,
        CompoundButton.OnCheckedChangeListener,
        SwipeRefreshLayout.OnRefreshListener {
    private static final String GET_DETAIL_DATA_TASK = "GET_DETAIL_DATA_TASK";
    private static final String GET_COMMENT_DATA_TASK = "GET_COMMENT_DATA";
    private static final String HANDLE_UP_TOPIC_TASK = "HANDLE_UP_TOPIC_TASK";
    private static final String HANDLE_DOWN_TOPIC_TASK = "HANDLE_DOWN_TOPIC_TASK";
    private static final String PUBLISH_COMMENT_TASK = "PUBLISH_COMMENT_TASK";

    @Bind(R.id.id_listview)
    ListView mListView;
    @Bind(R.id.id_swipeLayout)
    SwipeRefreshLayout mRefreshLayout;
    @Bind(R.id.id_detail_title_view)
    TextView mTitleView;
    @Bind(R.id.id_detail_count_view)
    TextView mCountView;
    @Bind(R.id.id_detail_order_new_view)
    RadioButton mOrderNewView;
    @Bind(R.id.id_detail_order_hot_view)
    RadioButton mOrderHotView;

    private int mPageNo = 1;
    private int mTopicId;
    private boolean mIsEnd;
    private String mOrder;
    private String mTopicName;
    private int mSelectPosition;
    private TopicModel mTopicModel;
    private TopicListAdapter mListAdapter;
    private TopicPopupWindow mPopupWindow;
    private CommentPopupWindow mCommentWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        mTopicId = intent.getIntExtra(INTENT_TOPIC_ID, 0);

        mListAdapter = new TopicListAdapter(this);
        mListAdapter.setShowTitle(false);
        mListView.setAdapter(mListAdapter);
        mListView.setOnScrollListener(new EndlessScrollListener(this));

        mPopupWindow = new TopicPopupWindow(this);
        mCommentWindow = new CommentPopupWindow(this);
        mOrderNewView.setOnCheckedChangeListener(this);
        mOrderHotView.setOnCheckedChangeListener(this);
        mRefreshLayout.setOnRefreshListener(this);

        mOrderNewView.setChecked(true);
    }

    private void sendRequest() {
        mPageNo = 1;
        mListAdapter.clearAll();
        obtainDetailData();
    }

    private void obtainDetailData() {
        RequestParams params = new RequestParams();
        params.addParam("sid", mTopicId + "");
        params.addParam("page", mPageNo + "");
        params.addParam("order", mOrder);
        params.addParam("auth", GlobalContext.getInstance().getUserAuth());
        executeTask(mService.obtainTopicDetail(params.query()), GET_DETAIL_DATA_TASK);
    }

    private void obtainCommentList(int tid, int max_id) {
        RequestParams params = new RequestParams();
        params.addParam("tid", tid + "");
        params.addParam("max_id", max_id + "");
        params.addParam("auth", GlobalContext.getInstance().getUserAuth());
        executeTask(mService.obtainCommentList(params.query()), GET_COMMENT_DATA_TASK);
    }

    private void handleUpTopic(int tid) {
        RequestParams params = new RequestParams();
        params.addParam("tid", tid + "");
        params.addParam("auth", GlobalContext.getInstance().getUserAuth());
        executeTask(mService.handleUpTopic(params.query()), HANDLE_UP_TOPIC_TASK);
    }

    private void handleDownTopic(int tid) {
        RequestParams params = new RequestParams();
        params.addParam("tid", tid + "");
        params.addParam("auth", GlobalContext.getInstance().getUserAuth());
        executeTask(mService.handleDownTopic(params.query()), HANDLE_DOWN_TOPIC_TASK);
    }

    private void publishComment(int tid, String content) {
        RequestParams params = new RequestParams();
        params.addParam("tid", tid + "");
        params.addField("text", content);
        params.addParam("auth", GlobalContext.getInstance().getUserAuth());
        executeTask(mService.publishComment(params.fields(), params.query()), PUBLISH_COMMENT_TASK);
    }

    private void updateList(TopicDetail topicDetail) {
        if (topicDetail == null) return;

        mTopicName = topicDetail.getTitle();
        mTitleView.setText(topicDetail.getTitle());
        mCountView.setText(UIUtils.getString(this,
                R.string.string_view_topic_detail_topic_count,
                topicDetail.getTc_count()));

        List<TopicModel> topicModels = topicDetail.getList();
        for (TopicModel topicModel : topicModels) {
            List<CommentModel> dataList = topicModel.getComment_list();
            mListAdapter.addItem(new Section(Section.SECTION_TOPIC, topicModel));

            for (int i = 0; i < dataList.size(); i++) {
                boolean state = (topicModel.getComment_max_id() <= 0) && (i == dataList.size() - 1);
                mListAdapter.addItem(new Section(Section.SECTION_COMMENT, dataList.get(i), state));
            }

            if (topicModel.getComment_max_id() > 0) {
                mListAdapter.addItem(new Section(Section.SECTION_LOAD_MORE, topicModel));
            }
        }

        mListAdapter.notifyDataSetChanged();
    }

    private void updateCommentList(List<CommentModel> dataList, int max_id) {
        for (int i = 0; i < dataList.size(); i++) {
            // 判断增加视图间距
            boolean state = (max_id <= 0) && (i == dataList.size() - 1);
            // 添加评论内容
            mListAdapter.addItem(mSelectPosition + i, new Section(Section.SECTION_COMMENT, dataList.get(i), state));
        }

        if (max_id > 0) {
            mTopicModel.setComment_max_id(max_id);
        } else {
            mListAdapter.removeItem(mSelectPosition + dataList.size());
        }
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        mRefreshLayout.setRefreshing(true);
        sendRequest();
    }

    @Override
    public boolean hasNext(int count) {
        return !mIsEnd;
    }

    @Override
    public void loadMoreData() {
        mPageNo++;
        obtainDetailData();
    }

    public void onEvent(TopicListAdapter.EventData eventData) {
        if (eventData.context != this) return;
        if (eventData.type.equals(TopicListAdapter.EventData.TYPE_LOAD_MORE)) {
            this.mTopicModel = eventData.topicModel;
            this.mSelectPosition = eventData.position;
            DebugLog.d("onEvent", "position = " + mSelectPosition + ", max_id = " + mTopicModel.getComment_max_id());
            // 加载更多评论
            obtainCommentList(mTopicModel.getId(), mTopicModel.getComment_max_id());
        } else if (eventData.type.equals(TopicListAdapter.EventData.TYPE_UP_DOWN)) {
            mPopupWindow.setmTopicModel(eventData.topicModel);
            mPopupWindow.showWindow(eventData.target);
        } else if (eventData.type.equals(TopicListAdapter.EventData.TYPE_ADD_COMMENT)) {
            this.mSelectPosition = eventData.position;
            mCommentWindow.setmTopicId(eventData.topicModel.getId());
            mCommentWindow.showWindow(getWindow().getDecorView());
        }
    }

    public void onEvent(TopicPopupWindow.EventPopupData eventData) {
        if (eventData.context != this) return;
        this.mTopicModel = eventData.topicModel;
        if (eventData.type.equals(TopicPopupWindow.EventPopupData.TYPE_UP)) {
            handleUpTopic(eventData.topicModel.getId());
        } else if (eventData.type.equals(TopicPopupWindow.EventPopupData.TYPE_DOWN)) {
            handleDownTopic(eventData.topicModel.getId());
        }
    }

    public void onEvent(EventCommentData eventData) {
        if (eventData.context != this) return;
        // 发表评论
        publishComment(eventData.topicId, eventData.content);
    }

    @OnClick(R.id.id_detail_post_view)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_detail_post_view:
                if (!TextUtils.isEmpty(mTopicName)) {
                    JumpUtils.startPublishAction(this, mTopicName);
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            mOrder = buttonView.getTag().toString();
            sendRequest();
        }
    }

    @Override
    public void onSuccess(String taskName, HttpResult result) {
        if (result.isOk()) {
            if (taskName.equals(GET_DETAIL_DATA_TASK)) {
                mIsEnd = result.isEnd();
                mRefreshLayout.setRefreshing(false);
                updateList((TopicDetail) result.getData());
            } else if (taskName.equals(GET_COMMENT_DATA_TASK)) {
                updateCommentList((List<CommentModel>) result.getData(), result.getMax_id());
            } else if (taskName.equals(PUBLISH_COMMENT_TASK)) {
                // 插入评论内容
                JSONObject json = JSON.parseObject(result.getData().toString());
                CommentModel commentModel = new CommentModel();
                commentModel.setId(json.getIntValue("id"));
                commentModel.setText(mCommentWindow.getCommentData());
                commentModel.setNick_name(GlobalContext.getInstance().getmUserModel().getNick_name());
                commentModel.setUser_face(GlobalContext.getInstance().getmUserModel().getUser_face());
                mListAdapter.addItem(mSelectPosition + 1, new Section(Section.SECTION_COMMENT, commentModel));
                mListAdapter.notifyDataSetChanged();

                Toaster.show(this, R.string.string_view_topic_detail_comment_success);
            } else if (taskName.equals(HANDLE_UP_TOPIC_TASK) || taskName.equals(HANDLE_DOWN_TOPIC_TASK)) {
                mTopicModel.updateCount(Integer.parseInt(result.getData().toString()));
                mListAdapter.notifyDataSetChanged();
                Toaster.show(this, result.getMsg());
            }
        } else {
            Toaster.show(this, result.getMsg());
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
