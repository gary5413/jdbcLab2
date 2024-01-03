package jdbc_2.eeit179.garylee.lab.lab10connectionpool;


import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0 {

	private ComboPooledDataSource ds;
	public ComboPooledDataSource openDataSource() throws Exception {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		ds.setDriverClass( "com.mysql.cj.jdbc.Driver" );
		ds.setJdbcUrl( "jdbc:mysql://localhost:3306/mytestdb" );
		ds.setUser("root");                                  
		ds.setPassword("5413gary");     
		return ds;
	}
	
	public void closeDataSource() {
		if(ds!=null) {
			ds.close();
		}
	}
}
