package jdbc_2.eeit179.garylee.lab.lab07batch;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class DemoBatchUpdate {
	
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
	
//	批次處理 先示範preparedstatment
	public void testInsert() throws SQLException {
		long start= System.currentTimeMillis();
		String sql="INSERT INTO goods(goods_name) VALUEs (?)";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		int row=0;
		for(int i=1;i<=10000;i++) {
			prepareStatement.setString(1, "name_"+i);
			row = prepareStatement.executeUpdate();
		}
		long end =System.currentTimeMillis();
		System.out.println("共更新"+row+"筆，所花費時間："+(end-start)+"ms");//4996 
		prepareStatement.close();
		
	}
	
	public void insertBatch() throws SQLException {
		String sql="INSERT INTO goods(goods_name) VALUEs (?)";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		long start= System.currentTimeMillis();
		int[] rows = null;
		for(int i=1;i<=10000;i++) {
			prepareStatement.setString(1, "name_"+i);
//			累積sql
			prepareStatement.addBatch();
			if(i % 1000 ==0) {
//				執行batch
				rows=prepareStatement.executeBatch();
//				清空batch
				prepareStatement.clearBatch();
			}
		}
		long end =System.currentTimeMillis();
		System.out.println("共更新"+rows.length+"筆，所花費時間："+(end-start)+"ms"); //4902ms
	}
	
//	batch示範 list
	public void insertBatch2() throws SQLException {
		ArrayList<String> list = new ArrayList<String>();
		list.add("name_1");
		list.add("name_2");
		list.add("name_3");
		String sql="INSERT INTO goods(goods_name) VALUES(?)";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		for (String name : list) {
			prepareStatement.setString(1, name);
			prepareStatement.addBatch();
		}
		int[] rows= prepareStatement.executeBatch();
		System.out.println("新增"+rows.length);
		prepareStatement.close();
	}
	
	
	public static void main(String[] args) {
		DemoBatchUpdate demoBatchUpdate = new DemoBatchUpdate();
		try {
			demoBatchUpdate.createConnection();
//			demoBatchUpdate.testInsert();
//			demoBatchUpdate.insertBatch();
			demoBatchUpdate.insertBatch2();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				demoBatchUpdate.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
