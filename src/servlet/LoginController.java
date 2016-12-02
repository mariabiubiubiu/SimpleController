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
		
		//���÷��䴴������͵��÷���
		Class<?> class1=Class.forName(classnameString);
		Object object=class1.newInstance();
		
		Method method=class1.getDeclaredMethod(methodString, HttpServletRequest.class,HttpServletResponse.class);
		String returnFlag = (String)method.invoke(object, request,response);
		Result result = actionMapping.getResults().get(returnFlag);
		String type = result.getType();	 
		String page = result.getValue();
		if ("redirect".equals(type)) {	// �ض�������
			response.sendRedirect(page);
		} else {	// ת������
			request.getRequestDispatcher(page).forward(request, response);
		}
//		super.service(arg0, arg1);
//		UserBean userBean=new UserBean();
//		String errMSG = "";//������Ϣ
//		RequestDispatcher rd;//��Servlet��ʹ��RequestDispatcher�����forward����ҳ�����ת
//		String username = request.getParameter("username");//��ȡ����Ĳ�������jsp�еı��л�ȡ���Ա������ֻ�ȡ�ͻ����������Ϣ
//        String password = request.getParameter("password");//��ȡ����Ĳ�������jsp�еı��л�ȡ���Ա������ֻ�ȡ�ͻ����������Ϣ
//        userBean.setUserName(username);
//        userBean.setPassWord(password);
        
//        HttpSession session = request.getSession();//��ȡsession����
//        session.setAttribute("name", username);//����session���ԣ������û��Ự״̬
//        if(userBean.isSuspect())
//        {rd = request.getRequestDispatcher("/login_success.jsp");//��Servlet��ʹ��RequestDispatcher�����forward����ҳ�����ת����ȡת������
//        rd.forward(request, response);}//ת������
//        else {
//        	rd = request.getRequestDispatcher("/login_failed.jsp");//��Servlet��ʹ��RequestDispatcher�����forward����ҳ�����ת����ȡת������
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
