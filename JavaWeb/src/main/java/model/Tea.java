package model;

public class Tea {
	private String id; // 商品代號
	private String name; // 品名
	private Integer price; // 價格
	private Integer qty; // 庫存
	
	public Tea() {
		
	}
	
	public Tea(String id, String name, Integer price, Integer qty) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.qty = qty;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
	
}
