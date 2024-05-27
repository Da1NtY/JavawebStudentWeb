<%--
  Created by IntelliJ IDEA.
  User: 28360
  Date: 2023/12/20
  Time: 22:29
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
    <a class="navbar-brand" href="studentNotice.jsp">高校学生事务中心</a>

    <!-- Links -->
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="#">公寓报修系统</a>
        </li>
        <!-- Dropdown -->
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-bs-toggle="dropdown">
                请销假系统
            </a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="studentLeave.jsp">学生请假系统</a>
                <a class="dropdown-item" href="studentBack.jsp">学生销假系统</a>
            </div>
        </li>
    </ul>
    <span class="navbar-text">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;欢迎用户${username}</span>
</nav>
<br>

<div class="container">
    <h1>宿舍报修申请</h1>
    <%
        UserDB userDB = new UserDB();
        List<User> UserList = userDB.getAdminList(); // 获取数据库中的教师信息列表

    %>
    <form action="SubmitRepairServlet" method="post" enctype="multipart/form-data">

        <input type="hidden"  name="studentid" value="${userid}" required>

        <label for="roomnumber">宿舍号：</label>
        <input type="text" id="roomnumber" name="roomnumber" required><br><br>

        <label for="reason">报修原因：</label>
        <input type="text" id="reason" name="reason" required><br><br>

        <label for="phonenumber">联系方式：</label>
        <input type="text" id="phonenumber" name="phonenumber" required><br><br>

        <label for="photo">上传照片：</label>
        <input type="file" id="photo" name="photo"><br><br>

        <label for="reviewer">审核人：</label>
        <select name="reviewer" id="reviewer">
            <% for (User admin : UserList){%>
            <option value="<%= admin.getUserid()%>"><%= admin.getUserid()%></option>
            <%
                }
            %>
        </select><br><br>
        <!-- 可以根据需求增加其他报修信息的输入 -->

        <input type="submit" value="提交报修申请">
    </form>
</div>

</body>
</html>
