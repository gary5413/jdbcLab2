package jdbc_2.eeit179.garylee.lab.lab09.product.dao;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.print.attribute.standard.PresentationDirection;

import jdbc_2.eeit179.garylee.lab.lab09.product.model.Product;

public class ProductDao2Impl implements ProductDao2 {
	
	private Connection conn;
	
	@Override
	public void createConnection() throws Exception {
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
	
	@Override
	public void closeConnection() throws SQLException {
		if(conn !=null) {
			conn.close();
			System.out.println("關閉連線");
		}
	}

//	查詢全部商品
	@Override
	public List<Product> getProducts() throws SQLException{
		String sql="SELECT * FROM product";
		ArrayList<Product> productList = new ArrayList<Product>();
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		ResultSet rs = prepareStatement.executeQuery();
		while(rs.next()) {
			Integer productId=rs.getInt("product_id");
			String productName=rs.getString("product_name");
			String category=rs.getString("category");
			String imageUrl=rs.getString("image_url");
			Integer price=rs.getInt("price");
			Integer stock=rs.getInt("stock");
			String description=rs.getString("description");
			Date createDate=rs.getDate("created_date");
			Date lastModifiedDate=rs.getDate("last_modified_date");
			Product product = new Product(productId,productName,category,imageUrl,price,stock,description,createDate,lastModifiedDate);
			productList.add(product);
		}
		prepareStatement.close();
		return productList;
	}
//	查詢單一商品
	@Override
	public Product getProductById(Integer productId) throws SQLException {
		String sql="SELECT * FROM product WHERE product_id=?";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, productId);
		ResultSet rs = prepareStatement.executeQuery();
		rs.next();
		String productName=rs.getString("product_name");
		String category=rs.getString("category");
		String imageUrl=rs.getString("image_url");
		Integer price=rs.getInt("price");
		Integer stock=rs.getInt("stock");
		String description=rs.getString("description");
		Date createDate=rs.getDate("created_date");
		Date lastModifiedDate=rs.getDate("last_modified_date");
		Product product = new Product(productId,productName,category,imageUrl,price,stock,description,createDate,lastModifiedDate);
		prepareStatement.close();
		return product;
		
	}
//	新增商品
	@Override
	public void createProduct(Product product) throws SQLException {
		String sql="INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date)"
				+ "VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setString(1, product.getProductName());
		prepareStatement.setString(2, product.getCategory());
		prepareStatement.setString(3, product.getImageUrl());
		prepareStatement.setInt(4, product.getPrice());
		prepareStatement.setInt(5, product.getStock());
		prepareStatement.setString(6, product.getDescription());
		LocalDate now = LocalDate.now();
		prepareStatement.setDate(7, java.sql.Date.valueOf(now));
	    prepareStatement.setDate(8, java.sql.Date.valueOf(now));
		prepareStatement.execute();
		prepareStatement.close();
	}
//	更新商品數量
	@Override
	public void updateStockById(Integer productId,Integer stock) throws SQLException {
		String sql="UPDATE product SET stock=?,last_modified_date=?"
				+ "WHERE product_id =?";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, stock);
		LocalDate now = LocalDate.now();
		prepareStatement.setDate(2, java.sql.Date.valueOf(now));
		prepareStatement.setInt(3, productId);
		prepareStatement.execute();
		prepareStatement.close();
	}
	
//	更新商品 暫時不考慮展示
	@Override
	public void updateProductById(Integer productId,Product product) throws SQLException {
		String sql="UPDATE product SET product_name=?, category=?, image_url=?, price=?, stock=?, description=?, last_modified_date=? "
				+ " WHERE product_id =?";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setString(1, product.getProductName());
		prepareStatement.setString(2, product.getCategory());
		prepareStatement.setString(3, product.getImageUrl());
		prepareStatement.setInt(4, product.getPrice());
		prepareStatement.setInt(5, product.getStock());
		prepareStatement.setString(6, product.getDescription());
		LocalDate now = LocalDate.now();
		prepareStatement.setDate(7, java.sql.Date.valueOf(now));
		prepareStatement.setInt(8, productId);
		prepareStatement.execute();
		prepareStatement.close();
	}
//	刪除商品
	@Override
	public void deletedProductById(Integer productId) throws SQLException {
		String sql="DELETE FROM product WHERE product_id=?";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, productId);
		prepareStatement.execute();
		prepareStatement.close();
	}
	
}
