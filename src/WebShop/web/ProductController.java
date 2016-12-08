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
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WebShop.model.Category;
import WebShop.model.Client;
import WebShop.model.Product;
import WebShop.services.CategoryService;
import WebShop.services.ProductService;

@ManagedBean
@SessionScoped
@WebServlet("/newProduct")
public class ProductController extends HttpServlet implements Serializable{
	private Product newProduct = new Product();
	private Product showProduct = new Product();
	private String categoryName = new String();
	boolean categoryView = false;
	private List<Product> products = null;
	private String searchedName=""; 
	
	public String getSearchedName() {
		return searchedName;
	}
	
	boolean editing = false;
	
	private Product current;
	
	public Product getCurrent() {
		if (current == null)
			current = new Product();
		return current;
	}

	public void setCurrent(Product current) {
		this.current = current;
	}

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}
	
	private DataModel<Product> items = null;
	
	public DataModel<Product> getItems() {
		if (items == null)
			items = new ListDataModel<Product>(productService.findAll());
		return items;
	}
	
	public void setItems(DataModel<Product> items) {
		this.items = items;
	}

	public void setSearchedName(String searchedName) {
		this.searchedName = searchedName;
	}

	@EJB
	ProductService productService;
	@EJB
	CategoryService categoryController;
	
	
	  
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
	
	/*public String createProduct(){
		productService.create(newProduct, categoryName);
		newProduct = new Product();
		return null;
	}*/

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
	
	public String prepareList() {
		items = null;
		return FacesUtil.pageWithRedirect("list_product.xhtml");
	}
	
	public String prepareNew() {
		editing = false;
		current = null;
		return FacesUtil.pageWithRedirect("edit_product.xhtml");
	}

	public String prepareEdit() {
		editing = true;
		current = getItems().getRowData();
		return FacesUtil.pageWithRedirect("edit_product.xhtml");
	}
	
	public String save() {
		Category cat = categoryController.getCategoryWithName(categoryName);
		current.setCategory(cat);
		if (editing){
			productService.edit(current);
		}
		else{
			productService.create(current);
		}
		FacesUtil.addInfoMessage("Entity successfully saved");
		items = null;
		current = null;

		return FacesUtil.pageWithRedirect("list_product.xhtml");
	}
	
	public String remove() {
		productService.remove(getItems().getRowData());

		FacesUtil.addInfoMessage("Entity successfully removed");

		items = new ListDataModel<Product>(productService.findAll());
		return FacesUtil.pageWithRedirect("list_product.xhtml");
	}

}
