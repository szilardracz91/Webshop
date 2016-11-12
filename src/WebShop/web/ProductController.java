package WebShop.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import WebShop.model.Category;
import WebShop.model.Client;
import WebShop.model.Product;
import WebShop.services.ProductService;

@ManagedBean
@RequestScoped
public class ProductController implements Serializable{
	private Product newProduct = new Product();
	private String categoryName = new String();
	private List<Product> products = null;
	
	@EJB
	ProductService productService;
	
	
	  
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	 
	public Product getNewProduct() {
		return newProduct;
	}

	public void setNewProduct(Product newProduct) {
		this.newProduct = newProduct;
	}
	
	
	public String createProduct(){
		productService.create(newProduct, categoryName);
		newProduct = new Product();
		return null;
	}

	public List<Product> getProducts() {
		if (products == null)
			products = productService.findAll();
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	

}
