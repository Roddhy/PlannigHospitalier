package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projet_hospitalier.Fonction;
/**
 * la classe fonctionDAO permet de creer modifier supprimer une fonction de labdd
 */

public class FonctionDAO  extends DAO<Fonction>{

	@Override
	/**
	 * Ajouter une fonction à la bdd
	 */
	public Fonction ajouter(Fonction fonction) throws SQLException {
		String query = "INSERT INTO fonction (nom_fonction) VALUES (?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, fonction.getNom_fonction());
		preparedStatement.executeUpdate();

		try {
			preparedStatement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fonction;
	}
	/**
	 * modifier un enregistremnt de la table fonction
	 */
	@Override
	public Fonction modifier(Fonction fonction) throws SQLException {
		String query = "UPDATE fonction SET nom_fonction = ? WHERE id_fonction = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, fonction.getNom_fonction());
		preparedStatement.setInt(2, fonction.getId_fonction());

		try {preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return fonction;
	}
	/**
	 * supprimer une fonction 
	 */

	@Override
	public boolean supprimer(int id) throws SQLException {
		String query = "DELETE FROM fonction WHERE id_fonction = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);

		try {
			preparedStatement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 *  recuperer une fonction de la bdd
	 */
	@Override
	public Fonction trouver(int id) throws SQLException {
		String query = "SELECT * FROM fonction WHERE id_fonction = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				Fonction fonction = new Fonction();
				fonction.setId_fonction(resultSet.getInt("id_fonction"));
				fonction.setNom_fonction(resultSet.getString("nom_fonction"));
				return fonction;
			}}
		return null;
	}
	/**
	 * lister l'ensemble des fonctions existante dans la bdd
	 */
	@Override
	public List<Fonction> lister() throws SQLException {
		List<Fonction> fonctions = new ArrayList<>();
		String query = "SELECT * FROM fonction";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Fonction fonction = new Fonction();
				fonction.setId_fonction(resultSet.getInt("id_fonction"));
				fonction.setNom_fonction(resultSet.getString("nom_fonction"));
				fonctions.add(fonction);
			}
		}

		return fonctions;
	}

}


