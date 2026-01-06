package cm.itac.Medecine.jsf.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="examen_type")
public class ExamenType {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "examen")
	private Examen_patient examen;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "typeexamen")
	private TypeExamen typeexamen;

	public Examen_patient getExamen() {
		return examen;
	}

	public void setExamen(Examen_patient examen) {
		this.examen = examen;
	}

	public TypeExamen getTypeexamen() {
		return typeexamen;
	}

	public void setTypeexamen(TypeExamen typeexamen) {
		this.typeexamen = typeexamen;
	}
	

	public ExamenType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExamenType(Examen_patient examen, TypeExamen typeexamen) {
		super();
		this.examen = examen;
		this.typeexamen = typeexamen;
	}
	
	
}
