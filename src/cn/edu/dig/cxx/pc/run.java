package cn.edu.dig.cxx.pc;

public class run {

	public static void main(String[] args) {
		String s = crawler.Get("http://www.jianshu.com/", "");
		System.out.println(s);
		String ss = crawler.Get("http://www.cxx666.xyz/", "iclass=161*****&idcard=******************&phone=176456123&message=123");
		System.out.println(ss);
	}

}
