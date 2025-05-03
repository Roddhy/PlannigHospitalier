package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import dao.ContraintesDAO;
import projet_hospitalier.Contraintes;

class Test_ContraintesDAO {
	// Ajouter une nouvelle contrainte à la base de données devrait réussir et renvoyer la contrainte ajoutée.
	@Test
	public void test_addConstraint_success() {
	    ContraintesDAO dao = new ContraintesDAO();
	    Contraintes contrainte = new Contraintes(1, "explication", 10, 1);
	    try {
	        Contraintes result = dao.ajouter(contrainte);
	        assertEquals(contrainte, result);
	    } catch (SQLException e) {
	        fail("SQLException thrown");
	    }
	}

	// Modifier une contrainte existante dans la base de données devrait réussir et renvoyer la contrainte modifiée.
	@Test
	public void test_modifyConstraint_success() {
	    ContraintesDAO dao = new ContraintesDAO();
	    Contraintes contrainte = new Contraintes(1, "explication", 10, 1);
	    try {
	        Contraintes result = dao.modifier(contrainte);
	        assertEquals(contrainte, result);
	    } catch (SQLException e) {
	        fail("SQLException thrown");
	    }
	}

	// Trouver une contrainte existante dans la base de données par son ID devrait réussir et renvoyer la contrainte correspondante.
	@Test
	public void test_findConstraintById_success() {
	    ContraintesDAO dao = new ContraintesDAO();
	    int id = 1;
	    Contraintes expected = new Contraintes(1, "explication", 10, 1);
	    try {
	        Contraintes result = dao.trouver(id);
	        assertEquals(expected, result);
	    } catch (SQLException e) {
	        fail("SQLException thrown");
	    }
	}

	// Ajouter une contrainte avec des valeurs nulles devrait échouer et déclencher une SQLException.
	@Test
	public void test_addConstraint_nullValues() {
	    ContraintesDAO dao = new ContraintesDAO();
	    Contraintes contrainte = new Contraintes(1, null, 10, 1);
	    assertThrows(SQLException.class, () -> dao.ajouter(contrainte));
	}

	 

	// Trouver une contrainte inexistante dans la base de données par son ID devrait renvoyer null.
	@Test
	public void test_findNonExistingConstraintById() {
	    ContraintesDAO dao = new ContraintesDAO();
	    int id = 100;
	    try {
	        assertNull(dao.trouver(id));
	    } catch (SQLException e) {
	        // Gérer l'exception SQLException ici
	        e.printStackTrace();
	    }
	}

}
