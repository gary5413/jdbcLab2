package jdbc_2.eeit179.garylee.garylee1.connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import jdbc_2.eeit179.garylee.garylee1.util.JDBCutils;

public class ConnectionTest5 {

	public static void main(String[] args) throws Exception  {
		Connection conn = JDBCutils.getConnection();
		System.out.println(conn);
		JDBCutils.close(conn);
	}

}
