package com.bwf.tuanche.ui.login;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.bwf.tuanche.application.MyApplication;

import java.util.Observable;
import java.util.Observer;

 /**
   * 类名:BaseActivity
   * 描述:基类Activity extends FragmentActivity implements Observer
   * 创建者:
   * 创建时间:
   * 更新者:
  */
public class BaseActivity extends FragmentActivity implements Observer{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication myApplication = (MyApplication) this.getApplication();
        myApplication.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication baseApplication = (MyApplication) this.getApplication();
        baseApplication.removeActivity(this);
    }

    @Override
    public void update(Observable observable, Object data) {
    }
}
