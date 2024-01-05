package jdbc_2.eeit179.garylee.lab.lab10connectionpool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp2.BasicDataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariDataSource;

import jdbc_2.eeit179.garylee.lab.util.JDBCutilsWithHikariCP;

public class DemoConnetionPool2 {

	public void queryCustomers() {
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			String sql = "SELECT * FROM user";
			conn = JDBCutilsWithHikariCP.getConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1) + "," + resultSet.getString(2) + "," + resultSet.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCutilsWithHikariCP.closeResuore(conn, statement, resultSet);
		}
	}

//	使用try with resource
	public void queryCustomers1() {
		String sql = "SELECT * FROM user";
		try (Connection conn = JDBCutilsWithHikariCP.getConnection();
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(sql);) {
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1) + "," + resultSet.getString(2) + "," + resultSet.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DemoConnetionPool2 demoConnetionPool = new DemoConnetionPool2();
		demoConnetionPool.queryCustomers();

	}

}
