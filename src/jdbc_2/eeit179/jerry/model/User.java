package jdbc_2.eeit179.jerry.model;

public class User {
	private Integer userId;
	private String userName;
	private String userPassword;
	private String userAddress;
	private String userPhone;
	
	
	public User() {
		super();
	}
	
	
	public User(String userName, String userPassword, String userAddress, String userPhone) {
		super();
		
		this.userName = userName;
		this.userPassword = userPassword;
		this.userAddress = userAddress;
		this.userPhone = userPhone;
	}


	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	
	
}
