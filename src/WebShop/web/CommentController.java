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
import WebShop.services.ClientService;
import WebShop.services.CommentService;
import WebShop.services.ProductService;

@ManagedBean
@RequestScoped
public class CommentController {
	
	private String clientName = new String(); 
	private int productId;
	private String commentMessage = new String();
	
	

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getCommentMessage() {
		return commentMessage;
	}

	public void setCommentMessage(String commentMessage) {
		this.commentMessage = commentMessage;
	}
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@EJB
	CommentService commentService;
	@EJB
	ProductService productService;
	@EJB
	ClientService clientService;
	
	public void insertComment(){
		System.out.println(productId);
		System.out.println(clientName);
		System.out.println("hahaha");
		Product product = productService.find(productId);
		Client client = clientService.find(clientName);

		Comment newComment = new Comment(client, product, commentMessage);
		commentService.create(newComment);
	}


	
}
