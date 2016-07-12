package com.fht.ada.scroller;

import com.fht.ada.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Scroller;
import android.widget.TextView;

public class ScrollerActivity extends Activity {
	private static final String TAG = "Scroller";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_scroller);

	}

	public void startScroller(View view) {
		int x = (int) (Math.random() * 600 - 300);
		int y = (int) (Math.random() * 600 - 300);
		ScrollerView scrollerView = (ScrollerView) view;
		scrollerView.smoothScrollT(x, y,500);
	}

}
