package com.fht.ada.view;

import com.fht.ada.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class VelocityTrackerActivity extends Activity {
	private static final String TAG="VelocityTracker";
	private TextView mShowVelocity;
	private CustomView mCustomView;
	private VelocityTracker mVelocityTracker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_velocity_tracker);
		mShowVelocity = (TextView) this.findViewById(R.id.show_velocity);
		mCustomView = (CustomView) this.findViewById(R.id.custom_view);
		mVelocityTracker = VelocityTracker.obtain();

		mCustomView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				//计算速度
				mVelocityTracker.addMovement(event);
				mVelocityTracker.computeCurrentVelocity(1000);
				float vx = mVelocityTracker.getXVelocity();
				float vy = mVelocityTracker.getYVelocity();
				Log.i(TAG, vx+"   "+vy);
				mShowVelocity.setText("vx : "+vx+"\nvy : "+vy);
				//存储数据，让CustomView进行绘制
				mCustomView.data.add(event.getX());
				mCustomView.data.add(event.getY());
				if (mCustomView.data.size() > 100) {
					mCustomView.data.remove(0);
					mCustomView.data.remove(0);
				}
				mCustomView.invalidate();
				return true;
			}
		});
	}
}
