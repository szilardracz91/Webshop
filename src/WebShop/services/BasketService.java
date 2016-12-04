package WebShop.services;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import WebShop.model.Basket;
import WebShop.model.Product;


@Stateless
@LocalBean
@PermitAll
public class BasketService extends AbstractService<Basket> {

	@PersistenceContext
	EntityManager em;

	public BasketService() {
        super(Basket.class);
	}
		
	@Override
	protected EntityManager em() {
		 return em;
	}
	
	public List<Basket> filterWithUser(String userName){
		List<Basket> orders =  em.createQuery("SELECT b FROM Basket b WHERE b.clientId LIKE :user", Basket.class)
				.setParameter("user", userName).getResultList();
		return orders;
	}
	
}
