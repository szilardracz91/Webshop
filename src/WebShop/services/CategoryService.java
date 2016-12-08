package WebShop.services;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import WebShop.model.Category;
import WebShop.model.Client;
import WebShop.model.Product;

@Stateless
@LocalBean

public class CategoryService extends AbstractService<Category> {

	@PersistenceContext
	EntityManager em;
	
	public CategoryService() {
        super(Category.class);
	}
	
	
	@Override
	public void create(Category entity) {
		// TODO Auto-generated method stub
		super.create(entity);
	}
	
	
	@Override
	public void edit(Category entity) {
		// TODO Auto-generated method stub
		super.edit(entity);
	}

	
	@Override
	public void remove(Category entity) {
		// TODO Auto-generated method stub
		super.remove(entity);
	}

	
	@Override
	public Category find(Object id) {
		// TODO Auto-generated method stub
		return super.find(id);
	}
	

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	
	@Override
	public List<Category> findRange(int[] range) {
		// TODO Auto-generated method stub
		return super.findRange(range);
	}

	
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return super.count();
	}

	@Override
	protected EntityManager em() {
		 return em;
	}
	
	public Category getCategoryWithName(String categoryName) {

		Category category = em
				.createQuery(
						"SELECT c FROM Category c WHERE c.categoryName LIKE :catName",
						Category.class).setParameter("catName", categoryName)
				.getSingleResult();
		return category;
	}
	
	
}
