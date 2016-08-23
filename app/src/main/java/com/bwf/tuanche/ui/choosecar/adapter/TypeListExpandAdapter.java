package com.bwf.tuanche.ui.choosecar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.bwf.framwork.image.ImageLoader;
import com.bwf.framwork.utils.LogUtils;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.choosecar.entity.hotcar.HotCarTypeBean;
import com.bwf.tuanche.ui.choosecar.entity.typelist.TypeBean;
import com.bwf.tuanche.ui.choosecar.entity.typelist.TypeBeanGroup;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * author zhaoyue
 * Description:品牌选车二级列别哦
 */
public class TypeListExpandAdapter extends BaseExpandableListAdapter{

    private Context context;

    private List<TypeBeanGroup> groupList;

    private ImageLoader imageLoader;

    private ExpandCallBack callBack;

    {
        imageLoader = ImageLoader.getInstance();
    }

    public void setCallBack(ExpandCallBack callBack) {
        this.callBack = callBack;
    }

    public TypeListExpandAdapter(Context context) {
        this.context = context;
    }

    public void setGroupList(List<TypeBeanGroup> groupList) {
        this.groupList = groupList;
    }

    @Override
    public int getGroupCount() {
        return groupList==null?0:groupList.size();
    }

    @Override
    public int getChildrenCount(int i) {

        return groupList.get(i).typeBeanList==null?0:groupList.get(i).typeBeanList.size();
    }

    @Override
    public Object getGroup(int i) {
        return groupList==null?null:groupList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {

        return groupList.get(i).typeBeanList==null?null:groupList.get(i).typeBeanList.get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
//        int id = 0;
//        for (int k = 0;k<groupList.size();k++){
//            if (groupList.get(k).typeBeanList==null)
//                break;
//            id+=groupList.get(k).typeBeanList.size();
//        }
//        return id+i1;
        return i*10+i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        view = View.inflate(context, R.layout.item_parent_letter,null);
        TextView tv_letter = (TextView) view.findViewById(R.id.tv_letter);
        tv_letter.setText(groupList.get(i).penname);
//        LogUtils.e("getGroupView===>"+groupList.get(i).penname);
        return view;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View convertView, ViewGroup viewGroup) {
        ChildViewHolder viewHolder = null;
        if (convertView ==null){
            viewHolder = new ChildViewHolder();
            convertView = View.inflate(context,R.layout.item_child_brand,null);
            viewHolder.img_elv_logo = (SimpleDraweeView) convertView.findViewById(R.id.img_elv_logo);
            viewHolder.tv_elv_name = (TextView) convertView.findViewById(R.id.tv_elv_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }

        imageLoader.disPlayImage(viewHolder.img_elv_logo,groupList.get(i).typeBeanList.get(i1).logo);
        viewHolder.tv_elv_name.setText(groupList.get(i).typeBeanList.get(i1).name);
//        LogUtils.e("getChildView===>"+groupList.get(i).typeBeanList.get(i1).name);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callBack!=null)
                    callBack.onChildClick(groupList.get(i).typeBeanList.get(i1));
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public class ChildViewHolder{

        public SimpleDraweeView img_elv_logo;

        public TextView tv_elv_name;

    }

    public interface ExpandCallBack{
        void onChildClick(TypeBean bean);
    }

}
