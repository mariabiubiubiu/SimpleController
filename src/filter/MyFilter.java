package filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resolution.Dom4JforXML;
import servlet.action.LoginAction;

public class MyFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest httpServletRequest=(HttpServletRequest) arg0;
		HttpServletResponse httpServletResponse=(HttpServletResponse) arg1;
		String urlString=httpServletRequest.getRequestURI();
		Dom4JforXML aDom4JforXML=new Dom4JforXML();
		try {
			aDom4JforXML.test();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,String> aMap=aDom4JforXML.m;
		String aString=aMap.get(urlString);
		if("name1".equals(aString)){
			try {
				Class class1=Class.forName(aString);
				Object object=class1.newInstance();
				
				}catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				//httpServletRequest.getRequestDispatcher(aString).forward(httpServletRequest, httpServletResponse);
		}
		else if ("name2".equals(aString)) {
			httpServletRequest.getRequestDispatcher(aString).forward(httpServletRequest, httpServletResponse);
		}
		else {
			httpServletRequest.getRequestDispatcher("/Error.jsp").forward(httpServletRequest, httpServletResponse);

		}

	}

	private void judge(String aString) {
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
	

}
