package cm.itac.Medecine.DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cm.itac.Medecine.jsf.bean.Resultats;

@Stateless
public class ResultatsDAO {
	
	@PersistenceContext(unitName = "medecine")
	private EntityManager em;
	
	public Resultats create(Resultats resultats){
		em.persist(resultats);
		em.flush();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultats;
		
	}
	
	public Resultats trouver(Long id){
		return em.find(Resultats.class, id);
		
	}
	
	public Resultats detail(Resultats resultats){
		return trouver(resultats.getId());
	}
	
	
	public void delete(Resultats resultats){
		em.remove(em.merge(resultats));
		
	}
	
	public Resultats modifier(Resultats resultats){
		return em.merge(resultats);
	}
	
	
	public List<Resultats> lister(){
		 TypedQuery<Resultats> query = em.createQuery(  "SELECT r FROM resultats r ORDER BY r.id", Resultats.class);
		return query.getResultList();
	}

}



