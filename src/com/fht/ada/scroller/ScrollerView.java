package com.fht.ada.scroller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Scroller;

public class ScrollerView extends View {
	private Scroller mScroller;
	Paint paint = new Paint(Color.RED);

	public ScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public ScrollerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mScroller = new Scroller(context);

	}

	public ScrollerView(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		paint.setColor((int) (0xffd71345 * Math.random()));
		canvas.drawRect(300, 400, 400, 500, paint);
	}

	public void smoothScrollT(int destX, int destY, int duration) {
		int scrollX = getScrollX();
		int deltaX = destX - scrollX;
		int scrollY = getScrollY();
		int deltaY = destY - scrollY;
		mScroller.startScroll(scrollX, scrollY, deltaX, deltaY, duration);
		invalidate();
	}

	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			postInvalidate();
		}

	}
}
