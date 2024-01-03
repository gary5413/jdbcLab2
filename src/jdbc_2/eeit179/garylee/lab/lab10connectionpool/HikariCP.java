package jdbc_2.eeit179.garylee.lab.lab10connectionpool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCP {
	
	private HikariDataSource ds;
	
	public HikariDataSource openDataSource() {
//基本設定一
		//		HikariConfig config = new HikariConfig();
//		config.setJdbcUrl("jdbc:mysql://localhost:3306/mytestdb");
//		config.setUsername("root");
//		config.setPassword("5413gary");
//		config.addDataSourceProperty("cachePrepStmts", "true");
//		config.addDataSourceProperty("prepStmtCacheSize", "250");
//		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

//		基本設定二
		HikariConfig config = new HikariConfig("src/hikariCP.properties");
		HikariDataSource ds = new HikariDataSource(config);
		return ds;
	}
	
	public void closeDataSource() {
		if(ds!=null) {
			ds.close();
		}
	}
}
