package cm.itac.Medecine.jsf.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="sousservice")
public class SousService {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String code;
	
	private String libelle;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public SousService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SousService(Long id, String code, String libelle) {
		super();
		this.id = id;
		this.code = code;
		this.libelle = libelle;
	}
	
	
}
