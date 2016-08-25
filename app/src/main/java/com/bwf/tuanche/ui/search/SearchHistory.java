package com.bwf.tuanche.ui.search;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.mainpager.entity.SearchHistoryBean;

import java.util.List;

/**
 * Created by zengqiang on 2016/8/24.
 * Description:Tuanche
 */
public class SearchHistory extends BaseAdapter {
    public List<SearchHistoryBean> strings;

    private Context context;

    public SearchHistory(Context context) {
        this.context = context;
    }




    @Override
    public int getCount() {
        return strings == null ? 0 : strings.size();
    }

    @Override
    public Object getItem(int i) {
        return strings == null ? null : strings.get(strings.size() - i - 1);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SearchHistoryViewHolder viewHolder;
        if (view == null) {
            viewHolder = new SearchHistoryViewHolder();
            view = View.inflate(context, R.layout.search_history_adapter, null);
            viewHolder.content = (TextView) view.findViewById(R.id.content);
            view.setTag(viewHolder);
        } else
            viewHolder = (SearchHistoryViewHolder) view.getTag();
        viewHolder.content.setText(strings.get(strings.size() - i - 1).content);
        return view;
    }

    public void setDatas(List<SearchHistoryBean> datas) {
        this.strings = datas;
    }

    public class SearchHistoryViewHolder {
        public TextView content;
    }
}
