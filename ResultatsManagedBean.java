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

import cm.itac.Medecine.DAO.ResultatsDAO;
import cm.itac.Medecine.DAO.Examen_patientDAO;
import cm.itac.Medecine.jsf.bean.Resultats;
import cm.itac.Medecine.jsf.bean.Examen_patient;

@ManagedBean
@SessionScoped
    public class ResultatsManagedBean {
	private Resultats resultats;
	 Map<String, Examen_patient> examens;
	 @EJB
	 Examen_patientDAO examenDao;

	public Map<String, Examen_patient> getExamens() {
		
		List<Examen_patient> examenList = new ArrayList<Examen_patient>();

			examens = new HashMap<String, Examen_patient>();
			examenList = examenDao.lister();
			for(Examen_patient examen : examenList)
				examens.put(String.valueOf(examen.getId()), examen);
		return examens;
	}

	
	public void setExamens(Map<String, Examen_patient> examens) {
		this.examens = examens;
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
	 * 
	 */
	public ResultatsManagedBean() {
		super();
		resultats = new Resultats();
		//nom = resultats.getExamen_patient().getNom();
	}
	
	public ResultatsManagedBean(Resultats resultats) {
		super();
		this.resultats = resultats;
	}

	
	public Resultats getResultats() {
		return resultats;
	}

	/**
	 * @param transaction the transaction to set
	 */
	public void setResultats(Resultats resultats) {
		this.resultats = resultats;
	}
	
	private void initialiserDateExamen() {
		Timestamp date_resultat = new Timestamp( System.currentTimeMillis());
			resultats.setDate_resultat(date_resultat);


	}
	
	@EJB
	ResultatsDAO resultatsDao;
	//TypeExamenDAO typeexamenDao;
	
	public String valid(){
		initialiserDateExamen();
		resultatsDao.create(resultats);
		 FacesMessage message = new FacesMessage( "Vous avez enregistré le resultat avec succès !" );
		    FacesContext.getCurrentInstance().addMessage( null, message );
		return "overviewResultats";
	}


}