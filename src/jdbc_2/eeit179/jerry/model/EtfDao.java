package jdbc_2.eeit179.jerry.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class EtfDao {
	
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
	
	public void insertEtfData(String stockId,String stockName,Integer stockQuantity) throws SQLException {
		String sql="insert into ETF_V(stock_id,stock_name,month_quantity) values(?,?,?)";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1, stockId);
		preState.setString(2, stockName);
		preState.setInt(3, stockQuantity);
		preState.execute();
		preState.close();
		System.out.println("Insert");
	}
	
	
	
}
