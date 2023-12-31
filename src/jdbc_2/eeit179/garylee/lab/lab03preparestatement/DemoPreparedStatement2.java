package jdbc_2.eeit179.garylee.lab.lab03preparestatement;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import jdbc_2.eeit179.garylee.lab.util.JDBCutils;

public class DemoPreparedStatement2 {
	
	private static Connection conn;
	
	
//	CRUD
//	增 新增
	public void insert() throws Exception {
		String sql="INSERT INTO user(name,password,email) VALUES(?,?,?)";
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, "prepared");
			prepareStatement.setString(2, "123456");
			prepareStatement.setString(3, "test2@mail.com");
			int row = prepareStatement.executeUpdate();
			System.out.println("新增"+row+"筆");
			prepareStatement.close();
	}
	
//	刪 刪除
	public void deletebyId(int id) throws SQLException {
		String sql="DELETE FROM user WHERE id = ?";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, id);
		int row = prepareStatement.executeUpdate();
		System.out.println("刪除"+row+"筆");
		prepareStatement.close();
	}
	
//	改 更新
//	參數是姓名 跟新的password email phone address
	public void updateUserByName(String name,String password,String email,String phone,String address) throws SQLException {
		String sql="UPDATE user SET password=?,email=?,phone=?,address=? WHERE name=?";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setString(5,name);
		prepareStatement.setString(1,password);
		prepareStatement.setString(2,email);
		prepareStatement.setString(3,phone);
		prepareStatement.setString(4,address);
		int row = prepareStatement.executeUpdate();
		System.out.println("更新"+row+"筆");
		prepareStatement.close();
		
	}
	
	public void findUserById(int id) throws SQLException {
		String sql="SELECT * FROM user WHERE id =?";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, id);
		ResultSet rs = prepareStatement.executeQuery();
//		while (rs.next()) {
//			System.out.println(rs.getInt("id")+","+rs.getString("name")+","+rs.getString("password")+","+rs.getString("email")+","+rs.getString("phone")+","+rs.getString("address"));
//		}
		if(rs.next()) {
			System.out.println(rs.getInt("id")+","+rs.getString("name")+","+rs.getString("password")+","+rs.getString("email")+","+rs.getString("phone")+","+rs.getString("address"));			
		}else {
			System.out.println("查無此人");
		}
		rs.close();
		prepareStatement.close();
	}
	
//	查全部
	public void findAllUser() throws SQLException {
		String sql="SELECT * FROM user";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		ResultSet rs = prepareStatement.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getInt("id")+","+rs.getString("name")+","+rs.getString("password")+","+rs.getString("email")+","+rs.getString("phone")+","+rs.getString("address"));						
		}
		rs.close();
		prepareStatement.close();
	}
	
	
	public static void main(String[] args) {
		DemoPreparedStatement2 demoPreparedStatement = new DemoPreparedStatement2();
		try {
			conn = JDBCutils.getConnection();
//			demoPreparedStatement.insert();
//			demoPreparedStatement.deletebyId(1);
//			demoPreparedStatement.updateUserByName("Ben", "abc", "newben@email.com", "1995", "F203");
//			demoPreparedStatement.findUserById(2);
			demoPreparedStatement.findAllUser();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JDBCutils.closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
