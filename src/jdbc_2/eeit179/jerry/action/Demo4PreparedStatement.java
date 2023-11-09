package jdbc_2.eeit179.jerry.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Demo4PreparedStatement {
private Connection conn;
	
	public void createConnection() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/mytestdb";
		String user="root";
		String password="5413gary";
//		使用this關鍵字
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
	
	public void insertData() throws SQLException {
		String sql="INSERT INTO customers(name,email,birth) VALUES(?,?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, "testp");
		pst.setString(2,"test2@mail.com");
		pst.setString(3, "1990-04-27");
		int row = pst.executeUpdate();
		System.out.println("新增了"+row+"筆");
		pst.close();
	}
//	透過 找資料
	public void queryByName(String name) throws SQLException {
		String sql="SELECT * FROM customers WHERE name =?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, name);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3));
		}
		rs.close();
		pst.close();
	}
//	修改email功能
//	參數是姓名 跟新的email
	public void updateEmailByName(String name,String newEmail) throws SQLException {
		String sql="UPDATE customers SET email = ? WHERE name =?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, newEmail);
		pst.setString(2, name);
		int row = pst.executeUpdate();
		System.out.println("更新了"+row+"筆");
		pst.close();
	}
	
//	參數id int 根據此id刪除資料
	public void deleteById(int id) throws SQLException {
		String sql="DELETE FROM customers WHERE id = ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, 10);
		int row = pst.executeUpdate();
		System.out.println("刪除了"+row+"筆");
		pst.close();
	}
	
	
	public static void main(String[] args) {
		Demo4PreparedStatement instantce = new Demo4PreparedStatement();
		try {
			instantce.createConnection();
//			instantce.insertData();
//			instantce.queryByName("Gary");
//			instantce.deleteById(10);
			instantce.updateEmailByName("Gary", "testUpdate");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				instantce.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
