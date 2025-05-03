package projet_hospitalier;

import java.sql.Date;
import java.sql.Time;
 


/**
 * La classe Creneau représente un intervalle de temps spécifique pendant lequel une activité est planifiée, utilisant la classe PlageHoraire.
 */
public class Creneau   {
	private long num_creneau;
	private  Date datedebut; // La date à laquelle le créneau est prévu
	private  Date datefin;
	private  Time heureDebut;
	private  Time  heureFin;
	private String nomcreneau;
	private int idemploi;
	 
	// constructeurs
	public Creneau() {};
	public Creneau(long num_creneau,Date datedebut, Date datefin, Time heureDebut, Time heureFin, String nomcreneau,int idEmploi) {
		this.num_creneau=num_creneau;
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.nomcreneau = nomcreneau; 
		this.idemploi=idEmploi;
	}
	public Creneau(Date datedebut, Date datefin, Time heureDebut, Time heureFin, String nomcreneau,int idEmploi) {
		 
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.nomcreneau = nomcreneau;
		this.idemploi=idEmploi;
	} 
	// Getters et Setters
	/**
	 * retourne le numero automatique du creneau
	 * @return 
	 */
	public long getNum_creneau() {
		return num_creneau;
	}
   /**
    * modifie le numero du creneau a celui en parametre
    * @param num_creneau
    */
	public void setNum_creneau(long num_creneau) {
		this.num_creneau = num_creneau;
	}

	/**
	 * renvoie la date du debut de creneau
	 * @return une date correspondant au debut de creneau
	 */
	public  Date getDatedebut() {
		return datedebut;
	}
	/**
	 * change la date de debut du creneau a celui passe en parametre
	 * @param datedebut
	 */
	public void setDatedebut( Date datedebut) {
		this.datedebut = datedebut;
	}
	/**
	 * renvoie la date de fin d'un creneau
	 * @return une date correspondant à la fin du crneau
	 */
	public  Date getDatefin() {
		return datefin;
	}
	/**
	 * modifie la date de fin du creneau a celle en parametre
	 * @param datefin
	 */
	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}
	/**
	 * renvoie l'heure du debut d'un creneau
	 * @return l'heure du debut
	 */
	public Time getHeureDebut() {
		return heureDebut;
	}
	/**
	 * modifie l'heure du debut du creneau à l'heure passée en parametres
	 * @param heureDebut
	 */
	public void setHeureDebut(Time heureDebut) {
		this.heureDebut = heureDebut;
	}
	/**
	 * renvoie l'heure de fin du creneau
	 * @return heure de fin
	 */
	public Time getHeureFin() {
		return heureFin;
	}
	/**
	 *  modifie l'heure de fin du creneau
	 * @param heureFin
	 */
	public void setHeureFin(Time heureFin) {
		this.heureFin = heureFin;
	}
	/** 
	 * renvoie le nom du creneau
	 * @return un string representant le nom du creneau
	 */
	public String getNomcreneau() {
		return nomcreneau;
	}
	/**
	 * Modifie le nom du creneau a celui passe en parametre
	 * @param nomcreneau
	 */
	public void setNomcreneau(String nomcreneau) {
		this.nomcreneau = nomcreneau;
	}
	 /**
	  * renvoie le le num d'emploi a qui le creneau est associe
	  * @return
	  */
	public int getIdemploi() {
		return idemploi;
	}
	/**
	 * modifier le emploi du temps a qui un creneau appartient 
	 * @param idemploi
	 */
	public void setIdemploi(int idemploi) {
		this.idemploi = idemploi;
	}
	@Override
	public String toString() {
		return "Creneau [datedebut=" + datedebut + ", datefin=" + datefin + ", heureDebut=" + heureDebut + ", heureFin="
				+ heureFin + ", nomcreneau=" + nomcreneau +", idEmploi" + idemploi
				+   "]";
	}


}
