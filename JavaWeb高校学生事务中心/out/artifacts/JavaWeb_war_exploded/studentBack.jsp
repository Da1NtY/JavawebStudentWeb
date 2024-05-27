<%--
  Created by IntelliJ IDEA.
  User: 28360
  Date: 2023/12/21
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: 28360
  Date: 2023/12/21
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
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
            <a class="nav-link" href="studentRepair.jsp">公寓报修系统</a>
        </li>
        <!-- Dropdown -->
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-bs-toggle="dropdown">
                请销假系统
            </a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="studentLeave.jsp">学生请假系统</a>
                <a class="dropdown-item" href="#">学生销假系统</a>
            </div>
        </li>
    </ul>
    <span class="navbar-text">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;欢迎用户${username}</span>
</nav>
<br>

<div class="container mt-3">
    <h2>学生销假申请</h2>
    <table class="table">
        <thead>
        <tr>
            <th>请假ID</th>
            <th>请假理由</th>
            <th>开始日期</th>
            <th>结束日期</th>
            <th>房间号</th>
            <th>学生ID</th>
            <th>审核状态</th>
            <th>审核操作</th>
        </tr>
        </thead>
        <tbody>
        <%
            LeaveDB leaveDB = new LeaveDB();
            List<Leave> leaveList = leaveDB.getLeaveList(); // 获取数据库中的请假信息列表

            UserDB userDB = new UserDB();
            List<User> UserList = userDB.getTeacherList(); // 获取数据库中的教师信息列表


            for (Leave leave : leaveList) {
                if (leave.getStudentid().equals(session.getAttribute("userid")))
                    if (leave.getStatus().equals("请假通过")){
        %>
        <tr>
            <td><%= leave.getLeaveid() %></td>
            <td><%= leave.getReason() %></td>
            <td><%= leave.getStartdate() %></td>
            <td><%= leave.getEnddate() %></td>
            <td><%= leave.getRoomnumber() %></td>
            <td><%= leave.getStudentid() %></td>
            <td><%= leave.getStatus() %></td>
            <td>
                <%-- 在此处添加审核操作的按钮 --%>
                <form action="ProcessLeaveServlet2" method="post">
                    <input type="hidden" name="leaveId" value="<%= leave.getLeaveid() %>">
                    <select name="teacherId">
                        <% for (User teacher : UserList){%>
                        <option value="<%= teacher.getUserid()%>"><%= teacher.getUserid()%></option>
                        <%
                            }
                        %>
                    </select>
                    <select name="status">
                        <option value="待销假">销假</option>
                        <option value="不销假">不销假</option>
                    </select>
                    <input type="submit" value="提交">
                </form>
            </td>
        </tr>
        <% }} %>
        </tbody>
    </table>
</div>

</body>
</html>


