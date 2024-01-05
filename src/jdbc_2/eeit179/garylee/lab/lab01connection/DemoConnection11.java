package jdbc_2.eeit179.garylee.lab.lab01connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DemoConnection11 {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/mytestdb";
		String user = "root";
		String password = "5413gary";
/*
 * 	Java提供了try-with-resource語法，這種語法可以代替我們執行close方法來進行釋放資源，
 *  可以使資源得到更好的利用，同時大大縮短了程式代碼的長度。
	使用try-with-resource語法時需要注意，
	寫在try( )中需要繼承AutoCloseable的介面才能進行所謂的自動關閉，
	且這個變數只能在try區塊所使用。
	如此一來，我們便不需要再寫finally區塊，
	就能在執行完成或是發生例外時執行資源的釋放。
 */		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, password)) {
				boolean status = !conn.isClosed();
				System.out.println("連線狀態：" + status);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
	}
}
