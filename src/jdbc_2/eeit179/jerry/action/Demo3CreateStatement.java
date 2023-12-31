package jdbc_2.eeit179.jerry.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo3CreateStatement {
	
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
	
	public void queryDB1() throws SQLException {
		String sql="SELECT * FROM customers";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		boolean result = rs.next();
		System.out.println("result:"+result);
		rs.close();
		st.close();
	}
	
	public void queryDB2() throws SQLException {
		String sql="SELECT * FROM customers";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
			System.out.println(rs.getInt(1)+","+rs.getString(2)+ ","+rs.getString(3)+","+rs.getDate(4));
		}
		rs.close();
		st.close();
	}
	
	public void updateDate() throws SQLException {
		String sql="UPDATE customers SET name ='s11' WHERE id =33";
		Statement st = conn.createStatement();
		int row = st.executeUpdate(sql);
		System.out.println("修改了："+row+"筆");
		st.close();
	}
//	新增一筆資料 1005,PS5 ,10000,2023-11-06
	public void insertData() throws SQLException {
		String sql="INSERT INTO customers(name,email,birth) VALUES('s111','s111@mail.com','2023-11-06')";
		Statement st = conn.createStatement();
		int row = st.executeUpdate(sql);
		System.out.println("新增了"+row+"筆");
		st.close();
	}
//	找出100元以上的商品
	public void select100() throws SQLException {
		String sql="SELECT * FROM customers WHERE id >10";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
			System.out.println(rs.getString("name")+rs.getString("email"));
		}
		rs.close();
		st.close();
	}
//	找出100元以上的商品2
	public void select1002() throws SQLException {
		String sql="SELECT name,email FROM customers WHERE id >10";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
			System.out.println(rs.getString(1)+rs.getString(2));
		}
		rs.close();
		st.close();
	}
//	DDL也可以
	public void createTable() {
		String sql="";
		
	}
	
//	刪除id為1的資料
	public void deleteData() throws SQLException {
		String sql="DELETE FROM customers WHERE id=1";
		Statement st = conn.createStatement();
		int row = st.executeUpdate(sql);
		System.out.println("刪除了:"+row+"筆");
		st.close();
	}
	
	public static void main(String[] args) {
//		介紹實體化 什麼是new
		Demo3CreateStatement instantce = new Demo3CreateStatement();
//		開連線
		try {
			instantce.createConnection();
//		執行sql
//			instantce.queryDB1();
//			instantce.queryDB2();
//			instantce.updateDate();
//			instantce.insertData();
//			instantce.select1002();
			instantce.deleteData();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
//		關連線
			try {
				instantce.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
