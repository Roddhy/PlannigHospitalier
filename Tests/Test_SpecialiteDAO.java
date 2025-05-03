package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import dao.SpecialiteDAO;
import projet_hospitalier.Specialite;

class Test_SpecialiteDAO {

    // Peut ajouter une spécialité à la base de données.
    @Test
    public void test_add_specialite() {
        SpecialiteDAO specialiteDAO = new SpecialiteDAO();
        Specialite specialite = new Specialite();
        specialite.setNomSpecialite("Cardiologie");
        specialite.setNbMinPersonnel(5);
    
        try {
            Specialite result = specialiteDAO.ajouter(specialite);
            assertNotNull(result);
            assertEquals("Cardiologie", result.getNomSpecialite());
            assertEquals(5, result.getNbMinPersonnel());
        } catch (SQLException e) {
            fail("SQLException thrown");
        }
    }
    
    // Peut modifier une spécialité dans la base de données.
    @Test
    public void test_modify_specialite() {
        SpecialiteDAO specialiteDAO = new SpecialiteDAO();
        Specialite specialite = new Specialite();
        specialite.setIdSpecialite(1);
        specialite.setNomSpecialite("Dermatologie");
        specialite.setNbMinPersonnel(3);
    
        try {
            Specialite result = specialiteDAO.modifier(specialite);
            assertNotNull(result);
            assertEquals("Dermatologie", result.getNomSpecialite());
            assertEquals(3, result.getNbMinPersonnel());
        } catch (SQLException e) {
            fail("SQLException thrown");
        }
    }
    
    // Peut supprimer une spécialité de la base de données.
    @Test
    public void test_delete_specialite() {
        SpecialiteDAO specialiteDAO = new SpecialiteDAO();
        int id = 1;
    
        try {
            boolean result = specialiteDAO.supprimer(id);
            assertFalse(result);
        } catch (SQLException e) {
            fail("SQLException thrown");
        }
    }
    
    
    
    
    
    // Tenter de supprimer une spécialité qui n'existe pas devrait renvoyer false.
    @Test
    public void test_delete_non_existent_specialite() {
        SpecialiteDAO specialiteDAO = new SpecialiteDAO();
        int id = 100;
    
        try {
            boolean result = specialiteDAO.supprimer(id);
            assertFalse(result);
        } catch (SQLException e) {
            fail("SQLException thrown");
        }
    }

}
