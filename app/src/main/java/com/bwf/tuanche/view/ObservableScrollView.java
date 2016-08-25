package com.bwf.tuanche.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * author zhaoyue
 * Description
 */
public class ObservableScrollView extends ScrollView {
    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private OnScrollChangeListener onScrollChangeListener = null;

    public OnScrollChangeListener getOnScrollChangeListener() {
        return onScrollChangeListener;
    }

    public void setOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
        this.onScrollChangeListener = onScrollChangeListener;
    }


    public interface  OnScrollChangeListener{
        void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy);
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (onScrollChangeListener != null) {
            onScrollChangeListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }


}
