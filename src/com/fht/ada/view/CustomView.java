package com.fht.ada.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 使用VelocityTracker计算手指滑动速度，并且绘制运动轨迹
 * 
 * @author Fht
 *
 */
public class CustomView extends View {
	private static final String TAG = "VelocityTracker";
	public List<Float> data = new ArrayList<Float>();
	Paint mPaint = new Paint();

	public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint.setColor(0xffed1941);
		mPaint.setTextSize(10);
	}

	public CustomView(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Log.i(TAG, "onDraw(Canvas canvas");
		float pts[] = new float[data.size()];
		for (int i = 0; i < pts.length; i++) {
			pts[i] = data.get(i);
		}
		canvas.drawLines(pts, mPaint);
	}

}
