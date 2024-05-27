package controller;
import db.LeaveDB;
import db.NoticeDB;
import object.Leave;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/SubmitLeaveServlet")
public class SubmitLeaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        //通过数据库获取下一个公告号
        LeaveDB leaveDB = new LeaveDB();
        int nextLeaveId = leaveDB.getNextLeaveId();
        String id = "";
        id = nextLeaveId + "";

        String studentid = request.getParameter("studentid");
        String reason = request.getParameter("reason");
        String startdate = request.getParameter("startdate");
        String enddate = request.getParameter("enddate");
        String roomnumber = request.getParameter("roomnumber");
        String reviwer = request.getParameter("reviewer");
       // String studentid = ""; // 从登录用户的 Session 中获取学生的 ID

//        // 在这里从 Session 中获取学生的 ID
//        HttpSession session = request.getSession();
//        if (session.getAttribute("studentId") != null) {
//            studentid = (String) session.getAttribute("studentId");
//        }

        Leave leave = new Leave();
        leave.setLeaveid(id);
        leave.setReason(reason);
        leave.setStartdate(startdate);
        leave.setEnddate(enddate);
        leave.setRoomnumber(roomnumber);
        leave.setStudentid(studentid);
        leave.setStatus("待审核"); // 默认状态为待审核
        leave.setReviewer(reviwer);

        LeaveDB leaveDB1 = new LeaveDB();
        boolean success = leaveDB1.addLeave(leave);

        if (success) {
            response.sendRedirect("studentLeave.jsp"); // 提交成功，返回学生请假页面
        } else {
            response.sendRedirect("error.jsp"); // 处理错误情况
        }

        leaveDB.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
