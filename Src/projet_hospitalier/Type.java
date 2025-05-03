package projet_hospitalier;
/**
 * cette classe est pour gerer les deux types de contraintes
 *
 */
public class Type {

	private int idType;     
	private String nom;    
	//constructeur
	public Type(int idType, String nom) {
		this.idType = idType;
		this.nom = nom;
	}
	public Type() {
		// TODO Auto-generated constructor stub
	}
	// les getters et les setters
	/**
	 * Renvoie le id du type
	 * @return
	 */
	public int getIdType() {
		return idType;
	}
	/**
	 * Modifie le id  type 
	 * @param idType
	 */
	public void setIdType(int idType) {
		this.idType = idType;
	}
	/**
	 * renvoie le nom du type
	 * @return
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * Modifie le nom du type
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * methode toString
	 */
	@Override
	public String toString() {
		return "Type{" +
				"idType=" + idType +
				", nom='" + nom + '\'' +
				'}';
	}
}
