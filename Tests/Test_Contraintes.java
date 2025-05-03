package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import projet_hospitalier.Contraintes;

class Test_Contraintes {

    // Création d'une nouvelle instance de 'Contraintes' avec des paramètres valides doit définir correctement les attributs.
    @Test
    public void testcreationdecreneau() {
        Contraintes contraintes = new Contraintes(1, "Explication", 10, 2);
        assertEquals(1, contraintes.getIdContrainte());
        assertEquals("Explication", contraintes.getExplicationContrainte());
        assertEquals(10, contraintes.getDuree());
        assertEquals(2, contraintes.getIdType());
    }
    
    // Obtenir l'ID d'une instance de 'Contraintes' devrait retourner la valeur correcte.
    @Test
    public void test_getIdContrainte() {
        Contraintes contraintes = new Contraintes(1, "Explication", 10, 2);
        assertEquals(1, contraintes.getIdContrainte());
    }
    
    // Obtenir l'explication d'une instance de 'Contraintes' devrait retourner la valeur correcte.
    @Test
    public void test_getExplicationContrainte() {
        Contraintes contraintes = new Contraintes(1, "Explication", 10, 2);
        assertEquals("Explication", contraintes.getExplicationContrainte());
    }
    
    
    
}
