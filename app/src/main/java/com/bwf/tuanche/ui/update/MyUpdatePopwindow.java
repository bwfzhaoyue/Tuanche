package com.bwf.tuanche.ui.update;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bwf.framwork.base.BaseBean;
import com.bwf.framwork.http.HttpCallBack;
import com.bwf.framwork.http.HttpHelper;
import com.bwf.framwork.utils.DisplayUtil;
import com.bwf.framwork.utils.ToastUtil;
import com.bwf.framwork.utils.UrlUtils;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.update.bean.ResultUpdateBean;

/**
 * Created by che on 2016/8/23
 * Description:.
 */
public class MyUpdatePopwindow extends PopupWindow implements View.OnClickListener,Handler.Callback{

    private TextView tv_update_version_num;//版本号
    private TextView tv_update_context;//更新的内容
    private ImageView img_close;//右上角关闭
    private ImageView img_update_now;//立即更新
    private ResultUpdateBean resultUpdateBean;
    private String url;
    private Context context;
    private Handler handler;
    private int now_versionCode,online_versionCode;

    public MyUpdatePopwindow(Context context) {
        this(context,null);
        this.context = context;
        initView(context);
    }

    public MyUpdatePopwindow(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyUpdatePopwindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void initView(Context context){
        View view = View.inflate(context, R.layout.update_version,null);//设置布局文件

        //popWindow相关设置
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(DisplayUtil.getDensity_Height(context));
        this.setFocusable(true);//设置PopupWindow的焦点
        this.setOutsideTouchable(false);
//        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);//设置弹出窗体需要软键盘
        this.setBackgroundDrawable(new BitmapDrawable());

        tv_update_version_num = (TextView) view.findViewById(R.id.tv_update_version_num);
        tv_update_context = (TextView) view.findViewById(R.id.tv_update_context);
        img_close = (ImageView) view.findViewById(R.id.img_close);
        img_update_now = (ImageView) view.findViewById(R.id.img_update_now);

        img_close.setOnClickListener(this);
        img_update_now.setOnClickListener(this);
        getUpdateData();
        getNowVersionCode(context);
    }



    //从网络请求版本更新的内容
    public void getUpdateData(){
        HttpHelper.getUpdateVersionData(UrlUtils.VERSION_UPDATE, new HttpCallBack<ResultUpdateBean>() {
            @Override
            public void onSuccess(ResultUpdateBean resultUpdateBean) {
                String version_num = String.format(context.getString(R.string.update_version_num),""+resultUpdateBean.result.versionName);
                tv_update_version_num.setText(version_num);
                tv_update_context.setText(resultUpdateBean.result.description);
                url = resultUpdateBean.result.url;
                online_versionCode =Integer.valueOf(resultUpdateBean.result.versionCode) ;
            }
            @Override
            public void onFail(String errMsg) {
                ToastUtil.showToastLong("获取版本更新信息失败");
            }
        });
    }

    /**
     * 显示popwindow
     *
     * @param view
     */
    public void showPopWindow(View view) {
        if (!isShowing()) {
            if(now_versionCode!=online_versionCode)
//            this.showAsDropDown(view);//显示在view的下方
                this.showAtLocation(view,Gravity.CENTER, 0, 0);//可以显示在指定view的指定位置
        }
    }

    /**
     * 结束popwindow
     */
    public void dismissPopWindow() {
        handler = new Handler(this);
        handler.sendEmptyMessageDelayed(0, 1000);
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (this.context != null && this.isShowing()) {
            this.dismiss();
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_close://右上角关闭
                dismissPopWindow();
//                this.dismiss();
                break;
            case R.id.img_update_now://立即更新
//                ToastUtil.showToast("正在下载更新，请稍等...");
//                webView.setVisibility(View.VISIBLE);
//                webView.loadUrl(url);//此处只能加载url网页地址，并不能直接加载下载地址

//                this.dismiss();
                //Android跳转浏览器打开URL
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
                dismissPopWindow();
                break;

        }
    }


    public int getNowVersionCode(Context context){
//        now_versionCode = 0;
//        now_versionCode = context.getPackageManager().getPackageInfo("com.bwf.tuanche.ui.update",0).versionCode;
       try{
           now_versionCode = 0;
           //得到当前版本号
           now_versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(),0).versionCode;

       }catch (Exception e){
            e.printStackTrace();
       }
        return now_versionCode;
    }
}
