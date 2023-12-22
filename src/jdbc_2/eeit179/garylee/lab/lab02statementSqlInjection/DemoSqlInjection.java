package jdbc_2.eeit179.garylee.lab.lab02statementSqlInjection;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class DemoSqlInjection {
	
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
	
//	方法先不寫傳遞變數 再展示
	public boolean login(String name,String password) throws SQLException {
//		String sql="SELECT * FROM user WHERE name='Ben' and password ='123456' ";
//		String sql="SELECT * FROM user WHERE name=' a' or 1=  ' and password =' or '1' = '1 ' ";
		String sql="SELECT * FROM user WHERE name='"+name+"' and password ='"+password+"' ";
//		                                                       a' or 1=                    or '1' = '1
		Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		boolean checkOK = resultSet.next();
		resultSet.close();
		statement.close();
		return  checkOK;
	}
	
	public boolean login2(String name,String password) throws SQLException {
		String sql="SELECT * FROM user WHERE name=? and password=?";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setString(1, name);
		prepareStatement.setString(2, password);
		ResultSet resultSet = prepareStatement.executeQuery();
		boolean checkOK = resultSet.next();
		resultSet.close();
		prepareStatement.close();
		return  checkOK;
	}
	
	
	public static void main(String[] args) {
		boolean status=true;
		Scanner scanner = new Scanner(System.in);
		while(status) {
			System.out.println("姓名：");
//			nextLine() 讀一整行的字串回傳。
			String name = scanner.nextLine();
			System.out.println("密碼：");
			String password= scanner.nextLine();
			DemoSqlInjection demoSqlInjection = new DemoSqlInjection();
			try {
				demoSqlInjection.CreateConnection();
//				boolean checkOK = demoSqlInjection.login(name, password);
				boolean checkOK = demoSqlInjection.login2(name, password);
					if(checkOK) {
						System.out.println("登入成功");
						status=false;
					}else {
						System.out.println("登入失敗");
						status=true;
					}
			
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					demoSqlInjection.closeConnection();
					System.out.println("==============================");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}//psvm of end

}
