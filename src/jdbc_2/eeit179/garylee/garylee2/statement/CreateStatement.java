package jdbc_2.eeit179.garylee.garylee2.statement;

import java.sql.Connection;
import java.sql.Statement;

import jdbc_2.eeit179.garylee.garylee1.util.JDBCutils;

public class CreateStatement {
	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		try {
			conn = JDBCutils.getConnection();
			st = conn.createStatement();
			String sql="INSERT INTO customers (name,email) VALUES ('s2','s2@mail.com')";
			boolean status = st.execute(sql);
			System.out.println("status:"+status);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCutils.close(conn, st);
		}
	}
}
