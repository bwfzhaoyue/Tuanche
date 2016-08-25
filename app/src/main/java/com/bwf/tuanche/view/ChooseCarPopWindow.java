package com.bwf.tuanche.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.framwork.base.BaseBean;
import com.bwf.framwork.http.HttpCallBack;
import com.bwf.framwork.http.HttpHelper;
import com.bwf.framwork.utils.DisplayUtil;
import com.bwf.framwork.utils.IntentUtils;
import com.bwf.framwork.utils.LogUtils;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.choosecar.adapter.CarInfoAdapter;
import com.bwf.tuanche.ui.choosecar.entity.carsofbrand.CarInfoBean;
import com.bwf.tuanche.ui.choosecar.entity.carsofbrand.CarsResult;
import com.bwf.tuanche.ui.mainpager.HotModleAndBrandDetailsActivity;

/**
 * author zhaoyue
 * Description
 */
public class ChooseCarPopWindow extends PopupWindow implements View.OnClickListener{

    private Activity activity;

    private String type,cityId,brandId;//参数

    private RelativeLayout rl_hot,rl_price;//点击热门和价格
    private TextView tv_hot,tv_price;
    private View view_belowhot,view_belowprice;
    private int currentType;//0为热门，1为价格

    private LoadingView view_loading;//加载信息

    private TextView tv_carname;//汽车品牌

    private ListView lv_cars;//具体汽车列表

    private CarInfoAdapter adapter;//具体汽车信息适配器

    public void setBrandId(String brandId,String cityId) {
        this.cityId = cityId;
        this.brandId = brandId;
        type = "0";
        getData();
    }

    public ChooseCarPopWindow(Activity activity) {
        super(activity);
        this.activity = activity;
        init();
        getData();
    }

    private void init(){
        //popWindow相关设置
        View view = View.inflate(activity, R.layout.pop_choosecar, null);
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
       // this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        this.setBackgroundDrawable(new BitmapDrawable());
        setAnimationStyle(R.style.PopupAnimation);
        update();

        initView(view);
        adapter = new CarInfoAdapter(activity);
        lv_cars.setAdapter(adapter);

    }

    /**
     * 初始化各个View
     * @param view
     */
    private void initView(View view){
        tv_carname = (TextView) view.findViewById(R.id.tv_carname);
        view_loading = (LoadingView) view.findViewById(R.id.view_loading);
        lv_cars = (ListView) view.findViewById(R.id.lv_cars);
        rl_hot = (RelativeLayout) view.findViewById(R.id.rl_hot);
        rl_price = (RelativeLayout) view.findViewById(R.id.rl_price);
        tv_hot = (TextView) view.findViewById(R.id.tv_hot);
        tv_price = (TextView) view.findViewById(R.id.tv_price);
        view_belowhot = view.findViewById(R.id.view_belowhot);
        view_belowprice = view.findViewById(R.id.view_belowprice);

        //左边的透明View点击就消失PopWindow
        view.findViewById(R.id.view_transparent).setOnClickListener(this);
        rl_hot.setOnClickListener(this);
        rl_price.setOnClickListener(this);
    }

    //获取网络数据
    private void getData(){
        if (brandId==null||type==null||cityId==null)
            return;
        LogUtils.e("getData执行");
        view_loading.setOnLoad();
        HttpHelper.getCarListByBrand(type, cityId, brandId, new HttpCallBack<CarsResult>() {
            @Override
            public void onSuccess(CarsResult result) {
                view_loading.setLoadingFinish();
                tv_carname.setText(result.result.get(0).brandName);
                adapter.settList(result.result.get(0).styleList);
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onFail(String errMsg) {
                view_loading.setLoadFail();
            }
        });

        lv_cars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CarInfoBean bean = (CarInfoBean)adapter.getItem(i);
                Bundle bundle = new Bundle();
                bundle.putString("brandId",bean.brandId);
                bundle.putString("styleId",bean.id);
                bundle.putInt("type",1);
                bundle.putString("cityId",cityId);

                //打开详情Activity
                IntentUtils.openActivity(activity, HotModleAndBrandDetailsActivity.class);
            }
        });
    }

    /**
     * 显示popwindow
     * @param view
     */
    public void showPopWindow(View view) {
        if (!isShowing()) {
            this.showAsDropDown(view);//显示在view的下方
//             this.showAtLocation(view, Gravity.CENTER, 0, 0);//可以显示在指定view的指定位置
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.view_transparent://点击透明View消失
                ChooseCarPopWindow.this.dismiss();
                break;
            case R.id.rl_hot://热门
                if (currentType==0)
                    return;
                tv_hot.setTextColor(activity.getResources().getColor(R.color.title_red));
                tv_price.setTextColor(activity.getResources().getColor(R.color.black));
                view_belowhot.setVisibility(View.VISIBLE);
                view_belowprice.setVisibility(View.GONE);
                type = "0";
                currentType = 0;
                getData();
                break;
            case R.id.rl_price://价格
                if (currentType==1)
                    return;
                tv_hot.setTextColor(activity.getResources().getColor(R.color.black));
                tv_price.setTextColor(activity.getResources().getColor(R.color.title_red));
                view_belowhot.setVisibility(View.GONE);
                view_belowprice.setVisibility(View.VISIBLE);
                type = "1";
                currentType = 1;
                getData();
                break;
        }
    }
}
