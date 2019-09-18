package model;

import java.util.Calendar;

public class Clan implements Comparable<Clan>{

	//Attribute
	private String name;
	private Shinobi firstShinobi;//List
	
	//Constructor
	public Clan(String name){
		this.name=name;
	}
	
	//Add
	public boolean addShinobi(String name, String personality, Calendar creationDate, int power){
		boolean exist=searchShinobi(name);
		
		if(!exist){
			if(firstShinobi!=null){
				Shinobi actual=new Shinobi(name, personality, creationDate, power);
				actual.setNextShinobi(firstShinobi);
				firstShinobi.setPrevShinobi(actual);
				firstShinobi=actual;
			}
			else{
				firstShinobi=new Shinobi(name, personality, creationDate, power);
			}
		}
		
		return !exist;
	}
	
	//Delete
	public boolean deleteShinobi(String name){
		boolean found=false;
		Shinobi actual=firstShinobi;
		while((actual!=null) && !found){
			if(actual.getName().equals(name)){
				found=true;
				if(actual.getPrevShinobi()!=null)
					actual.getPrevShinobi().setNextShinobi(actual.getNextShinobi());
				else
					firstShinobi=actual.getNextShinobi();
				if(actual.getNextShinobi()!=null)
					actual.getNextShinobi().setPrevShinobi(actual.getPrevShinobi());
			}	
			else
				actual=actual.getNextShinobi();
		}
		return found;
	}
	
	//Search
	public boolean searchShinobi(String name){
		boolean found=false;
		Shinobi actual=firstShinobi;
		while((actual!=null) && !found){
			if(actual.getName().equals(name))
				found=true;
			else
				actual=actual.getNextShinobi();
		}
		return found;
	}
	
	//No se
	public String printShinobis(){
		String shinobis="";
		Shinobi actual=firstShinobi;
		while(actual!=null){
			shinobis+=actual;
			actual=actual.getNextShinobi();
		}
		return shinobis;
	}
	
	public int compareTo(Clan clan){
		int delta=name.compareTo(clan.name);//Mejor hacerlo con el metodo getName()?
		return delta;
	}

	public String toString() {
		return "Clan [name=" + name + "]";
	}
	
}
