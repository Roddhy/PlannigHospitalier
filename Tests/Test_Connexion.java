package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import projet_hospitalier.Connexion;

class Test_Connexion {

    // Obtention réussie de l'instance de Connexion
    @Test
    public void test_instance_obtained() {
        try {
            Connection conn = Connexion.getInstance();
            assertNotNull(conn);
        } catch (SQLException e) {
            fail("Exception levée");
        }
    }
    
    // Requête SQL exécutée avec succès
    @Test
    public void test_query_executed() {
        try {
            Connection conn = Connexion.getInstance();
            Statement st = conn.createStatement();
            String sqlQuery = "SELECT * FROM Personnel";
            ResultSet rs = st.executeQuery(sqlQuery);
            assertNotNull(rs);
        } catch (SQLException e) {
            fail("Exception levée");
        }
    }
    
    // Résultat de la requête imprimé avec succès
    @Test
    public void test_result_printed() {
        try {
            Connection conn = Connexion.getInstance();
            Statement st = conn.createStatement();
            String sqlQuery = "SELECT * FROM Personnel";
            ResultSet rs = st.executeQuery(sqlQuery);
            while (rs.next()) {
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
            fail("Exception levée");
        }
    }
    
    
    // Échec de la connexion à la base de données
    @Test
    public void test_connection_fails() {
        try {
            Connection conn = Connexion.getInstance();
            conn.close();
            fail("Aucune exception levée");
        } catch (SQLException e) {
            assertEquals("Aucune opération autorisée après la fermeture de la connexion.", e.getMessage());
        }
    }
    

}
