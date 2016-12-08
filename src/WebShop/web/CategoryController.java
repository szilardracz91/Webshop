package WebShop.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.bean.SessionScoped;

import WebShop.model.Category;
import WebShop.model.Client;
import WebShop.model.Product;
import WebShop.services.CategoryService;
import WebShop.services.ProductService;

@ManagedBean
@SessionScoped
public class CategoryController {
	
	Map<String,String> itemCategories = new HashMap<String, String>();
	List<Category> outCategories = null;
	boolean editing = false;
	
	private DataModel<Category> items = null;
	
	public DataModel<Category> getItems() {
		if (items == null)
			items = new ListDataModel<Category>(categoryService.findAll());
		return items;
	}
	
	public void setItems(DataModel<Category> items) {
		this.items = items;
	}

	
	private Category current;
	
	public Category getCurrent() {
		if (current == null)
			current = new Category();
		return current;
	}

	public void setCurrent(Category current) {
		this.current = current;
	}
	
	public String prepareNew() {
		editing = false;
		current = null;
		return FacesUtil.pageWithRedirect("edit_category.xhtml");
	}

	public String prepareEdit() {
		editing = true;
		current = getItems().getRowData();
		System.out.println(current.getCategoryName());
		return FacesUtil.pageWithRedirect("edit_category.xhtml");
	}
	
	public String prepareList() {
		items = null;
		return FacesUtil.pageWithRedirect("list_category.xhtml");
	}

	
	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}
	
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

	/*public String addProduct(){
		categoryService.create(newCategory);
		newCategory = new Category();
		return null;
	}*/
	
	public String remove() {
		categoryService.remove(getItems().getRowData());
		
		FacesUtil.addInfoMessage("Entity successfully removed");

		items = new ListDataModel<Category>(categoryService.findAll());
		return FacesUtil.pageWithRedirect("list_category.xhtml");
	}
	
	public String save() {
		if (editing){
			categoryService.edit(current);
		}
		else{
			categoryService.create(current);
		}
		FacesUtil.addInfoMessage("Entity successfully saved");
		items = null;
		current = null;

		return FacesUtil.pageWithRedirect("list_category.xhtml");
	}
	public String navigateTo(String categoryName){
		return categoryName+".xhtml";
	}


}
