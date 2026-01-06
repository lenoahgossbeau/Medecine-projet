package cm.itac.Medecine.ManagedBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import cm.itac.Medecine.DAO.SousServiceDAO;
import cm.itac.Medecine.DAO.TypeExamenDAO;
import cm.itac.Medecine.jsf.bean.SousService;
import cm.itac.Medecine.jsf.bean.TypeExamen;

@ManagedBean
public class TypeExamenManagedBean {
	
	
	private TypeExamen typeexamen;
	private String libelle;
	 Map<String, SousService> sousservices;
	 private Long souservice;
	 @EJB
	 SousServiceDAO sousserviceDao;
	 
	public TypeExamen getTypeexamen() {
		return typeexamen;
	}
	public void setTypeexamen(TypeExamen typeexamen) {
		this.typeexamen = typeexamen;
	}
	public Map<String, SousService> getSousservices() {
		
		List<SousService> sousserviceList = new ArrayList<SousService>();

		sousservices = new HashMap<String, SousService>();
		sousserviceList = sousserviceDao.lister();
		for(SousService sousservice : sousserviceList)
			sousservices.put(String.valueOf(sousservice.getId()), sousservice);
		return sousservices;
	}
	public void setSousservices(Map<String, SousService> sousservices) {
		this.sousservices = sousservices;
	}
	public Long getSouservice() {
		return souservice;
	}
	public void setSouservice(Long souservice) {
		this.souservice = souservice;
	}
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public TypeExamenManagedBean() {
		super();
		// TODO Auto-generated constructor stub
		typeexamen = new TypeExamen();
		libelle = typeexamen.getSousservice().getLibelle();
	}
	 
	 TypeExamenDAO typeexamenDao;
	 
	 public String valid(){
		 typeexamenDao.create(typeexamen);
			 FacesMessage message = new FacesMessage( "Vous avez enregistré un type d'examen avec succès !" );
			    FacesContext.getCurrentInstance().addMessage( null, message );
			return "overviewTypeExamen";
		}
	 public void onAddNew() {
	        // Add one new car to the table:
//	        Car car2Add = service.createCars(1).get(0);
//	        cars1.add(car2Add);
		 typeexamen = new TypeExamen();
	        FacesMessage msg = new FacesMessage("New typeexamen added", typeexamen.getReference());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
}
