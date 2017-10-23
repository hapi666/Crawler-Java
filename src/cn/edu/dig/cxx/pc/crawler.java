package cn.edu.dig.cxx.pc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class crawler {
		public static String Get(String url,String param) {
			String result="";
			BufferedReader in = null;
			try {
				String urlnamestring = url;
				if(!param.equals(""))
					urlnamestring = url+"?"+param;
				URL realUrl = new URL(urlnamestring);
				URLConnection connection = realUrl.openConnection();
				connection.setRequestProperty("accept", "*/*");
				connection.setRequestProperty("connection", "Keep-Alive");
				connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
				connection.connect();
				Map<String,List<String>> map = connection.getHeaderFields();
				for(String key : map.keySet()) {
					System.out.println(key+":"+map.get(key));
				}
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				while((line = in.readLine())!=null) {
					result += line;
				}
			} catch(Exception e1) {
				System.out.println("发送 GET 请求出现异常！"+e1);
	            e1.printStackTrace();
			}
			finally{
				try {
					if(in != null) {
						in.close();
					} 
					
				}catch(Exception e2) {
				 	e2.printStackTrace();
				}
			}
			return result;
		}
		public static String POST(String url,String param) {
			String result = "";
			PrintWriter out = null;
			BufferedReader in = null;
			try {
				URL RealUrl = new URL(url);
				URLConnection connection = RealUrl.openConnection();
				connection.setRequestProperty("accept", "*/*");
				connection.setRequestProperty("connection", "Keep-Alive");
				connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
				//connection.connect();
				connection.setDoOutput(true);
	            connection.setDoInput(true);
	            out = new PrintWriter(connection.getOutputStream());
	            out.print(param);
	            out.flush();
	            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            String line;
				while((line = in.readLine())!=null) {
					result += line;
				}
			}catch(Exception e3) {
				System.out.println("发送 POST 请求出现异常！"+e3);
				e3.printStackTrace();
			}
			finally {
				try {
					if(out != null) {
						out.close();
					}
					if(in != null) {
						out.close();
					}
				}catch(Exception e4){
					e4.printStackTrace();
				}
			}
			return result;
		}
		
}
