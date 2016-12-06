//package WebShop.model;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
//
//
//@Entity
//public class Order {
//	@Id
//	@GeneratedValue
//	private Integer orderId;
//	
//	private String state;
//	
//	public String getState() {
//		return state;
//	}
//
//	public void setState(String state) {
//		this.state = state;
//	}
//
//	@ManyToOne
//    private Client client;
//
//    @ManyToMany
//    private List<Product> products;
//    
//	public List<Product> getProducts() {
//		return products;
//	}
//
//	public void setProducts(List<Product> products) {
//		this.products = products;
//	}
//
//	public Order(){}
//    
//    public Order(Client client, List<Product> products, String state){
//    	this.client=client;
//    	this.products=products;
//    	this.state=state;
//    }
//
//	public Client getClient() {
//		return client;
//	}
//
//	public void setClient(Client client) {
//		this.client = client;
//	}
//	
//}
