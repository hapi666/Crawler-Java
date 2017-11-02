package cn.edu.dig.cxx.pc;

import java.awt.TextArea;
import java.net.MalformedURLException;

public class run {

	public static void main(String[] args) {
		crawler a = new crawler();
		String[] head = new String[1000];
		head = null;
		String s = null;
		try {
			s = a.Net_Get("http://192.168.2.141:8080/16Pf", "username=123&&num=123", head);
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out.println(s);
		a.daili("http://www.baidu.com");
	}
}
