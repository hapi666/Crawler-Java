package cn.edu.dig.cxx.pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class crawler {
	
		private Map<String,List<String>> map;
		private void pb(String[] Head,URL realUrl,URLConnection connection) {
			try {
				if(Head != null)
					for(int i = 0;i < Head.length ;i++){
						connection.setRequestProperty(Head[i],Head[i+1]);
						i++;
					}
				else {
					connection.setRequestProperty("accept", "*/*");
					connection.setRequestProperty("connection", "Keep-Alive");
					connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
				}
			}catch (Exception e){
				System.out.println("设置请求属性值失败");
			}
		}
		public String Net_Get(String url,String param,String[] Head) throws MalformedURLException {
			String result="";
			BufferedReader in = null;
			String urlnamestring = url;
			if(!param.equals(""))
				urlnamestring = url+"?"+param;
			URL realUrl = new URL(urlnamestring);
			try {
				URLConnection connection = realUrl.openConnection();
				pb(Head,realUrl,connection);
				connection.connect();
				map = connection.getHeaderFields();
				for(String key : map.keySet()) {
					System.out.println(key+":"+map.get(key));
				}
				in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
				String line;
				while((line = in.readLine())!=null) {
					result += "\n"+line;
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
			result = result.trim();
			return result;
		}
		public String Net_POST(String url,String param,String[] Head) {
			String result = "";
			try{
				URL realUrl = new URL(url);
				URLConnection conn = realUrl.openConnection();
				pb(Head,realUrl,conn);
				conn.setDoOutput(true);//允许输入流，默认false
				conn.setDoInput(true);//允许输出流，默认false
				
				try(PrintWriter out = new PrintWriter(conn.getOutputStream())){
					out.print(param);
					out.flush();
				}
				map = conn.getHeaderFields();
				for(String key : map.keySet()) {
					System.out.println(key+":"+map.get(key));
				}
				try(BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"))){
					String line;
					while((line = in.readLine())!= null){
						result += "\n" + line;
					}
				}
			}catch(Exception e){
				System.out.println("发送POST请求出现异常"+e);
			}
			result = result.trim(); 
			return result;
		}
		
		public void daili(String urlstring) {
			URL url = null;
			try {
				url = new URL(urlstring);
			} catch (MalformedURLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		    InetSocketAddress addr = new InetSocketAddress("61.135.217.7", 80);
		    Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); 
		    Authenticator.setDefault(new MyAuthenticator("username", "password"));
		    HttpURLConnection connection = null;
			try {
				connection = (HttpURLConnection) url.openConnection(proxy);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			map = connection.getHeaderFields();
			for(String key : map.keySet()) {
				System.out.println(key+":"+map.get(key));
			}
		    InputStreamReader in = null;
			try {
				in = new InputStreamReader(connection.getInputStream());
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		    BufferedReader reader = new BufferedReader(in);
		    while (true) {
		    	String s1 = null;
		    	try {
		    		s1 = reader.readLine();
		    	} catch (IOException e) {
		    		// TODO 自动生成的 catch 块
		    		e.printStackTrace();
		    	}
		    	if (s1 != null) {
		    		System.out.println(s1);
		    		System.out.println("\n");
		    	}
		    }
		}
}

