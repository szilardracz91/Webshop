package WebShop.web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import WebShop.model.Client;
import WebShop.services.ClientService;

@Named
@RequestScoped
public class ClientController implements Serializable {
	private String regMessage;
	private String checkPassword;
	public String getCheckingPass() {
		return checkPassword;
	}

	public void setCheckingPass(String checkingPass) {
		this.checkPassword = checkingPass;
	}

	public String getRegMessage() {
		return regMessage;
	}

	public void setRegMessage(String regMessage) {
		this.regMessage = regMessage;
	}

	private Client current;
	private Client newClient = new Client();
	public Client getNewClient() {
		return newClient;
	}

	public void setNewClient(Client newClient) {
		this.newClient = newClient;
	}

	private DataModel<Client> items = null;
	boolean editing = false;

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}

	@EJB
	private ClientService clientService;
	
	public String remove() {
		clientService.remove(getItems().getRowData());

		FacesUtil.addInfoMessage("Entity successfully removed");

		items = new ListDataModel<Client>(clientService.findAll());
		return FacesUtil.pageWithRedirect("list.xhtml");
	}
	
	public String prepareNew() {
		editing = false;
		current = null;
		return FacesUtil.pageWithRedirect("edit.xhtml");
	}

	public String prepareEdit() {
		editing = true;
		current = getItems().getRowData();
		return FacesUtil.pageWithRedirect("edit.xhtml");
	}


	
	public DataModel<Client> getItems() {
		if (items == null)
			items = new ListDataModel<Client>(clientService.findAll());
		return items;
	}
	
	public void setItems(DataModel<Client> items) {
		this.items = items;
	}

	public String prepareList() {
		items = null;
		return FacesUtil.pageWithRedirect("list.xhtml");
	}

	public Client getCurrent() {
		if (current == null)
			current = new Client();
		return current;
	}

	public void setCurrent(Client current) {
		this.current = current;
	}
	
	public String registrateClient(){
		if(!newClient.getPassword().equals(checkPassword)){
			regMessage="Passwords do not match";
			return null;
		}
		
		
		try {	
			clientService.create(newClient);
			regMessage = "Successfully created";
		}
		catch(Exception e){
			regMessage = "Client already exists";
		}
		
		return null;
		//return FacesUtil.pageWithRedirect("index.xhtml");
	}
	
	public String save() {
		if (editing)
			clientService.edit(current);
		else
			clientService.create(current);
		FacesUtil.addInfoMessage("Entity successfully saved");
		items = null;
		current = null;

		return FacesUtil.pageWithRedirect("list.xhtml");
	}

}

class FacesUtil {

	public static void addInfoMessage(String text) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, text,
				text);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, msg);
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
	}
	
	public static String pageWithRedirect(String page){
		return page + "?faces-redirect=true";
	}

}

