package com.yezh.materialdesigndemo;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class BehaviorActivity extends AppCompatActivity {
    private Toolbar mToolBar;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout refreshView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "BehaviorActivity");
        setContentView(R.layout.activity_behavior);

        findView();
    }

    private void findView() {
        mToolBar = findViewById(R.id.tool_bar);
//        setSupportActionBar(mToolBar);

        mToolBar.inflateMenu(R.menu.menu);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        refreshView = findViewById(R.id.refresh_view);
        refreshView.setColorSchemeColors(Color.BLUE, Color.RED, Color.GREEN);
        refreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //这里获取数据的逻辑
                //更新RecyclerView
                refreshView.setRefreshing(false);
            }
        });
        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View itemView = LayoutInflater.from(BehaviorActivity.this).inflate(R.layout.item_behavior, parent, false);
                View itemView = View.inflate(getApplicationContext(), R.layout.item_behavior, null);
                ViewHolder viewHolder = new ViewHolder(itemView);
                return viewHolder;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 100;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
