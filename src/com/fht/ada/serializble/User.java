package com.fht.ada.serializble;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 特点:代码简单,性能低,属于Java 代码简单:用于存储和网络传输
 * 
 * @author Fht
 *
 */
public class User implements Serializable {
	public static final long serialVersionUID = 59852348023l;
	private String name = "fht";
	private int age = 26;
	transient private boolean sex = true;// 不会别序列化

	void write() throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.o"));
		oos.writeObject(new User());
		oos.close();

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.o"));
		Object o = ois.readObject();
		if (o instanceof User) {
			User user = (User) o;
		}
		ois.close();
	}
}
