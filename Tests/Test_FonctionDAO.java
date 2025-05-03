package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import dao.FonctionDAO;
import projet_hospitalier.Fonction;

class Test_FonctionDAO {

    // FonctionDAO peut ajouter avec succès une nouvelle fonction à la base de données.
    @Test
    public void test_addFunction_success() {
        FonctionDAO fonctionDAO = new FonctionDAO();
        Fonction fonction = new Fonction();
        fonction.setNom_fonction("Test Function");

        try {
            Fonction addedFunction = fonctionDAO.ajouter(fonction);
            assertNotNull(addedFunction);
            assertEquals(fonction.getNom_fonction(), addedFunction.getNom_fonction());
        } catch (SQLException e) {
            fail("SQLException thrown");
        }
    }
    
    // FonctionDAO peut modifier avec succès une fonction existante dans la base de données.
    @Test
    public void test_modifyFunction_success() {
        FonctionDAO fonctionDAO = new FonctionDAO();
        Fonction fonction = new Fonction();
        fonction.setId_fonction(1);
        fonction.setNom_fonction("Modified Function");

        try {
            Fonction modifiedFunction = fonctionDAO.modifier(fonction);
            assertNotNull(modifiedFunction);
            assertEquals(fonction.getNom_fonction(), modifiedFunction.getNom_fonction());
        } catch (SQLException e) {
            fail("SQLException thrown");
        }
    }
    
    
    // FonctionDAO peut supprimer avec succès une fonction existante de la base de données.
    @Test
    public void test_deleteFunction_success() {
        FonctionDAO fonctionDAO = new FonctionDAO();
        int functionId = 1;

        try {
            boolean deleted = fonctionDAO.supprimer(functionId);
            assertTrue(deleted);
        } catch (SQLException e) {
            fail("SQLException thrown");
        }
    }
    
     

}
