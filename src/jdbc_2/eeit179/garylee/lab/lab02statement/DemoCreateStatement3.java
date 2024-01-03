package jdbc_2.eeit179.garylee.lab.lab02statement;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import jdbc_2.eeit179.garylee.lab.util.JDBCutils;

public class DemoCreateStatement3 {
	

//	CRUD 
//	增 新增 
	public void insert() {
		String sql="INSERT INTO `user`(name,`password`,email) VALUES('test','45678','test@mail.com')";
		Statement statement=null;
		Connection conn=null;
		try {
			conn = JDBCutils.getConnection();
			statement = conn.createStatement();
			int row = statement.executeUpdate(sql);//回傳int 成功筆數	
			System.out.println("新增"+row+"筆");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JDBCutils.closeConnection(conn, statement);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
//	刪 刪除
	public void delete() {
		Statement statement=null;
		Connection conn=null;
		try {
			String sql="DELETE FROM user WHERE id=7";
			conn = JDBCutils.getConnection();
			statement = conn.createStatement();
			int row = statement.executeUpdate(sql);
			System.out.println("刪除"+row+"筆");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				JDBCutils.closeConnection(conn, statement);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
//	改 更新
//	這邊讓同學操作
	public void update()  {
		Connection conn = null;
		Statement statement = null;
		try {
			String sql="UPDATE user SET name='banana',email='banana@mail.com' WHERE id=1";
			 conn = JDBCutils.getConnection();
			 statement = conn.createStatement();
			int row = statement.executeUpdate(sql);
			System.out.println("更新"+row+"筆");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				JDBCutils.closeConnection(conn,statement );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
//	查 搜尋 讀取
	public void queryCustomers() {
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			String sql="SELECT * FROM user";
			conn = JDBCutils.getConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				System.out.println(resultSet.getInt(1)+","+resultSet.getString(2)+","+resultSet.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JDBCutils.closeConnection(conn, statement, resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
//	搜尋單筆
//	給同學操作
	public void findCustomerById()  {
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			String sql="SELECT * FROM user WHERE id =1";
			 conn = JDBCutils.getConnection();
			 statement = conn.createStatement();
			 resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1)+","+resultSet.getString(2)+","+resultSet.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JDBCutils.closeConnection(conn, statement, resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
//	考慮放在preparestatement在變化展示 
//	public void insert2(String name,String email) throws SQLException {
//		String sql="INSERT INTO customers(name,email) VALUES('"+name+"','"+email+"')";
//		Statement statement = conn.createStatement();
////		statement.execute(sql);	
//		int row = statement.executeUpdate(sql);//回傳int 成功筆數	
//		System.out.println("新增"+row+"筆");
//		statement.close();
//	}
	
	
	
	public static void main(String[] args) {
//		介紹實體化 什麼是new
		DemoCreateStatement3 demo02 = new DemoCreateStatement3();
//		demo02.insert();
//		demo02.delete();
//		demo02.update();
		demo02.queryCustomers();
//		demo02.findCustomerById();
			
	}


}
