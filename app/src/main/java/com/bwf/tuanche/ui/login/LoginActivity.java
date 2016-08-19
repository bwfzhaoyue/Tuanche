package com.bwf.tuanche.ui.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bwf.framwork.utils.NetWorkUtils;
import com.bwf.tuanche.R;

public class LoginActivity extends AppCompatActivity {

    private TextView submit;
    private TextView getSecurityCode;
    private EditText security_code;
    private EditText telphone;
    private RadioButton accountLogin;
    private RadioButton fastLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();
        setLinsenter();
        textChangedLisenter();
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
                if(!NetWorkUtils.isMobileNO(telephoneV)){
                    getSecurityCode.setBackgroundColor(getColor(R.color.little_red));
                    getSecurityCode.setClickable(false);
                }else{
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

                        break;
                }
            }
        };
        for (View v : views) {
            v.setOnClickListener(lisenter);
        }
    }


}