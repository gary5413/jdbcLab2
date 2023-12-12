package jdbc_2.eeit179.jerry.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
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
	
	public void addUser(User user) throws SQLException {
		String sql="INSERT INTO user(name,password,address,phone) VALUES(?,?,?,?)";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1, user.getUserName());
		preState.setString(2, user.getUserPassword());
		preState.setString(3, user.getUserAddress());
		preState.setString(4, user.getUserPhone());
		preState.execute();
		preState.close();
		System.out.println("新增資料完成");
	}
	
	public User findUserById(Integer userId) throws SQLException {
//		透過id 拿User sql
		String sql="SELECT name,password,address,phone FROM user WHERE id=?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setInt(1, userId);
		ResultSet rs = preState.executeQuery();
//		new User 把資料封裝
		User user = new User();
		while(rs.next()) {
			user.setUserName(rs.getString("name"));
			user.setUserPassword(rs.getString("password"));
			user.setUserAddress(rs.getString(3));
			user.setUserPhone(rs.getString(4));
		}
		rs.close();
		preState.close();
//		回傳該User
		return user;
	}
	
	public List<User> getAllUser() throws SQLException{
		String sql="SELECT * FROM user";
		PreparedStatement preState = conn.prepareStatement(sql);
		ResultSet rs = preState.executeQuery();
		ArrayList<User> list = new ArrayList<User>();
		while(rs.next()) {
			User user = new User();
			user.setUserId(rs.getInt(1));
			user.setUserName(rs.getString(2));
			user.setUserPassword(rs.getString(3));
			user.setUserAddress(rs.getString(4));
			user.setUserPhone(rs.getString(5));
			list.add(user);
		}
		rs.close();
		preState.close();
		return list;
	}
	
	public void updateAddressById(Integer userId,String userAddress) throws SQLException {
		String sql="UPDATE user SET address = ? WHERE id=? ";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1,userAddress);
		preState.setInt(2, userId);
		int row = preState.executeUpdate();
		preState.close();
		System.out.println("更新"+row+"筆地址");
	}
	
	public void deleteById(int id) throws SQLException {
		String sql="DELETE FROM user WHERE id=?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setInt(1, id);
		int row = preState.executeUpdate();
		preState.close();
		System.out.println("刪除"+row+"筆資料");
		
	}
	
	
	
}
