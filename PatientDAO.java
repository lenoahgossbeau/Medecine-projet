package cm.itac.Medecine.DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cm.itac.Medecine.jsf.bean.Patient;

@Stateless
public class PatientDAO {
	@PersistenceContext(unitName = "medecine")
	private EntityManager em;
	
	public Patient create(Patient patient){
		em.persist(patient);
		em.flush();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return patient;
		
	}
	
	public Patient trouver(Long id){
		return em.find(Patient.class, id);
		
	}
	
	public Patient detail(Patient patient){
		return trouver(patient.getId());
	}
	
	
	public void delete(Patient patient){
		em.remove(em.merge(patient));
		
	}
	
	public Patient modifier(Patient patient){
		return em.merge(patient);
	}
	
	
	public List<Patient> lister(){
		 TypedQuery<Patient> query = em.createQuery(  "SELECT p FROM Patient p ORDER BY p.id", Patient.class);
		return query.getResultList();
	}

}
