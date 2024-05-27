package controller;
import db.NoticeDB;
import object.Notice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/addNoticeServlet")
public class addNoticeServlet extends HttpServlet {
    private static final long SerialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        request.setCharacterEncoding("UTF-8");

        // 获取表单提交的公告标题和内容
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String creater = request.getParameter("creater");

        //获取当前时间作为公告时间
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = currentTime.format(formatter);

        //通过数据库获取下一个公告号
        NoticeDB noticeDB = new NoticeDB();
        int nextNoticeId = noticeDB.getNextNoticeId();
        String id = "";
        id = nextNoticeId + "";

        // 创建一个公告对象
        Notice notice = new Notice();
        notice.setNoticeid(id);
        notice.setTitle(title);
        notice.setContent(content);
        notice.setCreater(creater);
        notice.setCreatetime(time);

        //创建NoticeDB实例添加到数据库
        NoticeDB noticeDB1 = new NoticeDB();
        noticeDB1.addNotice(notice);

        //添加完成后跳转到管理界面
        response.sendRedirect("adminNotice.jsp");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
