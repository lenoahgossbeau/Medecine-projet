package cm.itac.Medecine.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import cm.itac.Medecine.jsf.bean.SousService;
import cm.itac.Medecine.jsf.bean.TypeExamen;

@Stateless
public class TypeExamenDAO {
	
	@PersistenceContext(unitName = "medecine")
	private EntityManager em;
	
	public TypeExamen create(TypeExamen typeexamen){
		em.persist(typeexamen);
		em.flush();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return typeexamen;
		
	}
	
	public TypeExamen trouver(Long id){
		return em.find(TypeExamen.class, id);
		
	}
	
	
	public List<TypeExamen> trouverBySousService(SousService sousservice){
		long id = sousservice.getId();
		String qerryString ="SELECT t FROM typeexamen t WHERE t.sousservice=:sousservice"; 
		//TypedQuery<TypeExamen> query = em.createQuery(  "SELECT t FROM TypeExamen t WHERE t.sousservice=:sousservice ORDER BY t.id", TypeExamen.class);
		List<TypeExamen> typeExamens = new ArrayList<TypeExamen>();
		try{
			Query requette = em.createQuery(qerryString);
			requette.setParameter("sousservice", sousservice);
			 typeExamens = requette.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return typeExamens;
		
	}
	
	public TypeExamen detail(TypeExamen typeexamen){
		return trouver(typeexamen.getId());
	}
	
	
	public void delete(TypeExamen typeexamen){
		em.remove(em.merge(typeexamen));
	
	}
	
	public TypeExamen modifier(TypeExamen typeexamen){
		return em.merge(typeexamen);
	}
	
	
	public List<TypeExamen> lister(){
		 TypedQuery<TypeExamen> query = em.createQuery(  "SELECT t FROM typeexamen t ORDER BY t.id", TypeExamen.class);
		return query.getResultList();
	}
}
