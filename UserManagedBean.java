package cm.itac.Medecine.ManagedBean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import cm.itac.Medecine.DAO.UserDAO;
import cm.itac.Medecine.jsf.bean.User;


@ManagedBean
@SessionScoped
public class UserManagedBean {
	
	private static final String Algo_CHIFFREMENT = "SHA-256";
	private User user;
	private boolean medecin;
	private boolean laboratoire;
	private boolean accueil;

	
	public boolean isMedecin() {
		medecin = "Medecin".equals(user.getRole().getDescription());
		return medecin;
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

	public UserManagedBean() {
		super();
		user = new User();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@EJB
	UserDAO userDao;
	
	private boolean con;

	public String connexion(){
		String email = user.getEmail();
		String password = user.getPassword();
		
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm(Algo_CHIFFREMENT);
		passwordEncryptor.setPlainDigest(false);
		String MotdePasseChiffre = passwordEncryptor.encryptPassword(password);
		
		user.setPassword(MotdePasseChiffre);
		
		user = userDao.trouver(email) ;
		
		if (user == null) {
			FacesMessage message = new FacesMessage("User not found !" );
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "connexion";
			
		}else if(!passwordEncryptor.checkPassword(password, user.getPassword())){
			FacesMessage message = new FacesMessage("Password not found !" );
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "connexion";
		} 
		
		FacesMessage message = new FacesMessage( "Succés de la connexion !");
		con = true;
		FacesContext.getCurrentInstance().addMessage(null, message);
		return "MainPage";
	}
}
