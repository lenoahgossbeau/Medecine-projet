package cm.itac.Medecine.DAO;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cm.itac.Medecine.jsf.bean.Examen_patient;

@Stateless
public class Examen_patientDAO {
	
	@PersistenceContext(unitName = "medecine")
	private EntityManager em;
	
	public Examen_patient create(Examen_patient examen_patient){
		em.persist(examen_patient);
		em.flush();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return examen_patient;
		
	}
	
	public Examen_patient trouver(Long id){
		return em.find(Examen_patient.class, id);
		
	}
	
	public Examen_patient detail(Examen_patient examen_patient){
		return trouver(examen_patient.getId());
	}
	
	
	public void delete(Examen_patient examen_patient){
		em.remove(em.merge(examen_patient));
		
	}
	
	public Examen_patient modifier(Examen_patient examen_patient){
		return em.merge(examen_patient);
	}
	
	private void initialiserDateExamen() {
		Timestamp date_examen = new Timestamp( System.currentTimeMillis());
	}
	public List<Examen_patient> lister(){
		 TypedQuery<Examen_patient> query = em.createQuery(  "SELECT e FROM examen e ORDER BY e.id", Examen_patient.class);
		return query.getResultList();
	}

}



