package jdbc_2.eeit179.jerry.action;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Demo6CallableStatement {
	
	private Connection conn;
	public void createConnection() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/mytestdb";
		String user="root";
		String password="5413gary";
		this.conn = DriverManager.getConnection(url,user,password);
		boolean status = !conn.isClosed();
		if(status) {
			System.out.println("開啟連線");
		}
	}
	
	public void closeConnection() throws SQLException {
		if(conn !=null) {
			conn.close();
			System.out.println("關閉連線");
		}
	}
	
	public void callProcecure() throws SQLException {
		CallableStatement callState = conn.prepareCall("{call productProc(?,?)}");
		callState.setInt(1, 1002);
		callState.registerOutParameter(2, java.sql.Types.VARCHAR);
		callState.execute();
		String productName = callState.getString(2);
		callState.close();
	}
	
	public void callProceureOnlyInput(int id) throws SQLException {
		CallableStatement callState = conn.prepareCall("{call producteProc2(?)}");
		callState.setInt(1,id);
		ResultSet rs = callState.executeQuery();
		rs.next();
		String result = rs.getString(1);
		rs.close();
		callState.close();
	}
	
	public static void main(String[] args) {
		Demo6CallableStatement demo6 = new Demo6CallableStatement();
		try {
			demo6.createConnection();
//			demo6.callProcecure();
			demo6.callProceureOnlyInput(1);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				demo6.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
