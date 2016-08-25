package com.bwf.tuanche.ui.login;

import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.utils.LogUtils;
import com.bwf.framwork.utils.NetWorkUtils;
import com.bwf.framwork.utils.ToastUtil;
import com.bwf.tuanche.R;

public class LoginActivity extends BaseActivity {

    private final int LEFT = 0, RIGHT = 1;//快速登陆为0，账号登陆为1

    private TextView tv_getSecurityCode;//获取验证码按钮

    private EditText ed_telphone, ed_security_code;//账号和密码（验证码）的输入框

    private ImageView img_delete_phone, img_delete_sec;//清空账号和密码

    private TextView tv_fastlogin, tv_accountlogin;//账号登陆和快速登陆

    private TextView tv_login;//登陆按钮

    private int method;//当前登陆方式，默认为快速登陆,快速登陆为0，账号登陆为1

    private String verification, password;//保存当前的验证码和密码

    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {
        setToBack(findViewById(R.id.iv_back));//设置返回键

        tv_login = findViewByIdNoCast(R.id.tv_login);
        tv_getSecurityCode = findViewByIdNoCast(R.id.tv_getSecurityCode);
        ed_telphone = findViewByIdNoCast(R.id.ed_telphone);
        tv_fastlogin = findViewByIdNoCast(R.id.tv_fastlogin);
        tv_accountlogin = findViewByIdNoCast(R.id.tv_accountlogin);
        img_delete_phone = findViewByIdNoCast(R.id.img_delete_phone);
        img_delete_sec = findViewByIdNoCast(R.id.img_delete_sec);
        ed_telphone = findViewByIdNoCast(R.id.ed_telphone);
        ed_security_code = findViewByIdNoCast(R.id.ed_security_code);

        setOnClick(tv_login, tv_fastlogin, tv_getSecurityCode, tv_accountlogin,
                img_delete_phone, img_delete_sec);
    }

    @Override
    public void initData() {
        initTextChangeListener();
    }


    private void initTextChangeListener() {
        ed_telphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(ed_telphone.getText().toString())) {
                    tv_getSecurityCode.setClickable(false);
                    tv_getSecurityCode.setBackgroundResource(R.drawable.shape_btn_unclickable);

                    tv_login.setClickable(false);
                    tv_login.setBackgroundResource(R.drawable.shape_btn_unclickable);

                    img_delete_phone.setVisibility(View.GONE);
                } else {
                    img_delete_phone.setVisibility(View.VISIBLE);
                    tv_getSecurityCode.setClickable(true);
                    tv_getSecurityCode.setBackgroundResource(R.drawable.selector_btn_clickable);

                    if (!TextUtils.isEmpty(ed_security_code.getText().toString())) {
                        tv_login.setClickable(true);
                        tv_login.setBackgroundResource(R.drawable.selector_btn_clickable);
                    }
                }
            }
        });

        ed_security_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (method == 0)
                    verification = ed_security_code.getText().toString();
                else
                    password = ed_security_code.getText().toString();

                if (TextUtils.isEmpty(ed_security_code.getText().toString())) {
                    tv_login.setClickable(false);
                    tv_login.setBackgroundResource(R.drawable.shape_btn_unclickable);
                    img_delete_sec.setVisibility(View.GONE);
                } else {
                    img_delete_sec.setVisibility(View.VISIBLE);
                    if (!TextUtils.isEmpty(ed_telphone.getText().toString())) {
                        tv_login.setClickable(true);
                        tv_login.setBackgroundResource(R.drawable.selector_btn_clickable);
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_delete_phone://清空账号
                ed_telphone.setText("");
                break;
            case R.id.img_delete_sec://清空密码或者验证码
                ed_security_code.setText("");
                break;
            case R.id.tv_fastlogin://快速登陆
                if (method == 0) {
                    return;
                }
                ed_security_code.setInputType(InputType.TYPE_CLASS_TEXT);
                tv_fastlogin.setBackgroundResource(R.mipmap.round_red_left);
                tv_fastlogin.setTextColor(getResources().getColor(R.color.white));
                tv_accountlogin.setBackgroundResource(R.mipmap.round_white_right);
                tv_accountlogin.setTextColor(getResources().getColor(R.color.title_red));
                tv_getSecurityCode.setVisibility(View.VISIBLE);
                ed_security_code.setHint("请输入验证码");
                tv_login.setText("验证登录");
                method = 0;
                ed_security_code.setText(verification);
                break;
            case R.id.tv_accountlogin://账号登陆
                if (method == 1) {
                    return;
                }
//                LogUtils.e("Type:"+ed_security_code.getInputType());
                ed_security_code.setInputType(129);//设置输入方式为密码
                tv_fastlogin.setBackgroundResource(R.mipmap.round_white_left);
                tv_fastlogin.setTextColor(getResources().getColor(R.color.title_red));
                tv_accountlogin.setBackgroundResource(R.mipmap.round_red_right);
                tv_accountlogin.setTextColor(getResources().getColor(R.color.white));
                tv_getSecurityCode.setVisibility(View.GONE);
                ed_security_code.setHint("请输入密码");
                tv_login.setText("登录");
                method = 1;
                ed_security_code.setText(password);
                break;
            case R.id.tv_login://清空账号
                if (method == 0){
                    StringBuilder info = new StringBuilder();
                    info.
                            append("快速登录：\n手机号").append(ed_telphone.getText().toString()).
                            append("\n验证码：").append(ed_security_code.getText().toString());
                    ToastUtil.showToast(info.toString());
                }else {
                    StringBuilder info = new StringBuilder();
                    info.
                            append("账号登录：\n账号").append(ed_telphone.getText().toString()).
                            append("\n密码：").append(ed_security_code.getText().toString());
                    ToastUtil.showToast(info.toString());
                }
                break;
        }
    }
}