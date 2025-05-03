package Tests;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import dao.PersonnelDAO;
import projet_hospitalier.Personnel;
class PersonnelDAO_Test {

	@Test
	public void test_ajout() {
		PersonnelDAO pdao=new PersonnelDAO();
		Date d=new Date(1000);
		Personnel p=new Personnel(1,"Exemple","Test",d,120,1,3);
		try {
			Personnel test=pdao.ajouter(p);
			assertEquals(p,test);
		} catch (SQLException e) {
			fail("SQLException thrown");
		}
	}

	@Test
	public void test_modif() {
		PersonnelDAO pdao=new PersonnelDAO();
		Date d=new Date(1000);
		Personnel p=new Personnel(1,"Exemple","Test",d,120,1,3);
		try {
			Personnel test=pdao.modifier(p);
			assertEquals(p,test);
		} catch (SQLException e) {
			fail("SQLException thrown");
		}
	}

	@Test
	public void test_suppr() {
		PersonnelDAO pdao=new PersonnelDAO();
		Date d=new Date(1000);
		Personnel p=new Personnel(1,"Exemple","Test",d,120,1,3);
		try {
			assertEquals(pdao.trouver(1),p);
			pdao.supprimer(1);
			assertEquals(pdao.trouver(1),p);
		} catch (SQLException e) {
			fail("SQLException thrown");
		}
	}
}
