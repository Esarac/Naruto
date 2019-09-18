package testModel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import model.Clan;
import model.HagoromoSekai;

class testHagoromoSekai {

	private HagoromoSekai sekai;
	
	private void setUpSceneSekai(){
		sekai=new HagoromoSekai();
	}
	
	@Test
	void testSearchClan() {
		setUpSceneSekai();
		assertFalse(sekai.searchClan("Uchiha"));
		ArrayList<Clan> clans=new ArrayList<Clan>();
		clans.add(new Clan("Aburame"));
		clans.add(new Clan("Hiuga"));
		clans.add(new Clan("Sarutobi"));
		sekai.setClans(clans);
		assertTrue(sekai.searchClan("Aburame"));
		assertTrue(sekai.searchClan("Hiuga"));
		assertTrue(sekai.searchClan("Sarutobi"));
		assertFalse(sekai.searchClan("Uchiha"));
	}

	@Test
	void testAddClan(){
		setUpSceneSekai();
		assertTrue(sekai.addClan("H"));
		assertTrue(sekai.addClan("A"));
		assertFalse(sekai.addClan("H"));
		
		ArrayList<Clan> clans=new ArrayList<Clan>();
		clans.add(new Clan("A"));
		clans.add(new Clan("H"));
		
		assertArrayEquals(clans.toArray(), sekai.getClans().toArray());//Porque no funciona?
		
	}
	
	@Test
	void testDeleteClan() {
		setUpSceneSekai();
		ArrayList<Clan> clans=new ArrayList<Clan>();
		clans.add(new Clan("Aburame"));
		clans.add(new Clan("Hiuga"));
		sekai.setClans(clans);
		
		assertTrue(sekai.deleteClan("Aburame"));
		assertTrue(sekai.deleteClan("Hiuga"));
		assertFalse(sekai.deleteClan("Aburame"));
	}
}
