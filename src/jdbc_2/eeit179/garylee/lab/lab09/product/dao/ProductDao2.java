package jdbc_2.eeit179.garylee.lab.lab09.product.dao;

import java.sql.SQLException;
import java.util.List;

import jdbc_2.eeit179.garylee.lab.lab09.product.model.Product;

public interface ProductDao2 {

	void createConnection() throws Exception;

	void closeConnection() throws SQLException;

	//	查詢全部商品
	List<Product> getProducts() throws SQLException;

	//	查詢單一商品
	Product getProductById(Integer productId) throws SQLException;

	//	新增商品
	void createProduct(Product product) throws SQLException;

	//	更新商品數量
	void updateStockById(Integer productId, Integer stock) throws SQLException;

	//	更新商品 暫時不考慮展示
	void updateProductById(Integer productId, Product product) throws SQLException;

	//	刪除商品
	void deletedProductById(Integer productId) throws SQLException;

}