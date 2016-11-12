package WebShop.services;
import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
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
public class ProductService extends AbstractService<Product> {
	
	@PersistenceContext
	EntityManager em;

	public ProductService() {
        super(Product.class);
	}
		
	@Override
	protected EntityManager em() {
		 return em;
	}
	
	
	public void create(Product p, String categoryName){
		Category category = em.createQuery("SELECT c FROM Category c WHERE c.categoryName LIKE :catName", Category.class) .setParameter("catName", categoryName).getSingleResult();
		category.addProduct(p);
		em.persist(p);
	}
	

}
