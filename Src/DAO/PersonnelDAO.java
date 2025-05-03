package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projet_hospitalier.Personnel;

public class PersonnelDAO  extends DAO<Personnel>{

	@Override
	/*
	 *  Méthode pour ajouter un nouveau personnel
	 */
	public Personnel ajouter(Personnel personnel) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO personnel (nom, prenom, date_naissance, temps_de_travail_mensuel, id_fonction, id_specialite) VALUES (?, ?, ?, ?, ?, ?)")) {
			preparedStatement.setString(1, personnel.getNom());
			preparedStatement.setString(2, personnel.getPrenom());
			preparedStatement.setDate(3, personnel.getDateNaissance());
			preparedStatement.setInt(4, personnel.getTempsTravailMensuel());
			preparedStatement.setInt(5, personnel.getIdFonction());
			preparedStatement.setInt(6, personnel.getIdSpecialite());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personnel;
	}
	/**
	 *  Méthode pour mettre à jour un personnel
	 */
	@Override
	public Personnel modifier(Personnel personnel) throws SQLException {
		try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE personnel SET nom=?, prenom=?, date_naissance=?, temps_de_travail_mensuel=?, id_fonction=?, id_specialite=? where id_personnel=?")) {

			preparedStatement.setString(1, personnel.getNom());
			preparedStatement.setString(2, personnel.getPrenom());
			preparedStatement.setDate(3, personnel.getDateNaissance());
			preparedStatement.setInt(4, personnel.getTempsTravailMensuel());
			preparedStatement.setInt(5, personnel.getIdFonction());
			preparedStatement.setInt(6, personnel.getIdSpecialite());
			preparedStatement.setInt(7, personnel.getIdPersonnel());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return personnel;
	}

	@Override
	/**
	 * Méthode pour supprimer un personnel
	 */
	public boolean supprimer(int id) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM personnel WHERE id_personnel=?")) {
			preparedStatement.setInt(1, id);
			int rowsAffected = preparedStatement.executeUpdate();

			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	} 
	// methode pour supprimer selon le nom de la personne 
	public boolean supprimer(String nom) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM personnel WHERE nom=?")) {
			preparedStatement.setString(1, nom);
			int rowsAffected = preparedStatement.executeUpdate();

			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	/*
	 * methode pour trouver un infirmier selon son id 
	 */
	public Personnel trouver(int id) throws SQLException {
		String requete = "SELECT * FROM personnel WHERE id_personnel = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(requete);
		preparedStatement.setInt(1, id);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			Personnel personnel = new Personnel();
			personnel.setIdPersonnel(resultSet.getInt("id_personnel"));
			personnel.setNom(resultSet.getString("nom"));
			personnel.setPrenom(resultSet.getString("prenom"));
			personnel.setDateNaissance(resultSet.getDate("date_naissance"));
			personnel.setTempsTravailMensuel(resultSet.getInt("temps_de-travail_mensuel"));
			personnel.setIdFonction(resultSet.getInt("id_fonction"));
			personnel.setIdSpecialite(resultSet.getInt("id_specialite"));
			return personnel;
		}

		return null; // Retourne null si aucun personnel n'est trouvé avec cet ID
	}
	 
	// la plus logique c'est plus facile de memoriser un non qu'un id !
	public Personnel trouver(String nom) throws SQLException {
		String requete = "SELECT * FROM personnel WHERE nom= ?";
		PreparedStatement preparedStatement = connection.prepareStatement(requete);
		preparedStatement.setString(1, nom);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			Personnel personnel = new Personnel();
			personnel.setIdPersonnel(resultSet.getInt("id_personnel"));
			personnel.setNom(resultSet.getString("nom"));
			personnel.setPrenom(resultSet.getString("prenom"));
			personnel.setDateNaissance(resultSet.getDate("date_naissance"));
			personnel.setTempsTravailMensuel(resultSet.getInt("temps_de-travail_mensuel"));
			personnel.setIdFonction(resultSet.getInt("id_fonction"));
			personnel.setIdSpecialite(resultSet.getInt("id_specialite"));
			return personnel;
		}

		return null; // Retourne null si aucun personnel n'est trouvé avec cet ID
	}
	@Override
	/**
	 * methode pour lire tout le personnel
	 */
	public List<Personnel> lister() throws SQLException {
		ArrayList<Personnel> personnels = new ArrayList<>();
		try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM personnel");
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Personnel personnel = new Personnel();
				personnel.setIdPersonnel(resultSet.getInt("id_personnel"));
				personnel.setNom(resultSet.getString("nom"));
				personnel.setPrenom(resultSet.getString("prenom"));
				personnel.setDateNaissance(resultSet.getDate("date_naissance"));
				personnel.setTempsTravailMensuel(resultSet.getInt("temps_de_travail_mensuel"));
				personnel.setIdFonction(resultSet.getInt("id_fonction"));
				personnel.setIdSpecialite(resultSet.getInt("id_specialite"));

				personnels.add(personnel);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personnels;
	}
	public List<Integer> listerID() throws SQLException {
		ArrayList<Integer> personnelID = new ArrayList<>();
		try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT id_personnel FROM personnel");
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				int personnelid = resultSet.getInt("id_personnel");
	
				personnelID.add(personnelid);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personnelID;
	}
/**
 *  Méthode pour ajouter la participation d'un personnel à un créneau
 * @param idPersonnel
 * @param numCreneau
 * @throws SQLException
 */
   public void ajouterParticipation(int idPersonnel, int numCreneau) throws SQLException {
        String requete = "INSERT INTO participe(num_creneau, id_personnel) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(requete);
        preparedStatement.setInt(1, numCreneau);
        preparedStatement.setInt(2, idPersonnel);
        preparedStatement.executeUpdate();
    }



}
