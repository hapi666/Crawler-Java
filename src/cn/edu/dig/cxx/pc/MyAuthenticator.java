package cn.edu.dig.cxx.pc;

import java.net.PasswordAuthentication;
import java.net.Authenticator;

public class MyAuthenticator extends Authenticator{
	    private String user = "";
	    private String password = "";
	  
	    public MyAuthenticator(String user, String password) {
	      this.user = user;
	      this.password = password;
	    }
	  
	    protected PasswordAuthentication getPasswordAuthentication() {
	      return new PasswordAuthentication(user, password.toCharArray());
	    }
}
