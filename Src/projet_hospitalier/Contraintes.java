package projet_hospitalier;


public class Contraintes {

	private int idContrainte;
	private String explicationContrainte;
	private int duree;
	private int idType;  // Ceci correspond à l'ID du type de contrainte

    //constructeur 
	public Contraintes(int idContrainte, String explicationContrainte, int duree, int idType) {
		this.idContrainte = idContrainte;
		this.explicationContrainte = explicationContrainte;
		this.duree = duree;
		this.idType = idType;
	}
	 // les getters et les setters
      /**
       * retourne le id de la contrainte
       * @return un entier 
       */
	public int getIdContrainte() {
		return idContrainte;
	}
    /**
     * Modifie le id d'une contrainte à celui passé en parametre
     * @param idContrainte
     */
	public void setIdContrainte(int idContrainte) {
		this.idContrainte = idContrainte;
	}
    /**
     * Renvoie l'explication de la contrainte 
     * @return une chaine de caracteres
     */
	public String getExplicationContrainte() {
		return explicationContrainte;
	}
   /**
    * remplacer l'explication de la contrainte par la chaine en parametre
    * @param explicationContrainte
    */
	public void setExplicationContrainte(String explicationContrainte) {
		this.explicationContrainte = explicationContrainte;
	}
    /**
     * renvoie la durée sur laquelle cette contrainte s'ettend
     * @return un entier
     */
	public int getDuree() {
		return duree;
	}
    /**
     * modifie la duree d'une contrainte a celle en parametre
     * @param duree
     */
	public void setDuree(int duree) {
		this.duree = duree;
	}
	/**
	 * retourne le id correspondant au type de la contrainte
	 * @return  un entier
	 */

	public int getIdType() {
		return idType;
	}
    /**
     * Modifie le id du type de la contrainte avec celui en parametre
     * @param idType
     */
	public void setIdType(int idType) {
		this.idType = idType;
	}
}

