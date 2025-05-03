package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projet_hospitalier.Specialite;
/**
 * cette classe fournis des methodes pour inserer supprimer modifier une specialite dans la bdd
 *
 */
public class SpecialiteDAO extends DAO<Specialite>{
	/**
	 * Methode pour inseerer une specialite dans la bdd
	 */
	@Override
	public Specialite ajouter(Specialite specialite) throws SQLException {
		String query = "INSERT INTO specialite (nom_specialite, nb_min_personnel) VALUES (?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, specialite.getNomSpecialite());
		preparedStatement.setInt(2, specialite.getNbMinPersonnel());
		try {preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return specialite;
	}
	/**
	 * methode pour modifier une specialite son nom et nbminimal personnel
	 */
	@Override
	public Specialite modifier(Specialite specialite) throws SQLException {
		String query = "UPDATE specialite SET nom_specialite= ? , nb_min_personnel=? WHERE id_specialite = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1,specialite.getNomSpecialite());
		preparedStatement.setInt(2,specialite.getNbMinPersonnel());
		preparedStatement.setInt(3, specialite.getIdSpecialite());

		try {preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return specialite;
	}
	/**
	 * methode pour supprimer une specialite de la bdd
	 */
	@Override
	public boolean supprimer(int id) throws SQLException {
		String query = "DELETE FROM specialite WHERE id_specialite = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;
		}
	}
	/**
	 * une methode pour recuperer une specialite de la bdd
	 */
	@Override
	public Specialite trouver(int id) throws SQLException {
		String query = "SELECT * FROM specialite WHERE id_specialite = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				Specialite specialite = new Specialite();
				specialite.setIdSpecialite(resultSet.getInt("id_specialite"));
				specialite.setNomSpecialite(resultSet.getString("nom_specialite"));
				specialite.setNbMinPersonnel(resultSet.getInt("nb_min_personnel"));
				return specialite;
			}}
		return null;
	}
	
	/**
	 * une methode pour recuperer l'ensemble des specialte de la bdd
	 */
	@Override
	public List<Specialite> lister() throws SQLException {
		List<Specialite> specialites = new ArrayList<>();
		String query = "SELECT * FROM specialite";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Specialite specialite = new Specialite();
				specialite.setIdSpecialite(resultSet.getInt("id_specialite"));
				specialite.setNomSpecialite(resultSet.getString("nom_specialite"));
				specialite.setNbMinPersonnel(resultSet.getInt("nb_min_personnel"));
				specialites.add(specialite);
			}
		}

		return specialites;

	}

}
