package jdbc_2.eeit179.garylee.lab.lab01connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DemoConnection31 {
	public static void main(String[] args) {
		try (FileInputStream fileInputStream = new FileInputStream(new File("src/jdbc2.properties"))) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Properties properties = new Properties();
            properties.load(fileInputStream);
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                boolean status = !conn.isClosed();
                System.out.println("連線狀態：" + status);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
	}
}
