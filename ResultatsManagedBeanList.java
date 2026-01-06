package cm.itac.Medecine.ManagedBean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.SelectEvent;

import cm.itac.Medecine.DAO.ResultatsDAO;
import cm.itac.Medecine.jsf.bean.Resultats;

@ManagedBean
public class ResultatsManagedBeanList {
	private List<Resultats> resultatsList;
	private Resultats resultats;
	private Resultats selectedResultats;
	private String resultatsSelected;
	

	
	public Resultats getSelectedResultats() {
		return selectedResultats;
	}

	public void setSelectedResultats(Resultats selectedResultats) {
		this.selectedResultats = selectedResultats;
	}

	public List<Resultats> getresultatsList() {
		return resultatsList;
	}

	public void setResultatsList(List<Resultats> resultatsList) {
		this.resultatsList = resultatsList;
	}

	/**
	 * 
	 */
	public ResultatsManagedBeanList() {
		super();
		resultats = new Resultats();
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
    public void init() {
	 if(resultatsList == null)
	 resultatsList = resultatsDao.lister();
    }
	
	@EJB
	ResultatsDAO resultatsDao;
	
	public String detail(Resultats resultats){
		this.resultats = resultatsDao.detail(resultats);
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        ResultatsManagedBean resultatsManagedBean = (ResultatsManagedBean) request.getSession().getAttribute("resultatsManagedBean");
        resultatsManagedBean.setResultats(resultats);
		return "ResultatsDetail";
	}
	
	public String edit(Resultats resultats){
		this.resultats = resultatsDao.modifier(resultats);
		return "resultatsEdit";
	}
	
	public String supprimer(Resultats resultats){
		resultatsDao.delete(resultats);
		addMessage("Notification", "Vous avez supprimé le resultat avec success.");
		return "resultatsList";
	}
	public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	public String modifier(){
		Resultats resultats = new Resultats();
		resultats.setId(new Long(resultatsSelected));
		this.setResultats(resultats);
		resultats = resultatsDao.modifier(resultats);
		return "resultatsEdit";
	}

	public Resultats getResultats() {
		return resultats;
	}

	public void setResultats(Resultats resultats) {
		this.resultats = resultats;
	}

	public String getResultatsSelected() {
		return resultatsSelected;
	}

	public void setResultatsSelected(String resultatsSelected) {
		this.resultatsSelected = resultatsSelected;
	}

	public void onRowSelect(SelectEvent event) {
		Resultats resultatsCurrent = (Resultats) event.getObject();
        FacesMessage msg = new FacesMessage("resultat sélectioné", resultatsCurrent.getDescription());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        resultats = resultatsDao.trouver(resultatsCurrent.getId());
        ResultatsManagedBean resultatsManagedBean = (ResultatsManagedBean) request.getSession().getAttribute("resultatsManagedBean");
        resultatsManagedBean.setResultats(resultats);
        
    }

	
	
}
