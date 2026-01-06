package cm.itac.Medecine.DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cm.itac.Medecine.jsf.bean.Role;

@Stateless
public class RoleDAO {
	
	@PersistenceContext(unitName = "medecine")
	private EntityManager em;
	
	public Role create(Role role){
		em.persist(role);
		em.flush();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return role;
		
	}
	
	public Role trouver(Long id){
		return em.find(Role.class, id);
		
	}
	
	public Role detail(Role role){
		return trouver(role.getId());
	}
	
	
	public void delete(Role role){
		em.remove(em.merge(role));
		
	}
	
	public Role modifier(Role role){
		return em.merge(role);
	}
	
	
	public List<Role> lister(){
		 TypedQuery<Role> query = em.createQuery(  "SELECT r FROM Role r ORDER BY r.id", Role.class);
		return query.getResultList();
	}
}
