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
	
	//Menu-------------------------------------------------------------
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
		System.out.println("Hagoromo Sekai:\n 1.Crear Clan\n 2.Borrar Clan\n 3.Imprimir Clanes\n 4.Entrar Clan\n 5.Salir");
		int option=askInt(1,5);
		switch(option){
			case 1:
				System.out.println("Nombre del clan:");
				String a1=scanner.nextLine();
				
				boolean aT=sekai.addClan(a1);
				if(aT)System.out.println("Se agrego el clan");else System.out.println("No se agrego el clan");//Message
			break;
			case 2:
				System.out.println("Nombre del clan:");
				String b1=scanner.nextLine();
				
				boolean bT=sekai.deleteClan(b1);
				if(bT)System.out.println("Se borro el clan");else System.out.println("No se borro el clan");//Message
			break;
			case 3:
				System.out.println("Clanes:");
				System.out.println(sekai.printClans());//Message
			break;
			case 4:
				System.out.println("Nombre del clan:");
				String c1=scanner.nextLine();
				
				this.actualClan=sekai.getClan(c1);
				if(actualClan!=null)System.out.println("Se entro al clan");else System.out.println("No se entro al clan");//Message
			break;
			case 5:
				run=false;
				System.out.println("Hasta la proxima!");//Message
				sekai.save();
			break;
		}
		return run;
	}
	
	public void clanMenu(){
		System.out.println("Clan:\n 1.Actualizar Clan\n 2.Crear Personaje\n 3.Borrar Personaje\n 4.Imprimir Personajes\n 5.Entrar Personaje\n 6.Retroceder");
		int option=askInt(1,6);
		switch(option){
			case 1:
				System.out.println("Nuevo nombre del clan:");
				String a1=scanner.nextLine();
				
				boolean aT=sekai.updateClanName(a1, actualClan);
				if(aT)System.out.println("Se actualizo al clan");else System.out.println("No se actualizo al clan");//Message
			break;
			case 2:
				System.out.println("Nombre del personaje:");
				String b1=scanner.nextLine();
				
				System.out.println("Personalidad del personaje:");
				String b2=scanner.nextLine();
				
				System.out.println("Fecha de creacion del personaje:");
				System.out.println(" Dia");
				int b31=askInt(1,31);
				System.out.println(" Mes");
				int b32=askInt(1,12);
				System.out.println(" Anio");
				int b33=askInt(1999,2020);
				Calendar b3=new GregorianCalendar(b33,(b32-1),b31);
				
				System.out.println("Poder del personaje:");
				int b4=askInt(0,9999999);
				
				boolean bT=actualClan.addShinobi(b1, b2, b3, b4);
				if(bT)System.out.println("Se agrego el personaje");else System.out.println("No se agrego el personaje");//Message
			break;
			case 3:
				System.out.println("Nombre del personaje:");
				String c1=scanner.nextLine();
				
				boolean cT=actualClan.deleteShinobi(c1);
				if(cT)System.out.println("Se borro el personaje");else System.out.println("No se borro el personaje");//Message
			break;
			case 4:
				System.out.println("Orden:\n 1.Nombre\n 2.Fecha de creacion\n 3.Poder");
				int d1=askInt(1,3);
				
				switch(d1){
					case 1:
						System.out.println("Tiempo: "+actualClan.sortShinobiName()+"ns");
						
					break;
					case 2:
						System.out.println("Tiempo: "+actualClan.sortShinobiCreationDate()+"ns");
						
					break;
					case 3:
						System.out.println("Tiempo: "+actualClan.sortShinobiPower()+"ns");
						
					break;
				}
				
				System.out.println("Personajes:");
				System.out.println(actualClan.printShinobis());//Message
			break;
			case 5:
				System.out.println("Nombre del personaje:");
				String e1=scanner.nextLine();
				
				this.actualShinobi=actualClan.getShinobi(e1);
				if(actualShinobi!=null)System.out.println("Se entro al personaje");else System.out.println("No se entro al personaje");//Message
			break;
			case 6:
				this.actualClan=null;
				sekai.save();
			break;
		}
	}
	
	public void shinobiMenu(){
		System.out.println("Personaje:\n 1.Actualizar Personaje\n 2.Crear Tecnica\n 3.Borrar Tecnica\n 4.Imprimir Tecnicas\n 5.Entrar Tecnica\n 6.Retroceder");
		int option=askInt(1,6);
		switch(option){
			case 1:
				System.out.println("Cambiar:\n 1.Nombre\n 2.Personalidad\n 3.Fecha de creacion\n 4.Poder");
				int a1=askInt(1,4);
				
				switch(a1){
					case 1:
						System.out.println("Nuevo nombre del personaje:");
						String a21=scanner.nextLine();
						
						boolean aT=actualClan.updateShinobiName(a21, actualShinobi);
						if(aT)System.out.println("Se actualizo al personaje");else System.out.println("No se actualizo al personaje");//Message
					break;
					case 2:
						System.out.println("Nueva personalidad del personaje:");
						String a22=scanner.nextLine();
						
						actualShinobi.setPersonality(a22);
					break;
					case 3:
						System.out.println("Nueva fecha de creacion del personaje:");
						System.out.println(" Dia");
						int a231=askInt(1,31);
						System.out.println(" Mes");
						int a232=askInt(1,12);
						System.out.println(" Anio");
						int a233=askInt(1999,2020);
						Calendar a23=new GregorianCalendar(a233,(a232-1),a231);
						
						actualShinobi.setCreationDate(a23);
					break;
					case 4:
						System.out.println("Nuevo poder del personaje:");
						int a24=askInt(0,9999999);
						
						actualShinobi.setPower(a24);
					break;
				}
				
				if(a1!=1)System.out.println("Se actualizo al personaje");//Message
			break;
			case 2:
				System.out.println("Nombre de la tecnica:");
				String b1=scanner.nextLine();
				
				System.out.println("Factor de la tecnica:");
				double b2=askDouble();
				
				boolean bT=actualShinobi.addJutsu(b1, b2);
				if(bT)System.out.println("Se agrego la tecnica");else System.out.println("No se agrego la tecnica");//Message
			break;
			case 3:
				System.out.println("Nombre de la tecnica:");
				String c1=scanner.nextLine();
				
				boolean cT=actualShinobi.deleteJutsu(c1);
				if(cT)System.out.println("Se borro la tecnica");else System.out.println("No se borro la tecnica");//Message
			break;
			case 4:
				System.out.println("Tecnicas:");
				System.out.println(actualShinobi.printJutsus());//Message
			break;
			case 5:
				System.out.println("Nombre de la tecnica:");
				String d1=scanner.nextLine();
				
				this.actualJutsu=actualShinobi.getJutsu(d1);
				if(actualJutsu!=null)System.out.println("Se entro a la tecnica");else System.out.println("No se entro a la tecnica");//Message
			break;
			case 6:
				this.actualShinobi=null;
				sekai.save();
			break;
		}
	}
	
	public void jutsuMenu(){
		System.out.println("Tecnica:\n 1.Actualizar Tecnica\n 2.Retroceder");
		int option=askInt(1,2);
		switch(option){
			case 1:
				System.out.println("Cambiar:\n 1.Nombre\n 2.Factor");
				int a1=askInt(1,2);
				
				switch(a1){
					case 1:
						System.out.println("Nuevo nombre de la tecnica:");
						String a21=scanner.nextLine();
						
						boolean aT=actualShinobi.updateJutsuName(a21, actualJutsu);
						if(aT)System.out.println("Se actualizo a la tecnica");else System.out.println("No se actualizo a la tecnica");//Message
					break;
					case 2:
						System.out.println("Nuevo factor de la tecnica:");
						double a22=askDouble();
						
						actualShinobi.updateJutsuFactor(a22, actualJutsu);
						System.out.println("Se actualizo a la tecnica");//Message
					break;
				}
			break;
			case 2:
				this.actualJutsu=null;
				sekai.save();
			break;
		}
	}
	//-----------------------------------------------------------------
	
	//Ask
	public int askInt(int start, int end){
		
		int ask=0;
		boolean run=true;
		
		while(run){
			try{
				ask=scanner.nextInt();
				scanner.nextLine();
				if((ask>=start)&&(ask<=end)){run=false;}
				else{System.out.println("Opcion invalida!");}
			}
			catch(InputMismatchException e){
				System.out.println("Opcion invalida!");
				scanner=new Scanner(System.in);
			}
		}
		
		return ask;

	}
	
	public double askDouble(){
		
		double ask=0;
		boolean run=true;
		
		while(run){
			try{
				ask=scanner.nextDouble();
				scanner.nextLine();
				run=false;
			}
			catch(InputMismatchException e){
				System.out.println("Opcion invalida!");
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
