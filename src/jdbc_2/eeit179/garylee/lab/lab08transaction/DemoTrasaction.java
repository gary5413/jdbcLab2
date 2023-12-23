package jdbc_2.eeit179.garylee.lab.lab08transaction;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import jdbc_2.eeit179.garylee.lab.util.JDBCutils;

public class DemoTrasaction {
/*
 * 1. 什麼是資料庫事務
 * 事務 一組邏輯操作單元 使資料從一種狀態轉到另一種狀態
 *  	> 一組邏輯操作單元 一個或多DML
 * 2. 事務處理的原則 要所有事務都完成 就commit
 * 		如果過程中有出現異常 就rollback
 * 
 * 3. 資料一但commit 就不可rollback
 * 
 * 4. 哪些操作會導致資料自動提交
 * 		DDL 操作一但執行 都會自動提交
 * 			我們可以通過 set autocommit =false 方式取消 DML
 * 		DML 默認情況下 一但執行 就會自動提交
 * 			我們可以通過 set autocommit =false 方式取消 DML
 * 		默認在關閉連接時,會自動提交
 * 
 *  事務的ACID
 *  	
		1. 原子性(Atomicity) 原子性是指事务是一个不可分割的工作单位，事务中的操作要么都发生，要么都不发 生。
		2. 一致性(Consistency) 事务必须使数据库从一个一致性状态变换到另外一个一致性状态。
		3. 隔离性(Isolation) 事务的隔离性是指一个事务的执行不能被其他事务干扰，即一个事务内部的操作及使用的
  			数据对并发的其他事务是隔离的，并发执行的各个事务之间不能互相干扰。
		4. 持久性(Durability) 持久性是指一个事务一旦被提交，它对数据库中数据的改变就是永久性的，接下来的其
  			他操作和数据库故障不应该对其有任何影响。
  			
  	 交易的幾個問題
  	 	髒讀
  	 		 T1讀取已被T2更新但未commit資料 之後若T2rollback T1內容就是臨時且無效的
  	 	不可重複讀
  	 		T1 T2讀取同一個資料 然後T2更新資料後 T1在讀取直就不同了
  	 	幻讀
  	 		T1 T2讀取同一個資料 然後T2插入新的資料後 如果T1在讀取同一資料就會多出幾行
 */
	
	
	private Connection conn;
	public void createConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream(new File("src/jdbc2.properties"));
		properties.load(fileInputStream);
		String url=properties.getProperty("url");
		String user=properties.getProperty("user");
		String password=properties.getProperty("password");
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

	public void testUpdate() throws SQLException {
		String sql="UPDATE account SET account_balance = ? WHERE account_name =?";
		
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setString(2, "AA");
		prepareStatement.setInt(1, 1000);
		prepareStatement.execute();
		
//		System.out.println(10/0);
		
		prepareStatement.setString(2, "BB");
		prepareStatement.setInt(1, 2000);
		prepareStatement.execute();
		
		prepareStatement.close();
		
	}

	
	public void testUpdateWithTX() throws SQLException {
		String sql="UPDATE account SET account_balance = ? WHERE account_name =?";
		
//			開啟隱含交易
			conn.setAutoCommit(false);
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(2, "AA");
			prepareStatement.setInt(1, 900);
			prepareStatement.execute();
//			模擬失敗
//			System.out.println(10/0);
			prepareStatement.setString(2, "BB");
			prepareStatement.setInt(1, 2100);
			prepareStatement.execute();
//			提交交易
			conn.commit();
			System.out.println("轉帳成功");
//			恢復預設
			conn.setAutoCommit(true);
			prepareStatement.close();
	}
	
	
	
	public static void main(String[] args) {
		DemoTrasaction demoTrasaction = new DemoTrasaction();
		try {
			demoTrasaction.createConnection();
//			demoTrasaction.testUpdate();
			demoTrasaction.testUpdateWithTX();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				demoTrasaction.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
