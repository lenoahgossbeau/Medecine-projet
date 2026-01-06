package cm.itac.Medecine.ManagedBean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.SelectEvent;

import cm.itac.Medecine.DAO.InscriptionDAO;
import cm.itac.Medecine.jsf.bean.User;

@ManagedBean
@RequestScoped
public class UtilisateurManagedBeanList {
	private List<User> userList;
	private User user;
	private String userSelected;
	private User selectedUser;
	
	

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	/**
	 * 
	 */
	public UtilisateurManagedBeanList() {
		super();
		user = new User();
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
    public void init() {
	 if(userList == null)
	 userList = inscriptionDao.lister();
    }
	
	@EJB
	InscriptionDAO inscriptionDao;
	
	public String detail(User user){
		this.user = inscriptionDao.detail(user);
		return "inscriptionDetail";
	}
	
	public String edit(User user){
		this.user = inscriptionDao.modifier(user);
		return "inscriptionEdit";
	}
	
	public String supprimer(User user){
		inscriptionDao.supprimer(user);
		addMessage("Notification", "Vous avez supprimé l'utilisateur avec success.");
		return "userList";
	}
	
	public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	public String modifier(){
		User user = new User();
		user.setId(new Long(userSelected));
		this.setUser(user);
		user = inscriptionDao.modifier(user);
		return "userEdit";
	}

	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserSelected() {
		return userSelected;
	}

	public void setUserSelected(String userSelected) {
		this.userSelected = userSelected;
	}

	public void onRowSelect(SelectEvent event) {
		
		User userCurrent = (User) event.getObject();
        FacesMessage msg = new FacesMessage("Utilisateur sélectioné", userCurrent.getUsername());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        user = inscriptionDao.trouve(userCurrent.getId());
        InscriptionManagedBean inscriptionManagedBean = (InscriptionManagedBean) request.getSession().getAttribute("InscriptionManagedBean");
        inscriptionManagedBean.setUser(user);
        
    }
	
}
