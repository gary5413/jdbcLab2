package jdbc_2.eeit179.jerry.action;

import java.sql.SQLException;
import java.util.List;

import jdbc_2.eeit179.jerry.model.User;
import jdbc_2.eeit179.jerry.model.UserDao;

public class Demo12DaoAction {

	public static void main(String[] args) {
		UserDao userDao = new UserDao();
		
		//簡單商業邏輯
		User user1 = new User("test","test","test","test");
		
		try {
			//dao處理	
			userDao.createConnection();
//			userDao.addUser(user1);
//			User userTest = userDao.findUserById(6);
//			System.out.println(userTest.getUserName());
//			System.out.println(userTest.getUserPassword());
//			System.out.println(userTest.getUserAddress());
//			System.out.println(userTest.getUserPhone());
//			List<User> allUser = userDao.getAllUser();
//			for (User user : allUser) {
//				System.out.println("id:"+user.getUserId());
//				System.out.println("name:"+user.getUserName());
//				System.out.println("password:"+user.getUserPassword());
//				System.out.println("address:"+user.getUserAddress());
//				System.out.println("phone:"+user.getUserPhone());
//				System.out.println("=========================ß");
//			}
//			userDao.updateAddressById(6, "new address");
			userDao.deleteById(6);
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				userDao.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
