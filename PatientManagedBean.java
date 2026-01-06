package cm.itac.Medecine.ManagedBean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import cm.itac.Medecine.DAO.PatientDAO;
import cm.itac.Medecine.jsf.bean.Patient;

@ManagedBean
@SessionScoped
public class PatientManagedBean {
	private Patient patient;


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

	/**
	 * 
	 */
	public PatientManagedBean() {
		super();
		patient = new Patient();
		// TODO Auto-generated constructor stub
	}
	
	
	
	/**
	 * @param patient
	 */
	public PatientManagedBean(Patient patient) {
		super();
		this.patient = patient;
	}

	@EJB
	PatientDAO patientDao;
	
	public String valid(){ 
		Long id=patient.getId();
		if (id==null){
			patientDao.create(patient);
			FacesMessage message = new FacesMessage( "Vous avez enregistré un Patient avec succès !" );
			FacesContext.getCurrentInstance().addMessage( null, message );
		}
		patientDao.modifier(getPatient());
		return "overviewPatient";
	}

	
}
