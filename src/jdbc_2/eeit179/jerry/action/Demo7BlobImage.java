package jdbc_2.eeit179.jerry.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Demo7BlobImage {
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
	
	public void saveImageInDB() throws Exception {
		File file = new File("C:/myImage/question_mark.jpg");
		FileInputStream fis = new FileInputStream(file);
		String sql="INSERT INTO gallery(image_name,image_file) VALUES(?,?))";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1, "meme1");
		preState.setBinaryStream(2, fis);
		preState.execute();
		preState.close();
		fis.close();
		System.out.println("save file OK");
		
	}

	public static void main(String []args) {
		Demo7BlobImage demo7 = new Demo7BlobImage();
		try {
			demo7.createConnection();
			demo7.saveImageInDB();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				demo7.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
