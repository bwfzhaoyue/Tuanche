package com.bwf.tuanche.ui.choosecar.entity.typelist;

import com.bwf.framwork.base.BaseBean;
import com.bwf.framwork.utils.LogUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * author zhaoyue
 * Description
 */
public class TypeListResult extends BaseBean {
    public List<TypeBean> result;

    @Override
    public String toString() {
        return "TypeListResult{" +
                "result=" + result +
                "} " + super.toString();
    }

    /**
     * 返回分割后并且已排序号的集合，可以直接设置给适配器使用
     * @return
     */
    public List<TypeBeanGroup> getSeparatedList(){
        if (result == null||result.isEmpty())//判空
            return null;

        List<TypeBeanGroup> groups = new ArrayList<>();
        for (int i = 0;i < result.size();i++){
            boolean contains = false;
            for (int j = 0;j < groups.size();j++){
                //如果groups中已有该penname 则break，继续在result中寻找下一个TypeBean
                if (groups.get(j).penname.equals(result.get(i).penname)){
                    contains = true;
                    groups.get(j).typeBeanList.add(result.get(i));//如果找到了，将第i个添加进去
                    break;
                }
            }
            //如果groups中没有当前的penname的group，则创建一个并添加到groups中，
            //但其中的List只初始化，不添加元素
            if (!contains){
                TypeBeanGroup group = new TypeBeanGroup();
                group.penname = result.get(i).penname;
                group.typeBeanList = new ArrayList<>();
                group.typeBeanList.add(result.get(i));//创建新的之后将第i个添加进去
                groups.add(group);
            }
        }

        //分别将一级列表和二级列表都排序
        Collections.sort(groups);
        for (int i = 0;i<groups.size();i++){
            Collections.sort(groups.get(i).typeBeanList);
            LogUtils.e("groups的第i个penname："+groups.get(i).penname);
        }
        LogUtils.e("groups.size:"+groups.size());
        LogUtils.e("result.size:"+result.size());

        return groups;
    }
}
