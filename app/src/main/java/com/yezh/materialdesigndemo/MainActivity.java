package com.yezh.materialdesigndemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private View mTitleBar;
    private MyScrollView mScrollView;
    private ImageView mImageView;

    private int mImageHeight;

    /**
     * qq空间好友动态栏 头部效果
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG","MainActivity");
        setContentView(R.layout.activity_main);
        mTitleBar = findViewById(R.id.title_bar);
        mScrollView = findViewById(R.id.scrollView);
        mImageView = findViewById(R.id.imageView);
//        statusBarUtil.setActivityTranslucent(this);

        mImageView.post(new Runnable() {
            @Override
            public void run() {
                mImageHeight = mImageView.getMeasuredHeight();
            }
        });

        //1、刚进来背景完全透明
        mTitleBar.getBackground().setAlpha(0);
        //不断监听滚动
        //判断当前滚动的位置跟头部的位置去比较 计算透明度
        mScrollView.setScrollListener(new MyScrollView.ScrollChangeListenner() {
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
                //获取图片的高度  根据当前滚动的位置计算alpha值
                if (mImageHeight == 0) return;
                float alpha = (float) t / mImageHeight;
                if (alpha <= 0) {
                    alpha = 0;
                }
                if (alpha > 1) {
                    alpha = 1;
                }
                mTitleBar.getBackground().setAlpha((int) (alpha * 255));
            }
        });
    }
}
