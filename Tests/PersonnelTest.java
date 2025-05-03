package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import projet_hospitalier.Personnel;

class PersonnelTest {

	// Un objet Personnel peut être créé avec tous les paramètres
	@Test
	public void test_createPersonnelWithAllParameters() {
		// Création d'une LocalDate représentant la date de naissance
		//Local
		LocalDate dateNaissance =LocalDate.of(2000, 01, 01);

		// Création d'une instance de la classe Personnel en utilisant le constructeur approprié
		Personnel personnel = new Personnel("John", "Doe", Date.valueOf(dateNaissance), 160, 1, 1);
		LocalDate dateNaissanceFromPersonnel = personnel.getDateNaissance().toLocalDate();

		assertEquals("John", personnel.getNom());
		assertEquals("Doe", personnel.getPrenom());
		assertEquals(dateNaissance,dateNaissanceFromPersonnel);
		assertEquals(0, personnel.getTempsTravailMensuel());
		assertEquals(1, personnel.getIdFonction());
		assertEquals(1, personnel.getIdSpecialite());
	}

}
