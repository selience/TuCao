package com.sdx.mobile.tucao.widget;

import android.widget.AbsListView;

/**
 * Created by yi on 2015/9/17.
 */
public class EndlessScrollListener implements AbsListView.OnScrollListener {

    private OnLoadMoreListener mOnLoadMoreListener;

    public EndlessScrollListener(OnLoadMoreListener listener) {
        this.mOnLoadMoreListener = listener;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (mOnLoadMoreListener != null && scrollState == SCROLL_STATE_IDLE
                && view.getLastVisiblePosition() == (view.getCount() - 1)) {
            // 执行加载更多操作
            if (mOnLoadMoreListener.hasNext(view.getCount())) {
                mOnLoadMoreListener.loadMoreData();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }


    public interface OnLoadMoreListener {

        boolean hasNext(int count);

        void loadMoreData();
    }
}
