package WebShop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Orders implements Serializable {
	@Id
	@GeneratedValue
	private Integer orderId;
	
	@ManyToOne
    private Client client;
	
	private String address;
	
	private String state;
    
    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@ManyToMany(fetch = FetchType.EAGER)/*, cascade = CascadeType.ALL)*/
    private List<Product> products;
    
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
    
    public Orders(){}
    
    public Orders(Client client, List<Product> products, String address, String state){
    	this.client=client;
    	this.products=products;
    	this.address=address;
    	this.state=state;
    }

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
}
