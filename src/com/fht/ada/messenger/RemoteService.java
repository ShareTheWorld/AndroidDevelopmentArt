package com.fht.ada.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Messenger;

public class RemoteService extends Service {
	private Messenger mMessenger =new Messenger(new MessengerHander());
	@Override
	public IBinder onBind(Intent intent) {
		return mMessenger.getBinder();
	}
}
