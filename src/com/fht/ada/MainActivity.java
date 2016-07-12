package com.fht.ada;

import com.fht.ada.aidl.ClientActivity;
import com.fht.ada.start.mode.StandardActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String TAG = "hongtao.fu";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i(TAG, "onCreate");

	}

	public void startDemo(View view) {
		Log.i(TAG, "startDemo");
		switch (view.getId()) {
		case R.id.start_start_mode_demo:
			startActivity(StandardActivity.class);
			break;
		case R.id.start_client_aidl:
			startActivity(com.fht.ada.aidl.ClientActivity.class);
			break;
		case R.id.start_client_messenger:
			startActivity(com.fht.ada.messenger.ClientActivity.class);
			break;
		case R.id.start_client_socket:
			startActivity(com.fht.ada.socket.ClientActivity.class);
			break;
		case R.id.start_velocity_tracker:
			startActivity(com.fht.ada.view.VelocityTrackerActivity.class);
			break;
		case R.id.start_geturer_detector:
			startActivity(com.fht.ada.view.gesture.detector.GestureDetectorActivity.class);
			break;
		case R.id.start_scroller:
			startActivity(com.fht.ada.scroller.ScrollerActivity.class);
			break;

		}
	}

	public void startActivity(Class c) {
		Intent intent = new Intent(this, c);
		intent.setPackage(getPackageName());
		this.startActivity(intent);

	}
}
