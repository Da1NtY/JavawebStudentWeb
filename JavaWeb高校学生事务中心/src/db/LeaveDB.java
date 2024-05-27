package db;

import object.Leave;

import java.sql.*;
import java.util.*;

public class LeaveDB extends DbConn{
    public boolean addLeave(Leave leave) {
        boolean success = false;

        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO leave_applications (leave_id, student_id, reason, start_date, end_date, status, reviewed_by, roomnumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, leave.getLeaveid());
            pstmt.setString(2, leave.getStudentid());
            pstmt.setString(3, leave.getReason());
            pstmt.setString(4, leave.getStartdate());
            pstmt.setString(5, leave.getEnddate());
            pstmt.setString(6, leave.getStatus());
            pstmt.setString(7, leave.getReviewer());
            pstmt.setString(8, leave.getRoomnumber());

            int rowsAffected = pstmt.executeUpdate();
            success = (rowsAffected > 0);

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public int getNextLeaveId() {
        int nextLeaveId = 0;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(leave_id) AS max_id FROM leave_applications");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                nextLeaveId = rs.getInt("max_id") + 1;
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return nextLeaveId;
    }

    public List<Leave> getLeaveList() {
        List<Leave> leaveList = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM leave_applications");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Leave leave = new Leave();
                leave.setLeaveid(rs.getString("leave_id"));
                leave.setReason(rs.getString("reason"));
                leave.setStudentid(rs.getString("student_id"));
                leave.setStartdate(rs.getString("start_date"));
                leave.setEnddate(rs.getString("end_date"));
                leave.setStatus(rs.getString("status"));
                leave.setRoomnumber(rs.getString("roomnumber"));
                leave.setReviewer(rs.getString("reviewed_by")); // 如果有审核人的话

                leaveList.add(leave);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return leaveList;
    }

    public Leave getLeaveById(String leaveId) {
        Leave leave = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM leave_applications WHERE leave_id = ?");
            pstmt.setString(1, leaveId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                leave = new Leave();
                leave.setLeaveid(rs.getString("leave_id"));
                leave.setReason(rs.getString("reason"));
                leave.setStudentid(rs.getString("student_id"));
                leave.setStartdate(rs.getString("start_date"));
                leave.setEnddate(rs.getString("end_date"));
                leave.setStatus(rs.getString("status"));
                leave.setRoomnumber(rs.getString("roomnumber"));
                leave.setReviewer(rs.getString("reviewed_by")); // 如果有审核人的话
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return leave;
    }

    public boolean updateLeave(Leave leave) {
        boolean updated = false;

        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "UPDATE leave_applications SET reason = ?, student_id = ?, start_date = ?, end_date = ?, status = ?, roomnumber = ?, reviewed_by = ? WHERE leave_id = ?"
            );

            pstmt.setString(1, leave.getReason());
            pstmt.setString(2, leave.getStudentid());
            pstmt.setString(3, leave.getStartdate());
            pstmt.setString(4, leave.getEnddate());
            pstmt.setString(5, leave.getStatus());
            pstmt.setString(6, leave.getRoomnumber());
            pstmt.setString(7, leave.getReviewer());
            pstmt.setString(8, leave.getLeaveid());

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                updated = true; // 如果更新成功，将 updated 置为 true
            }

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }
}
