package projet_hospitalier;

import java.sql.Date;

public class Personnel {

    private int idPersonnel;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private int tempsTravailMensuel;
    private int idFonction;
    private int idSpecialite;
    
    //les constructeurs
    public Personnel() {};
    public Personnel(int idPersonnel,String nom,String prenom,Date dateNaissance,int tempsTravailMensuel,int idFonction,int idSpecialite) {
    	this.idPersonnel=idPersonnel;
    	this.nom=nom;
    	this.prenom=prenom;
    	this.dateNaissance=dateNaissance;
    	this.idFonction=idFonction;
    	this.idSpecialite=idSpecialite;
    }
    public Personnel(String nom,String prenom,Date dateNaissance,int tempsTravailMensuel,int idFonction,int idSpecialite) {
    
    	this.nom=nom;
    	this.prenom=prenom;
    	this.dateNaissance=dateNaissance;
    	this.idFonction=idFonction;
    	this.idSpecialite=idSpecialite;
    }
    /**
     * renvoie le id d'un infirmier
     * @return entier
     */
	public int getIdPersonnel() {
		return idPersonnel;
	}
	/**
	 * modifie le id d'un infirmier a celui en parametre
	 * @param idPersonnel
	 */
	public void setIdPersonnel(int idPersonnel) {
		this.idPersonnel = idPersonnel;
	}
	/**
	 * renvoie le nom d'un infirmier
	 * @return chaine de caractere le nom de l'infirmier
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * Modifie le nom d'un infirmier à celui en parametre
	 * @param nom string
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * renvoie le prenom d'un infirmier 
	 * @return string 
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * modifie le prenom d'un infirmeir au string passé en parametre
	 * @param prenom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * renvoie la date de naissance d'un infirmier
	 * @return String
	 */
	public Date getDateNaissance() {
		return dateNaissance;
	}
	/**
	 * modifie la date de naissance d'un infirmier à celle en parametre
	 * @param dateNaissance
	 */
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	/**
	 * Renvoie un entier representant le nombre d'heure par mois
	 * @return entier
	 */
	public int getTempsTravailMensuel() {
		return tempsTravailMensuel;
	}
	/**
	 * modifie la date de naissance d'un infirmier
	 * @param tempsTravailMensuel
	 */
	public void setTempsTravailMensuel(int tempsTravailMensuel) {
		this.tempsTravailMensuel = tempsTravailMensuel;
	}
	/**
	 * Renvoie l'identifiant de la fonction d'un infirmier
	 * @return entier
	 */
	public int getIdFonction() {
		return idFonction;
	}
	/**
	 *  modifie le id de la fonction 
	 * @param idFonction
	 */
	public void setIdFonction(int idFonction) {
		this.idFonction = idFonction;
	}
	/**
	 * Renvoie le id de la specialité d'un infirmier
	 * @return
	 */
	public int getIdSpecialite() {
		return idSpecialite;
	}
	/**
	 * modifie le id de la specialite d'un personnel
	 * @param idSpecialite
	 */
	public void setIdSpecialite(int idSpecialite) {
		this.idSpecialite = idSpecialite;
	}
}
