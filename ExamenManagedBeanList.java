package cm.itac.Medecine.ManagedBean;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import cm.itac.Medecine.jsf.bean.Patient;
import cm.itac.Medecine.DAO.PatientDAO;



@ManagedBean
@RequestScoped
public class ExamenManagedBeanList {
	private List<Patient> examenList;

	/**
	 * @return the examenList
	 */
	public List<Patient> getExamenList() {
		return examenList;
	}

	/**
	 * @param examenList the examenList to set
	 */
	public void setExamenList(List<Patient> examenList) {
		this.examenList = examenList;
	}

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

	private String examenSelected;

	/**
	 * @return the examenList
	 */
	public List<Patient> getPatientList() {
		return examenList;
	}

	/**
	 * @param examenList the examenList to set
	 */
	public void setPatientList(List<Patient> examenList) {
		this.examenList = examenList;
	}


	/**
	 * 
	 */
	public ExamenManagedBeanList() {
		super();
		patient = new Patient();
		// TODO Auto-generated constructor stub
	}

	

	@PostConstruct
    public void init() {
	 if(examenList == null)
	 examenList = patientDAO.lister();
    }
	
	@EJB
	PatientDAO patientDAO;
	
	public String detail(Patient examen){
		this.patient = patientDAO.detail(examen);
		return "examenDetail";
	}
	
	public String edit(Patient examen){
		this.patient = patientDAO.detail(examen);
		return "examenEdit";
	}
	
	public String supprimer(Patient examen){
		patientDAO.delete(examen);
		return "examenList";
	}
	
	public String modifier(){
		Patient examen = new Patient();
		examen.setId(new Long(examenSelected));
		this.patient = patientDAO.modifier(examen);
		return "examenEdit";
	}

	

	/**
	 * @return the examen
	 */

	/**
	 * @return the examenSelected
	 */
	public String getExamenSelected() {
		return examenSelected;
	}

	/**
	 * @param examenSelected the examenSelected to set
	 */
	public void setExamenSelected(String examenSelected) {
		this.examenSelected = examenSelected;
	}


	
	
}
