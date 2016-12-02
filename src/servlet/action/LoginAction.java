package servlet.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.UserBean;

public class LoginAction {
	public String login(HttpServletRequest request, HttpServletResponse response){
		//Object uri = null;
	//	String name = request.getParameter("username");
		String name = request.getParameter("username");
		//String password = request.getParameter("password");
		String password = request.getParameter("password");
		UserBean user = new UserBean();
		user.setUserName(name);
		user.setPassWord(password);
		
		if(user.isSuspect())
			return "success";
		else {
			return "fail";
		}
	}
}
