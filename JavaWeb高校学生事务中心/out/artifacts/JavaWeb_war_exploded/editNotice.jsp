<%--
  Created by IntelliJ IDEA.
  User: 28360
  Date: 2023/12/20
  Time: 8:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="db.NoticeDB, object.Notice" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>编辑公告</title>
    <!-- 样式表和脚本 -->
</head>
<body>
<h1>编辑公告</h1>

<%
    // 获取待编辑的公告id
    String noticeId = request.getParameter("id");

    // 从数据库中获取待编辑的公告信息
    NoticeDB noticeDB = new NoticeDB();
    Notice notice = noticeDB.getNoticeById(noticeId);
%>

<div>
    <form action="updateNoticeServlet" method="post">
        <input type="hidden" name="noticeId" value="<%= notice.getNoticeid() %>">
        <label for="title">标题：</label>
        <input type="text" id="title" name="title" value="<%= notice.getTitle() %>" required><br><br>
        <label for="content">内容：</label>
        <textarea id="content" name="content" required><%= notice.getContent() %></textarea><br><br>
        <input type="submit" value="更新公告">
    </form>
</div>

</body>
</html>
