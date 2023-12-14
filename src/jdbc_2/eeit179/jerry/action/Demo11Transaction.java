package jdbc_2.eeit179.jerry.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Demo11Transaction {

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
	
	public void controlTarsaction() throws SQLException {
		String sql="UPDATE user_table set balance =? WHERE user =?";
		
		conn.setAutoCommit(false);//啟動隱含交易模式 
		
		PreparedStatement preState = conn.prepareStatement(sql);
		
		preState.setInt(1, 700);
		preState.setString(2, "AA");
		preState.execute();
		
		//測試失敗
//		System.out.println(10/0); 
		
		preState.setInt(1, 1100);
		preState.setString(2, "BB");
		preState.execute();
		
		conn.commit();
		System.out.println("commit ok");
		
		conn.setAutoCommit(true);//回到預設 前後都沒關係
		preState.close();
		
		
		
	}
	
	public static void main(String[] args) {
		Demo11Transaction demo11 = new Demo11Transaction();
		try {
			demo11.createConnection();
			demo11.controlTarsaction();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("錯誤:"+e.getMessage());
		}finally {
			try {
				demo11.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
