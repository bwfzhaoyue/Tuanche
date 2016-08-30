package com.bwf.tuanche.ui.search;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.db.model.SearchHistoryModel;
import com.bwf.framwork.http.HttpCallBack;
import com.bwf.framwork.http.HttpHelper;
import com.bwf.framwork.utils.LogUtils;
import com.bwf.framwork.utils.ToastUtil;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.mainpager.entity.HotSearch;
import com.bwf.tuanche.ui.mainpager.entity.SearchHistoryBean;
import com.bwf.tuanche.view.LoadingView;

import java.util.Date;
import java.util.List;

public class SearchActivity extends BaseActivity {

    private ListView recycler_searching, recycler_search_history;

    private EditText et_search;

    private ImageView img_back_search;

    private TextView tv_search, tv_1, tv_2, tv_3, tv_4, tv_5, tv_6, tv_7, tv_8, tv_9, tv_10, tv_11;

    private String cityId;

    private LoadingView search_boy;

    private boolean runBoy = true;

    private SearchHistory adapter;

    private LinearLayout footerView;

    private List<SearchHistoryBean> searchHistoryBeanList;

    @Override
    public int getContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    public void beforeInitView() {
        cityId = getIntent().getStringExtra("cityId");
        getData();
    }

    private void getData() {
        HttpHelper.getHotSearch(cityId, new HttpCallBack<HotSearch>() {

            @Override
            public void onSuccess(HotSearch result) {
                if (result != null) {
                    tv_1.setText(result.result.get(0));
                    tv_2.setText(result.result.get(1));
                    tv_3.setText(result.result.get(2));
                    tv_4.setText(result.result.get(3));
                    tv_5.setText(result.result.get(4));
                    tv_6.setText(result.result.get(5));
                    tv_7.setText(result.result.get(6));
                    tv_8.setText(result.result.get(7));
                    tv_9.setText(result.result.get(8));
                    tv_10.setText(result.result.get(9));
                    tv_11.setText(result.result.get(10));
                    if (runBoy) {
                        search_boy.setLoadingFinish();
                        runBoy = false;
                    }

                }
                LogUtils.e(result.toString());

            }

            @Override
            public void onFail(String errMsg) {
                LogUtils.e("1111111");
                if (runBoy) {
                    search_boy.setLoadFail();
                    runBoy = false;
                }
            }
        });
    }

    @Override
    public void initView() {
        recycler_searching = findViewByIdNoCast(R.id.recycler_searching);
        recycler_search_history = findViewByIdNoCast(R.id.recycler_search_history);
        et_search = findViewByIdNoCast(R.id.et_search);
        et_search.setHint("请输入品牌、车型");
        img_back_search = findViewByIdNoCast(R.id.img_back_search);
        tv_search = findViewByIdNoCast(R.id.tv_search);
        tv_1 = findViewByIdNoCast(R.id.tv_1);
        tv_2 = findViewByIdNoCast(R.id.tv_2);
        tv_3 = findViewByIdNoCast(R.id.tv_3);
        tv_4 = findViewByIdNoCast(R.id.tv_4);
        tv_5 = findViewByIdNoCast(R.id.tv_5);
        tv_6 = findViewByIdNoCast(R.id.tv_6);
        tv_7 = findViewByIdNoCast(R.id.tv_7);
        tv_8 = findViewByIdNoCast(R.id.tv_8);
        tv_9 = findViewByIdNoCast(R.id.tv_9);
        tv_10 = findViewByIdNoCast(R.id.tv_10);
        tv_11 = findViewByIdNoCast(R.id.tv_11);
        search_boy = findViewByIdNoCast(R.id.search_boy);
        setToBack(img_back_search);
    }

    @Override
    public void initData() {
        adapter = new SearchHistory(this);
        footerView = (LinearLayout) View.inflate(this, R.layout.activity_footer_view, null);
        footerView.getChildAt(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchHistoryModel.getUserModel().clear();
                searchHistoryBeanList.clear();
                adapter.setDatas(searchHistoryBeanList);
                adapter.notifyDataSetChanged();
            }
        });
        recycler_search_history.addFooterView(footerView);
        setOnClick(tv_search, tv_1, tv_2, tv_3, tv_4, tv_5, tv_6, tv_7, tv_8, tv_9, tv_10, tv_11);
        searchHistoryBeanList = SearchHistoryModel.getUserModel().queryAllCustomer();
        adapter.setDatas(searchHistoryBeanList);
        recycler_search_history.setAdapter(adapter);

        //修改输入框的回车键显示
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEND
                        || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    if ("".equals(et_search.getText().toString())) {
                        ToastUtil.showToast("请输入搜索内容");
                        return true;
                    }
                    insert();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                if ("".equals(et_search.getText().toString())) {
                    ToastUtil.showToast("请输入搜索内容");
                    return;
                }
                insert();
                break;
            case R.id.tv_1:
                et_search.setText(tv_1.getText());
                insert();
                break;
            case R.id.tv_2:
                et_search.setText(tv_2.getText());
                insert();
                break;
            case R.id.tv_3:
                et_search.setText(tv_3.getText());
                insert();
                break;
            case R.id.tv_4:
                et_search.setText(tv_4.getText());
                insert();
                break;
            case R.id.tv_5:
                et_search.setText(tv_5.getText());
                insert();
                break;
            case R.id.tv_6:
                et_search.setText(tv_6.getText());
                insert();
                break;
            case R.id.tv_7:
                et_search.setText(tv_7.getText());
                insert();
                break;
            case R.id.tv_8:
                et_search.setText(tv_8.getText());
                insert();
                break;
            case R.id.tv_9:
                et_search.setText(tv_9.getText());
                insert();
                break;
            case R.id.tv_10:
                et_search.setText(tv_10.getText());
                insert();
                break;
            case R.id.tv_11:
                et_search.setText(tv_11.getText());
                insert();
                break;
        }
    }

    public void insert() {
        SearchHistoryBean historyBean = new SearchHistoryBean();
        historyBean.date = new Date().toString();
        historyBean.content = et_search.getText().toString();
        SearchHistoryModel.getUserModel().insertUser(historyBean);

        for (int i = 0; i < searchHistoryBeanList.size(); i++) {
            if (searchHistoryBeanList.get(i).content.equals(historyBean.content)) {
                searchHistoryBeanList.remove(i);
            }
        }
        searchHistoryBeanList.add(historyBean);
        if (searchHistoryBeanList.size() >= 11) {
            SearchHistoryModel.getUserModel().deleteOne(searchHistoryBeanList.get(0));
            searchHistoryBeanList.remove(0);
        }
        adapter.setDatas(searchHistoryBeanList);
        adapter.notifyDataSetChanged();
    }
}
