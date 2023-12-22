package jdbc_2.eeit179.garylee.lab.lab05blobImage;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;



public class DemoBlobImage {
	
	private Connection conn;

	public void CreateConnection() throws Exception {
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
	
	public void saveImg() throws SQLException, IOException {
		String sql="INSERT INTO user(name,password,photo) VALUES(?,?,?)";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setString(1, "cat");
		prepareStatement.setString(2, "cat");
		FileInputStream fis = new FileInputStream(new File("resource2/sleepcat.jpg"));
		prepareStatement.setBinaryStream(3, fis);
		int row = prepareStatement.executeUpdate();
		System.out.println("新增"+row+"筆");
		fis.close();
		prepareStatement.close();
	}
	
	public void getImg() throws SQLException, IOException 
	{
		String sql="SELECT photo FROM user WHERE id =?";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, 10);
		ResultSet rs = prepareStatement.executeQuery();
		rs.next();
		boolean wasNull = rs.wasNull();
		System.out.println(wasNull);
//		注意是sql.blob
		Blob blob= rs.getBlob(1);
		FileOutputStream fos = new FileOutputStream("resource2/test2.jpg");
//		fos.write(blob.getBytes(1, (int)blob.length()));
		fos.write(blob.getBytes(1, 5000));
		fos.close();
		rs.close();
		prepareStatement.close();
		System.out.println("圖片輸出完成");
	}
	
	public void getImg2() throws SQLException, IOException 
	{
		String sql="SELECT photo FROM user WHERE id =?";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, 10);
		ResultSet rs = prepareStatement.executeQuery();
		rs.next();
		boolean wasNull = rs.wasNull();
		System.out.println(wasNull);
//		注意是sql.blob
		Blob blob= rs.getBlob(1);
		FileOutputStream fos = new FileOutputStream("resource2/test2.jpg");
//		記憶體緩衝區
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		bos.write(blob.getBytes(1, (int)blob.length()));
//		寫出緩衝區
		bos.flush();
		bos.close();
		fos.close();
		rs.close();
		prepareStatement.close();
		System.out.println("圖片輸出完成");
	}
	
	
	public static void main(String[] args) {
		DemoBlobImage demoBlobImage = new DemoBlobImage();
		try {
			demoBlobImage.CreateConnection();
//			demoBlobImage.saveImg();
			demoBlobImage.getImg();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				demoBlobImage.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
