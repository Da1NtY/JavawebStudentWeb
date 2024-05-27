<%--
  Created by IntelliJ IDEA.
  User: 28360
  Date: 2023/12/8
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>登录</title>
    <link type="text/css" href="login.css" rel="stylesheet"/>
    <script type="text/javascript">
        function change(){
            document.getElementById("validate").src = "ValidateServlet?random=" + Math.random();
        }
    </script>
</head>
<body>
<div class="login">
    <h2>用户登录</h2>
    <div >
        <form action="LoginServlet" method="post">
            <div class="frm">用户名：
                <input type="text" name="username" class="txtuser"/>
            </div>
            <div class="frm">密&nbsp&nbsp&nbsp码：
                <input type="password" name="password" class="txtpwd"/>
            </div>
            <div class="frm">身&nbsp&nbsp&nbsp份：
                <select id="role" name="role">
                    <option value="管理员">管理员</option>
                    <option value="教师">教师</option>
                    <option value="学生">学生</option>
                </select>
            </div>
            <div class="frm">验证码：
                <input type="text" name="userVCode" class="textVCode">
                <span>
                    <img src="ValidateServlet" id="validate" onclick="change()"/>
                    <a href="javascript:change()">看不清，换一张</a>
                </span>
            </div>
            <div class="btns">
                <input type="submit" name="login" class="btnlogin" value="登录"/>
                <input type="button" name="register" class="btnregister" onclick="window.open('Register.jsp')"value="注册"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>
