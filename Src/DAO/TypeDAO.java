package dao;

 
import projet_hospitalier.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeDAO extends DAO<Type> {

	// on a pas besoin d'implementer d'insertion suppression ni de modification 
	// car les types sont stables
	@Override
	public Type ajouter(Type type) throws  SQLException {
		return null;
	}

	@Override
	public Type modifier(Type type) throws SQLException {
		return null;
	}

	@Override
	public boolean supprimer(int id) throws SQLException {
		return false;
	}
  /**
   * cette methode renvoie un type selon le id mis en parametre
   */
	@Override
	public Type trouver(int id) throws SQLException {
		String query = "SELECT * FROM type WHERE id_type = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				Type type = new Type();
				type.setIdType(resultSet.getInt("id_type"));
				type.setNom(resultSet.getString("nom"));

				return type;
			}}
		return null;
	}
  /**
   * cette methode fait la liste des types existants
   */
	@Override
	public List<Type> lister() throws SQLException {
		List<Type> typeList = new ArrayList<>();

		String query = "SELECT * FROM type";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Type type = new Type();
				type.setIdType(resultSet.getInt("id_type"));
				type.setNom(resultSet.getString("nom"));
				typeList.add(type);
			}
		}

		return typeList;
	}
}


