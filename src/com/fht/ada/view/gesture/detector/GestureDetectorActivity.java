package com.fht.ada.view.gesture.detector;

import com.fht.ada.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnContextClickListener;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class GestureDetectorActivity extends Activity {
	private static final String TAG = "GestureDetector";
	private GestureDetector mGestureDetector;
	private TextView mTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_gesture_detector);
		mGestureDetector = new GestureDetector(getApplicationContext(), new GestureDecectorListener());
		// 解决长按屏幕过后无法拖动的现象
		mGestureDetector.setIsLongpressEnabled(false);
		mTextView = (TextView) this.findViewById(R.id.show_gesture_detector);
		mTextView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				boolean b = mGestureDetector.onTouchEvent(event);
				return true;
			}
		});
	}

	class GestureDecectorListener implements OnGestureListener, OnDoubleTapListener {
		int count = 0;
		@Override
		public boolean onDown(MotionEvent e) {
			// 手指触摸屏幕的时候回产生一个down事件，也就是手指按下
			Log.i(TAG, "onDown(MotionEvent e)");
			String mid = mTextView.getText().toString();
			mTextView.setText((count++) + "   onDown(MotionEvent e)\n" + mid);
			return false;
		}

		@Override
		public void onShowPress(MotionEvent e) {
			// 这个也是手指刚刚按下的时候，也是有ACTION_DOWN出发的，它强调的是手指按下没有抬起或滑动的状态
			Log.i(TAG, "onShowPress(MotionEvent e)");
			String mid = mTextView.getText().toString();
			mTextView.setText((count++) + "   onShowPress(MotionEvent e)\n" + mid);
		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// 手指按下，并且抬起来，也就是我们说的单击
			Log.i(TAG, "onSingleTapUp(MotionEvent e)");
			String mid = mTextView.getText().toString();
			mTextView.setText((count++) + "   onSingleTapUp(MotionEvent e)\n" + mid);
			return false;
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			// 手指按下，接着在屏幕上面滑动，触发的一个拖动事件，有一个Action_DOWN 和多个 Action_MOVE
			Log.i(TAG, "onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)");
			String mid = mTextView.getText().toString();
			mTextView.setText((count++)
					+ "   onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)\n" + mid);
			return false;
		}

		@Override
		public void onLongPress(MotionEvent e) {
			// 手指按下，并且在屏幕上面停留一定的时间 也就是我们所说的长按事件
			Log.i(TAG, "onLongPress(MotionEvent e)");
			String mid = mTextView.getText().toString();
			mTextView.setText((count++) + "   onLongPress(MotionEvent e)\n" + mid);
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			// 用手指在屏幕上面快速的滑动一下
			Log.i(TAG, "onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)");
			String mid = mTextView.getText().toString();
			mTextView.setText(
					(count++) + "   onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)\n" + mid);
			return false;
		}

		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			// 严格的单击事件
			Log.i(TAG, "onSingleTapConfirmed(MotionEvent e) ");
			String mid = mTextView.getText().toString();
			mTextView.setText((count++) + "   onSingleTapConfirmed(MotionEvent e)\n" + mid + mid);
			return false;
		}

		@Override
		public boolean onDoubleTap(MotionEvent e) {
			// 双击事件 它可能和onSingleTapConfirmed共存
			Log.i(TAG, "onDoubleTap(MotionEvent e)");
			String mid = mTextView.getText().toString();
			mTextView.setText((count++) + "   onDoubleTap(MotionEvent e)\n" + mid);
			return false;
		}

		@Override
		public boolean onDoubleTapEvent(MotionEvent e) {
			// 双击事件，在用户双击的事件里，ACTION_DOWN,ACTION_MOVE,ACTION_UP都会别触发
			Log.i(TAG, "onDoubleTapEvent(MotionEvent e)");
			String mid = mTextView.getText().toString();
			mTextView.setText((count++) + "   onDoubleTapEvent(MotionEvent e)\n" + mid);
			return false;
		}

	}
}
