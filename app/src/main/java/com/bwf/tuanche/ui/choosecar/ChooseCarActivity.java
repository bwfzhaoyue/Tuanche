package com.bwf.tuanche.ui.choosecar;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.base.BaseBean;
import com.bwf.framwork.http.HttpCallBack;
import com.bwf.framwork.http.HttpHelper;
import com.bwf.framwork.utils.ToastUtil;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.choosecar.adapter.BosRecyclerAdapter;
import com.bwf.tuanche.ui.choosecar.adapter.HotTypeRecyclerAdapter;
import com.bwf.tuanche.ui.choosecar.adapter.SeriesRecyclerAdapter;
import com.bwf.tuanche.ui.choosecar.entity.condition.ConditionResult;
import com.bwf.tuanche.ui.choosecar.entity.hotcar.HotTypeResult;
import com.bwf.tuanche.view.LoadingView;

/**
 * 选车界面 包括品牌选车和条件选车
 */
public class ChooseCarActivity extends BaseActivity {

    private String cityId = "156";//城市ID 成都156

    private ImageView img_back,img_search;//左边返回 右边搜索

    private TextView tv_brand_choosecar,tv_condition_choosecar;//品牌选车和条件选车按钮

    private LinearLayout ll_brand_content;//品牌选车

    private ScrollView ll_condition_content;//条件选车整个页面

    private LoadingView view_loading;

    private RecyclerView recycler_hotcar;//热门品牌的RecyclerView

    private HotTypeRecyclerAdapter recyclerAdapter;//热门品牌的RecyclerView的适配器

    private RecyclerView recycler_bos,recycler_series;//条件选车-级别,国别

    private BosRecyclerAdapter bosRecyclerAdapter;//级别适配器

    private SeriesRecyclerAdapter seriesRecyclerAdapter;//国别适配器

    @Override
    public int getContentViewId() {
        return R.layout.activity_choose_car;
    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {
        img_back = findViewByIdNoCast(R.id.img_back);
        setToBack(img_back);
        img_search = findViewByIdNoCast(R.id.img_search);
        tv_brand_choosecar = findViewByIdNoCast(R.id.tv_brand_choosecar);
        tv_condition_choosecar = findViewByIdNoCast(R.id.tv_condition_choosecar);
        ll_brand_content = findViewByIdNoCast(R.id.ll_brand_content);
        ll_condition_content = findViewByIdNoCast(R.id.ll_condition_content);
        view_loading = findViewByIdNoCast(R.id.view_loading);
        recycler_hotcar = findViewByIdNoCast(R.id.recycler_hotcar);
        recycler_bos = findViewByIdNoCast(R.id.recycler_bos);
        recycler_series = findViewByIdNoCast(R.id.recycler_series);

        setOnClick(img_search,tv_brand_choosecar,tv_condition_choosecar);
    }

    @Override
    public void initData() {

        recycler_hotcar.setLayoutManager(new GridLayoutManager(this,4));//表格布局 每行四个
        recyclerAdapter = new HotTypeRecyclerAdapter(this);//初始化热门车型适配器
        recycler_hotcar.setAdapter(recyclerAdapter);//设置适配器

        //条件选车-级别
        recycler_bos.setLayoutManager(new GridLayoutManager(this,3));
        bosRecyclerAdapter = new BosRecyclerAdapter(this);
        recycler_bos.setAdapter(bosRecyclerAdapter);

        //条件选车-国别
        recycler_series.setLayoutManager(new GridLayoutManager(this,3));
        seriesRecyclerAdapter = new SeriesRecyclerAdapter(this);
        recycler_series.setAdapter(seriesRecyclerAdapter);

        //加载到热门车型数据以后刷新适配器
        HttpHelper.getHotCar(cityId, new HttpCallBack<HotTypeResult>() {
            @Override
            public void onSuccess(HotTypeResult result) {
                recyclerAdapter.setList(result.result.list);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String errMsg) {
                ToastUtil.showToast(errMsg);
            }
        });

        //加载条件选车数据----级别 国别
        HttpHelper.getConditionToChoose(new HttpCallBack<ConditionResult>() {
            @Override
            public void onSuccess(ConditionResult result) {
                bosRecyclerAdapter.setList(result.result.bos);
                bosRecyclerAdapter.notifyDataSetChanged();

                seriesRecyclerAdapter.setList(result.result.series);
                seriesRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String errMsg) {
                ToastUtil.showToast("级别列表加载失败："+ errMsg);
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_search://搜索
                ToastUtil.showToast("暂无搜索功能");
                break;
            case R.id.tv_brand_choosecar://品牌选车按钮
                tv_brand_choosecar.setTextColor(getResources().getColor(R.color.white));
                tv_brand_choosecar.setBackground(getResources().getDrawable(R.mipmap.round_red_left));

                tv_condition_choosecar.setTextColor(getResources().getColor(R.color.title_red));
                tv_condition_choosecar.setBackground(getResources().getDrawable(R.mipmap.round_white_right));

                ll_brand_content.setVisibility(View.VISIBLE);
                ll_condition_content.setVisibility(View.GONE);
                break;
            case R.id.tv_condition_choosecar://条件选车按钮
                tv_brand_choosecar.setTextColor(getResources().getColor(R.color.title_red));
                tv_brand_choosecar.setBackground(getResources().getDrawable(R.mipmap.round_white_left));

                tv_condition_choosecar.setTextColor(getResources().getColor(R.color.white));
                tv_condition_choosecar.setBackground(getResources().getDrawable(R.mipmap.round_red_right));

                ll_brand_content.setVisibility(View.GONE);
                ll_condition_content.setVisibility(View.VISIBLE);
                break;
        }
    }
}
