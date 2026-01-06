package cm.itac.Medecine.ManagedBean;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.SelectEvent;

import com.sun.xml.internal.txw2.Document;

import cm.itac.Medecine.jsf.bean.Patient;
import cm.itac.Medecine.DAO.PatientDAO;



@ManagedBean
@RequestScoped
public class PatientManagedBeanList {
	private List<Patient> patientList;
	private Patient selectedPatient;
	private Patient patient;
	private String nom;
	
	public Patient getSelectedPatient() {
		return selectedPatient;
	}

	public void setSelectedPatient(Patient selectedPatient) {
		this.selectedPatient = selectedPatient;
	}

	/**
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	private String patientSelected;

	/**
	 * @return the patientList
	 */
	public List<Patient> getPatientList() {
		return patientList;
	}

	/**
	 * @param patientList the patientList to set
	 */
	public void setPatientList(List<Patient> patientList) {
		this.patientList = patientList;
	}


	/**
	 * 
	 */
	public PatientManagedBeanList() {
		super();
		patient = new Patient();
		// TODO Auto-generated constructor stub
	}

	

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@PostConstruct
    public void init() {
	 if(patientList == null)
	 patientList = patientDAO.lister();
    }
	
	@EJB
	PatientDAO patientDAO;
	
	public String detail(Patient patient){
		this.patient = patientDAO.detail(patient);
		 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	        PatientManagedBean patientManagedBean = (PatientManagedBean) request.getSession().getAttribute("patientManagedBean");
	        patientManagedBean.setPatient(patient);
		return "PatientDetail";
	}
	
	public String edit(Patient patient){
		this.patient = patientDAO.detail(patient);
		return "patientEdit";
	}
	
	public String supprimer(Patient patient){
		patientDAO.delete(patient);
		addMessage("Notification", "Vous avez supprimé le patient avec success.");
		return "patientList";
	}
	
	public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	public String modifier(){
		Patient patient = new Patient();
		patient.setId(new Long(patientSelected));
		this.patient = patientDAO.modifier(patient);
		return "patientEdit";
	}

	public String getPatientSelected() {
		return patientSelected;
	}

	public void setPatientSelected(String patientSelected) {
		this.patientSelected = patientSelected;
	}
	
	public void onRowSelect(SelectEvent event) {
		
		Patient patientCurrent = (Patient) event.getObject();
        FacesMessage msg = new FacesMessage("Patient sélectioné", patientCurrent.getPrenom());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        patient = patientDAO.trouver(patientCurrent.getId());
        PatientManagedBean patientManagedBean = (PatientManagedBean) request.getSession().getAttribute("patientManagedBean");
        patientManagedBean.setPatient(patient);
        
    }
	
	public void preProcessPDF(Object document) throws IOException {
        Document pdf = (Document) document;
 
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    }
	
}
