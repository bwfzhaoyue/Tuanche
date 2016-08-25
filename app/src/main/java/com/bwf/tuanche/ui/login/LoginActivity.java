package com.bwf.tuanche.ui.login;

import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bwf.framwork.utils.NetWorkUtils;
import com.bwf.tuanche.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.mob.commons.SMSSDK;

import org.json.JSONObject;

import cn.smssdk.EventHandler;
import cn.smssdk.utils.SMSLog;

public class LoginActivity extends AppCompatActivity {

    private TextView submit;
    private TextView getSecurityCode;
    private EditText security_code;
    private EditText telphone;
//    private String AppKey = ;
//    private String APPSECRET = ;
    private RadioButton accountLogin;
    private RadioButton fastLogin;
    private String phone;
    private TimeCount time;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            if (result == cn.smssdk.SMSSDK.RESULT_COMPLETE) {
                if (event == cn.smssdk.SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    Toast.makeText(getApplicationContext(), "提交验证码成功",
                            Toast.LENGTH_SHORT).show();
                } else if (event == cn.smssdk.SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    // 已经验证
                    Toast.makeText(getApplicationContext(), "验证码已经发送",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                int status = 0;
                try {
                    ((Throwable) data).printStackTrace();
                    Throwable throwable = (Throwable) data;

                    JSONObject object = new JSONObject(throwable.getMessage());
                    String des = object.optString("detail");
                    status = object.optInt("status");
                    if (!TextUtils.isEmpty(des)) {
                        Toast.makeText(LoginActivity.this, des,
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (Exception e) {
                    SMSLog.getInstance().w(e);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();
//        SMSSDK.initSDK(this, AppKey, APPSECRET);
//        SMSSDK.setAppKey(AppKey);

        setLinsenter();
        textChangedLisenter();
        EventHandler eh = new EventHandler() {

            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
     //   SMSSDK.registerEventHandler(eh);
    }


    private void textChangedLisenter() {
        telphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String telephoneV = telphone.getText().toString();
                if (!NetWorkUtils.isMobileNO(telephoneV)) {
                    getSecurityCode.setBackgroundColor(getColor(R.color.little_red));
                    getSecurityCode.setClickable(false);
                } else {
                    getSecurityCode.setBackgroundColor(getColor(R.color.title_red));
                    getSecurityCode.setClickable(true);
                }
            }
        });
    }

    private void findView() {
        ImageView back = (ImageView) findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        fastLogin = (RadioButton) findViewById(R.id.rb_fastLogin);
        accountLogin = (RadioButton) findViewById(R.id.rb_accountLogin);
        telphone = (EditText) findViewById(R.id.ed_telphone);
        security_code = (EditText) findViewById(R.id.ed_security_code);
        getSecurityCode = (TextView) findViewById(R.id.tv_getSecurityCode);
        submit = (TextView) findViewById(R.id.to_login);

        time = new TimeCount(60000, 1000);
        getSecurityCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time.start();
            }
        });
    }


    private void setLinsenter(View... views) {
        View.OnClickListener lisenter = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.iv_back:
                        finish();
                        break;
                    case R.id.rb_fastLogin://快速登录


                        break;
                    case R.id.rb_accountLogin://账号注册
                        getSecurityCode.setVisibility(View.GONE);
                        security_code.setText(getString(R.string.password));
                        submit.setText(getString(R.string.login));
                        break;
                    case R.id.to_login://登录
                        cn.smssdk.SMSSDK.submitVerificationCode("86", phone, security_code.getText()
                                .toString());

                        break;
                    case R.id.tv_getSecurityCode: //获取验证码
                        if (!TextUtils.isEmpty(security_code.getText().toString())) {
                            getContentResolver().registerContentObserver(
                                    Uri.parse("content://sms"), true,
                                    new SmsObserver(new Handler()));
                            cn.smssdk.SMSSDK.getVerificationCode("86", security_code.getText().toString());

                            phone = telphone.getText().toString();
                        } else {
                            Toast.makeText(LoginActivity.this, "电话号码不能为空", Toast.LENGTH_LONG)
                                    .show();
                        }
                        break;
                }
            }
        };
        for (View v : views) {
            v.setOnClickListener(lisenter);
        }
    }

    protected void onDestroy() {
        cn.smssdk.SMSSDK.unregisterAllEventHandler();
        getContentResolver().unregisterContentObserver(new SmsObserver(handler));
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            getSecurityCode.setClickable(false);//防止重复点击
            getSecurityCode.setText(millisUntilFinished / 1000 +"s");
        }

        @Override
        public void onFinish() {
            getSecurityCode.setText("获取验证码");
            getSecurityCode.setClickable(true);
        }
    }
}