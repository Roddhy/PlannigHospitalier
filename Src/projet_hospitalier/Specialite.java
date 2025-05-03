package projet_hospitalier;

public class Specialite {
	private int idSpecialite;
	private String nomSpecialite;
	private int nbMinPersonnel;

	// Constructeurs
	public Specialite() {}

	public Specialite(int idSpecialite,String nomSpecialite,int nbMinPersonnel) {
		this.idSpecialite=idSpecialite;
		this.nomSpecialite=nomSpecialite;
		this.nbMinPersonnel=nbMinPersonnel;

	}
	public Specialite(String nomSpecialite,int nbMinPersonnel) {
		this.nomSpecialite=nomSpecialite;
		this.nbMinPersonnel=nbMinPersonnel;
	}

	// Getters et setters
	/*
	 * renvoie le id de la specialite
	 */
	public int getIdSpecialite() {
		return idSpecialite;
	}
	/**
	 *  modifie le nom de la specialite au parametre
	 * @param idSpecialite 
	 */
	public void setIdSpecialite(int idSpecialite) {
		this.idSpecialite = idSpecialite;
	}
	/**
	 * renvoie le nom de la specialite
	 * @return String nom de la specialite
	 */
	public String getNomSpecialite() {
		return nomSpecialite;
	}
	/**
	 * Modifie le nom de la specialite
	 * @param nomSpecialite
	 */
	public void setNomSpecialite(String nomSpecialite) {
		this.nomSpecialite = nomSpecialite;
	}
	/**
	 * Renvoie le nombre minimal de personnel par personnalite
	 * @return
	 */
	public int getNbMinPersonnel() {
		return nbMinPersonnel;
	}
	/**
	 * Modifie le nombre personnel minimal par specialite
	 * @param nbMinPersonnel
	 */
	public void setNbMinPersonnel(int nbMinPersonnel) {
		this.nbMinPersonnel = nbMinPersonnel;
	}


	@Override
	public String toString() {
		return "Specialite{" +
				"idSpecialite=" + idSpecialite +
				", nomSpecialite='" + nomSpecialite + '\'' +
				", nbMinPersonnel=" + nbMinPersonnel +
				'}';
	}
}


