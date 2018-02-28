<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3>登录</h3>
<form action="<%=basePath%>login" method="post">
username:<input type="text" name="username"><br><br>
password:<input type="password" name="password"><br><br>

<input type="submit" value="登录">
</form>
</body>
</html>