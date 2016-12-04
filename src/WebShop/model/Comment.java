package WebShop.model;


import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
public class Comment implements Serializable {

    @Id
    private int id;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Product product;

    private String text;

    public Comment(){}
    public Comment(Client client, Product product, String text){
    	this.client=client;
    	this.product=product;
    	this.text=text;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
    


}