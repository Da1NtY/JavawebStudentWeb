package db;

import object.Notice;
import object.User;

import java.sql.*;
import java.util.*;

public class NoticeDB extends DbConn{
    public List<Notice> getNotices() {
        List<Notice> notices = new ArrayList<>();

        try {
            // 创建 PreparedStatement 对象
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM notices ORDER BY notice_id DESC LIMIT 5");

            // 执行查询
            ResultSet rs = pstmt.executeQuery();

            // 遍历结果集，将公告信息添加到列表中
            while (rs.next()) {
                Notice notice = new Notice();
                notice.setNoticeid(rs.getString("notice_id"));
                notice.setTitle(rs.getString("title"));
                notice.setContent(rs.getString("content"));
                notice.setCreater(rs.getString("created_by"));
                notices.add(notice);
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return notices;
    }
    public int getNextNoticeId() {
        int nextNoticeId = 0;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(notice_id) AS max_id FROM notices");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                nextNoticeId = rs.getInt("max_id") + 1;
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
        return nextNoticeId;
    }
    public void addNotice(Notice notice)
    {
        if(conn!=null)
        {
            try
            {
                PreparedStatement ps=conn.prepareStatement("INSERT INTO notices VALUES(?,?,?,?,?)");
                ps.setString(1,notice.getNoticeid());
                ps.setString(2,notice.getTitle());
                ps.setString(3,notice.getContent());
                ps.setString(4,notice.getCreater());
                ps.setString(5,notice.getCreatetime());
                ps.execute();
                System.out.println("插入成功！");		// 打印成功信息到控制台
            }
            catch(SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
    public Notice getNoticeById(String noticeId) {
        Notice notice = new Notice();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM notices WHERE notice_id = ?");
            int id = Integer.parseInt(noticeId);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                notice = new Notice();
                notice.setNoticeid(rs.getString("notice_id"));
                notice.setTitle(rs.getString("title"));
                notice.setContent(rs.getString("content"));
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notice;
    }

    public boolean updateNotice(Notice notice) {
        boolean success = false;
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE notices SET title = ?, content = ? WHERE notice_id = ?");
            pstmt.setString(1, notice.getTitle());
            pstmt.setString(2, notice.getContent());
            pstmt.setString(3, notice.getNoticeid());

            int rowsUpdated = pstmt.executeUpdate();
            success = rowsUpdated > 0;

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

    public boolean deleteNotice(String noticeId) {
        boolean success = false;
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM notices WHERE notice_id = ?");
            pstmt.setString(1, noticeId);

            int rowsDeleted = pstmt.executeUpdate();
            success = rowsDeleted > 0;

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
}
