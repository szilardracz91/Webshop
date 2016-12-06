package WebShop.web;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import WebShop.model.Basket;
import WebShop.model.Client;
import WebShop.model.Comment;
import WebShop.model.Product;
import WebShop.services.BasketService;
import WebShop.services.ClientService;
import WebShop.services.ProductService;

@ManagedBean
@SessionScoped
public class BasketController {
	
	private String clientName = new String();
	public String getClientName() {
		return clientName;
	}


	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	private int productId;
	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}

	private Basket newOrder = new Basket(); 
	private List<Basket> orders = null;
	public List<Basket> getOrders() {
		return orders;
	}


	public void setOrders(List<Basket> orders) {
		this.orders = orders;
	}

	List<Product> products = null;
	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}


	@EJB
	BasketService basketService;
	@EJB
	ProductService productService;
	@EJB
	ClientService clientService;
	
	public Basket getNewOrder() {
		return newOrder;
	}


	public void setNewOrder(Basket newOrder) {
		this.newOrder = newOrder;
	}
	
	
	public void putInBasket(){

		Product product = productService.find(productId);
		Client client = clientService.find(clientName);

		newOrder = new Basket(client, product);

		basketService.create(newOrder);
	}
	
	public List<Product> getUserBasket(){	
		products =  new ArrayList<Product>();
		orders =  basketService.filterWithUser(clientName);
		for(Basket order : orders){
			products.add(order.getProduct());
		}
		return products;
		
	}
}
