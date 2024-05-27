package controller;

import db.NoticeDB;
import db.RepairDB;
import object.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@MultipartConfig
@WebServlet("/SubmitRepairServlet")
public class SubmitRepairServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        //通过数据库获取下一个维修号
        RepairDB repairDB = new RepairDB();
        int nextRepairId = repairDB.getNextRepairId();
        String id = "";
        id = nextRepairId + "";

        // 获取上传的文件
        Part filePart = request.getPart("photo");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // 获取文件名

        // 保存文件到指定位置
        String Dir = "Web课设";
        String uploadPath = "D:\\JavaWeb高校学生事务中心\\out\\artifacts\\JavaWeb_war_exploded\\" + Dir; // 设置上传文件的存储路径
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String filePath = Dir + File.separator + fileName;
        String file = uploadPath + File.separator + fileName;
        filePart.write(file);


        String studentId = request.getParameter("studentid");
        String reason = request.getParameter("reason");
        String roomnumber = request.getParameter("roomnumber");
        String phonenumber = request.getParameter("phonenumber");
        String viewer = request.getParameter("reviewer");
        //String photoUrl = ; // 从上传文件获取或其他方式获取照片URL
        String status = "待审核";
        String repairTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String workerphonenumber = "12345678910";


        repair repair = new repair();
        repair.setRepairid(id);
        repair.setStudentid(studentId);
        repair.setReason(reason);
        repair.setPhotourl(filePath);
        repair.setStatus(status);
        repair.setRepirtime(repairTime);
        repair.setRoomnumber(roomnumber);
        repair.setPhonenumber(phonenumber);
        repair.setWorkerphone(workerphonenumber);
        repair.setViewer(viewer);

        RepairDB repairDB1 = new RepairDB();
        boolean success = repairDB1.submitRepair(repair);

        if (success) {
            // 报修提交成功，重定向回学生界面或其他页面
            response.sendRedirect("studentNotice.jsp");
        } else {
            // 报修提交失败，重定向到错误页面或其他处理方式
            response.sendRedirect("error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
