package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {
//	
	private static DBConnection connection = 
					new DBConnection();
	
	private DBConnection() {
	}
	
	public static DBConnection getInstance() {
		return connection;
	}
	
	public Connection getConnection() {
		
		Connection con = null;
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "ora_user";
		String pw = "hong";
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,pw);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return con;
	} // getConnection end
	
	public void close(Connection con, PreparedStatement pstmt) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close(Connection con, PreparedStatement pstmt
					,ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}// class end 
