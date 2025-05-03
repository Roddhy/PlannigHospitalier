package projet_hospitalier;

public class Fonction {
  private int id_fonction;
  private String nom_fonction;
  
  //constructeurs
  public Fonction() {}
  public Fonction(int id_fonction, String nom_fonction) {
	this.id_fonction = id_fonction;
	this.nom_fonction = nom_fonction;
}
  public Fonction (String nom_fonction) {
	  this.nom_fonction=nom_fonction;
  }
  /**
  Renvoie le id de la fonction.
  * @return id de la fonction
  */
public int getId_fonction() {
	return id_fonction;
}
/**
 * Modifie le id de la fonction.
 * @param nouvel id de la fonction
 */
public void setId_fonction(int id_fonction) {
	this.id_fonction = id_fonction;
}
/**
 * Renvoie le nom de la fonction.
 * @return Nom de la fonction
 */
public String getNom_fonction() {
	return nom_fonction;
}
/**
 * Modifie le nom de la fonction.
 * @param  Nouveau nom de la fonction
 */
public void setNom_fonction(String nom_fonction) {
	this.nom_fonction = nom_fonction;
}
}
