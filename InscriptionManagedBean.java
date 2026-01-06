package cm.itac.Medecine.ManagedBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import cm.itac.Medecine.DAO.InscriptionDAO;
import cm.itac.Medecine.DAO.RoleDAO;
import cm.itac.Medecine.jsf.bean.Role;
import cm.itac.Medecine.jsf.bean.User;


@ManagedBean
@SessionScoped
public class InscriptionManagedBean {
	
	private static final String Algo_CHIFFREMENT = "SHA-256";
	private String description;
	 Map<String, Role> Roles;
	private User user;
	private long role;
	private String confirmationPassword;
	private boolean medecin;
	private boolean laboratoire;
	private boolean accueil;
	@EJB
	 RoleDAO roleDao;

	
	public boolean isMedecin() {
		return "Medecin".equals(user.getRole().getDescription());
	}

	public void setMedecin(boolean medecin) {
		this.medecin = medecin;
	}

	public boolean isLaboratoire() {
		return "Laboratoire".equals(user.getRole().getDescription());
	}

	public void setLaboratoire(boolean laboratoire) {
		this.laboratoire = laboratoire;
	}

	public boolean isAccueil() {
		return "Accueil".equals(user.getRole().getDescription());
	}

	public void setAccueil(boolean accueil) {
		this.accueil = accueil;
	}

	public Map<String, Role> getRoles() {
		
		List<Role> roleList = new ArrayList<Role>();
		
		Roles = new HashMap<String, Role>();
		roleList = roleDao.lister();
		for(Role role :roleList)
			Roles.put(String.valueOf(role.getId()), role);
		return Roles;
	}

	public void setRoles(Map<String, Role> roles) {
		this.Roles = roles;
	}

	public InscriptionManagedBean() {
		super();
		user = new User();
		description = user.getRole().getDescription();
		// TODO Auto-generated constructor stub
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getRole() {
		return role;
	}

	public void setRole(long role) {
		this.role = role;
	}

	/**
	 * @return the confirmationPassword
	 */
	public String getConfirmationPassword() {
		return confirmationPassword;
	}

	/**
	 * @param confirmationPassword the confirmationPassword to set
	 */
	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}
	
	
	
	@EJB
	InscriptionDAO inscriptionDao;
	
	public String inscription() {
		
		
		User utilisateur = inscriptionDao.trouver(user.getEmail());
		if(utilisateur !=null){
			FacesMessage message = new FacesMessage( "Email deja existant" );
		    FacesContext.getCurrentInstance().addMessage( null, message );
		    return "";
		}
		
		if(!confirmationPassword.equals(user.getPassword())){
			FacesMessage message = new FacesMessage( "Password differents" );
		    FacesContext.getCurrentInstance().addMessage( null, message );
		    return "";
		}
		String MotdePasse = user.getPassword();
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
				passwordEncryptor.setAlgorithm( Algo_CHIFFREMENT );
		passwordEncryptor.setPlainDigest(false);
		String MotdePasseChiffre = passwordEncryptor.encryptPassword(MotdePasse);
		user.setPassword(MotdePasseChiffre);
		
		inscriptionDao.create(user);
		FacesMessage message = new FacesMessage( "Vous avez enregistré un Utilisateur avec succès ! ");
	    FacesContext.getCurrentInstance().addMessage( null, message );
		return "";
	}
	public String connexion(){
		String email = user.getEmail();
		String password = user.getPassword();
		
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm(Algo_CHIFFREMENT);
		passwordEncryptor.setPlainDigest(false);
		String MotdePasseChiffre = passwordEncryptor.encryptPassword(password);
		
		//user.setPassword(MotdePasseChiffre);
		String pass = user.getPassword();
		
		if (inscriptionDao.trouver(email) == null) {
			FacesMessage message = new FacesMessage("User not found !" );
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "connexion";
			
		}else if(!passwordEncryptor.checkPassword(MotdePasseChiffre, user.getPassword())){
			FacesMessage message = new FacesMessage("Password not found !" );
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "connexion";
		} 
//		else if(!passwordEncryptor.checkPassword(password, user.getPassword())) {
//			FacesMessage message = new FacesMessage("Password not found !" );
//			
//			FacesContext.getCurrentInstance().addMessage(null, message);
//			return "connexion";
//			
//		}
		
		
		FacesMessage message = new FacesMessage( "Succés de la connexion !");
		
		FacesContext.getCurrentInstance().addMessage(null, message);
		return "MainPage";
	}
}
