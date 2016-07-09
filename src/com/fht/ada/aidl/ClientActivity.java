package com.fht.ada.aidl;

import java.util.List;

import com.fht.ada.R;
import com.fht.ada.aidl.BookManagerService.BookManager;
import com.fht.ada.aidl.IBookManager;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ClientActivity extends Activity {
	private static final String TAG = "AIDL";
	private EditText mEditText;
	private TextView mTextView;
	private Intent mContectBookManagerService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_client_aidl);
		mEditText = (EditText) findViewById(R.id.edit_book_name);
		mTextView = (TextView) findViewById(R.id.show_all_book);
		mContectBookManagerService = new Intent(this, BookManagerService.class);
	}

	@Override
	protected void onStart() {
		super.onStart();
		this.bindService(mContectBookManagerService, mConn, Context.BIND_AUTO_CREATE);
	}

	public void addBook(View view) {
		Log.i(TAG, "addBook(View view) ");
		String bookName = mEditText.getText().toString();
		if (bookName != null && "".equals(bookName.trim())) {
			Toast.makeText(getApplicationContext(), "书名为空,不能添加", Toast.LENGTH_SHORT).show();
			return;
		}
		try {
			mConn.mBookManager.putBook(mEditText.getText().toString());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		Toast.makeText(getApplicationContext(), "图书添加成功", Toast.LENGTH_SHORT).show();
	}

	public void queryAllBooks(View view) {
		Log.i(TAG, "queryAllBooks(View view) ");
		List<String> books = null;
		try {
			books = mConn.mBookManager.getAllBook();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (books != null) {
			String mid = "";
			for (int i = 0; i < books.size(); i++) {
				mid += i + "  " + books.get(i) + "\n";
			}
			mTextView.setText(mid);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.unbindService(mConn);
	}

	private Connection mConn = new Connection();

	class Connection implements ServiceConnection {
		private IBookManager mBookManager;

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			if (service == null) {
				Log.i(TAG, "service is null");
				return;
			}
			mBookManager = BookManager.Stub.asInterface(service);
		};
	};
}
