package com.fht.ada.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 特点:性能高,代码复杂,属于android 性能高:用于进程间通信
 * 
 * @author Fht
 *
 */
public class User implements Parcelable {
	private String name = "fht";
	private int age = 26;
	private boolean sex = true;

	public User(Parcel source) {
		this.name = source.readString();
		this.age = source.readInt();
		this.sex = source.readInt() == 1 ? true : false;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeInt(age);
		dest.writeInt(sex ? 1 : 0);
	}

	public static final Parcelable.Creator<User> creator = new Creator<User>() {

		@Override
		public User[] newArray(int size) {
			return new User[size];
		}

		@Override
		public User createFromParcel(Parcel source) {
			return new User(source);
		}
	};
}
