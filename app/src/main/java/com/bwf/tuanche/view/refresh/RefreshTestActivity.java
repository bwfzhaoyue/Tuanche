package com.bwf.tuanche.view.refresh;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bwf.tuanche.R;

public class RefreshTestActivity extends AppCompatActivity {

    private boolean result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_test);
        ((PullToRefreshLayout) findViewById(R.id.refresh_view))
                .setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
                        result = !result;
                        // 下拉刷新操作
                        new Handler()
                        {
                            @Override
                            public void handleMessage(Message msg)
                            {
                                // 千万别忘了告诉控件刷新完毕了哦！
                                pullToRefreshLayout.refreshFinish(result?PullToRefreshLayout.SUCCEED:PullToRefreshLayout.FAIL);
                            }
                        }.sendEmptyMessageDelayed(0, 3000);

                    }

                    @Override
                    public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
                        result = !result;
                        // 加载操作
                        new Handler()
                        {
                            @Override
                            public void handleMessage(Message msg)
                            {
                                // 千万别忘了告诉控件加载完毕了哦！
                                pullToRefreshLayout.loadmoreFinish(result?PullToRefreshLayout.SUCCEED:PullToRefreshLayout.FAIL);
                            }
                        }.sendEmptyMessageDelayed(0, 3000);
                    }
                });
    }

}
