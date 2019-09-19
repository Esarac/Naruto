package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;

public class Shinobi implements Comparable<Shinobi>, Comparator<Shinobi>{

	//Attribute
	private String name;
	private String personality;
	private Calendar creationDate;
	private int power;
	private Jutsu firstJutsu;//List
	//Code
	private Shinobi prevShinobi;
	private Shinobi nextShinobi;
	
	//Constructor
	public Shinobi(String name, String personality, Calendar creationDate, int power){
		this.name=name;
		this.personality=personality;
		this.creationDate=creationDate;
		this.power=power;
	}

	//Add
	public boolean addJutsu(String name, double factor){
		boolean exist=searchJutsu(name);
		if(!exist){
			
			Jutsu created=new Jutsu(name, factor);
			if(firstJutsu!=null){
				Jutsu previous=null;
				Jutsu actual=firstJutsu;
				boolean found=false;
				while((actual!=null)&&!found){
					if(created.compareTo(actual)<0){
						found=true;
						created.setNextJutsu(actual);
						if(previous!=null){
							previous.setNextJutsu(created);
						}
						else
							firstJutsu=created;
					}
					else{
						previous=actual;
						actual=actual.getNextJutsu();
					}
				}
				if(!found){
					previous.setNextJutsu(created);
				}
			}
			else
				firstJutsu=created;
			
		}
		return !exist;
	}
	
	//Delete
	public boolean deleteJutsu(String name){
		boolean found=false;
		Jutsu previous=null;
		Jutsu actual=firstJutsu;
		while((actual!=null) && !found){
			if(actual.getName().equals(name)){
				found=true;
				if(previous!=null){
					previous.setNextJutsu(actual.getNextJutsu());
				}
				else{
					this.firstJutsu=actual.getNextJutsu();
				}
			}	
			else{
				previous=actual;
				actual=actual.getNextJutsu();
			}
		}
		return found;
	}
	
	//Search
	public boolean searchJutsu(String name){
		boolean found=false;
		Jutsu actual=firstJutsu;
		while((actual!=null) && !found){
			if(actual.getName().equals(name))
				found=true;
			else
				actual=actual.getNextJutsu();
		}
		return found;
	}
	
	//Compare
	public int compareTo(Shinobi shinobi){//Name
		int delta=name.compareTo(shinobi.name);
		return delta;
	}
	
	public int compare(Shinobi shinobi1, Shinobi shinobi2){//Power
		int delta=shinobi1.power-shinobi2.power;
		return delta;
	}
	
	public int compareCreationDate(Shinobi shinobi){
		int delta=creationDate.compareTo(shinobi.creationDate);
		return delta;
	}
	
	//Print
	public String printJutsus(){//Testeo??
		String shinobis="";
		Jutsu actual=firstJutsu;
		while(actual!=null){
			shinobis+=actual;
			actual=actual.getNextJutsu();
		}
		return shinobis;
	}
	
	public String toString() {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		String toString="-[name=" + name + ", personality=" + personality + ", creationDate=" + f.format(creationDate.getTime())+ ", power="+ power + "]-";
		return toString;
	}
	
	//Set
	//Update----
	public void setName(String name){
		this.name=name;
	}
	
	public void setPersonality(String personality){
		this.personality=personality;
	}
	
	public void setCreationDate(Calendar creationDate){
		this.creationDate=creationDate;
	}
	
	public void setPower(int power){
		this.power=power;
	}
	//----------
	
	public void setFirstJutsu(Jutsu firstJutsu){
		this.firstJutsu=firstJutsu;
	}
	
	public void setPrevShinobi(Shinobi prevShinobi) {
		this.prevShinobi = prevShinobi;
	}
	
	public void setNextShinobi(Shinobi nextShinobi) {
		this.nextShinobi = nextShinobi;
	}
	
	//Get
	public String getName(){
		return name;
	}
	
	public String getPersonality(){
		return personality;
	}
	
	public Calendar getCreationDate(){
		return creationDate;
	}
	
	public int getPower(){
		return power;
	}
	
	public Jutsu getFirstJutsu(){
		return firstJutsu;
	}
	
	public Shinobi getPrevShinobi() {
		return prevShinobi;
	}
	
	public Shinobi getNextShinobi() {
		return nextShinobi;
	}
	
}
