package cm.itac.Medecine.DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import cm.itac.Medecine.jsf.bean.User;


@Stateless
public class UserDAO {
	
	@PersistenceContext(unitName = "medecine")
	private EntityManager em;
	
	public User create(User user){
		em.persist(user);
		em.flush();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
		 
	}
	
	public User trouve(Long id){
		return em.find(User.class, id);
		
	}
	
	public User trouver(String email){
		
		String queryString = "SELECT user From User user WHERE user.email=:email";
		Query requete = em.createQuery(queryString);
				requete.setParameter("email", email);
		List<User> listUsers = requete.getResultList();
		User user = null;
		if(listUsers.size() >0)
			user =listUsers.get(0);
		return user;
		
	}
	
	public User detail(User user){
		return trouver(user.getEmail());
	}
	
	public void supprimer(User user){
		em.remove(em.merge(user));
	}
	
	public User modifier(User user){
		return em.merge(user);
	}

	public List<User> lister() {
		TypedQuery<User> query = em.createQuery(  "SELECT u FROM User u ORDER BY u.id", User.class);
		return query.getResultList();
	}

}
