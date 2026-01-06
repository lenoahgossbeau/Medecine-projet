package cm.itac.Medecine.jsf.bean;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Patient {
	/**
	 * @return the id
	 */
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
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the tel
	 */
	public Double getTelephone() {
		return telephone;
	}
	/**
	 * @param tel the tel to set
	 */
	public void setTelephone(Double telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return the sexe
	 */
	public String getSexe() {
		return sexe;
	}
	/**
	 * @param sexe the sexe to set
	 */
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}
	/**
	 * @return the information_comp
	 */
	public String getPoids() {
		return poids;
	}
	/**
	 * @param information_comp the information_comp to set
	 */
	public void setPoids(String poids) {
		this.poids = poids;
	}
	/**
	 * @return the libelle
	 */
	public String getTemperature() {
		return temperature;
	}
	/**
	 * @param libelle the libelle to set
	 */
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	/**
	 * @return the pouls
	 */
	public Integer getPouls() {
		return pouls;
	}
	/**
	 * @param pouls the pouls to set
	 */
	public void setPouls(Integer pouls) {
		this.pouls = pouls;
	}
	/**
	 * @return the tension
	 */
	public Integer getTension() {
		return tension;
	}
	/**
	 * @param tension the tension to set
	 */
	public void setTension(Integer tension) {
		this.tension = tension;
	}
	/**
	 * 
	 */
	
	
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the lieu_de_residence
	 */
	public String getLieu_de_residence() {
		return lieu_de_residence;
	}
	/**
	 * @param lieu_de_residence the lieu_de_residence to set
	 */
	public void setLieu_de_residence(String lieu_de_residence) {
		this.lieu_de_residence = lieu_de_residence;
	}
	/**
	 * @return the statut_matrimoniale
	 */
	public String getStatut_matrimoniale() {
		return statut_matrimoniale;
	}
	/**
	 * @param statut_matrimoniale the statut_matrimoniale to set
	 */
	public void setStatut_matrimoniale(String statut_matrimoniale) {
		this.statut_matrimoniale = statut_matrimoniale;
	}
	/**
	 * @return the motif_de_la_consultation
	 */
	public String getMotif_de_la_consultation() {
		return motif_de_la_consultation;
	}
	/**
	 * @param motif_de_la_consultation the motif_de_la_consultation to set
	 */
	public void setMotif_de_la_consultation(String motif_de_la_consultation) {
		this.motif_de_la_consultation = motif_de_la_consultation;
	}
	/**
	 * @return the medicament_pris
	 */
	public String getMedicament_pris() {
		return medicament_pris;
	}
	/**
	 * @param medicament_pris the medicament_pris to set
	 */
	public void setMedicament_pris(String medicament_pris) {
		this.medicament_pris = medicament_pris;
	}
	/**
	 * @return the antecedent
	 */
	public String getAntecedent() {
		return antecedent;
	}
	/**
	 * @param antecedent the antecedent to set
	 */
	public void setAntecedent(String antecedent) {
		this.antecedent = antecedent;
	}
	/**
	 * @return the bilan
	 */
	public String getBilan() {
		return bilan;
	}
	/**
	 * @param bilan the bilan to set
	 */
	public void setBilan(String bilan) {
		this.bilan = bilan;
	}
	/**
	 * @return the traitement
	 */
	public String getTraitement() {
		return traitement;
	}
	/**
	 * @param traitement the traitement to set
	 */
	public void setTraitement(String traitement) {
		this.traitement = traitement;
	}
	public Patient(Long id, String prenom, String nom, Double telephone, String sexe, String age, String poids, String temperature, Integer pouls,Integer tension,String lieu_de_residence, String statut_matrimoniale, String motif_de_la_consultation,String medicament_pris, String antecedent, String bilan,String traitement) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.telephone = telephone;
		this.sexe = sexe;
		this.age = age;
		this.poids = poids;
		this.temperature = temperature;
		this.pouls = pouls;
		this.tension = tension;
		this.lieu_de_residence = lieu_de_residence;
		this.statut_matrimoniale = statut_matrimoniale;
		this.motif_de_la_consultation = motif_de_la_consultation;
		this.medicament_pris = medicament_pris;
		this.antecedent = antecedent;
		this.bilan = bilan;
		this.traitement = traitement;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String prenom;
	
	private String nom;
	
	private Double telephone;
	
	private String sexe;
	
	private String age;
	
	private String poids;
	
	private String temperature;
	
	private Integer pouls;
	
	private Integer tension;
	
	private String lieu_de_residence;
	
	private String statut_matrimoniale;
	
	private String motif_de_la_consultation;
	
	private String medicament_pris;
	
	private String antecedent;
	
	private String bilan;
	
	private String traitement;

}

	
	



