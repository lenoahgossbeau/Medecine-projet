package cm.itac.Medecine.jsf.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity(name="examen")

public class Examen_patient {
	
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
	public Date getDate_examen() {
		return date_examen;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate_examen(Date date_examen) {
		this.date_examen = date_examen;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public Examen_patient() {
		
		// TODO Auto-generated constructor stub
		patient = new Patient();
		//typeexamen = new TypeExamen();
	}
	/**
	 * @return the id
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "id_patient")
	private Patient patient;
//	@ManyToOne
//	@JoinColumn(name = "type")
//	private TypeExamen typeexamen;
	@NotNull
	private String description;
	@NotNull
	private String type;
	@NotNull
	private Date date_examen;
	public String statut;
	
	
//	public TypeExamen getTypeexamen() {
//		return typeexamen;
//	}
//	public void setTypeexamen(TypeExamen typeexamen) {
//		this.typeexamen = typeexamen;
//	}
	/**
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}
	/**
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
}
	
	
	
	