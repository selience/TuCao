package com.sdx.mobile.tucao.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sdx.mobile.tucao.R;
import com.sdx.mobile.tucao.adapter.SearchTopicAdapter;
import com.sdx.mobile.tucao.adapter.TopicListAdapter;
import com.sdx.mobile.tucao.adapter.TopicListAdapter.EventData;
import com.sdx.mobile.tucao.app.GlobalContext;
import com.sdx.mobile.tucao.base.BaseActivity;
import com.sdx.mobile.tucao.model.CommentModel;
import com.sdx.mobile.tucao.model.HttpResult;
import com.sdx.mobile.tucao.model.RequestParams;
import com.sdx.mobile.tucao.model.Section;
import com.sdx.mobile.tucao.model.TopicModel;
import com.sdx.mobile.tucao.model.TopicWord;
import com.sdx.mobile.tucao.model.UserModel;
import com.sdx.mobile.tucao.util.BitmapUtils;
import com.sdx.mobile.tucao.util.ClickExitHelper;
import com.sdx.mobile.tucao.util.DebugLog;
import com.sdx.mobile.tucao.util.EditTextUtils;
import com.sdx.mobile.tucao.util.JumpUtils;
import com.sdx.mobile.tucao.util.SettingUtils;
import com.sdx.mobile.tucao.util.Toaster;
import com.sdx.mobile.tucao.util.UIUtils;
import com.sdx.mobile.tucao.widget.CommentPopupWindow;
import com.sdx.mobile.tucao.widget.CommentPopupWindow.EventCommentData;
import com.sdx.mobile.tucao.widget.EndlessScrollListener;
import com.sdx.mobile.tucao.widget.TopicPopupWindow;
import com.sdx.mobile.tucao.widget.TopicPopupWindow.EventPopupData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import de.greenrobot.event.EventBus;

/**
 * Name: MainAcitivy
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/7 12:56
 * Desc:
 */
