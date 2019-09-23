package testModel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import model.Clan;
import model.Shinobi;

class testClan {

	//Tested Class
	private Clan clan;
	
	//Scene
	private void setUpSceneEmptyClan(){
		clan=new Clan("Uchiha");
	}
	
	private void setUpSceneNormalClan(){
		clan=new Clan("Uchiha");
		Shinobi s1=new Shinobi("Sasuke","Alegre",new GregorianCalendar(2001, 12-1, 24), 2000);
		Shinobi s2=new Shinobi("Itachi","Aburrido",new GregorianCalendar(2000, 4-1, 3), 1500);
		Shinobi s3=new Shinobi("Obito","Frio",new GregorianCalendar(2002, 9-1, 9), 1000);
		s2.setNextShinobi(s3);//s2-s3
		s3.setPrevShinobi(s2);
		s1.setNextShinobi(s2);//s1-s2
		s2.setPrevShinobi(s1);
		clan.setFirstShinobi(s1);
	}
	
	//Test
	@Test
	void testAddShinobi(){
		setUpSceneEmptyClan();
		assertTrue(clan.addShinobi("Shisui","Introvertido",Calendar.getInstance(), 2000));
		assertTrue(clan.addShinobi("Madara","Enojado",Calendar.getInstance(), 3000));
		assertFalse(clan.addShinobi("Shisui","Introvertido",Calendar.getInstance(), 2000));
	}
	
	@Test
	void testDeleteShinobi() {
		setUpSceneEmptyClan();
		assertFalse(clan.deleteShinobi("Danzo"));
		setUpSceneNormalClan();
		assertTrue(clan.deleteShinobi("Sasuke"));
		assertTrue(clan.deleteShinobi("Obito"));
		assertTrue(clan.deleteShinobi("Itachi"));
	}
	
	@Test
	void testUpdateShinobiName(){
		setUpSceneNormalClan();
		clan.updateShinobiName("Madara",clan.getShinobi(1));
		assertEquals(clan.getShinobi(1).getName(), "Madara");
		assertFalse(clan.updateShinobiName("Madara",clan.getShinobi(0)));
	}
	
	@Test
	void testSearchShinobi(){
		setUpSceneEmptyClan();
		assertFalse(clan.searchShinobi("Danzo"));
		setUpSceneNormalClan();
		assertTrue(clan.searchShinobi("Sasuke"));
		assertTrue(clan.searchShinobi("Itachi"));
		assertTrue(clan.searchShinobi("Obito"));
	}
	
	@Test
	void testGetShinobi(){
		setUpSceneEmptyClan();
		assertEquals(clan.getShinobi("Madara"),null);
		setUpSceneNormalClan();
		assertEquals(clan.getShinobi("Sasuke").getName(),"Sasuke");
		assertEquals(clan.getShinobi("Itachi").getName(),"Itachi");
		assertEquals(clan.getShinobi("Obito").getName(),"Obito");
	}
	
	@Test
	void testSortShinobiName(){
		setUpSceneNormalClan();
		clan.sortShinobiName();
		assertEquals(clan.getShinobi(0).getName(),"Itachi");
		assertEquals(clan.getShinobi(1).getName(),"Obito");
		assertEquals(clan.getShinobi(2).getName(),"Sasuke");
	}
	
	@Test
	void testSortShinobiPower(){
		setUpSceneNormalClan();
		clan.sortShinobiPower();
		assertEquals(clan.getShinobi(0).getPower(),1000);
		assertEquals(clan.getShinobi(1).getPower(),1500);
		assertEquals(clan.getShinobi(2).getPower(),2000);
	}
	
	@Test
	void testSortShinobiCreationDate(){
		setUpSceneNormalClan();
		clan.sortShinobiCreationDate();
		assertEquals(clan.getShinobi(0).getCreationDate(),new GregorianCalendar(2000, 4-1, 3));
		assertEquals(clan.getShinobi(1).getCreationDate(),new GregorianCalendar(2001, 12-1, 24));
		assertEquals(clan.getShinobi(2).getCreationDate(),new GregorianCalendar(2002, 9-1, 9));
	}
	
	@Test
	void testShinobiSize(){
		setUpSceneEmptyClan();
		assertEquals(clan.shinobiSize(), 0);
		setUpSceneNormalClan();
		assertEquals(clan.shinobiSize(), 3);
	}
	
	@Test
	void testSetShinobi(){
		setUpSceneEmptyClan();
		assertFalse(clan.setShinobi(1,new Shinobi("Madara","Enojado",new GregorianCalendar(2001, 12-1, 24), 2000)));
		setUpSceneNormalClan();
		assertTrue(clan.setShinobi(1,new Shinobi("Madara","Enojado",new GregorianCalendar(2001, 12-1, 24), 2000)));
		assertTrue(clan.setShinobi(1,clan.getShinobi(2)));
	}
	
	@Test
	void testGetShinobiList(){
		setUpSceneEmptyClan();
		assertEquals(clan.getShinobi(1),null);
		setUpSceneNormalClan();
		assertEquals(clan.getShinobi(0).getName(), "Sasuke");
		assertEquals(clan.getShinobi(1).getName(), "Itachi");
		assertEquals(clan.getShinobi(2).getName(), "Obito");
	}
	
}
