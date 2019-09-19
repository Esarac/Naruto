package testModel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import model.Clan;
import model.HagoromoSekai;

class testHagoromoSekai {

	//Tested Class
	private HagoromoSekai sekai;
	
	//Scene
	private void setUpSceneEmptySekai(){
		sekai=new HagoromoSekai();
	}
	
	private void setUpSceneNormalSekai(){
		sekai=new HagoromoSekai();
		ArrayList<Clan> clans=new ArrayList<Clan>();
		clans.add(new Clan("Aburame"));
		clans.add(new Clan("Hiuga"));
		clans.add(new Clan("Sarutobi"));
		sekai.setClans(clans);
	}
	
	private void setUpSceneDisorderedSekai(){
		sekai=new HagoromoSekai();
		ArrayList<Clan> clans=new ArrayList<Clan>();
		clans.add(new Clan("Sarutobi"));
		clans.add(new Clan("Hiuga"));
		clans.add(new Clan("Aburame"));
		sekai.setClans(clans);
	}
	
	//Test
	@Test
	void testSearchClan() {
		setUpSceneEmptySekai();
		assertFalse(sekai.searchClan("Uchiha"));
		setUpSceneNormalSekai();
		assertTrue(sekai.searchClan("Aburame"));
		assertTrue(sekai.searchClan("Hiuga"));
		assertTrue(sekai.searchClan("Sarutobi"));
	}

	@Test
	void testAddClan(){
		setUpSceneEmptySekai();
		assertTrue(sekai.addClan("Hiuga"));
		assertTrue(sekai.addClan("Aburame"));
		assertFalse(sekai.addClan("Hiuga"));
		
		//Verify
		ArrayList<Clan> clans=new ArrayList<Clan>();
		clans.add(new Clan("A"));
		clans.add(new Clan("H"));
		assertArrayEquals(clans.toArray(), sekai.getClans().toArray());//Como arreglarlo?
		//...
		
	}
	
	@Test
	void testDeleteClan() {
		setUpSceneNormalSekai();
		assertTrue(sekai.deleteClan("Aburame"));
		assertTrue(sekai.deleteClan("Sarutobi"));
		assertTrue(sekai.deleteClan("Hiuga"));
		setUpSceneEmptySekai();
		assertFalse(sekai.deleteClan("Aburame"));
	}
	
	@Test
	void testGetClan(){
		setUpSceneEmptySekai();
		assertEquals(sekai.getClan("Uchiha"),null);
		setUpSceneNormalSekai();
		assertEquals(sekai.getClan("Aburame").getName(),"Aburame");
		assertEquals(sekai.getClan("Hiuga").getName(),"Hiuga");
		assertEquals(sekai.getClan("Sarutobi").getName(),"Sarutobi");
	}
	
	@Test
	void testUpdateClanName(){
		setUpSceneNormalSekai();
		sekai.updateClanName("Haruno",sekai.getClans().get(1));
		assertEquals(sekai.getClans().get(1).getName(), "Haruno");
		assertFalse(sekai.updateClanName("Haruno",sekai.getClans().get(0)));
	}
	
	@Test
	void sortClanName(){
		setUpSceneDisorderedSekai();
		sekai.sortClanName();
		assertEquals(sekai.getClans().get(0).getName(),"Aburame");
		assertEquals(sekai.getClans().get(1).getName(),"Hiuga");
		assertEquals(sekai.getClans().get(2).getName(),"Sarutobi");
	}
}
