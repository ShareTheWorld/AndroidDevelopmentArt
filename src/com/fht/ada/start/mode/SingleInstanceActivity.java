package com.fht.ada.start.mode;

import com.fht.ada.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SingleInstanceActivity extends Activity {
	public static final String TAG="ActivityStratMode";
	private TextView mShowStack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_mode);
	}

	public void startModeActivity(View view) {
		switch (view.getId()) {
		case R.id.standard:
			startActivity(StandardActivity.class);
			break;
		case R.id.single_top:
			startActivity(SingleTopActivity.class);
			break;
		case R.id.singele_task:
			startActivity(SingleTaskActivity.class);
			break;
		case R.id.single_instace:
			startActivity(SingleInstanceActivity.class);
			break;
		}
	}

	public void startActivity(Class c) {
		Intent intent = new Intent(this, c);
		intent.setPackage(getPackageName());
		this.startActivity(intent);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mShowStack =(TextView) this.findViewById(R.id.show_stack);
		Data.mCurrentTextView=mShowStack;
		String s=Data.stack.toString();
		String mid="";
		for(int i=0;i<Data.stack.size();i++){
			mid=i+"  "+Data.stack.get(i)+"\n"+ mid;
		}
		mShowStack.setText("SingleInstanceActivity");
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		String mid = "";
		for (int i = 0; i < Data.stack.size(); i++) {
			mid = i + "  " + Data.stack.get(i) + "\n" + mid;
		}
		Data.mCurrentTextView.setText(mid);
	}
}
