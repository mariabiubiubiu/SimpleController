package servlet.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.UserBean;

public class RegisterAction {
	
	public String login(HttpServletRequest request, HttpServletResponse response){
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		UserBean user = new UserBean();
		user.setUserName(name);
		user.setPassWord(password);
		
		return "success";
	}
}
