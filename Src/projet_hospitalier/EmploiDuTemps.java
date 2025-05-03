package projet_hospitalier;


import java.sql.Time;
import java.sql.Date;

public class EmploiDuTemps {

	private int idEmploi;
	private Date date_debut;
	private Time heureDebut;
	private Time heureFin;
	private Date date_fin;

	// Constructeur par défaut
	public EmploiDuTemps() {}

	// Getters et setters
	public int getIdEmploi() {
		return idEmploi;
	}

	public void setIdEmploi(int idEmploi) {
		this.idEmploi = idEmploi;
	}

	public Date getDateDebut() {
		return date_debut;
	}

	public void setDateDebut(Date date) {
		this.date_debut = date;
	}

	public Time getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(Time heureDebut) {
		this.heureDebut = heureDebut;
	}

	public Time getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(Time heureFin) {
		this.heureFin = heureFin;
	}

	public Date getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

	 
}
