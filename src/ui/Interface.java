package ui;

import java.util.Calendar;
import java.util.Scanner;

import model.Clan;
import model.HagoromoSekai;
import model.Shinobi;

public class Interface {

	//Attribute
	private HagoromoSekai sekai;
	private Clan actualClan;
	private Shinobi actualShinobi;
	private Scanner scanner;
	
	//Menu
	public void menu(){
//		actualClan=new Clan("");
//		actualClan.addShinobi("C","Alegre",Calendar.getInstance(), 1000);
//		actualClan.addShinobi("B","Aburrido",Calendar.getInstance(), 2000);
//		actualClan.addShinobi("A","Frio",Calendar.getInstance(), 1500);
//		actualClan.deleteShinobi("B");
//		System.out.println(actualClan.printShinobis());
		
		actualShinobi=new Shinobi("","",Calendar.getInstance(),0);
		actualShinobi.addJutsu("B",0.2);
		actualShinobi.addJutsu("D",0.4);
		actualShinobi.addJutsu("C",0.3);
		actualShinobi.addJutsu("A",0.1);
		System.out.println(actualShinobi.printJutsu());
		
	}
	
	//Constructor
	public Interface(){
		init();
	}
	
	//Initializer
	public void init(){
		sekai=new HagoromoSekai();
		sekai.load();
	}
	
}
