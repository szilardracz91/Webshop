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
import WebShop.model.Comment;
import WebShop.model.Product;


@Stateless
@LocalBean
@PermitAll
public class CommentService extends AbstractService<Comment> {

	@PersistenceContext
	EntityManager em;

	public CommentService() {
        super(Comment.class);
	}
		
	@Override
	protected EntityManager em() {
		 return em;
	}
	
	@PermitAll
	public List<Comment> findByProductId(int id) {
		List<Comment> comments =  em.createQuery("SELECT c FROM Comment c WHERE c.product.id LIKE :id", Comment.class)
				.setParameter("id", id).getResultList();
		return comments;
	}
	
	/*public List<Basket> filterWithUser(String userName){
		List<Basket> orders =  em.createQuery("SELECT b FROM Basket b WHERE b.clientId LIKE :user", Basket.class)
				.setParameter("user", userName).getResultList();
		return orders;
	}*/
	
}
