<%--
  Created by IntelliJ IDEA.
  User: 28360
  Date: 2023/12/8
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>登录成功</title>
</head>
<body>
<div>欢迎
    <%=(String)session.getAttribute("username") %>
</div>
使用本系统！<br>
</body>
</html>
