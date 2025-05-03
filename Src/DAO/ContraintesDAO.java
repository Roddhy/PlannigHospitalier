package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projet_hospitalier.Contraintes;

public class ContraintesDAO extends DAO<Contraintes> {

	/**
	 * Ajoute une nouvelle contrainte à la base de données.
	 *
	 * @param contrainte L'objet Contraintes à ajouter.
	 * @return true si l'ajout a réussi, false sinon.
	 * @throws SQLException en cas d'erreur lors de l'exécution de la requête SQL.
	 */
	@Override
	public Contraintes ajouter(Contraintes contraintes) throws SQLException {

		String query = "INSERT INTO contraintes (explication_contrainte, duree, id_type) VALUES ( ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query) ;
		preparedStatement.setString(1, contraintes.getExplicationContrainte());
		preparedStatement.setInt(2, contraintes.getDuree());
		preparedStatement.setInt(3, contraintes.getIdType());
		
		try (ResultSet cles = preparedStatement.getGeneratedKeys()) {
			if (cles.next()) {
				int id = cles.getInt(1);
				 contraintes.setIdContrainte(id);
			}
		try {
			preparedStatement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contraintes;
		}
	}

	/**
	 * Modifie une contrainte existante dans la base de données.
	 *
	 * @param contrainte L'objet Contraintes à mettre à jour.
	 * @return true si la modification a réussi, false sinon.
	 * @throws SQLException en cas d'erreur lors de l'exécution de la requête SQL.
	 */
	@Override
	public Contraintes modifier(Contraintes contraintes) throws SQLException {
		String query = "UPDATE contraintes SET explication_contrainte=?, duree=?, id_type=? WHERE id_contrainte=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, contraintes.getExplicationContrainte());
			preparedStatement.setInt(2, contraintes.getDuree());
			preparedStatement.setInt(3, contraintes.getIdType());
			preparedStatement.setInt(4, contraintes.getIdContrainte());

			try {
				preparedStatement.executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return contraintes;

		}
	}

	/**
	 * Supprime une contrainte de la base de données en fonction de son identifiant.
	 *
	 * @param id L'identifiant de la contrainte à supprimer.
	 * @return true si la suppression a réussi, false sinon.
	 * @throws SQLException en cas d'erreur lors de l'exécution de la requête SQL.
	 */

	@Override
	public boolean supprimer(int id) throws SQLException {
		String query = "DELETE FROM contraintes WHERE id_contrainte=?";
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
	 * Trouve une contrainte dans la base de données en fonction de son identifiant.
	 *
	 * @param id L'identifiant de la contrainte à rechercher.
	 * @return L'objet Contraintes trouvé, ou null si non trouvé.
	 * @throws SQLException en cas d'erreur lors de l'exécution de la requête SQL.
	 */

	@Override
	public Contraintes trouver(int id) throws SQLException {
		Contraintes contrainte = null;
		String query = "SELECT * FROM contraintes WHERE id_contrainte=?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		try (ResultSet resultSet = preparedStatement.executeQuery()) {
			if (resultSet.next()) {
				int idContrainte = resultSet.getInt("id_contrainte");
				String explication = resultSet.getString("explication_contrainte");
				int duree = resultSet.getInt("duree");
				int idType = resultSet.getInt("id_type");
				contrainte = new Contraintes(idContrainte,explication,duree,idType);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return contrainte;
	}

	@Override
	public List<Contraintes> lister() throws SQLException {
		List<Contraintes> contraintes = new ArrayList<>();
		String query = "SELECT * FROM contraintes";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				int idContrainte = resultSet.getInt("id_contrainte");
				String explication = resultSet.getString("explication_contrainte");
				int duree = resultSet.getInt("duree");
				int idType = resultSet.getInt("id_type");
				contraintes.add(new Contraintes(idContrainte,explication,duree,idType));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contraintes;
	}
	 
}


