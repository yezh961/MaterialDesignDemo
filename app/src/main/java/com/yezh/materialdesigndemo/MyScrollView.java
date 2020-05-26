package com.yezh.materialdesigndemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mListener != null) {
            mListener.onScroll(l, t, oldl, oldt);
        }
    }

    public interface ScrollChangeListenner {
        public void onScroll(int l, int t, int oldl, int oldt);
    }

    private ScrollChangeListenner mListener;

    public void setScrollListener(ScrollChangeListenner listener) {
        this.mListener = listener;
    }
}
