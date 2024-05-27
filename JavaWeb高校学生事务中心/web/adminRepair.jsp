<%@ page import="db.RepairDB" %>
<%@ page import="object.repair" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 28360
  Date: 2023/12/18
  Time: 23:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>报修系统管理</title>
    <link type="text/css" href="admin.css" rel="stylesheet"/>
</head>
<body>

<div class="sidenav">
    <!-- 垂直导航栏内容 -->
    <a>管理员${username}</a>
    <a href="adminNotice.jsp" class="#">公告管理</a>
    <a href="#" class="active">报修系统管理</a>
    <!-- 添加更多导航链接 -->
</div>

<div class="main">
    <h1>管理员审核报修请求</h1>
    <%
        RepairDB repairDB = new RepairDB();
        List<repair> repairList = repairDB.getRepairRequests();
        repairDB.close();
    %>

    <!-- 展示报修请求列表 -->
    <table class="table">
        <thead>
        <tr>
            <th>报修ID</th>
            <th>学生ID</th>
            <th>报修原因</th>
            <th>照片</th>
            <th>报修状态</th>
            <th>报修时间</th>
            <th>审核</th>
        </tr>
        </thead>
        <tbody>
        <!-- 使用循环展示报修请求 -->
        <%-- 通过循环展示报修请求列表 --%>
        <% for (repair repair : repairList) {
            if (repair.getViewer().equals(session.getAttribute("userid")))
                if (repair.getStatus().equals("待审核")){%>
        <tr>
            <td><%= repair.getRepairid() %></td>
            <td><%= repair.getStudentid() %></td>
            <td><%= repair.getReason() %></td>
            <td><img src="<%= repair.getPhotourl() %>" alt="RepairPhoto"></td>
            <td><%= repair.getStatus()%></td>
            <td><%= repair.getRepirtime() %></td>
            <td>
                <form action="ProcessRepairServlet" method="post">
                    <input type="hidden" name="repairId" value="<%= repair.getRepairid() %>">
                    <input type="hidden" name="adminId" value="${userid}">
                    <select name="action">
                        <option value="approve">批准</option>
                        <option value="reject">拒绝</option>
                    </select>
                    <input type="submit" value="提交">
                </form>
            </td>
            <!-- 其他列 -->
        </tr>
        <% }} %>
        </tbody>
    </table>
</div>

</body>
</html>
