package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import dao.CreneauDAO;
import projet_hospitalier.Creneau;

class Test_CreneauDAO {

	// CreneauDAO peut modifier un créneau existant dans la bdd
	@Test
	public void test_modifyCreneau_success() {
	    
	    CreneauDAO creneauDAO = new CreneauDAO();
	    Creneau creneau = new Creneau();

	    
	    Creneau result = null;
	    try {
	        result = creneauDAO.modifier(creneau);
	    } catch (SQLException e) {
	        // Gérer l'exception SQLException ici
	        e.printStackTrace();
	    }

	    
	    assertNotNull(result);
	}


	// CreneauDAO peut supprimer  un créneau existant de la bdd
	@Test
	public void test_deleteCreneau_success() {
	    
	    CreneauDAO creneauDAO = new CreneauDAO();
	    int id = 1;

	    
	    boolean result = creneauDAO.supprimer(id);

	   
	    assertTrue(result);
	}

 

	// CreneauDAO ne peut pas récupérer un créneau inexistant de la bdd
	@Test
	public void test_findCreneau_nonExisting() {
	    
	    CreneauDAO creneauDAO = new CreneauDAO();
	    int id = 1;

	    
	    Creneau result = null;
	    try {
	        result = creneauDAO.trouver(id);
	    } catch (SQLException e) {
	        // Gérer l'exception SQLException ici
	        e.printStackTrace();
	    }

	    
	    assertNull(result);
	}


}
