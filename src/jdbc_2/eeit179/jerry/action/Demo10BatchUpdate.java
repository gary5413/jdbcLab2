package jdbc_2.eeit179.jerry.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Demo10BatchUpdate {
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
	
	public void couponSending() throws SQLException {
		ArrayList<String> email_list = new ArrayList<String>();
		email_list.add("test1@mail.com");
		email_list.add("test2@mail.com");
		email_list.add("test3@mail.com");
		String sql="INSERT goods1(name) VALUES(?)";
		PreparedStatement preState = conn.prepareStatement(sql);
		for(String e :email_list) {
			preState.setString(1, e);
			preState.addBatch();
		}
		int[] rows =preState.executeBatch();
		System.out.println("sql add rows:"+rows.length);
		preState.close();
	}
	
	public static void main(String[] args) {
		Demo10BatchUpdate demo10 = new Demo10BatchUpdate();
		try {
			demo10.createConnection();
			demo10.couponSending();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				demo10.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
