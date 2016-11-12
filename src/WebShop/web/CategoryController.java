package WebShop.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import WebShop.model.Category;
import WebShop.model.Product;
import WebShop.services.CategoryService;
import WebShop.services.ProductService;

@ManagedBean
@RequestScoped
public class CategoryController {
	
	Map<String,String> itemCategories = new HashMap<String, String>();
	List<Category> outCategories = null;
	
	public List<Category> getOutCategories() {
		if (outCategories == null)
			outCategories = categoryService.findAll();
		return outCategories;
	}

	public void setOutCategories(List<Category> outCategories) {
		this.outCategories = outCategories;
	}


	Category newCategory = new Category();

	@EJB
	CategoryService categoryService;
	
	@PostConstruct
    public void init() {
		itemCategories = new HashMap<String, String>();
		List<Category> categories = categoryService.findAll();
		for(Category cat: categories){
			itemCategories.put(cat.getCategoryName(), cat.getCategoryName());
		} 
		
	}
	
	public Map<String, String> getItemCategories() {
		return itemCategories;
	}

	public void setItemCategories(Map<String, String> itemCategories) {
		this.itemCategories = itemCategories;
	}
	
	public Category getNewCategory() {
		return newCategory;
	}

	public void setNewCategory(Category newCategory) {
		this.newCategory = newCategory;
	}
	
	public String createCategory(){
		categoryService.create(newCategory);
		newCategory = new Category();
		return null;
	}
	
	public String getCategories(){
		String returnCategories = "";
		List<Category> categories = categoryService.findAll();
		for(Category category : categories)
			returnCategories += " " + category.getCategoryName();
		return returnCategories;
	}
	

	public String addProduct(){
		categoryService.create(newCategory);
		newCategory = new Category();
		return null;
	}

}
