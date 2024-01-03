package jdbc_2.eeit179.garylee.lab.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JDBCutilsWithHikariCP {
	/*
	 * 使用hikariCP連線池
	 */
	private static HikariDataSource ds;
	static {
		HikariConfig config = new HikariConfig("src/hikariCP.properties");
		ds = new HikariDataSource(config);
	}
	
	public static Connection getConnection() throws SQLException {
		Connection conn = ds.getConnection();
		return conn;
	}
	
	public static void closeResource(Connection conn,Statement pstmt) {
		try {
			if(pstmt !=null)
				pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn !=null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeResuore(Connection conn,Statement pstmt,ResultSet rs) {
		try {
			if(pstmt !=null)
				pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn !=null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(rs !=null)
				rs.close();
		} catch (SQLException e) {
		}
	}
	
}
