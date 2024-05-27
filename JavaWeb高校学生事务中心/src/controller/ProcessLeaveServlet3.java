package controller;
import db.LeaveDB;
import object.Leave;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ProcessLeaveServlet3")
public class ProcessLeaveServlet3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String leaveId = request.getParameter("leaveId");
        String teacherId = request.getParameter("teacherId");
        String status = request.getParameter("status");

        LeaveDB leaveDB = new LeaveDB();
        Leave leave = leaveDB.getLeaveById(leaveId);

        if (leave != null) {
            leave.setStatus(status);
            leave.setReviewer(teacherId); // 设置审核人ID

            leaveDB.updateLeave(leave); // 更新请假信息状态和审核人ID

            response.sendRedirect("teacherBack.jsp"); // 重定向回教师审核页面
        } else {
            // 处理获取不到请假信息的情况
            response.getWriter().println("无法找到请假信息");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
