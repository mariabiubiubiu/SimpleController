<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@pageimport= "org.dom4j.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>

<body>


<!-- request.getAttribute("err")可得到JSP页面一表单中控件的Value。使用JSP脚本输出错误提示 -->
<span class="tip" style="color:red;font-weight: bolder;">
<%if(request.getAttribute("err") != null){
    out.println(request.getAttribute("err")+"<br/>");
}
%>
</span>
<%=System.getProperty("java.class.path") %>

请输入用户名和密码：
<!-- 登录表单，将表单提交到一个Servlet中 -->
<form action="login.action" id="login" method="post">
用户名：<input type="text" name="username"><br/><br/>
密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" name="password"><br/><br/>
<input type="submit" value="登录"><br/>
</form>

</body>
</html>