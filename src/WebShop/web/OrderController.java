//package WebShop.web;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.ejb.EJB;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
//import javax.faces.bean.SessionScoped;
//
//import WebShop.model.Basket;
//import WebShop.model.Client;
//import WebShop.model.Comment;
//import WebShop.model.Order;
//import WebShop.model.Product;
//import WebShop.services.BasketService;
//import WebShop.services.ClientService;
//import WebShop.services.OrderService;
//import WebShop.services.ProductService;
//
//@ManagedBean
//@SessionScoped
//public class OrderController {
//	
//	private String clientName = new String();
//	public String getClientName() {
//		return clientName;
//	}
//
//
//	public void setClientName(String clientName) {
//		this.clientName = clientName;
//	}
//
//	private int productId;
//	public int getProductId() {
//		return productId;
//	}
//
//
//	public void setProductId(int productId) {
//		this.productId = productId;
//	}
//
//	private Order newOrder = new Order(); 
//	private Order order = null;
//	
//
//	public Order getOrder() {
//		return order;
//	}
//
//
//	public void setOrder(Order order) {
//		this.order = order;
//	}
//
//	List<Product> products = null;
//	public List<Product> getProducts() {
//		return products;
//	}
//
//
//	public void setProducts(List<Product> products) {
//		this.products = products;
//	}
//
//
//	@EJB
//	OrderService orderService;
//	@EJB
//	ProductService productService;
//	@EJB
//	ClientService clientService;
//	
//	public Order getNewOrder() {
//		return newOrder;
//	}
//
//
//	public void setNewOrder(Order newOrder) {
//		this.newOrder = newOrder;
//	}
//	
//	
//	public void createNewOrder(){
//		System.out.println("aha");
//		for(Product prod : products)
//			System.out.println(prod.getProductName());
//		System.out.println(clientName);
//		Product product = productService.find(productId);
//		Client client = clientService.find(clientName);
//
//		//newOrder = new Order(client, product, "under the purchase");
//
//		//orderService.create(newOrder);
//	}
//	
//	public List<Product> getUserOrderProducts(){	
//		products =  new ArrayList<Product>();
//		order =  orderService.filterWithUser(clientName);
//		for(Product prod : order.getProducts()){
//			products.add(prod);
//		}
//		return products;
//		
//	}
//}
