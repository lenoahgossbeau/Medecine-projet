package cm.itac.Medecine.ManagedBean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import cm.itac.Medecine.DAO.TypeExamenDAO;
import cm.itac.Medecine.jsf.bean.TypeExamen;
import cm.itac.Medecine.jsf.bean.SousService;
import cm.itac.Medecine.DAO.SousServiceDAO;

@ManagedBean
@RequestScoped
public class TypeExamenManagedBeanList {
	private List<TypeExamen> typeexamenList;
	private TypeExamen typeexamen;
	private String typeexamenSelected;
	private SousService sousservice;
	private long id =1;
	
	
	public List<TypeExamen> getTypeexamenList() {
		return typeexamenList;
	}

	public void setTypeexamenList(List<TypeExamen> typeexamenList) {
		this.typeexamenList = typeexamenList;
	}

	/**
	 * 
	 */
	public TypeExamenManagedBeanList() {
		super();
		typeexamen = new TypeExamen();
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
    public void init() {
	 if(typeexamenList == null)
		 typeexamenList = typeexamenDao.lister();
    }
	
	@EJB
	TypeExamenDAO typeexamenDao;
	
	public String detail(TypeExamen typeexamen){
		this.typeexamen = typeexamenDao.detail(typeexamen);
		return "inscriptionDetail";
	}
	
	public String edit(TypeExamen typeexamen){
		this.typeexamen = typeexamenDao.modifier(typeexamen);
		return "inscriptionEdit";
	}
	
	public String supprimer(TypeExamen typeexamen){
		typeexamenDao.delete(typeexamen);
		addMessage("Notification", "Vous avez supprimé l'utilisateur avec success.");
		return "userList";
	}
	
	public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	public String modifier(){
		TypeExamen typeexamen = new TypeExamen();
		typeexamen.setId(new Long(typeexamenSelected));
		this.setTypeexamen(typeexamen);
		typeexamen = typeexamenDao.modifier(typeexamen);
		return "userEdit";
	}
	
	

	public SousService getSousservice() {
		return sousservice;
	}

	public void setSousservice(SousService sousservice) {
		this.sousservice = sousservice;
	}

	public TypeExamen getTypeexamen() {
		return typeexamen;
	}

	public void setTypeexamen(TypeExamen typeexamen) {
		this.typeexamen = typeexamen;
	}

	public String getTypeexamenSelected() {
		return typeexamenSelected;
	}

	public void setTypeexamenSelected(String typeexamenSelected) {
		this.typeexamenSelected = typeexamenSelected;
	}

	public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("TypeExamen Edited", ((TypeExamen) event.getObject()).getReference());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((TypeExamen) event.getObject()).getReference());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    SousServiceDAO sousserviceDao;
    public void onAddNew() {
	 typeexamen.getSousservice().setId(id);
    	TypeExamen typeexamen = typeexamenDao.create(getTypeexamen());
        FacesMessage msg = new FacesMessage("New typeexamen added", typeexamen.getReference());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

//	public void onRowSelect(SelectEvent event) {
//		
//		TypeExamen userCurrent = (TypeExamen) event.getObject();
//        FacesMessage msg = new FacesMessage("Utilisateur sélectioné", userCurrent.getUsername());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//        
//        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//        user = inscriptionDao.trouve(userCurrent.getId());
//        InscriptionManagedBean inscriptionManagedBean = (InscriptionManagedBean) request.getSession().getAttribute("InscriptionManagedBean");
//        inscriptionManagedBean.setUser(user);
//        
//    }
	
}
