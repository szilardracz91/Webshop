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
import WebShop.model.Orders;
import WebShop.model.Product;


@Stateless
@LocalBean
@PermitAll
public class OrderService extends AbstractService<Orders> {

	@PersistenceContext
	EntityManager em;

	public OrderService() {
        super(Orders.class);
	}
		
	@Override
	protected EntityManager em() {
		 return em;
	}
	
	public List<Orders> filterWithUser(String userName){
		List<Orders> orders =  em.createQuery("SELECT o FROM Orders o WHERE o.client.name LIKE :user", Orders.class)
				.setParameter("user", userName).getResultList();
		return orders;
	}
	
	
	
}
