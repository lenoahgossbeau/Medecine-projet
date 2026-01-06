package cm.itac.Medecine.ManagedBean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.SelectEvent;

import cm.itac.Medecine.DAO.Examen_patientDAO;
import cm.itac.Medecine.DAO.ResultatsDAO;
import cm.itac.Medecine.jsf.bean.Examen_patient;
import cm.itac.Medecine.jsf.bean.Resultats;
import cm.itac.Medecine.ManagedBean.ResultatsManagedBean;

@ManagedBean
@SessionScoped
public class Examen_patientManagedBeanList {
	private List<Examen_patient> ExamenList;
	private Resultats resultats;
	private ResultatsManagedBean resultatsManagedBean;
	private Examen_patient examen_patient;
	private Examen_patient selectedExamen;
	private String examenSelected;
	private boolean statut1;
	private boolean statut2;
	private boolean statut3;

	
	public boolean isStatut1() {
		return "A pratiquer".equals(examen_patient.getStatut());
	}

	public void setStatut1(boolean statut1) {
		this.statut1 = statut1;
	}

	public boolean isStatut2() {
		return "En cours".equals(examen_patient.getStatut());
	}

	public void setStatut2(boolean statut2) {
		this.statut2 = statut2;
	}

	public boolean isStatut3() {
		return "Terminé".equals(examen_patient.getStatut());
	}

	public void setStatut3(boolean statut3) {
		this.statut3 = statut3;
	}

	public Examen_patient getSelectedExamen() {
		return selectedExamen;
	}

	public void setSelectedExamen(Examen_patient selectedExamen) {
		this.selectedExamen = selectedExamen;
	}

	public List<Examen_patient> getExamenList() {
		return ExamenList;
	}

	public void setExamenList(List<Examen_patient> examenList) {
		ExamenList = examenList;
	}
	
	
	public Resultats getResultats() {
		return resultats;
	}

	public void setResultats(Resultats resultats) {
		this.resultats = resultats;
	}

	/**
	 * 
	 */
	public Examen_patientManagedBeanList() {
		super();
		examen_patient = new Examen_patient();
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
    public void init() {
	 if(ExamenList == null)
	 ExamenList = examen_patientDao.lister();
    }
	
	@EJB
	Examen_patientDAO examen_patientDao;
	ResultatsDAO resultatsDao;
	
	public String resultats(Examen_patient examen){
		examen.setStatut("Terminé");
		this.examen_patient = examen_patientDao.modifier(examen);
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Examen_patientManagedBean examen_patientManagedBean = (Examen_patientManagedBean) request.getSession().getAttribute("examen_patientManagedBean");
        examen_patientManagedBean.setExamen_patient(examen_patient);
        
		Resultats resultats = resultatsDao.create(getResultats());
		resultats.getExamen().setId(examen_patient.getId());
		this.resultats = resultatsDao.modifier(getResultats());
		HttpServletRequest requestR = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        ResultatsManagedBean resultatstManagedBean = (ResultatsManagedBean) requestR.getSession().getAttribute("resultatsManagedBean");
        resultatsManagedBean.setResultats(resultats);
		return "Resultats";
	}
	
	public String statut(Examen_patient examen){
		examen.setStatut("En cours");
		this.examen_patient = examen_patientDao.modifier(examen);
		return "Liste_Examen";
	}
	
	public String detail(Examen_patient examen){
		this.examen_patient = examen_patientDao.detail(examen);
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Examen_patientManagedBean examen_patientManagedBean = (Examen_patientManagedBean) request.getSession().getAttribute("examen_patientManagedBean");
        examen_patientManagedBean.setExamen_patient(examen_patient);
		return "ExamenDetail";
	}
	
	public String edit(Examen_patient examen){
		this.examen_patient = examen_patientDao.modifier(examen);
		return "examen_patientEdit";
	}
	
	public String supprimer(Examen_patient examen){
		examen_patientDao.delete(examen);
		addMessage("Notification", "Vous avez supprimé l'examen avec success.");
		return "ExamenList";
	}
	public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	public String modifier(){
		Examen_patient examen = new Examen_patient();
		examen.setId(new Long(examenSelected));
		this.setExamen_patient(examen);
		examen = examen_patientDao.modifier(examen);
		return "examenEdit";
	}

	public Examen_patient getExamen_patient() {
		return examen_patient;
	}

	public void setExamen_patient(Examen_patient examen_patient) {
		this.examen_patient = examen_patient;
	}

	public String getExamenSelected() {
		return examenSelected;
	}

	public void setExamenSelected(String examenSelected) {
		this.examenSelected = examenSelected;
	}

	public void onRowSelect(SelectEvent event) {
		Examen_patient examen_patientCurrent = (Examen_patient) event.getObject();
        FacesMessage msg = new FacesMessage("Examen sélectioné", examen_patientCurrent.getDescription());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        examen_patient = examen_patientDao.trouver(examen_patientCurrent.getId());
        Examen_patientManagedBean examen_patientManagedBean = (Examen_patientManagedBean) request.getSession().getAttribute("examen_patientManagedBean");
        examen_patientManagedBean.setExamen_patient(examen_patient);
        
    }

	
	
}
