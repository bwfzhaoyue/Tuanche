package com.bwf.tuanche.ui.citychoice.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.citychoice.bean.OpenCitysBean;

import java.util.List;

/**
 * Created by che on 2016/8/17
 * Description:.
 */
public class CityChoiceAdapter extends BaseAdapter{
    private Context context;
    private List<OpenCitysBean> openCitysBeanList;


    public CityChoiceAdapter(Context context) {
        this.context = context;
    }

    public CityChoiceAdapter(Context context, List<OpenCitysBean> openCitysBeanList) {
        this.context = context;
        this.openCitysBeanList = openCitysBeanList;
    }

    @Override
    public int getCount() {
        return openCitysBeanList == null ? 0:openCitysBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return openCitysBeanList == null ? null:openCitysBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        ViewHodler viewHodler = null;
        if(convertView == null){
            viewHodler = new ViewHodler();
            convertView = View.inflate(context, R.layout.city_items,null);
            viewHodler.tv_cityitems = (TextView) convertView.findViewById(R.id.tv_cityitems);
            convertView.setTag(viewHodler);
        }else{
            viewHodler = (ViewHodler) convertView.getTag();
        }
        OpenCitysBean openCitysBean = openCitysBeanList.get(position);
        viewHodler.tv_cityitems.setText(openCitysBean.name);

        return convertView;
    }

    private class ViewHodler{
        TextView tv_cityitems;
    }
}
