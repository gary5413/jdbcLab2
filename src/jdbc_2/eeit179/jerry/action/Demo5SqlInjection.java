package jdbc_2.eeit179.jerry.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo5SqlInjection {

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
	
	public boolean checkLogin(String username,String password) throws SQLException {
		String sql="SELECT * FROM user_table WHERE user = '"+username+"' and password= '"+password+"'";
		System.out.println(sql);
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		boolean checkOK = rs.next();
		return checkOK;
	}
	
	public static void main(String[] args) {
		
		Demo5SqlInjection demo5 = new Demo5SqlInjection();
//		String loginUser="AA";
		String loginUser=" ' OR 1=1 -- ";
		String loginPassword="123456";
		try {
			demo5.createConnection();
			boolean result = demo5.checkLogin(loginUser, loginPassword);
			if(result) {
				System.out.println("登入成功");
			}else {
				System.out.println("登入失敗");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				demo5.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
