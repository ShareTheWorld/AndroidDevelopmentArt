package com.fht.ada.touch;

import com.fht.ada.R;
import com.fht.ada.touch.MyHorizontalScrollView.ScrollerCompleteListener;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.TextView;

public class TouchFragmentActivity extends FragmentActivity {
	private static final String TAG = "Touch";
	private TextView mIndicate;// 指示当前的界面
	private int mScreenWidth;
	private FrameLayout.LayoutParams mIndicateParentParams;
	private FrameLayout mPageOneFragment;
	private FrameLayout mPageTwoFragment;
	private FrameLayout mPageThreeFragment;
	private MyHorizontalScrollView mHorizontalScrollView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_fragment_touch);
		mIndicate = (TextView) this.findViewById(R.id.indicate);
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		mScreenWidth = metric.widthPixels; // 屏幕宽度(像素)
		mIndicateParentParams = (FrameLayout.LayoutParams) (mIndicate.getLayoutParams());
		mIndicateParentParams.width = (int) (mScreenWidth / 3);
		mIndicate.requestLayout();
		mPageOneFragment = (FrameLayout) this.findViewById(R.id.fragment_page_one);
		mPageTwoFragment = (FrameLayout) this.findViewById(R.id.fragment_page_two);
		mPageThreeFragment = (FrameLayout) this.findViewById(R.id.fragment_page_three);
		mPageOneFragment.getLayoutParams().width = mScreenWidth;
		mPageOneFragment.requestLayout();

		mPageTwoFragment.getLayoutParams().width = mScreenWidth;
		mPageTwoFragment.requestLayout();

		mPageThreeFragment.getLayoutParams().width = mScreenWidth;
		mPageThreeFragment.requestLayout();
		mHorizontalScrollView = (MyHorizontalScrollView) this.findViewById(R.id.horizontal_scroll_view);
		mHorizontalScrollView.setOnTouchListener(new OnTouchListener() {
			float startX;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (startX == 0) {// event种没有Action_Down事件传递进来
					startX = event.getRawX();
				}
				int scrollX = mHorizontalScrollView.getScrollX();
				float x = event.getRawX();
				// 当滑动距离比较短的时候才，判断是往哪个方向发动的
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (Math.abs(x - startX) < mScreenWidth / 2) {
						Log.i(TAG, "detaX=" + (x - startX));
						if (x - startX > mScreenWidth / 6) {
							scrollX -= mScreenWidth / 2;
						}
						if (x - startX < -mScreenWidth / 6) {
							scrollX += mScreenWidth / 2;
						}
					}
					if (scrollX < (mScreenWidth / 2)) {
						mHorizontalScrollView.smoothScrollTo(0, 0);
					} else if (scrollX > (mScreenWidth / 2) && scrollX < (mScreenWidth / 2 + mScreenWidth)) {
						mHorizontalScrollView.smoothScrollTo(mScreenWidth, 0);
					} else {
						mHorizontalScrollView.smoothScrollTo(mScreenWidth * 2, 0);
					}
					startX = 0;
					return true;
				}
				return false;
			}
		});
		mHorizontalScrollView.setOnScrollerCompleteListener(new ScrollerCompleteListener() {

			@Override
			public void scroolerComplet() {
				int x = mHorizontalScrollView.getScrollX();
				setIndicatePosition((int) (x / 3));
			}
		});
	}

	// 设置页面指示器的位置
	public void setIndicatePosition(int position) {
		mIndicateParentParams.leftMargin = position;
		mIndicate.requestLayout();
	}

	public void moveIndicate(int x) {
		mIndicateParentParams.leftMargin += x;
		mIndicate.requestLayout();
	}

	public void switchPage(View view) {
		switch (view.getId()) {
		case R.id.page_one:
			setIndicatePosition(0);
			mHorizontalScrollView.smoothScrollTo(0, 0);
			Log.i(TAG, "switch page one");
			break;
		case R.id.page_two:
			setIndicatePosition((int) (mScreenWidth / 3));
			mHorizontalScrollView.smoothScrollTo(mScreenWidth, 0);
			Log.i(TAG, "switch page two");
			break;
		case R.id.page_three:
			setIndicatePosition((int) (mScreenWidth * 2 / 3));
			mHorizontalScrollView.smoothScrollTo(mScreenWidth * 2, 0);
			Log.i(TAG, "switch page three");
			break;
		}
	}

}
