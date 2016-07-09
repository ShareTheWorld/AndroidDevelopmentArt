package com.fht.ada.aidl;

import java.util.ArrayList;
import java.util.List;

import com.fht.ada.aidl.IBookManager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class BookManagerService extends Service {
	private BookManager mBookManager = new BookManager();
	private List<String> books = new ArrayList<String>();

	@Override
	public IBinder onBind(Intent intent) {
		return mBookManager;
	}

	class BookManager extends IBookManager.Stub {
		@Override
		public List<String> getAllBook() throws RemoteException {
			return books;
		}

		@Override
		public void putBook(String name) throws RemoteException {
			books.add(name);
		}

	}

}
