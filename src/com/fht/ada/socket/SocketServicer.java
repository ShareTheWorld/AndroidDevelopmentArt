package com.fht.ada.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class SocketServicer extends android.app.Service {
	private static final String TAG = "Socket";
	private ServerSocket mServerSocket;
	private static final int port = 44534;
	private ExecutorService mExecutorServicePool;

	public SocketServicer() {
		try {
			mServerSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// new 线程池
		mExecutorServicePool = Executors.newFixedThreadPool(5);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		Log.i(TAG, "start SocketServicer");
		// 启动服务器的时候 我们就启动一个线程，里面有一个Socket等待远程的连接
		new Thread() {
			@Override
			public void run() {
				// 这里采用while循环，是为了可以让多个客户端一起连接进来
				while (true) {
					try {
						Log.i(TAG, "mServerSocket 服务已经启动，正在等待连接");
						Socket socket = mServerSocket.accept();// 代码运行到这里了，会等待客户端的连接
						// 我们把socket放进线程池中进行管理
						mExecutorServicePool.execute(new SocketHandler(socket));
						Log.i(TAG, "mServerSocket 连接上了一个");
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		}.start();
	}

	// SocketHandler用来处理每一个连接的socket
	class SocketHandler implements Runnable {
		private Socket mSocket;

		public SocketHandler(Socket socket) {
			mSocket = socket;
		}

		@Override
		public void run() {
			try {
				InputStream in = mSocket.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(in);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				OutputStream out = mSocket.getOutputStream();

				while (true) {
					String inputData = bufferedReader.readLine();
					String reply = mReplyText[point];
					SystemClock.sleep(1000);
					point++;
					point=point%mReplyText.length;
					out.write((reply + "\n").getBytes());
					out.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	int point = 0;
	String[] mReplyText = new String[] { "把喜欢两字去掉才真是造孽", "唉，毕竟不是亲生的……", "成鸡思汉", "嗯，给你一头母猪，明年的肉价就能下跌！", "叫犯贱" };

}
