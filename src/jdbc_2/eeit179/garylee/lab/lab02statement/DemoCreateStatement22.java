package jdbc_2.eeit179.garylee.lab.lab02statement;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DemoCreateStatement22 {
	/*
	 * 靜態變數： static 修飾符表示這個變數是與類相關聯的，
	 * 而不是與類的實例（物件）相關聯的。這意味著，無論創建了多少個類的實例，
	 * conn 都只有一個拷貝。當你聲明一個變數為 static 時，它將與類一同存在，而不是與實例相關聯。
	 * 資料共享： 在這個特定的案例中，conn 變數是用來保存資料庫連接的實例。
	 * 如果它不是 static，那麼每次創建 DemoCreateStatement2 的實例時都會有一個新的 conn，
	 * 這樣在 main 方法中就無法共用相同的資料庫連接。
	 * 使用 static 保證了 conn 變數在整個類中是唯一的，這樣可以確保所有方法都在操作相同的連接實例。
	 */
	private static Connection conn;

	public Connection CreateConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream(new File("src/jdbc2.properties"));
		properties.load(fileInputStream);
		String url=properties.getProperty("url");
		String user=properties.getProperty("user");
		String password=properties.getProperty("password");
		conn = DriverManager.getConnection(url,user,password);
		boolean status = !conn.isClosed();
		if(status) {
			System.out.println("開啟連線");
		}
		return conn;
	}
	
	public void closeConnection(Connection conn) throws SQLException {
		if(conn !=null) {
			conn.close();
			System.out.println("關閉連線");
		}
	}
	
	public void insert() throws SQLException {
		String sql="INSERT INTO customers(name,email,birth) VALUES('insert','test@mail.com','2023-12-20')";
		Statement statement = conn.createStatement();
		statement.execute(sql);	
		System.out.println("執行excute");
		statement.close();
	}
	
	
	public static void main(String[] args) {
//		介紹實體化 什麼是new
		DemoCreateStatement22 demo02 = new DemoCreateStatement22();
			try {
				conn = demo02.CreateConnection();
				demo02.insert();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					demo02.closeConnection(conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}				
			}
		
	}


}
