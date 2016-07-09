package com.fht.ada.messenger;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MessengerHander extends Handler {
	private static final String TAG = "Messenger";

	public void handleMessage(android.os.Message msg) {
		Messenger messenger = msg.replyTo;
		switch (msg.what) {
		case 0:// 收到了客户端发过来的消息，然后回复客户端
			Log.i(TAG, "################service####################");
			Log.i(TAG, "client-->service : " + msg.getData().getString("msg"));
			Message replyMesage = Message.obtain(null, 1);
			Bundle data = new Bundle();
			data.putString("msg", "我收到了你的消息，我会尽快处理你发送的消息 ");
			replyMesage.setData(data);
			try {
				messenger.send(replyMesage);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			Log.i(TAG, "################service####################");
			break;
		case 1:// 处理客户端回复的消息
			Log.i(TAG, "################client####################");
			Log.i(TAG, "service-->client : " + msg.getData().getString("msg"));
			Log.i(TAG, "################client####################");
			String strMsg = msg.getData().getString("msg");
			String mid = ClientActivity.mTextView.getText().toString();
			String str = "###################\n service-->client: \n" + strMsg + "\n###################";
			ClientActivity.mTextView.setText(str + "\n" + mid);
			break;
		}
	};

}
