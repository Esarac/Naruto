package testModel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

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
	
	//Test
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

}
