package WebShop.web;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import WebShop.model.Basket;
import WebShop.model.Client;
import WebShop.model.Comment;
import WebShop.model.Orders;
import WebShop.model.Product;
import WebShop.services.BasketService;
import WebShop.services.ClientService;
import WebShop.services.OrderService;
import WebShop.services.ProductService;

@ManagedBean
@SessionScoped
public class OrderController {

	private String clientName = new String();
	private String address = new String();
	
	private String bgColorValue;

	public String getBgColorValue() {
		return bgColorValue;
	}

	public void setBgColorValue(String bgColorValue) {
		this.bgColorValue = bgColorValue;
	}

	private DataModel<Orders> items = null;

	public DataModel<Orders> getItems() {
		if (items == null)
			items = new ListDataModel<Orders>(orderService.findAll());
		return items;
	}

	public void setItems(DataModel<Orders> items) {
		this.items = items;
	}
	
	public String prepareList() {
		items = null;
		return FacesUtil.pageWithRedirect("list_orders.xhtml");
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	private Orders newOrder = new Orders();

	List<Product> products = null;

	public List<Product> getProducts() {
		return products;
	}

	List<Orders> orders = null;

	public List<Orders> getOrders() {
		products = new ArrayList<Product>();
		orders = orderService.filterWithUser(clientName);
		return orders;

	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@EJB
	OrderService orderService;
	@EJB
	ProductService productService;
	@EJB
	ClientService clientService;
	@EJB
	BasketService basketService;

	public Orders getNewOrder() {
		return newOrder;
	}

	public void setNewOrder(Orders newOrder) {
		this.newOrder = newOrder;
	}

	public String createNewOrder() {
		Client client = clientService.find(clientName);
		newOrder = new Orders(client, products, address, "under the purchase");
		orderService.create(newOrder);
		basketService.deleteProdocuts(clientName);
		return "Basket.xhtml";
	}
	
	public String remove() {
		orderService.remove(getItems().getRowData());

		FacesUtil.addInfoMessage("Entity successfully removed");

		items = new ListDataModel<Orders>(orderService.findAll());
		return FacesUtil.pageWithRedirect("list_orders.xhtml");
	}
	
	public String setUnderDelivery(){
		Orders order = getItems().getRowData();
		order.setState("Under delivery");
		orderService.edit(order);
		FacesUtil.addInfoMessage("Entity successfully changed");
		
		items = new ListDataModel<Orders>(orderService.findAll());
		return FacesUtil.pageWithRedirect("list_orders.xhtml");
		
	}
	
	public String setColour(Orders order){
		if(order.getState().equals("Under delivery"))
			bgColorValue = "background: #6CF67A";
		else 
			bgColorValue = "background: #FF6969";
		
		return bgColorValue;
	}
	
	

}
