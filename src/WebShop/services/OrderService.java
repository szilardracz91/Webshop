//package WebShop.services;
//
//import java.util.List;
//
//import javax.annotation.security.DeclareRoles;
//import javax.annotation.security.PermitAll;
//import javax.annotation.security.RolesAllowed;
//import javax.ejb.LocalBean;
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import WebShop.model.Basket;
//import WebShop.model.Order;
//import WebShop.model.Product;
//
//
//@Stateless
//@LocalBean
//@PermitAll
//public class OrderService extends AbstractService<Order> {
//
//	@PersistenceContext
//	EntityManager em;
//
//	public OrderService() {
//        super(Order.class);
//	}
//		
//	@Override
//	protected EntityManager em() {
//		 return em;
//	}
//	
//	public Order filterWithUser(String userName){
//		Order orders =  em.createQuery("SELECT o FROM Order o WHERE o.client.name LIKE :user", Order.class)
//				.setParameter("user", userName).getSingleResult();
//		return orders;
//	}
//	
//}
