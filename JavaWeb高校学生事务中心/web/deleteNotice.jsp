<%--
  Created by IntelliJ IDEA.
  User: 28360
  Date: 2023/12/20
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="db.NoticeDB" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>删除公告</title>
    <!-- 样式表和脚本 -->
</head>
<body>
<%
    String noticeId = request.getParameter("id");
    NoticeDB noticeDB = new NoticeDB();
    boolean success = noticeDB.deleteNotice(noticeId);

    if (success) {
        out.println("<h1>成功删除公告</h1>");
        // 如果需要跳转回管理员公告页面，可以添加重定向
        response.sendRedirect("adminNotice.jsp");
    } else {
        out.println("<h1>删除公告失败</h1>");
    }
%>
</body>
</html>
