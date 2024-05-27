package controller;

import db.RepairDB;
import object.repair;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/ProcessRepairServlet")
public class ProcessRepairServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String repairId = request.getParameter("repairId");
        String adminId = request.getParameter("adminId");
        String action = request.getParameter("action");

        RepairDB repairDB = new RepairDB();
        repair repair = repairDB.getRepairById(repairId);

        if (repair != null) {
            if ("approve".equals(action)) {
                // 批准报修请求
                repair.setStatus("通过");
                repair.setViewer(adminId); // 设置审核人ID
            } else if ("reject".equals(action)) {
                // 拒绝报修请求
                repair.setStatus("驳回");
                repair.setViewer(adminId); // 设置审核人ID
            }

            // 更新报修请求状态
            boolean success = repairDB.updateRepair(repair);

            if (success) {
                response.sendRedirect("adminRepair.jsp"); // 重定向回管理员审核页面
            } else {
                response.sendRedirect("error.jsp"); // 处理错误情况
            }
        } else {
            response.sendRedirect("error.jsp"); // 处理未找到报修请求的情况
        }

        repairDB.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
