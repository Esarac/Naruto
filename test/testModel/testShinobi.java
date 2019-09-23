package testModel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import model.Jutsu;
import model.Shinobi;

class testShinobi {

	//Tested Class
	private Shinobi ninja;
	
	//Scene
	private void setUpSceneEmptyShinobi(){
		ninja=new Shinobi("Sasori","Loco",Calendar.getInstance(),2000);
	}
	
	private void setUpSceneNormalShinobi(){
		ninja=new Shinobi("Sasori","Loco",Calendar.getInstance(),2000);
		Jutsu j1=new Jutsu("Control de Cuerpo Humano", 0.8);
		Jutsu j2=new Jutsu("100 Marionetas",1.2);
		Jutsu j3=new Jutsu("Arena de Hierro", 1.5);
		j2.setNextJutsu(j3);//s2-s3
		j1.setNextJutsu(j2);//s1-s2
		ninja.setFirstJutsu(j1);
	}
	
	private void setUpSceneDisorderedShinobi(){
		ninja=new Shinobi("Sasori","Loco",Calendar.getInstance(),2000);
		Jutsu j1=new Jutsu("Arena de Hierro", 1.5);
		Jutsu j2=new Jutsu("100 Marionetas",1.2);
		Jutsu j3=new Jutsu("Control de Cuerpo Humano", 0.8);
		j2.setNextJutsu(j3);//s2-s3
		j1.setNextJutsu(j2);//s1-s2
		ninja.setFirstJutsu(j1);
	}
	
	//Test
	@Test
	void testAddJutsu(){
		setUpSceneEmptyShinobi();
		assertTrue(ninja.addJutsu("Tirador de Agua", 0.4));
		assertTrue(ninja.addJutsu("Ocho Ondas de Agujas", 0.35));
		assertFalse(ninja.addJutsu("Tirador de Agua", 0.4));
		
		//Verify
		Jutsu j1=new Jutsu("Ocho Ondas de Agujas", 0.35);
		Jutsu j2=new Jutsu("Tirador de Agua", 0.4);
		j1.setNextJutsu(j2);//s1-s2
		assertEquals(j1,ninja.getFirstJutsu());//Como arreglarlo?
		//...
		
	}
	
	@Test
	void testDeleteJutsu() {
		setUpSceneNormalShinobi();
		assertTrue(ninja.deleteJutsu("Control de Cuerpo Humano"));
		assertTrue(ninja.deleteJutsu("Arena de Hierro"));
		assertTrue(ninja.deleteJutsu("100 Marionetas"));
		setUpSceneEmptyShinobi();
		assertFalse(ninja.deleteJutsu("Control de Cuerpo Humano"));
	}
	
	@Test
	void testUpdateJutsuName(){
		setUpSceneNormalShinobi();
		ninja.updateJutsuName("Tirador de Agua",ninja.getJutsu(1));
		assertEquals(ninja.getJutsu(1).getName(), "Tirador de Agua");
		assertFalse(ninja.updateJutsuName("Tirador de Agua",ninja.getJutsu(0)));
	}
	
	@Test
	void testSearchJutsu() {
		setUpSceneEmptyShinobi();
		assertFalse(ninja.searchJutsu("Kamui"));
		setUpSceneNormalShinobi();
		assertTrue(ninja.searchJutsu("Control de Cuerpo Humano"));
		assertTrue(ninja.searchJutsu("100 Marionetas"));
		assertTrue(ninja.searchJutsu("Arena de Hierro"));
	}
	
	@Test
	void testGetJutsu(){
		setUpSceneEmptyShinobi();
		assertEquals(ninja.getJutsu("Tirador de Agua"),null);
		setUpSceneNormalShinobi();
		assertEquals(ninja.getJutsu("Control de Cuerpo Humano").getName(),"Control de Cuerpo Humano");
		assertEquals(ninja.getJutsu("100 Marionetas").getName(),"100 Marionetas");
		assertEquals(ninja.getJutsu("Arena de Hierro").getName(),"Arena de Hierro");
	}
	
	@Test
	void testSortJutsuFactor(){
		setUpSceneDisorderedShinobi();
		ninja.sortJutsuFactor();
		assertEquals(ninja.getJutsu(0).getFactor(),0.8);
		assertEquals(ninja.getJutsu(1).getFactor(),1.2);
		assertEquals(ninja.getJutsu(2).getFactor(),1.5);
	}
	
	@Test
	void testJutsuSize(){
		setUpSceneEmptyShinobi();
		assertEquals(ninja.jutsuSize(), 0);
		setUpSceneNormalShinobi();
		assertEquals(ninja.jutsuSize(), 3);
	}
	
	@Test
	void testSetJutsu(){
		setUpSceneEmptyShinobi();
		assertFalse(ninja.setJutsu(1,new Jutsu("Tirador de Agua", 0.4)));
		setUpSceneNormalShinobi();
		assertTrue(ninja.setJutsu(1,new Jutsu("Tirador de Agua", 0.4)));
		assertTrue(ninja.setJutsu(1,ninja.getJutsu(2)));
	}
	
	@Test
	void testGetJutsuList(){
		setUpSceneEmptyShinobi();
		assertEquals(ninja.getJutsu(1),null);
		setUpSceneNormalShinobi();
		assertEquals(ninja.getJutsu(0).getName(), "Control de Cuerpo Humano");
		assertEquals(ninja.getJutsu(1).getName(), "100 Marionetas");
		assertEquals(ninja.getJutsu(2).getName(), "Arena de Hierro");
	}

}
