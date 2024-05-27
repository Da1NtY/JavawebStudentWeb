<%--
  Created by IntelliJ IDEA.
  User: 28360
  Date: 2023/12/10
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>
    <!-- 在这里添加您需要的样式表链接或其他资源 -->
    <link type="text/css" href="register.css" rel="stylesheet"/>
</head>
<body>
<div class="register">
<h2>用户注册</h2>
<div>
<form action="RegisterServlet" method="post">
    <label for="userid">用户ID:</label>
    <input type="text" id="userid" name="userid" required><br><br>
    <label for="username">用户名:</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password">密  码:</label>
    <input type="password" id="password" name="password" required><br><br>

    <label for="role">身  份:</label>
    <select id="role" name="role">
        <option value="管理员">管理员</option>
        <option value="教师">教师</option>
        <option value="学生">学生</option>
    </select><br><br>

    <!-- 其他所需的注册信息 -->

    <input type="submit" value="注册">
</form>
</div>
</div>
</body>
</html>
