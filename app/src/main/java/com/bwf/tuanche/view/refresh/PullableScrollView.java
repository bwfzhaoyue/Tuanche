package com.bwf.tuanche.view.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class PullableScrollView extends ScrollView implements Pullable
{
	private boolean canRefresh;//能下拉刷新
	private boolean canLoadMore;//能加载更多

	public void setCanRefresh(boolean canRefresh) {
		this.canRefresh = canRefresh;
	}

	public void setCanLoadMore(boolean canLoadMore) {
		this.canLoadMore = canLoadMore;
	}

	public PullableScrollView(Context context)
	{
		super(context);
	}

	public PullableScrollView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public PullableScrollView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	@Override
	public boolean canPullDown()
	{
		if (!canRefresh)
			return false;

		if (getScrollY() == 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean canPullUp()
	{
		if (!canLoadMore)
			return false;

		if (getScrollY() >= (getChildAt(0).getHeight() - getMeasuredHeight()))
			return true;
		else
			return false;
	}

}