public class MainActivity extends BaseActivity implements Runnable,
        EndlessScrollListener.OnLoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener {
    private static final String GET_INDEX_DATA_TASK = "GET_INDEX_DATA";
    private static final String GET_COMMENT_DATA_TASK = "GET_COMMENT_DATA";
    private static final String REGISTER_USER_TASK = "REGISTER_USER_TASK";
    private static final String HANDLE_UP_TOPIC_TASK = "HANDLE_UP_TOPIC_TASK";
    private static final String HANDLE_DOWN_TOPIC_TASK = "HANDLE_DOWN_TOPIC_TASK";
    private static final String PUBLISH_COMMENT_TASK = "PUBLISH_COMMENT_TASK";
    private static final String SEARCH_TOPIC_LIST_TASK = "SEARCH_TOPIC_LIST_TASK";

    @Bind(R.id.id_guide_view)
    ImageView mGuideView;
    @Bind(R.id.id_listview)
    ListView mListView;
    @Bind(R.id.id_swipeLayout)
    SwipeRefreshLayout mRefreshLayout;
    @Bind(R.id.id_topic_search_editview)
    AutoCompleteTextView mSearchTextView;

    private int mPageNo = 1;
    private boolean mIsEnd;
    private boolean hasResult;
    private boolean isFirst;
    private int mSelectPosition;
    private Bitmap mGuideBitmap;
    private TopicModel mTopicModel;
    private TopicListAdapter mListAdapter;
    private SearchTopicAdapter mTopicAdapter;
    private TopicPopupWindow mPopupWindow;
    private CommentPopupWindow mCommentWindow;
    private ClickExitHelper mExitHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        setupView();
        showGuideView();
        userRegister();
        obtainIndexData();
    }

    private void setupView() {
        mExitHelper = new ClickExitHelper(this);
        mPopupWindow = new TopicPopupWindow(this);
        mCommentWindow = new CommentPopupWindow(this);

        mTopicAdapter = new SearchTopicAdapter(this);
        mSearchTextView.setAdapter(mTopicAdapter);

        mListAdapter = new TopicListAdapter(this);
        mListView.setAdapter(mListAdapter);
        mListView.setOnScrollListener(new EndlessScrollListener(this));

        EditTextUtils.onDone(mSearchTextView, this);
        mSearchTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TopicWord topicWord = (TopicWord) parent.getItemAtPosition(position);
                if (topicWord != null) {
                    JumpUtils.startTopicDetailAction(MainActivity.this, topicWord.getId());
                }
            }
        });

        mRefreshLayout.setOnRefreshListener(this);
    }


    private void showGuideView() {
        if (!SettingUtils.isFirstStatus(this)) {
            isFirst = true;
            mGuideBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_guide);
            mGuideView.setImageBitmap(mGuideBitmap);
            mGuideView.setVisibility(View.VISIBLE);
            SettingUtils.storeFirstStatus(this);
        }
    }

    private void hideGuideView() {
        if (mGuideView.getVisibility() == View.VISIBLE) {
            isFirst = false;
            mGuideView.setVisibility(View.GONE);
            mGuideView.setImageDrawable(null);
            BitmapUtils.recycleBitmap(mGuideBitmap);
        }
    }

    private void sendRequest() {
        mPageNo = 1;
        mListAdapter.clearAll();
        mRefreshLayout.setRefreshing(true);
        obtainIndexData();
    }

    private void userRegister() {
        if (!GlobalContext.getInstance().isLogin()) {
            RequestParams params = new RequestParams();
            executeTask(mService.userRegister(params.query()), REGISTER_USER_TASK);
        }
    }

    private void obtainIndexData() {
        RequestParams params = new RequestParams();
        params.addParam("page", mPageNo + "");
        executeTask(mService.obtainIndexData(params.query()), GET_INDEX_DATA_TASK);
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

    private void searchTopicList(String keyword) {
        RequestParams params = new RequestParams();
        params.addField("keyword", keyword);
        params.addParam("auth", GlobalContext.getInstance().getUserAuth());
        executeTask(mService.searchTopicList(params.fields(), params.query()), SEARCH_TOPIC_LIST_TASK);
    }

    private void updateList(List<TopicModel> topicModels) {
        mRefreshLayout.setRefreshing(false);

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

    private void updateTopicWords(List<TopicWord> topicWords) {
        hasResult = !topicWords.isEmpty();
        mTopicAdapter.setItems(topicWords);
        mTopicAdapter.setWords(mSearchTextView.getText().toString());
        mTopicAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        sendRequest();
    }

    @Override
    public boolean hasNext(int count) {
        return !mIsEnd;
    }

    @Override
    public void loadMoreData() {
        mPageNo++;
        obtainIndexData();
    }

    @Override
    public void run() {
        if (hasResult) return;
        // 没有搜索结果进行创建
        String topicName = mSearchTextView.getText().toString();
        JumpUtils.startPublishAction(this, topicName);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!isFirst) {
            return super.dispatchTouchEvent(ev);
        }
        hideGuideView();
        return true;
    }

    public void onEvent(EventData eventData) {
        if (eventData.context != this) return;
        if (eventData.type.equals(EventData.TYPE_LOAD_MORE)) {
            this.mTopicModel = eventData.topicModel;
            this.mSelectPosition = eventData.position;
            DebugLog.d("onEvent", "position = " + mSelectPosition + ", max_id = " + mTopicModel.getComment_max_id());
            // 加载更多评论
            obtainCommentList(mTopicModel.getId(), mTopicModel.getComment_max_id());
        } else if (eventData.type.equals(EventData.TYPE_UP_DOWN)) {
            mPopupWindow.setmTopicModel(eventData.topicModel);
            mPopupWindow.showWindow(eventData.target);
        } else if (eventData.type.equals(EventData.TYPE_ADD_COMMENT)) {
            this.mTopicModel = eventData.topicModel;
            this.mSelectPosition = eventData.position;
            mCommentWindow.setmTopicId(eventData.topicModel.getId());
            mCommentWindow.showWindow(getWindow().getDecorView());
        }
    }

    public void onEvent(EventPopupData eventData) {
        if (eventData.context != this) return;
        this.mTopicModel = eventData.topicModel;
        if (eventData.type.equals(EventPopupData.TYPE_UP)) {
            handleUpTopic(eventData.topicModel.getId());
        } else if (eventData.type.equals(EventPopupData.TYPE_DOWN)) {
            handleDownTopic(eventData.topicModel.getId());
        }
    }

    public void onEvent(EventCommentData eventData) {
        if (eventData.context != this) return;
        // 发表评论
        publishComment(eventData.topicId, eventData.content);
    }


    @OnClick(R.id.id_delete_view)
    public void onClick(View view) {
        mSearchTextView.setText("");
        mSearchTextView.clearFocus();
        UIUtils.hideSoftInputFromWindow(mSearchTextView);
    }


    @OnTextChanged(R.id.id_topic_search_editview)
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!TextUtils.isEmpty(s)) {
            searchTopicList(s.toString());
        }
    }

    @Override
    public void onSuccess(String taskName, HttpResult result) {
        if (result.isOk()) {
            if (taskName.equals(REGISTER_USER_TASK)) {
                GlobalContext.getInstance().setmUserModel((UserModel) result.getData());
            } else if (taskName.equals(GET_INDEX_DATA_TASK)) {
                mIsEnd = result.isEnd();
                updateList((List<TopicModel>) result.getData());
            } else if (taskName.equals(GET_COMMENT_DATA_TASK)) {
                updateCommentList((List<CommentModel>) result.getData(), result.getMax_id());
            } else if (taskName.equals(PUBLISH_COMMENT_TASK)) {
                // 更新数目
                mTopicModel.updateCommentCount(1);
                // 插入评论内容
                JSONObject json = JSON.parseObject(result.getData().toString());
                CommentModel commentModel = new CommentModel();
                commentModel.setId(json.getIntValue("id"));
                commentModel.setText(mCommentWindow.getCommentData());
                commentModel.setNick_name(GlobalContext.getInstance().getmUserModel().getNick_name());
                commentModel.setUser_face(GlobalContext.getInstance().getmUserModel().getUser_face());
                mListAdapter.addItem(mSelectPosition + 1, new Section(Section.SECTION_COMMENT, commentModel));
                mListAdapter.notifyDataSetChanged();

                mCommentWindow.clearText();
                Toaster.show(this, R.string.string_view_topic_detail_comment_success);
            } else if (taskName.equals(SEARCH_TOPIC_LIST_TASK)) {
                updateTopicWords((List<TopicWord>) result.getData());
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

    @Override
    public void onBackPressed() {
        if (mExitHelper.onBackPressed()) {
            super.onBackPressed();
        }
    }
}
