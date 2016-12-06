package WebShop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Basket implements Serializable {
	@Id
	@GeneratedValue
	private Integer orderId;
	
	@ManyToOne
    private Client client;

    @ManyToOne
    private Product product;
    
    public Basket(){}
    
    public Basket(Client client, Product product){
    	this.client=client;
    	this.product=product;
    }

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
	
}
