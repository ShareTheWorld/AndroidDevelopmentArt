package com.fht.ada.touch;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

public class MyHorizontalScrollView extends HorizontalScrollView {
	public ScrollerCompleteListener mScrollerCompleteListener;

	public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public MyHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyHorizontalScrollView(Context context) {
		super(context);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	protected void onDraw(Canvas canvas) {
	}

	public void setOnScrollerCompleteListener(ScrollerCompleteListener completeListener) {
		this.mScrollerCompleteListener=completeListener;
	}

	interface ScrollerCompleteListener {
		public void scroolerComplet();
	}
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		if (mScrollerCompleteListener != null) {
			mScrollerCompleteListener.scroolerComplet();
		}
		super.onScrollChanged(l, t, oldl, oldt);
	}
}
