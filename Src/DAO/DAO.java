package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import projet_hospitalier.Connexion;

public abstract class DAO<T> {

	// L'objet Connection pour se connecter à la base de données
	protected Connection connection;
	protected  Statement stmt ;
    //constructeur 
	public DAO (){
		open();
	}

	public void open() {
		try {
			connection=Connexion.getInstance();
			stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(" === ERREUR OPEN DAO === ");
			e.printStackTrace();
		}

	}

	// Méthodes abstraites que chaque DAO doit implémenter
	public abstract T ajouter(T obj) throws SQLException;

	public abstract T modifier(T obj) throws SQLException;

	public abstract boolean supprimer(int id) throws SQLException;

	public abstract T trouver(int id) throws SQLException;

	public abstract List<T> lister() throws SQLException;

	// Méthode pour fermer les ressources
	protected void fermer(PreparedStatement preparedStatement) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void fermer(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// Méthode pour fermer la connexion
	public void fermerConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}



