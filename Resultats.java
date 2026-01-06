package cm.itac.Medecine.jsf.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity(name="resultats")

public class Resultats {
	
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	/**
	 * @return the date
	 */
	public Date getDate_resultat() {
		return date_resultat;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate_resultat(Date date_resultat) {
		this.date_resultat = date_resultat;
	}
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Resultats() {
		
		// TODO Auto-generated constructor stub
		examen = new Examen_patient();
		//typeexamen = new TypeExamen();
	}
	/**
	 * @return the id
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "id_examen")
	private Examen_patient examen;
	@NotNull
	private String description;
	@NotNull
	private String libelle;
	@NotNull
	private Date date_resultat;
	
	
//	public TypeExamen getTypeexamen() {
//		return typeexamen;
//	}
//	public void setTypeexamen(TypeExamen typeexamen) {
//		this.typeexamen = typeexamen;
//	}
	/**
	 * @return the examen
	 */
	public Examen_patient getExamen() {
		return examen;
	}
	/**
	 * @param examen the examen to set
	 */
	public void setExamen(Examen_patient examen) {
		this.examen = examen;
	}
	
}
	
	
	
	