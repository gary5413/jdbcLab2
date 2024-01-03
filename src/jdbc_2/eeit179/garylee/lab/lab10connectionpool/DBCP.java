package jdbc_2.eeit179.garylee.lab.lab10connectionpool;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBCP {
	
	private BasicDataSource ds;
	
	public BasicDataSource openDataSource() {
		BasicDataSource ds =new BasicDataSource();
//		基本設定
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/mytestdb");
		ds.setUsername("root");
		ds.setPassword("5413gary");
//		設定其他連線池設定
		ds.setInitialSize(10);
		ds.setMaxTotal(10);
		return ds;
	}
	public void closeDataSource() {
		if(ds!=null) {
			try {
				ds.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
