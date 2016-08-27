package com.bwf.tuanche.ui.citychoice;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.bwf.framwork.utils.IntentUtils;
import com.bwf.framwork.utils.ListViewUtils;
import com.bwf.framwork.utils.LogUtils;
import com.bwf.framwork.utils.PinYinUtil;
import com.bwf.framwork.utils.UrlUtils;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.citychoice.adapter.CityChoiceAdapter;
import com.bwf.tuanche.ui.citychoice.bean.OpenCitysBean;
import com.bwf.tuanche.ui.citychoice.bean.ResultCityBean;
import com.bwf.tuanche.ui.citychoice.bean.ResultLocationCityBean;
import com.bwf.tuanche.ui.citychoice.callback.BackLocation;
import com.bwf.tuanche.ui.mainpager.MainPagerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by che on 2016/8/17
 * Description:.
 */
public class CityChoiceActivity extends BaseActivity implements BackLocation,View.OnClickListener {

    //顶部选择城市
    private TextView tvcity_title;

    //百度定位
    private LocationClient locationClient;
    private MyLocationListener myLocationListener = new MyLocationListener(this);
    private TextView tvcity_location;
    private String longitude;//经度
    private String latitude;//纬度

    //周边城市
    private TextView tvcity_zb1,tvcity_zb2,tvcity_zb3,tvcity_zb4;

    //热门城市
    private TextView tvcity_rm1,tvcity_rm2,tvcity_rm3, tvcity_rm4,tvcity_rm5,tvcity_rm6,tvcity_rm7,tvcity_rm8;

    //城市索引
    private LinearLayout ll_citysy;
    private ResultCityBean  resultCityBean;
    private ResultLocationCityBean resultLocationCityBean;



    @Override
    public int getContentViewId() {
        return R.layout.city_choices;
    }

    @Override
    public void beforeInitView() {

        locationClient = new LocationClient(getApplicationContext());//声明LocationClient类
        locationClient.registerLocationListener(myLocationListener);//注册监听函数
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

        setToBack(R.id.img_city_back);
        //顶部选择城市
        tvcity_title = findViewByIdNoCast(R.id.tvcity_title);

        //定位
        tvcity_location = findViewByIdNoCast(R.id.tvcity_location);

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
        ll_citysy = findViewByIdNoCast(R.id.ll_citysy);
    }

    @Override
    public void initData() {
        setOnClick(tvcity_location);
        getCitySYData();



    }

    public void getCitySYData(){
        HttpHelper.getCityData(UrlUtils.CITY_DATA, new HttpCallBack<ResultCityBean>() {
            @Override
            public void onSuccess(ResultCityBean result) {
//                ResultCityBean resultCityBean = JSON.parse(result);
//                LogUtils.e("result "+result.toString());
                if (result == null)
                    return;
                resultCityBean = result;
                hotCityData();
                List<OpenCitysBean> openCitysBean = resultCityBean.result.openCitys;
//                LogUtils.e("openCitysBean:``````````"+openCitysBean);
                //先得到第一个字母
                String one_zimu = openCitysBean.get(0).pinyin.substring(0,1);
                suoyinCity(one_zimu);

                for(int i = 0 ;i<openCitysBean.size();i++){

                    String pinyin = openCitysBean.get(i).pinyin;
//                    LogUtils.e("pinyin:~~~~~~"+pinyin);
//                    String py = pinyin.substring(0,1);
                    String shouzimu = PinYinUtil.getFirstTag(pinyin);
                    if(one_zimu.equals(shouzimu)){
                        continue;
                    }else{
                        one_zimu = shouzimu;//值不同时进行赋值
                        suoyinCity(shouzimu);
                    }

                }
        }

            @Override
            public void onFail(String errMsg) {
                LogUtils.e("errMsg:%%%%%%%%%%%%%%%%%%"+errMsg);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvcity_location:
                IntentUtils.openActivity(this, MainPagerActivity.class);
                break;
        }
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
        tv.setText(shouzimu.toUpperCase());//小写转大写
        tv.setBackgroundColor(Color.parseColor("#7FDDDDDD"));
        tv.setPadding(20,5,0,5);
        tv.setTextColor(Color.BLACK);
        //中文字体加粗方法
//        TextPaint tp = tv.getPaint();
//        tp.setFakeBoldText(true);
        ll_citysy.addView(tv);

//        OpenCitysBean open = OpenCitysBean.;
        List<OpenCitysBean> addcity = new ArrayList<>();
        for (OpenCitysBean open : resultCityBean.result.openCitys){
            if (shouzimu.equals(open.pinyin.substring(0,1))){
//                LogUtils.e("open:--------->"+open);
                addcity.add(open);
            }
        }


//        for(int i=0;i<resultCityBean.result.openCitys.size();i++){
//            if (shouzimu.equals(open.pinyin.substring(0,1))){
//                addcity.add(open);
//            }
//        }
//        LogUtils.e("addcity:$$$$$$$$$$$$"+addcity);

        //自定义ListView
        ListView listView = new ListView(this);

        //设置布局属性
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


//        for(int i = 0 ;i<citys.size();i++){
//            String name = citys.get(i).name;
//        }
        listView.setLayoutParams(params);
        CityChoiceAdapter cityChoiceAdapter = new CityChoiceAdapter(this,addcity);

        listView.setAdapter(cityChoiceAdapter);
//        LogUtils.e(listView.getAdapter().getCount()+"");
        //因为在ScrollView中，因此需要计算listView的高度
        listView.setDivider(new ColorDrawable(Color.parseColor("#DDDDDD")));
//        listView.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        listView.setDividerHeight(1);
        ListViewUtils.measureListViewHeight(listView);
        ll_citysy.addView(listView);

    }



    //Longitude 经度   Latitude  纬度
    public class MyLocationListener implements BDLocationListener {

        private BackLocation backLocation;

        public MyLocationListener(BackLocation backLocation) {
            this.backLocation = backLocation;
        }

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

                backLocation.getBack(Longitude,Latitude);//huidiaole

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



//                Log.i("BaiduLocationApiDem", sb.toString());


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


    //huidiao
    @Override
    public void getBack(String longitude,String latitude) {

        //定位城市，请求数据
        HttpHelper.getLocationCityData(UrlUtils.LOCATION_LOLATUDE, longitude, latitude, new HttpCallBack<ResultLocationCityBean>() {
            @Override
            public void onSuccess(ResultLocationCityBean result) {
                if (result == null||result.result==null)
                    return;
                resultLocationCityBean = result;
                LogUtils.e("result:----------&&&>"+result);
                tvcity_location.setText(resultLocationCityBean.result.name);

                //周边城市
                tvcity_zb1.setText(resultLocationCityBean.result.name);
                tvcity_zb2.setText(resultLocationCityBean.result.name);
                tvcity_zb3.setText(resultLocationCityBean.result.name);
                tvcity_zb4.setText(resultLocationCityBean.result.name);

                //顶部城市取值
                String title = String.format(getString(R.string.citychoice),""+resultLocationCityBean.result.name);
                tvcity_title.setText(title);
            }

            @Override
            public void onFail(String errMsg) {

            }
        });
    }
}
