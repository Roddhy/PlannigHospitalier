package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import projet_hospitalier.Specialite;

class SpecialiteTest {

	// Créer un objet Specialite avec tous les paramètres correctement définis devrait renvoyer un objet avec les mêmes paramètres.
	@Test
	public void test_createSpecialiteWithAllParameters() {
	    Specialite specialite = new Specialite(1, "Specialite 1", 5);
	    assertEquals(1, specialite.getIdSpecialite());
	    assertEquals("Specialite 1", specialite.getNomSpecialite());
	    assertEquals(5, specialite.getNbMinPersonnel());
	}

	// Créer un objet Specialite avec seulement les paramètres nécessaires devrait renvoyer un objet avec ces paramètres et les autres définis sur des valeurs par défaut.
	@Test
	public void test_createSpecialiteWithNecessaryParameters() {
	    Specialite specialite = new Specialite("Specialite 2", 10);
	    assertEquals(0, specialite.getIdSpecialite());
	    assertEquals("Specialite 2", specialite.getNomSpecialite());
	    assertEquals(10, specialite.getNbMinPersonnel());
	}

	// Définir et obtenir les paramètres idSpecialite, nomSpecialite et nbMinPersonnel devrait renvoyer la même valeur que celle définie.
	@Test
	public void test_setAndGetParameters() {
	    Specialite specialite = new Specialite();
	    specialite.setIdSpecialite(3);
	    specialite.setNomSpecialite("Specialite 3");
	    specialite.setNbMinPersonnel(15);
	    assertEquals(3, specialite.getIdSpecialite());
	    assertEquals("Specialite 3", specialite.getNomSpecialite());
	    assertEquals(15, specialite.getNbMinPersonnel());
	}

	 
}
