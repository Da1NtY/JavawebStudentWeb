package db;

import java.sql.*;

public class DbConn{
    private String dbUrl = "jdbc:mysql://localhost:3306/studentweb";
    private String dbUser = "root";
    private String dbPwd = "123456";
    private String jdbcDriver = "com.mysql.jdbc.Driver";
    protected Connection conn = null;

    public DbConn(){
        Connection con = null;
        try {
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
        }
        catch(java.lang.ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        this.conn = con;
    }

    public void close() {
        try {
            if(conn != null)
                conn.close();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}