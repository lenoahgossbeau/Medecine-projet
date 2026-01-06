package cm.itac.Medecine.jsf.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="typeexamen")
public class TypeExamen {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "sousservice")
	private SousService sousservice;
	
	private String reference;
	
	private String prestation;
	
	private int prix;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getPrestation() {
		return prestation;
	}

	public void setPrestation(String prestation) {
		this.prestation = prestation;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public SousService getSousservice() {
		return sousservice;
	}

	public void setSousservice(SousService sousservice) {
		this.sousservice = sousservice;
	}

	public TypeExamen() {
		super();
		// TODO Auto-generated constructor stub
		sousservice = new SousService();
	}

	public TypeExamen(Long id, String reference, String prestation, int prix, SousService sousservice) {
		super();
		this.id = id;
		this.reference = reference;
		this.prestation = prestation;
		this.prix = prix;
		this.sousservice = sousservice;
	}
	
	
}
