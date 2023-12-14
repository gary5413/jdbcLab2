package jdbc_2.eeit179.jerry.model;

public class Etf {
	private Integer id;
	private String stockId;
	private String stockName;
	private Integer monthQuantity;
	public Etf() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public Integer getMonthQuantity() {
		return monthQuantity;
	}
	public void setMonthQuantity(Integer monthQuantity) {
		this.monthQuantity = monthQuantity;
	}
	
}
