package com.bwf.tuanche.ui.choosecar;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.base.BaseBean;
import com.bwf.framwork.http.HttpCallBack;
import com.bwf.framwork.http.HttpHelper;
import com.bwf.framwork.utils.IntentUtils;
import com.bwf.framwork.utils.LogUtils;
import com.bwf.framwork.utils.ToastUtil;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.choosecar.adapter.BosRecyclerAdapter;
import com.bwf.tuanche.ui.choosecar.adapter.HotTypeRecyclerAdapter;
import com.bwf.tuanche.ui.choosecar.adapter.IndexAdapter;
import com.bwf.tuanche.ui.choosecar.adapter.LevelRecyclerAdapter;
import com.bwf.tuanche.ui.choosecar.adapter.SeriesRecyclerAdapter;
import com.bwf.tuanche.ui.choosecar.adapter.TypeListExpandAdapter;
import com.bwf.tuanche.ui.choosecar.entity.condition.ConditionResult;
import com.bwf.tuanche.ui.choosecar.entity.hotcar.HotCarTypeBean;
import com.bwf.tuanche.ui.choosecar.entity.hotcar.HotTypeResult;
import com.bwf.tuanche.ui.choosecar.entity.typelist.TypeBean;
import com.bwf.tuanche.ui.choosecar.entity.typelist.TypeListResult;
import com.bwf.tuanche.view.ChooseCarPopWindow;
import com.bwf.tuanche.view.LoadingView;
import com.bwf.tuanche.view.refresh.RefreshTestActivity;

import java.util.ArrayList;

/**
 * 选车界面 包括品牌选车和条件选车
 */
public class ChooseCarActivity extends BaseActivity {

    private String cityId = "156";//城市ID 成都156

    private ImageView img_back,img_search;//左边返回 右边搜索

    private TextView tv_brand_choosecar,tv_condition_choosecar;//品牌选车和条件选车按钮

    private int current = 0;//当前在品牌选车为0，条件选车为1

    /**
     * 品牌选车上方热门和下方列表加载状态：
     * 1.正在加载
     * 2.加载完成
     * 3.加载失败
     */
    private int topState,bottomState;

    private LinearLayout ll_brand_content;//品牌选车

    private ScrollView sc_condition_content;//条件选车整个页面

    private LoadingView view_loading;//正在加载中的自定义View

    private RecyclerView recycler_hotcar;//热门品牌的RecyclerView

    private HotTypeRecyclerAdapter recyclerAdapter;//热门品牌的RecyclerView的适配器

    private RecyclerView recycler_bos,recycler_series,recycler_level;//条件选车-级别,国别,排量

    private BosRecyclerAdapter bosRecyclerAdapter;//级别适配器

    private SeriesRecyclerAdapter seriesRecyclerAdapter;//国别适配器

    private LevelRecyclerAdapter levelRecyclerAdapter;//排量适配器

    private Button bt_reset,bt_check;//底部重置和查看按钮

    private ExpandableListView exlv_brandlist;//二级展开列表

    private TypeListExpandAdapter expandAdapter;//耳机列表适配器

    private ChooseCarPopWindow popWindow;//点击车型弹窗
    private RelativeLayout rl_abovepop;//让popWindow显示在他之下

    private ListView lv_index;//索引列表

    private IndexAdapter indexAdapter;//索引列表适配器

    @Override
    public int getContentViewId() {
        return R.layout.activity_choose_car;
    }

    @Override
    public void beforeInitView() {
        IntentUtils.openActivity(this, RefreshTestActivity.class);
    }

    @Override
    public void initView() {
        img_back = findViewByIdNoCast(R.id.img_back);
        setToBack(img_back);
        img_search = findViewByIdNoCast(R.id.img_search);
        tv_brand_choosecar = findViewByIdNoCast(R.id.tv_brand_choosecar);
        tv_condition_choosecar = findViewByIdNoCast(R.id.tv_condition_choosecar);
        ll_brand_content = findViewByIdNoCast(R.id.ll_brand_content);
        sc_condition_content = findViewByIdNoCast(R.id.sc_condition_content);
        view_loading = findViewByIdNoCast(R.id.view_loading);
        recycler_hotcar = findViewByIdNoCast(R.id.recycler_hotcar);
        recycler_bos = findViewByIdNoCast(R.id.recycler_bos);
        recycler_series = findViewByIdNoCast(R.id.recycler_series);
        recycler_level = findViewByIdNoCast(R.id.recycler_level);
        exlv_brandlist = findViewByIdNoCast(R.id.exlv_brandlist);
        bt_reset = findViewByIdNoCast(R.id.bt_reset);
        bt_check = findViewByIdNoCast(R.id.bt_check);
        rl_abovepop = findViewByIdNoCast(R.id.rl_abovepop);
        lv_index = findViewByIdNoCast(R.id.lv_index);

        setOnClick(img_search,tv_brand_choosecar,tv_condition_choosecar,bt_reset,bt_check);
    }

