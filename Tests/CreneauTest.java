package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.Test;

import projet_hospitalier.Creneau;

class CreneauTest {

	@Test
	void testEgalitecreneau() {
		 long num_creneau = 1;
	        Date datedebut = Date.valueOf("2021-01-01");
	        Date datefin = Date.valueOf("2021-01-02");
	        Time heureDebut = Time.valueOf("08:00:00");
	        Time heureFin = Time.valueOf("10:00:00");
	        String nomcreneau = "Test Creneau";
	        int idemploi = 1;

	        
	        Creneau creneau = new Creneau(num_creneau, datedebut, datefin, heureDebut, heureFin, nomcreneau, idemploi);

	        
	        assertNotNull(creneau);
	        assertEquals(num_creneau, creneau.getNum_creneau());
	        assertEquals(datedebut, creneau.getDatedebut());
	        assertEquals(datefin, creneau.getDatefin());
	        assertEquals(heureDebut, creneau.getHeureDebut());
	        assertEquals(heureFin, creneau.getHeureFin());
	        assertEquals(nomcreneau, creneau.getNomcreneau());
	        assertEquals(idemploi, creneau.getIdemploi());
	    }
	   
}
