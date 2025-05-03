package dao;

import projet_hospitalier.EmploiDuTemps;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmploiDuTempsDAO extends DAO<EmploiDuTemps>{

	@Override
	public EmploiDuTemps ajouter(EmploiDuTemps emploi) throws SQLException {
		String query2 = "INSERT INTO emploi (date_debut,date_fin, heure_debut, heure_fin) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = connection.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS);
			pstmt.setDate(1,emploi.getDateDebut());
			pstmt.setDate(2,emploi.getDate_fin());
			pstmt.setTime(3,emploi.getHeureDebut());
			pstmt.setTime(4, emploi.getHeureFin());
			pstmt.executeUpdate();
			// cle genre automatiquement
			try (ResultSet cles = pstmt.getGeneratedKeys()) {
				if (cles.next()) {
					int id = cles.getInt(1);
					emploi.setIdEmploi(id);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emploi;
	}

	@Override
	public EmploiDuTemps modifier(EmploiDuTemps emploi) throws SQLException {
		String requete = "UPDATE emploi  SET idEmploi='" +  emploi.getIdEmploi()+"', ";
		requete +="date_debut= '"+ emploi.getDateDebut()+ "', ";
		requete += "date_fin= '"+ emploi.getDate_fin();
		requete += "heure_debut= '"+ emploi.getHeureDebut();
		requete += "heure_fin= '"+ emploi.getHeureFin();
		requete +="WHERE idEmploi= " + emploi.getIdEmploi();
		try {
			stmt.executeUpdate(requete);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emploi;
	}

	@Override
	public boolean supprimer(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
   /**
    * methode pour retourner un emploi selon son id 
    */
	@Override
	public EmploiDuTemps trouver(int id) throws SQLException {
		return null;
	}
  //on peut pas l'utiliser
	@Override
	public List<EmploiDuTemps> lister() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}

