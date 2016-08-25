package com.bwf.tuanche.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.bwf.tuanche.ui.mainpager.entity.hotmodle.HotModleResult;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.socialize.PlatformConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Lizhangfeng on 2016/8/16 0016.
 * Description:
 */
public class MyApplication extends Application {
    private List<Activity> activities = new ArrayList<>();
    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        results = new ArrayList<>();
        //QQ appid appsecret
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        //初始化facebook
        Fresco.initialize(this);
        //初始化okhttp
        initOkhttp();

    }

    /**
     * 初始化okhttp
     */
    public void initOkhttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("Tuanche"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    public static MyApplication getMyApplication() {
        return myApplication;
    }

    public static Context getAppContext() {
        return myApplication.getApplicationContext();
    }

    /**
     * 添加Activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activity != null)
            activities.add(activity);
    }

    /**
     * 移除activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null)
            activities.remove(activity);
    }

    /**
     * 移除某个activity以外的所有
     *
     * @param activity
     */
    public void removeAll(Activity activity) {
        if (activity != null) {
            for (Activity temp : activities)
                if (temp != activity)
                    temp.finish();
            activities.clear();
            activities.add(activity);
        }
    }

    /**
     * 移除所有
     */
    public void removeAll() {
        for (Activity temp : activities)
            temp.finish();
        activities.clear();
    }

    public void exit() {
        try {
            for (Activity activity : activities)
                if (activity != null)
                    activity.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        exit();
    }

    /**
     * 浏览记录
     */
    private List<HotModleResult> results;

    public void addHotModleResult(HotModleResult result) {
        if (results != null) {
            for (int i = 0; i < results.size(); i++) {
                if (results.get(i).id.equals(result.id)) {
                    results.remove(i);
                }
            }
        }
        if (results.size() == 20) {
            results.remove(0);
        }
        results.add(result);
    }

    public List<HotModleResult> getHotModleList() {
        return results;
    }
}
