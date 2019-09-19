package ui;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.HagoromoSekai;
import model.Clan;
import model.Shinobi;
import model.Jutsu;

public class Interface {

	//Attribute
	private HagoromoSekai sekai;
	private Clan actualClan;
	private Shinobi actualShinobi;
	private Jutsu actualJutsu;
	private Scanner scanner;
	
	//Menu
	public void menu(){
		boolean run=true;
		while(run){
			if(actualClan==null){
				run=hagoromoSekaiMenu();
			}
			else if(actualShinobi==null){
				clanMenu();
			}
			else if(actualJutsu==null){
				shinobiMenu();
			}
			else{
				jutsuMenu();
			}
		}
	}
	public boolean hagoromoSekaiMenu(){
		boolean run=true;
		System.out.println("Hagoromo Sekai:\n 1.\n 2.Salir");
		int option=askInt(1,2);
		switch(option){
			case 1:
				
			break;
			case 2:
				run=false;
			break;
		}
		return run;
	}
	
	public void clanMenu(){
		System.out.println("Clan:\n 1.\n 2.Retroceder");
		int option=askInt(1,2);
		switch(option){
			case 1:
				
			break;
			case 2:
				this.actualClan=null;
			break;
		}
	}
	
	public void shinobiMenu(){
		System.out.println("Shinobi:\n 1.\n 2.Retroceder");
		int option=askInt(1,2);
		switch(option){
			case 1:
				
			break;
			case 2:
				this.actualShinobi=null;
			break;
		}
	}
	
	public void jutsuMenu(){
		System.out.println("Jutsu:\n 1.\n 2.Retroceder");
		int option=askInt(1,2);
		switch(option){
			case 1:
				
			break;
			case 2:
				this.actualJutsu=null;
			break;
		}
	}
	
	//Ask
	public int askInt(int start, int end){
		
		int ask=0;
		boolean run=true;
		
		while(run){
			try{
				ask=scanner.nextInt();
				scanner.nextLine();
				if((ask>=start)&&(ask<=end)){run=false;}
				else{System.out.println("Opcion Invalida!");}
			}
			catch(InputMismatchException e){
				System.out.println("Opcion Invalida!");
				scanner=new Scanner(System.in);
			}
		}
		
		return ask;

	}
	
	//Constructor
	public Interface(){
		init();
	}
	
	//Initializer
	public void init(){
		this.scanner=new Scanner(System.in);
		this.sekai=new HagoromoSekai();
		sekai.load();
		this.actualClan=null;
		this.actualShinobi=null;
		this.actualJutsu=null;
	}
	
}
