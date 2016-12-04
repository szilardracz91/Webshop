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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import WebShop.model.Category;
import WebShop.model.Client;
import WebShop.model.Product;
import WebShop.services.ProductService;

@ManagedBean
@SessionScoped
public class ProductController implements Serializable{
	private Product newProduct = new Product();
	private Product showProduct = new Product();
	private String categoryName = new String();
	boolean categoryView = false;
	private List<Product> products = null;
	private String searchedName=""; 
	
	public String getSearchedName() {
		return searchedName;
	}

	public void setSearchedName(String searchedName) {
		this.searchedName = searchedName;
	}

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
		if(searchedName.isEmpty()){
			products = productService.findAll();
		}
		else {
		products=productService.productsWithName(searchedName);
		}
		
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product getShowProduct() {
		return showProduct;
	}

	public void setShowProduct(Product showProduct) {
		this.showProduct = showProduct;
	}
	
	public String prepareShowProduct() {
		return FacesUtil.pageWithRedirect("shop_item.html");
	}
	
	public List<Product> productsByCategory(String categoryName){
		products = productService.filterWithCategory(categoryName);
		return products;
	}

}
