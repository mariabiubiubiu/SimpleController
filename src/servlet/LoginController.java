package servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.ActionMapping;
import resolution.*;
import entity.Result;
import entity.UserBean;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class LoginController extends HttpServlet{
	private ResolutionXML resolutionXML;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
//		super.init();
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try{
			resolutionXML = new ResolutionXML();
		HttpServletRequest httpServletRequest=(HttpServletRequest) request;
		HttpServletResponse httpServletResponse=(HttpServletResponse) response;
		
		String urlString=httpServletRequest.getRequestURI();
		
		String actionName = urlString.substring(urlString.lastIndexOf("/")+1,urlString.indexOf(".action"));
		//String actionName=urlString;
		System.out.println(actionName);
		ActionMapping actionMapping=resolutionXML.getActionMapping(actionName);
		
		String classnameString=actionMapping.getClassName();
		String methodString=actionMapping.getMethod();
		
		//利用反射创建对象和调用方法
		Class<?> class1=Class.forName(classnameString);
		Object object=class1.newInstance();
		
		Method method=class1.getDeclaredMethod(methodString, HttpServletRequest.class,HttpServletResponse.class);
		String returnFlag = (String)method.invoke(object, request,response);
		Result result = actionMapping.getResults().get(returnFlag);
		String type = result.getType();	 
		String page = result.getValue();
		if ("redirect".equals(type)) {	// 重定向类型
			response.sendRedirect(page);
		} else {	// 转发类型
			request.getRequestDispatcher(page).forward(request, response);
		}
//		super.service(arg0, arg1);
//		UserBean userBean=new UserBean();
//		String errMSG = "";//错误信息
//		RequestDispatcher rd;//在Servlet中使用RequestDispatcher对象的forward进行页面的跳转
//		String username = request.getParameter("username");//获取请求的参数，从jsp中的表单中获取，以表单的名字获取客户端输入的信息
//        String password = request.getParameter("password");//获取请求的参数，从jsp中的表单中获取，以表单的名字获取客户端输入的信息
//        userBean.setUserName(username);
//        userBean.setPassWord(password);
        
//        HttpSession session = request.getSession();//获取session对象
//        session.setAttribute("name", username);//设置session属性，跟踪用户会话状态
//        if(userBean.isSuspect())
//        {rd = request.getRequestDispatcher("/login_success.jsp");//在Servlet中使用RequestDispatcher对象的forward进行页面的跳转，获取转发对象
//        rd.forward(request, response);}//转发请求
//        else {
//        	rd = request.getRequestDispatcher("/login_failed.jsp");//在Servlet中使用RequestDispatcher对象的forward进行页面的跳转，获取转发对象
//            rd.forward(request, response);
//		}
		}catch (Exception e){
			
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
