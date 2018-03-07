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
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>

<link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">

<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<h2>抱歉，服务器开了小差</h2>
		</div>
	</div>
</body>
</html>