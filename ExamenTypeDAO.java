package cm.itac.Medecine.DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import cm.itac.Medecine.jsf.bean.Examen_patient;
import cm.itac.Medecine.jsf.bean.ExamenType;
import cm.itac.Medecine.jsf.bean.TypeExamen;

@Stateless
public class ExamenTypeDAO {
	
	@PersistenceContext(unitName = "medecine")
	private EntityManager em;
	
	public ExamenType create(ExamenType examentype){
		em.persist(examentype);
		em.flush();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return examentype;
		
	}
	
	public ExamenType trouver(Long id){
		return em.find(ExamenType.class, id);
		
	}
	
	
//	public List<TypeExamen> trouverBySousService(SousService sousservice){
//		 TypedQuery<TypeExamen> query = em.createQuery(  "SELECT t FROM typeexamen t ORDER BY t.id", TypeExamen.class);
//			return query.getResultList();
//		
//	}
//	
//	public ExamenType detail(ExamenType examentype){
//		return trouver(examentype.getId());
//	}
	
	
	public void delete(ExamenType examentype){
		em.remove(em.merge(examentype));
	
	}
	
	public ExamenType modifier(ExamenType examentype){
		return em.merge(examentype);
	}
	
	
	public List<ExamenType> lister(){
		 TypedQuery<ExamenType> query = em.createQuery(  "SELECT t FROM examentype t ORDER BY t.id", ExamenType.class);
		return query.getResultList();
	}
}
