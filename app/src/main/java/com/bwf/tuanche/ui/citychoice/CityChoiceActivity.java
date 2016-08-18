package com.bwf.tuanche.ui.citychoice;

import android.graphics.Color;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.http.HttpCallBack;
import com.bwf.framwork.http.HttpHelper;
import com.bwf.framwork.utils.ListViewUtils;
import com.bwf.framwork.utils.LogUtils;
import com.bwf.framwork.utils.PinYinUtil;
import com.bwf.framwork.utils.UrlUtils;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.citychoice.adapter.CityChoiceAdapter;
import com.bwf.tuanche.ui.citychoice.bean.OpenCitysBean;
import com.bwf.tuanche.ui.citychoice.bean.ResultCityBean;
import com.hp.hpl.sparta.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by che on 2016/8/17
 * Description:.
 */
public class CityChoiceActivity extends BaseActivity{

    private LocationClient locationClient;
    private BDLocationListener bdLocationListener = new MyLocationListener();

    //定位
    private TextView tv_location;

    //周边城市
    private TextView tvcity_zb1,tvcity_zb2,tvcity_zb3,tvcity_zb4;

    //热门城市
    private TextView tvcity_rm1,tvcity_rm2,tvcity_rm3, tvcity_rm4,tvcity_rm5,tvcity_rm6,tvcity_rm7,tvcity_rm8;

    //城市索引
    private LinearLayout ll_citisy;
    private ResultCityBean  resultCityBean;



    @Override
    public int getContentViewId() {
        return R.layout.city_choices;
    }

    @Override
    public void beforeInitView() {

        locationClient = new LocationClient(getApplicationContext());//声明LocationClient类
        locationClient.registerLocationListener(bdLocationListener);//注册监听函数
        initLocation();//初始化定位参数
        locationClient.start();//开始定位
    }

    /**
     * 初始化定位参数
     */
    private void initLocation() {
        LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        locationClientOption.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 900;
        locationClientOption.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        locationClientOption.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        locationClientOption.setOpenGps(true);//可选，默认false,设置是否使用gps
        locationClientOption.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        locationClientOption.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        locationClientOption.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        locationClientOption.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        locationClientOption.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        locationClientOption.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        locationClient.setLocOption(locationClientOption);
    }

    @Override
    public void initView() {
        //定位
        tv_location= findViewByIdNoCast(R.id.tv_location);

        //周边城市
        tvcity_zb1 = findViewByIdNoCast(R.id.tvcity_zb1);
        tvcity_zb2 = findViewByIdNoCast(R.id.tvcity_zb2);
        tvcity_zb3 = findViewByIdNoCast(R.id.tvcity_zb3);
        tvcity_zb4 = findViewByIdNoCast(R.id.tvcity_zb4);

        //热门城市
        tvcity_rm1 = findViewByIdNoCast(R.id.tvcity_rm1);
        tvcity_rm2 = findViewByIdNoCast(R.id.tvcity_rm2);
        tvcity_rm3 = findViewByIdNoCast(R.id.tvcity_rm3);
        tvcity_rm4 = findViewByIdNoCast(R.id.tvcity_rm4);
        tvcity_rm5 = findViewByIdNoCast(R.id.tvcity_rm5);
        tvcity_rm6 = findViewByIdNoCast(R.id.tvcity_rm6);
        tvcity_rm7 = findViewByIdNoCast(R.id.tvcity_rm7);
        tvcity_rm8 = findViewByIdNoCast(R.id.tvcity_rm8);

        //城市索引
        ll_citisy = findViewByIdNoCast(R.id.ll_citisy);
    }

    @Override
    public void initData() {

        getData();

    }

    @Override
    public void onClick(View view) {

    }

