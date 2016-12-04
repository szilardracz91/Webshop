package WebShop.web;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import WebShop.model.Basket;
import WebShop.model.Product;
import WebShop.services.BasketService;
import WebShop.services.ProductService;

@ManagedBean
@SessionScoped
public class BasketController {
	
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

	private String userName = new String();
	
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	@EJB
	BasketService basketService;
	@EJB
	ProductService productService;
	
	public Basket getNewOrder() {
		return newOrder;
	}


	public void setNewOrder(Basket newOrder) {
		this.newOrder = newOrder;
	}
	
	
	public void putInBasket(){
		basketService.create(newOrder);
	}
	
	public List<Product> getUserBasket(){	
		products =  new ArrayList<Product>();
		orders =  basketService.filterWithUser(userName);
		for(Basket order : orders){
			products.add(productService.find(order.getProductId()));
		}
		return products;
		
	}
}
