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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理员界面</title>
    <link type="text/css" href="admin.css" rel="stylesheet"/>
</head>
<body>

<div class="sidenav">
    <!-- 垂直导航栏内容 -->
    <a>管理员${username}</a>
    <a href="#" class="active">公告管理</a>
    <a href="adminRepair.jsp" class="#">报修系统管理</a>
    <!-- 添加更多导航链接 -->
</div>

<div class="main">
    <!-- 页面内容 -->
    <%-- 显示现有的公告列表 --%>
    <h2>现有公告列表</h2>
    <table class="table">
        <tr>
            <th>标题</th>
            <th>内容</th>
            <th>操作</th>
        </tr>
        <%
            NoticeDB notice1=new NoticeDB();
            List<Notice> notices = notice1.getNotices(); // 假设您的方法可以获取公告列表
            for (Notice notice : notices) {
        %>
        <tr>
            <td><%= notice.getTitle() %></td>
            <td><%= notice.getContent() %></td>
            <td>
                <a href="editNotice.jsp?id=<%=notice.getNoticeid() %>">编辑</a>
                <a href="deleteNotice.jsp?id=<%=notice.getNoticeid() %>">删除</a>
            </td>
        </tr>
        <% } %>
    </table>
    <h2>添加新公告</h2>
    <form action="addNoticeServlet" method="post">
        <label for="title">标题：</label>
        <input type="text" id="title" name="title" required><br><br>
        <label for="content">内容：</label>
        <textarea id="content" name="content" required></textarea><br><br>
        <label for="creater">作者：</label>
        <input id="creater" name="creater" required><br><br>
        <input type="submit" value="添加公告">
    </form>
</div>

</body>
</html>
