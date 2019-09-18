package model;

import java.util.Calendar;

public class Shinobi {

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
	
	//No se
	public String printJutsu(){
		String shinobis="";
		Jutsu actual=firstJutsu;
		while(actual!=null){
			shinobis+=actual;
			actual=actual.getNextJutsu();
		}
		return shinobis;
	}
	
	//Set
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
	
	public Shinobi getPrevShinobi() {
		return prevShinobi;
	}
	
	public Shinobi getNextShinobi() {
		return nextShinobi;
	}

	@Override
	public String toString() {
		String toString="[name=" + name + /*", personality=" + personality + ", creationDate=" + creationDate.getWeekYear() + ", power="+ power + ", firstJutsu=" + firstJutsu + */", prevShinobi=";
		if(prevShinobi!=null)toString+=prevShinobi.name;
		else toString+="null";
		toString+=", nextShinobi=";
		if(nextShinobi!=null)toString+=nextShinobi.name;
		else toString+="null";
		toString+="]";
		return toString;
	}
	
	
	
	
	
}
