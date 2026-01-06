package cm.itac.Medecine.DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cm.itac.Medecine.jsf.bean.Patient;

@Stateless
public class ExamenDAO {
	@PersistenceContext(unitName = "medecine")
	private EntityManager em;
	
	public Patient create(Patient examen){
		em.persist(examen);
		return examen;
		
	}
	
	public Patient trouver(Long id){
		return em.find(Patient.class, id);
		
	}
	
	public Patient detail(Patient examen){
		return trouver(examen.getId());
	}
	
	
	public void delete(Patient examen){
		em.remove(em.merge(examen));
		
	}
	
	public Patient modifier(Patient examen){
		return em.merge(examen);
	}
	
	
	public List<Patient> lister(){
		 TypedQuery<Patient> query = em.createQuery(  "SELECT p FROM Patient p ORDER BY p.id", Patient.class);
		return query.getResultList();
	}

}
