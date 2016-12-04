package WebShop.services;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import WebShop.model.Client;

@Stateless
@LocalBean
@PermitAll
@DeclareRoles({ Client.USER_ROLE, Client.ADMIN_ROLE })
public class ClientService extends AbstractService<Client> {

	@PersistenceContext
	private EntityManager em;

	public ClientService() {
		super(Client.class);
	}

	 
	@Override
	public void create(Client entity) {
		// TODO Auto-generated method stub
		super.create(entity);
	}

	@RolesAllowed(Client.ADMIN_ROLE)
	@Override
	public void edit(Client entity) {
		// TODO Auto-generated method stub
		super.edit(entity);
	}

	@RolesAllowed(Client.ADMIN_ROLE)
	@Override
	public void remove(Client entity) {
		// TODO Auto-generated method stub
		super.remove(entity);
	}
	@PermitAll
	@RolesAllowed({Client.USER_ROLE, Client.ADMIN_ROLE})
	@Override
	public Client find(Object id) {
		// TODO Auto-generated method stub
		return super.find(id);
	}
	
	@RolesAllowed({Client.USER_ROLE, Client.ADMIN_ROLE})
	@Override
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	@RolesAllowed({Client.USER_ROLE, Client.ADMIN_ROLE})
	@Override
	public List<Client> findRange(int[] range) {
		// TODO Auto-generated method stub
		return super.findRange(range);
	}

	@RolesAllowed({Client.USER_ROLE, Client.ADMIN_ROLE})
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return super.count();
	}

	@Override
	protected EntityManager em() {
		return em;
	}
}