    @Override
    public void initData() {

        popWindow = new ChooseCarPopWindow(this);

        //条件选车-级别
        recycler_bos.setLayoutManager(new GridLayoutManager(this,3));
        bosRecyclerAdapter = new BosRecyclerAdapter(this);
        recycler_bos.setAdapter(bosRecyclerAdapter);

        //条件选车-国别
        recycler_series.setLayoutManager(new GridLayoutManager(this,3));
        seriesRecyclerAdapter = new SeriesRecyclerAdapter(this);
        recycler_series.setAdapter(seriesRecyclerAdapter);

        //条件选车-国别
        recycler_level.setLayoutManager(new GridLayoutManager(this,3));
        levelRecyclerAdapter = new LevelRecyclerAdapter(this);
        recycler_level.setAdapter(levelRecyclerAdapter);

        //热门车型
        View headerView = View.inflate(this,R.layout.header_hotbrand,null);
        recycler_hotcar = (RecyclerView) headerView.findViewById(R.id.recycler_hotcar);
        recycler_hotcar.setLayoutManager(new GridLayoutManager(this,4));//表格布局 每行四个
        recyclerAdapter = new HotTypeRecyclerAdapter(this);//初始化热门车型适配器
        recycler_hotcar.setAdapter(recyclerAdapter);//设置适配器

        //索引列表
        indexAdapter = new IndexAdapter(this);
        lv_index.setAdapter(indexAdapter);
        lv_index.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                exlv_brandlist.setSelectedGroup(i-1);
            }
        });

        //热门品牌的RecyclerView点击回调
        recyclerAdapter.setCallBack(new HotTypeRecyclerAdapter.HotBrandCallBack() {
            @Override
            public void onHotBrandClick(HotCarTypeBean bean) {
                popWindow.setBrandId(bean.id,cityId);
                popWindow.showPopWindow(rl_abovepop);
            }
        });
        //品牌选车扩展列表
        exlv_brandlist.setGroupIndicator(null);
        exlv_brandlist.addHeaderView(headerView,null,true);
        exlv_brandlist.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            //设置Group的点击事件 屏蔽点击关闭
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,int groupPosition, long id) {
                return true;
            }
        });
        expandAdapter = new TypeListExpandAdapter(this);
        expandAdapter.setCallBack(new TypeListExpandAdapter.ExpandCallBack() {
            @Override
            public void onChildClick(TypeBean bean) {
                popWindow.setBrandId(bean.id,cityId);
                popWindow.showPopWindow(rl_abovepop);

            }
        });

        loadLeft();//首次进入的时候加载品牌选车


    }

    /**
     * 加载品牌选车
     */
    private void loadLeft(){
        view_loading.setOnLoad();
        topState = 1;
        bottomState = 1;
        //加载到热门车型数据以后刷新适配器
        HttpHelper.getHotCar(cityId, new HttpCallBack<HotTypeResult>() {
            @Override
            public void onSuccess(HotTypeResult result) {
                if (current == 1)
                    return;
                recyclerAdapter.setList(result.result.list);
                recyclerAdapter.notifyDataSetChanged();
                //判断加载状态
                topState = 2;
                if (bottomState==2||bottomState==3)
                    view_loading.setLoadingFinish();
            }

            @Override
            public void onFail(String errMsg) {
                if (current == 1)
                    return;
                ToastUtil.showToast("热门品牌列表加载失败："+errMsg);
                //判断加载状态
                topState=3;
                if (bottomState==2)
                    view_loading.setLoadingFinish();
                if (bottomState==3)
                    view_loading.setLoadFail();
            }
        });

        //加载品牌列表以后刷新适配器,并且可以加载右边的索引数据
        HttpHelper.getCarTypeList(cityId, new HttpCallBack<TypeListResult>() {
            @Override
            public void onSuccess(TypeListResult result) {
                if (current == 1)
                    return;
                expandAdapter.setGroupList(result.getSeparatedList());
                exlv_brandlist.setAdapter(expandAdapter);
                for (int i=0;i< result.getSeparatedList().size();i++){
                    exlv_brandlist.expandGroup(i);
                }

                //加载右边索引列表的数据
                ArrayList<String> indexList = new ArrayList<String>();
                indexList.add("*");
                for (int i = 0;i<result.getSeparatedList().size();i++){
                    indexList.add(result.getSeparatedList().get(i).penname);
                }
                indexAdapter.settList(indexList);
                indexAdapter.notifyDataSetChanged();

                //判断加载状态
                bottomState = 2;
                if (topState==2||topState==3)
                    view_loading.setLoadingFinish();


            }

            @Override
            public void onFail(String errMsg) {
                if (current == 1)
                    return;
                ToastUtil.showToast("品牌列表加载失败："+ errMsg);

                //判断加载状态
                bottomState=3;
                if (topState==2)
                    view_loading.setLoadingFinish();
                if (topState==3)
                    view_loading.setLoadFail();
            }
        });

    }

    /**
     * 加载条件选车
     */
    private void loadRight(){
        //加载条件选车数据----级别 国别
        HttpHelper.getConditionToChoose(new HttpCallBack<ConditionResult>() {
            @Override
            public void onSuccess(ConditionResult result) {
                if (current==0)
                    return;
                bosRecyclerAdapter.setList(result.result.bos);
                bosRecyclerAdapter.notifyDataSetChanged();

                seriesRecyclerAdapter.setList(result.result.series);
                seriesRecyclerAdapter.notifyDataSetChanged();

                levelRecyclerAdapter.setList(result.result.levle);
                levelRecyclerAdapter.notifyDataSetChanged();
                view_loading.setLoadingFinish();
            }

            @Override
            public void onFail(String errMsg) {
                if (current==0)
                    return;
                ToastUtil.showToast("条件加载失败："+ errMsg);
                view_loading.setLoadFail();
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
                loadLeft();
                tv_brand_choosecar.setTextColor(getResources().getColor(R.color.white));
                tv_brand_choosecar.setBackground(getResources().getDrawable(R.mipmap.round_red_left));

                tv_condition_choosecar.setTextColor(getResources().getColor(R.color.title_red));
                tv_condition_choosecar.setBackground(getResources().getDrawable(R.mipmap.round_white_right));

                ll_brand_content.setVisibility(View.VISIBLE);
                sc_condition_content.setVisibility(View.GONE);
                current = 0;
                break;
            case R.id.tv_condition_choosecar://条件选车按钮
                loadRight();
                tv_brand_choosecar.setTextColor(getResources().getColor(R.color.title_red));
                tv_brand_choosecar.setBackground(getResources().getDrawable(R.mipmap.round_white_left));

                tv_condition_choosecar.setTextColor(getResources().getColor(R.color.white));
                tv_condition_choosecar.setBackground(getResources().getDrawable(R.mipmap.round_red_right));

                ll_brand_content.setVisibility(View.GONE);
                sc_condition_content.setVisibility(View.VISIBLE);
                current = 1;
                break;
            case R.id.bt_reset://重置
                for (int i = 0;i < bosRecyclerAdapter.getItemCount();i++){
                    bosRecyclerAdapter.getList().get(i).isSelected = false;
                }
                bosRecyclerAdapter.notifyDataSetChanged();
                for (int i = 0;i < seriesRecyclerAdapter.getItemCount();i++){
                    seriesRecyclerAdapter.getList().get(i).isSelected = false;
                }
                seriesRecyclerAdapter.notifyDataSetChanged();
                for (int i = 0;i < levelRecyclerAdapter.getItemCount();i++){
                    levelRecyclerAdapter.getList().get(i).isSelected = false;
                }
                levelRecyclerAdapter.notifyDataSetChanged();
                break;
            case R.id.bt_check://查看
                StringBuilder info = new StringBuilder();
                info.append("==>级别信息：\n");
                for (int i = 0;i < bosRecyclerAdapter.getItemCount();i++){
                    if (bosRecyclerAdapter.getList().get(i).isSelected)
                        info.append(bosRecyclerAdapter.getList().get(i).name+"+");
                }
                info.append("\n==>国别信息：\n");
                for (int i = 0;i < seriesRecyclerAdapter.getItemCount();i++){
                    if (seriesRecyclerAdapter.getList().get(i).isSelected)
                        info.append(seriesRecyclerAdapter.getList().get(i).name+"+");
                }
                info.append("\n==>排量信息：\n");
                for (int i = 0;i < levelRecyclerAdapter.getItemCount();i++){
                    if (levelRecyclerAdapter.getList().get(i).isSelected)
                        info.append(levelRecyclerAdapter.getList().get(i).name+"+");
                }
                ToastUtil.showToast(info.toString());
                break;
        }
    }
}
