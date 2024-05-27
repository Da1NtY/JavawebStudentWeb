<%--
  Created by IntelliJ IDEA.
  User: 28360
  Date: 2023/12/18
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="db.*" %>
<%@ page import="object.*" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>高校学生事务中心</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.staticfile.org/twitter-bootstrap/5.1.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.staticfile.org/twitter-bootstrap/5.1.1/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <!-- Brand -->
    <a class="navbar-brand" href="#">高校学生事务中心</a>

    <!-- Links -->
    <ul class="navbar-nav">
        <!-- Dropdown -->
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-bs-toggle="dropdown">
                请销假系统
            </a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="teacherLeave.jsp">学生请假系统</a>
                <a class="dropdown-item" href="teacherBack.jsp">学生销假系统</a>
            </div>
        </li>
    </ul>
    <span class="navbar-text">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;欢迎用户${username}</span>
</nav>
<br>

<div class="container">
    <h3>公告</h3>
    <ul>
        <%
            try {
                NoticeDB notice1=new NoticeDB();
                List<Notice> notices = notice1.getNotices(); // 假设您的方法可以获取公告列表
                for (Notice notice : notices) {
        %>
        <li>
            <h4><%= notice.getTitle() %></h4>
            <p><%= notice.getContent() %></p >
            <hr>
        </li>
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
    </ul>
</div>

</body>
</html>
