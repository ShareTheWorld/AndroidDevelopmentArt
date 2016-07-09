package com.fht.ada.aidl;
import java.util.List;
interface IBookManager{
	 List<String> getAllBook();
	 void putBook(String name);
}