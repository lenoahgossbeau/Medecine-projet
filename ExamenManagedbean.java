package cm.itac.Medecine.ManagedBean;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import cm.itac.Medecine.DAO.PatientDAO;
import cm.itac.Medecine.jsf.bean.Patient;


@ManagedBean
@RequestScoped
public class ExamenManagedbean {
	private Patient examen;
	private String nomPatient;
	 Map<String, Patient> patients;
	 private Date date;
	 @EJB
	 PatientDAO patientDAO;

	/**
	 * @return the examens
	 */
	public Map<String, Patient> getPatients() {
		List<Patient> examenList = new ArrayList<Patient>();

			patients = new HashMap<String, Patient>();
			examenList = patientDAO.lister();
			for(Patient examen : examenList)
				patients.put(String.valueOf(examen.getId()), examen);
//		}
		return patients;
	}

	/**
	 * @param examens the examens to set
	 */
	public void setPatients(Map<String, Patient> examens) {
		this.patients = examens;
	}

	/**
	 * 
	 */
	public ExamenManagedbean() {
		super();
		examen = new Patient();
		nomPatient = examen.getNom();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the examen
	 */
	public Patient getPatient() {
		return examen;
	}

	/**
	 * @param examen the examen to set
	 */
	public void setPatient(Patient examen) {
		this.examen = examen;
	}
	
	@EJB
	PatientDAO examenDao;
	
	public String valid(){
		//examen.setDate_resultat(date);
		examenDao.create(examen);
		 FacesMessage message = new FacesMessage( "Vous avez effectués votre examen avec succès !" );
		    FacesContext.getCurrentInstance().addMessage( null, message );
		return "overviewPatient";
	}

	/**
	 * @return the nomPatient
	 */
	public String getNomPatient() {
		return nomPatient;
	}

	/**
	 * @param nomPatient the nomPatient to set
	 */
	public void setNomPatient(String nomPatient) {
		this.nomPatient = nomPatient;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}
