package com.fht.ada.messenger;

import com.fht.ada.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ClientActivity extends Activity {
	private static final String TAG = "Messenger";
	Messenger mMessenger;
	Messenger mReplyMessenger = new Messenger(new MessengerHander());
	private static EditText mEditText;
	public static TextView mTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_client_messenger);
		Intent service = new Intent(this, RemoteService.class);
		this.bindService(service, mConn, Context.BIND_AUTO_CREATE);
		mEditText = (EditText) findViewById(R.id.input_message);
		mTextView = (TextView) findViewById(R.id.show_message);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.unbindService(mConn);
	}

	ServiceConnection mConn = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName name) {
			mMessenger = null;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mMessenger = new Messenger(service);
		}
	};

	public void sendMessage(View view) {
		String strMsg = mEditText.getText().toString();
		String mid = mTextView.getText().toString();
		String str = "###################\n client-->service:\n " + strMsg + "\n###################";
		mTextView.setText(str + "\n\n\n" + mid);
		Message msg = Message.obtain(null, 0);
		Bundle data = new Bundle();
		data.putString("msg", strMsg);
		msg.setData(data);
		msg.replyTo = mReplyMessenger;
		try {
			if (mMessenger != null) {
				mMessenger.send(msg);
			} else {
				Log.i(TAG, "远程连接断开");
			}
		} catch (RemoteException e) {
			Log.i(TAG, "远程连接出问题");
			e.printStackTrace();
		}
	}
}
