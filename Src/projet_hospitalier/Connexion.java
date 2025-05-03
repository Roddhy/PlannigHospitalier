package projet_hospitalier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlDataSource;

public class Connexion {
	/**
	 * Cette classe réalise une connexion unique à la base de données.
	 */
	private static Connection connect;

	/**
	 * Constructeur privé qui établit une connexion à la base de données.
	 *@throws SQLException en cas d'erreur de connexion
	 */
	private Connexion() throws SQLException {
		String serveur = "127.0.0.1";
		String nombd = "planning_hospitalier";
		String login = "root";
		String motpasse = "";
		String url = "jdbc:mysql://" + serveur + ":3306/"
				+ nombd + "?serverTimezone=UTC";
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(url);
		mysqlDS.setUser(login);
		mysqlDS.setPassword(motpasse);
		connect = mysqlDS.getConnection();
	}

	/**
	 * Obtient l'instance unique de la connexion.
	 * @return l'instance de connexion
	 * @throws SQLException en cas d'erreur de connexion
	 */
	public static Connection getInstance() throws SQLException {
		if (connect == null) {
			new Connexion();
		}
		return connect;
	}

	/**
	 * Ferme la connexion à la base de données.
	 */
	public static void close() {
		try {
			if (connect != null) {
				connect.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Point d'entrée principal pour tester la connexion
	 * et effectuer des requêtes.
	 * @param args arguments de la ligne de commande
	 */
	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = Connexion.getInstance();
			if (conn != null) {
				System.out.println("Connexion à la base de"
						+ " données réussie !");
			} else {
				System.err.println("La connexion à la base "
						+ "de données a échoué.");
			}

			// Exécution de requêtes
			st = conn.createStatement();
			String sqlQuery = "SELECT * FROM Personnel";
			rs = st.executeQuery(sqlQuery);

			// Affichage du résultat
			while (rs.next()) {
				// Extraction et affichage des données
				//de chaque ligne
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String datenaissance = rs.getString("date_naissance");
				int tempsTravail = rs.getInt("temps_de_travail_mensuel");
				int idFonction = rs.getInt("id_fonction");
				int idSpecialite = rs.getInt("id_specialite");
				System.out.println("Infirmier : " + nom + " " + prenom
						+ ", date de naissance : " + datenaissance
						+ ", temps de travail mensuel : " + tempsTravail
						+", Fonction : " + idFonction
						+ ", Spécialité : " + idSpecialite);
			}
		} catch (SQLException e) {
			System.err.println("Erreur lors de la connexion ou"
					+ " de la requête SQL");
			e.printStackTrace();
		} finally {
			// Fermeture des ressources
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				Connexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