    public void getData(){
        HttpHelper.getCityData(UrlUtils.CITY_DATA, new HttpCallBack<ResultCityBean>() {
            @Override
            public void onSuccess(ResultCityBean result) {
//                ResultCityBean resultCityBean = JSON.parse(result);
                Log.e("result","result "+result.toString());
                if (result == null)
                    return;
                resultCityBean = result;
                hotCityData();
                List<OpenCitysBean> openCitysBean = resultCityBean.result.openCitys;
                LogUtils.e("openCitysBean:``````````"+openCitysBean);
                //先得到第一个字母
                String first = openCitysBean.get(0).pinyin.substring(0,1);
                suoyinCity(first.toUpperCase());

                for(int i = 0 ;i<openCitysBean.size()-1;i++){

                    String pinyin = openCitysBean.get(i).pinyin;
//                    LogUtils.e("pinyin:~~~~~~"+pinyin);
                    String shouzimu = PinYinUtil.getFirstTag(pinyin);
                    if(first.equals(shouzimu)){
                        continue;
                    }else{
                        first = shouzimu;//值不同时赋值
                        suoyinCity(shouzimu.toUpperCase());//小写转大写
                    }

                }
        }

            @Override
            public void onFail(String errMsg) {

            }
        });
    }

    //热门城市数据
    public void hotCityData(){
        if(resultCityBean != null){
            tvcity_rm1.setText(resultCityBean.result.hotCitys.get(0).name);
            tvcity_rm2.setText(resultCityBean.result.hotCitys.get(1).name);
            tvcity_rm3.setText(resultCityBean.result.hotCitys.get(2).name);
            tvcity_rm4.setText(resultCityBean.result.hotCitys.get(3).name);
            tvcity_rm5.setText(resultCityBean.result.hotCitys.get(4).name);
            tvcity_rm6.setText(resultCityBean.result.hotCitys.get(5).name);
            tvcity_rm7.setText(resultCityBean.result.hotCitys.get(6).name);
            tvcity_rm8.setText(resultCityBean.result.hotCitys.get(7).name);
        }


    }

    //城市索引方法
    public void suoyinCity(String shouzimu){

        TextView tv = new TextView(this);
        //索引的字母
        tv.setText(shouzimu);
        tv.setBackgroundColor(Color.parseColor("#DDDDDD"));
        tv.setPadding(15,3,0,3);
        tv.setTextSize(16);
        tv.setTextColor(Color.BLACK);
        //中文字体加粗方法
//        TextPaint tp = tv.getPaint();
//        tp.setFakeBoldText(true);
        ll_citisy.addView(tv);

        //自定义View
        ListView listView = new ListView(this);
        //设置布局属性
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


//        for(int i = 0 ;i<citys.size();i++){
//            String name = citys.get(i).name;
//        }
        CityChoiceAdapter cityChoiceAdapter = new CityChoiceAdapter(this);
        listView.setLayoutParams(params);
        listView.setAdapter(cityChoiceAdapter);
        //因为在ScrollView中，因此需要计算listView的高度
        ListViewUtils.measureListViewHeight(listView);
        ll_citisy.addView(listView);

    }
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation != null) {
                String Province = bdLocation.getProvince();//省份
                String City = bdLocation.getCity();//城市
                String District = bdLocation.getDistrict();//区域
                String Address = bdLocation.getAddrStr();//详细地址
                String Longitude = "" + bdLocation.getLongitude();//经度
                String Latitude = "" + bdLocation.getLatitude();//纬度
                String error_code = "" + bdLocation.getLocType();//网络定位结果

                //周边和附近
                StringBuilder sb = new StringBuilder();
                List<Poi> list = bdLocation.getPoiList();// POI数据
                if (list != null) {
                    sb.append("\npoilist size = : ");
                    sb.append(list.size());
                    for (Poi p : list) {
                        sb.append("\npoi= : ");
                        sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                    }
                }
                Log.i("BaiduLocationApiDem", sb.toString());


                Log.e("msg", "省份~~~~~~~：" + Province);
                Log.e("msg", "城市~~~~~~~：" + City);
                Log.e("msg", "区域~~~~~~~：" + District);
                Log.e("msg", "详细地址~~~~~~~：" + Address);
                Log.e("msg", "经度~~~~~~~：" + Longitude);
                Log.e("msg", "纬度~~~~~~~：" + Latitude);
                Log.e("msg", "纬度~~~~~~~：" + Latitude);
                Log.e("msg", "网络定位结果~~~~~~~：" + error_code);
                Log.e("msg", "周边和附近~~~~~~~：" + sb);
            }
        }
    }
}
