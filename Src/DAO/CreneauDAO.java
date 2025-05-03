package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import projet_hospitalier.Creneau;

public class CreneauDAO extends DAO<Creneau> {
	/**
	 * surchage de la methode ajouter du dao pour inserer un creneau a la bd
	 */
	@Override
	public Creneau ajouter(Creneau creneau)   {
		String query2 = "INSERT INTO creneau (date_creneau, date_fin, heure_debut, heure_fin, nomcreneau, idEmploi) VALUES (?, ?, ?, ?, ?,?)";
		try {
			PreparedStatement pstmt = connection.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS);
			pstmt.setDate(1,creneau.getDatedebut());
			pstmt.setDate(2,creneau.getDatefin());
			pstmt.setTime(3,creneau.getHeureDebut());
			pstmt.setTime(4,creneau.getHeureFin());
			pstmt.setString(5, creneau.getNomcreneau());
			pstmt.setInt(6, creneau.getIdemploi());
			pstmt.executeUpdate();
			// cle genre automatiquement
			try (ResultSet cles = pstmt.getGeneratedKeys()) {
				if (cles.next()) {
					long id = cles.getLong(1);
					creneau.setNum_creneau(id);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return creneau;
	}
	/**
	 * surcharge de la methode modifier pour modifier un creneau dans la bdd
	 */
	@Override
	public Creneau modifier(Creneau creneauA) throws SQLException {
		String requete = "UPDATE creneau  SET num_creneau='" +  creneauA.getNum_creneau()+"', ";
		requete +="nomcreneau= '"+ creneauA.getNomcreneau()+ "', ";
		requete += "date_creneau= '"+ creneauA.getDatedebut();
		requete += "date_fin= '"+ creneauA.getDatefin();
		requete += "heure_debut= '"+ creneauA.getHeureDebut();
		requete += "heure_fin= '"+ creneauA.getHeureFin();
		requete += "id_emploi ='"+creneauA.getIdemploi();
		requete +="WHERE num_creneau= " + creneauA.getNum_creneau();
		try {
			stmt.executeUpdate(requete);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return creneauA;
	}
	/*
	 * methode pour supprimer un creneau 
	 */
	@Override
	public boolean supprimer(int id )   {
		String requete = "DELETE FROM creneau WHERE num_creneau = " +id;
		try {
			stmt.executeUpdate(requete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	/*
	 * selectionner un creneau de la bdd correspondant a l'id en parametre
	 */
	@Override
	public Creneau trouver(int id) throws SQLException {
		Creneau newcrn= null;
		String query = "SELECT * FROM creneau WHERE num_creneau=?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		try (ResultSet resultSet = preparedStatement.executeQuery()){
			if (resultSet.next()) {
				int num_creneau = resultSet.getInt("num_creneau");
				String nom = resultSet.getString("nomcreneau");
				Date  datedebut = resultSet.getDate("date_creneau");
				Date  datefin = resultSet.getDate("date_fin");
				Time heuredebut=resultSet.getTime("heure_debut");
				Time heurefin=resultSet.getTime("heure_fin");
				int idEmploi=resultSet.getInt("idEmploi");

				newcrn= new Creneau(num_creneau,datedebut,datefin,heuredebut,heurefin, nom, idEmploi);
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return newcrn;
	}
	/*
	 * selection de la liste des creneaux
	 */
	@Override
	public List<Creneau> lister() throws SQLException {
		List<Creneau> listeCreneaux = new ArrayList<>();
		String requete = "SELECT * FROM creneau";

		try (PreparedStatement preparedStatement = connection.prepareStatement(requete);
				ResultSet resultat = preparedStatement.executeQuery()) {  

			while (resultat.next()) {

				Creneau creneau = new Creneau(
						resultat.getInt("num_creneau"),   
						resultat.getDate("date_creneau"),
						resultat.getDate("date_fin"),
						resultat.getTime("heure_debut"),
						resultat.getTime("heure_fin"),
						resultat.getString("nomcreneau"),
						resultat.getInt("idEmploi")
						);

				listeCreneaux.add(creneau);
			}

		} catch (SQLException e) {
			e.printStackTrace();  
			throw e;   
		}

		return listeCreneaux;
	}
	/**
	 * methode pour selectionner les creneaux concernant un employe
	 * @throws SQLException 
	 */
	public List<Creneau> CreneauPersonnel(int id) throws SQLException {
		List<Creneau> creneauxpersonnel= new ArrayList<>();
		String query="SELECT c.* FROM creneau c";
		query+="INNER JOIN participe p ON c.num_creneau = p.num_creneau";
		query+=" WHERE p.id_personnel = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id); 
		try (ResultSet resultSet = preparedStatement.executeQuery()){
			if (resultSet.next()) {
				int num_creneau = resultSet.getInt("num_creneau");
				String nom = resultSet.getString("nomcreneau");
				Date  datedebut = resultSet.getDate("date_creneau");
				Date  datefin = resultSet.getDate("date_fin");
				Time heuredebut=resultSet.getTime("heure_debut");
				Time heurefin=resultSet.getTime("heure_fin");
				int idEmploi=resultSet.getInt("idEmploi");

				Creneau creneaupersonnel= new Creneau(num_creneau,datedebut,datefin,heuredebut,heurefin, nom, idEmploi);
				creneauxpersonnel.add(creneaupersonnel);
			}} catch (SQLException e) {
				e.printStackTrace();  
				throw e;   
			}
		return creneauxpersonnel;
	}
}


