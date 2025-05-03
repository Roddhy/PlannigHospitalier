package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import projet_hospitalier.Fonction;

class FonctionsTest {

	// Créer une instance de Fonction avec un identifiant et un nom définit.
    @Test
    public void testcreationFonction() {
        Fonction fonction = new Fonction(1, "test");
        assertEquals(1, fonction.getId_fonction());
        assertEquals("test", fonction.getNom_fonction());
    }
    
    // Créer une instance de Fonction avec seulement un nom définit
    @Test
    public void TestcreationavecNom() {
        Fonction fonction = new Fonction("test");
        assertEquals("test", fonction.getNom_fonction());
    }
    
    // Appeler getId_fonction() retourne le bon identifiant.
    @Test
    public void test_getIdFonction() {
        Fonction fonction = new Fonction(1, "test");
        assertEquals(1, fonction.getId_fonction());
    }
    
    
}
