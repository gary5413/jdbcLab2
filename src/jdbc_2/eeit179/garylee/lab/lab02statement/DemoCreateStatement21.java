package jdbc_2.eeit179.garylee.lab.lab02statement;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DemoCreateStatement21 {
	
	private Connection conn;

	public void CreateConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream(new File("src/jdbc2.properties"));
		properties.load(fileInputStream);
		String url=properties.getProperty("url");
		String user=properties.getProperty("user");
		String password=properties.getProperty("password");
//		this關鍵字
		/*
		 * 區分成員變數和區域變數： 如果方法的參數名稱或方法內部的區域變數與類的成員變數同名，
		 * 使用 this 關鍵字可以將引用限定為類的成員變數。這樣可以區分出哪個是成員變數，哪個是區域變數。
		 * 在你的程式碼中，this.conn 用來指向類的成員變數 conn，而不是方法內部的 conn。
		 */
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
//	CRUD 
//	增 新增 
	public void insert() throws SQLException {
		String sql="INSERT INTO `user`(name,`password`,email) VALUES('test','45678','test@mail.com')";
		Statement statement = conn.createStatement();
//		statement.execute(sql);	
		int row = statement.executeUpdate(sql);//回傳int 成功筆數	
//		System.out.println("執行execute");
		System.out.println("新增"+row+"筆");
		statement.close();
	}
	
//	刪 刪除
	public void delete() throws SQLException {
		String sql="DELETE FROM user WHERE id=7";
		Statement statement = conn.createStatement();
		int row = statement.executeUpdate(sql);
		System.out.println("刪除"+row+"筆");
		statement.close();
	}
//	改 更新
//	這邊讓同學操作
	public void update() throws SQLException {
		String sql="UPDATE user SET name='banana',email='banana@mail.com' WHERE id=1";
		Statement statement = conn.createStatement();
		int row = statement.executeUpdate(sql);
		System.out.println("更新"+row+"筆");
		statement.close();
	}
	
//	查 搜尋 讀取
	public void queryCustomers() throws SQLException {
		String sql="SELECT * FROM user";
		Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
//		boolean result = resultSet.next();
//		System.out.println("result:"+result);
		while(resultSet.next()) {
			System.out.println(resultSet.getInt(1)+","+resultSet.getString(2)+","+resultSet.getString(3));
		}
		resultSet.close();
		statement.close();
	}
//	搜尋單筆
//	給同學操作
	public void findCustomerById() throws SQLException {
		String sql="SELECT * FROM user WHERE id =1";
		Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			System.out.println(resultSet.getInt(1)+","+resultSet.getString(2)+","+resultSet.getString(3));
		}
		resultSet.close();
		statement.close();
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
		DemoCreateStatement21 demo02 = new DemoCreateStatement21();
			try {
				demo02.CreateConnection();
//				demo02.insert();
//				demo02.delete();
//				demo02.update();
//				demo02.queryCustomers();
				demo02.findCustomerById();
//				不展示
//				demo02.insert2("insert2","insert2@mail.com");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					demo02.closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}				
			}
		
	}


}
