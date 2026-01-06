package cm.itac.Medecine.DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cm.itac.Medecine.jsf.bean.SousService;

@Stateless
public class SousServiceDAO {
	
	@PersistenceContext(unitName = "medecine")
	private EntityManager em;
	
	public SousService create(SousService sousservice){
		em.persist(sousservice);
		em.flush();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sousservice;
		
	}
	
	public SousService trouver(Long id){
		return em.find(SousService.class, id);
		
	}
	
	public SousService detail(SousService sousservice){
		return trouver(sousservice.getId());
	}
	
	
	public void delete(SousService sousservice){
		em.remove(em.merge(sousservice));
		
	}
	
	public SousService modifier(SousService sousservice){
		return em.merge(sousservice);
	}
	
	
	public List<SousService> lister(){
		 TypedQuery<SousService> query = em.createQuery(  "SELECT s FROM sousservice s ORDER BY s.id", SousService.class);
		return query.getResultList();
	}
}
