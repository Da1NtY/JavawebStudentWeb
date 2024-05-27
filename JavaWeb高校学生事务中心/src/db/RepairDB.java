package db;
import object.repair;
import java.sql.*;
import java.util.*;

public class RepairDB extends DbConn{
    public List<repair> getRepairRequests() {
        List<repair> repairs = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM repair_applications");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                repair repair = new repair();
                repair.setRepairid(rs.getString("repair_id"));
                repair.setStudentid(rs.getString("student_id"));
                repair.setReason(rs.getString("reason"));
                repair.setPhotourl(rs.getString("photo_url"));
                repair.setStatus(rs.getString("status"));
                repair.setRepirtime(rs.getString("applied_at"));
                repair.setViewer(rs.getString("reviewed_by"));
                repair.setWorkerphone(rs.getString("maintenance_worker_phone"));

                repairs.add(repair);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return repairs;
    }
    public int getNextRepairId() {
        int nextRepairId = 0;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(repair_id) AS max_id FROM repair_applications");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                nextRepairId = rs.getInt("max_id") + 1;
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
        return nextRepairId;
    }

    public boolean submitRepair(repair repair) {
        boolean success = false;
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO  repair_applications(repair_id, student_id, reason, photo_url, status, applied_at, reviewed_by, maintenance_worker_phone, roomnumber, phonenumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, repair.getRepairid());
            pstmt.setString(2, repair.getStudentid());
            pstmt.setString(3, repair.getReason());
            pstmt.setString(4, repair.getPhotourl());
            pstmt.setString(5, repair.getStatus());
            pstmt.setString(6, repair.getRepirtime());
            pstmt.setString(7, repair.getViewer());
            pstmt.setString(8, repair.getWorkerphone());
            pstmt.setString(9, repair.getRoomnumber());
            pstmt.setString(10, repair.getPhonenumber());

            int rowsInserted = pstmt.executeUpdate();
            success = rowsInserted > 0;

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public repair getRepairById(String repairId) {
        repair repair = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM repair_applications WHERE repair_id = ?");
            pstmt.setString(1, repairId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                repair = new repair();
                repair.setRepairid(rs.getString("repair_id"));
                repair.setStudentid(rs.getString("student_id"));
                repair.setReason(rs.getString("reason"));
                repair.setPhotourl(rs.getString("photo_url"));
                repair.setStatus(rs.getString("status"));
                repair.setRepirtime(rs.getString("applied_at"));
                repair.setViewer(rs.getString("reviewed_by"));
                repair.setWorkerphone(rs.getString("maintenance_worker_phone"));
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return repair;
    }

    public boolean updateRepair(repair repair) {
        boolean success = false;

        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE repair_applications SET status = ?, reviewed_by = ? WHERE repair_id = ?");
            pstmt.setString(1, repair.getStatus());
            pstmt.setString(2, repair.getViewer());
            pstmt.setString(3, repair.getRepairid());

            int rowsAffected = pstmt.executeUpdate();
            success = (rowsAffected > 0);

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }
}
