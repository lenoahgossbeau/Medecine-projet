package cm.itac.Medecine.ManagedBean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.SelectEvent;

import cm.itac.Medecine.DAO.Examen_patientDAO;
import cm.itac.Medecine.DAO.PatientDAO;
import cm.itac.Medecine.DAO.SousServiceDAO;
import cm.itac.Medecine.DAO.TypeExamenDAO;
import cm.itac.Medecine.jsf.bean.Examen_patient;
import cm.itac.Medecine.jsf.bean.Patient;
import cm.itac.Medecine.jsf.bean.SousService;
import cm.itac.Medecine.jsf.bean.TypeExamen;

@ManagedBean
@SessionScoped
    public class Examen_patientManagedBean {
	private Examen_patient examen_patient;
	 Map<String, Patient> patients;
	 Map<String, SousService> sous_Services;
	 private String[] selectedTypesExamens;
	    private List<String> typesExamens;
	    private String sousServiceSelected;
	 //Map<String, TypeExamen> typeexamens;
//	 private String description;
//	 private String type;
//	 private Long id_patient;
//	 private Date date_examen;
	 @EJB
	 PatientDAO patientDao;
	 @EJB
	 SousServiceDAO sousServiceDao;
	 @EJB
	 TypeExamenDAO typeExamenDAO;

	public Map<String, Patient> getPatients() {
		
		List<Patient> patientList = new ArrayList<Patient>();

			patients = new HashMap<String, Patient>();
			patientList = patientDao.lister();
			for(Patient patient : patientList)
				patients.put(String.valueOf(patient.getId()), patient);
		return patients;
	}

	
	public void setPatients(Map<String, Patient> patients) {
		this.patients = patients;
	}
	
	
	
//	public Map<String, TypeExamen> getTypeexamens() {
//		
//		List<TypeExamen> typeexamenList = new ArrayList<TypeExamen>();
//
//		typeexamens = new HashMap<String, TypeExamen>();
//		typeexamenList = typeexamenDao.lister();
//		for(TypeExamen typeexamen : typeexamenList)
//			typeexamens.put(String.valueOf(typeexamen.getId()), typeexamen);
//		return typeexamens;
//	}


	/**
	 * @return the sousServiceSelected
	 */
	public String getSousServiceSelected() {
		return sousServiceSelected;
	}


	/**
	 * @param sousServiceSelected the sousServiceSelected to set
	 */
	public void setSousServiceSelected(String sousServiceSelected) {
		this.sousServiceSelected = sousServiceSelected;
	}


	/**
	 * @return the sous_Services
	 */
	public Map<String, SousService> getSous_Services() {
		List<SousService> SousServiceList = new ArrayList<SousService>();

		sous_Services = new HashMap<String, SousService>();
		SousServiceList = sousServiceDao.lister();
		for(SousService sous_Service : SousServiceList)
			sous_Services.put(String.valueOf(sous_Service.getId()), sous_Service);
	return sous_Services;
	}


	/**
	 * @param sous_Services the sous_Services to set
	 */
	public void setSous_Services(Map<String, SousService> sous_Services) {
		this.sous_Services = sous_Services;
	}


	/**
	 * 
	 */
	public Examen_patientManagedBean() {
		super();
		examen_patient = new Examen_patient();
		//nom = examen_patient.getPatient().getNom();
	}
	
	public Examen_patientManagedBean(Examen_patient examen_patient) {
		super();
		this.examen_patient = examen_patient;
	}

	
	public Examen_patient getExamen_patient() {
		return examen_patient;
	}

	/**
	 * @param transaction the transaction to set
	 */
	public void setExamen_patient(Examen_patient examen_patient) {
		this.examen_patient = examen_patient;
	}
	
	private void initialiserDateExamen() {
		Timestamp date_examen = new Timestamp( System.currentTimeMillis());
			examen_patient.setDate_examen(date_examen);


	}
	
	@EJB
	Examen_patientDAO examen_patientDao;
	//TypeExamenDAO typeexamenDao;
	
	public String valid(){
		initialiserDateExamen();
		examen_patient.setStatut("A pratiquer");
		examen_patientDao.create(examen_patient);
		 FacesMessage message = new FacesMessage( "Vous avez enregistré l'examen avec succès !" );
		    FacesContext.getCurrentInstance().addMessage( null, message );
		return "overviewExamen_patient";
	}


	/**
	 * @return the selectedTypesExamens
	 */
	public String[] getSelectedTypesExamens() {
		
		String idSousService = getSousServiceSelected();
		if(idSousService != null){
			SousService sousService = new SousService();
			sousService.setId(Long.valueOf(idSousService));
			List<TypeExamen> typeExamens = typeExamenDAO.trouverBySousService(sousService);
			int i = 0;
			selectedTypesExamens = new String[100];
			for (TypeExamen typeExamen : typeExamens){
				selectedTypesExamens[i] = typeExamen.getReference();
				i++;
			}
		}
		return selectedTypesExamens;
	}

	public void onSousServiceChange(){
		getSelectedTypesExamens();
	}


	/**
	 * @param selectedTypesExamens the selectedTypesExamens to set
	 */
	public void setSelectedTypesExamens(String[] selectedTypesExamens) {
		this.selectedTypesExamens = selectedTypesExamens;
	}


	/**
	 * @return the typesExamens
	 */
	public List<String> getTypesExamens() {
		
		
		return typesExamens;
	}


	/**
	 * @param typesExamens the typesExamens to set
	 */
	public void setTypesExamens(List<String> typesExamens) {
		this.typesExamens = typesExamens;
	}


}