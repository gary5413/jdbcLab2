package jdbc_2.eeit179.garylee.lab.lab09.product;

import java.sql.SQLException;
import java.util.List;

import jdbc_2.eeit179.garylee.lab.lab09.product.dao.ProductDao;
import jdbc_2.eeit179.garylee.lab.lab09.product.model.Product;

public class ProdcutAction {

	
	public static void main(String[] args) {
		
		ProductDao productDao = new ProductDao();
		try {
			productDao.createConnection();
//			List<Product> productsList = productDao.getProducts();
//			for (Product product : productsList) {
//				System.out.println(product.getProductName());
//			}
//			Product product = productDao.getProductById(1);
//			System.out.println(product.getProductName());
			
//			Product product = new Product("jdbc講義","BOOK",".jpg",1,1,"我愛JDBC");
//			productDao.createProduct(product);
			
//			Product product = new Product("apple","FOOD","apple.jpg",200,5,"Ａ");
//			productDao.updateProductById(1, product);
			
//			productDao.updateStockById(2, 100);
			
			productDao.deletedProductById(7);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				productDao.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
