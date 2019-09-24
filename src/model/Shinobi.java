package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;

public class Shinobi implements Serializable, Comparable<Shinobi>, Comparator<Shinobi>{
	
	//Constant
	private static final long serialVersionUID = 5515108758496534696L;
	
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
	
	//Update
	public boolean updateJutsuName(String name, Jutsu jutsu){
		 boolean exist=searchJutsu(name);
		 if(!exist)
			 jutsu.setName(name);
		 return !exist;
	}
	
	public void updateJutsuFactor(double factor, Jutsu jutsu){
		jutsu.setFactor(factor);
		sortJutsuFactor();
	}
	
	//Print
	public String printJutsus(){
		String jutsus="";
		
		if(jutsuSize()!=0){
			Jutsu actual=firstJutsu;
			while(actual!=null){
				jutsus+=actual+"\n";
				actual=actual.getNextJutsu();
			}
		}
		else{
			jutsus="Ø";
		}
		
		return jutsus;
	}
	
	public String toString() {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		String toString="-[name=" + name + ", personality=" + personality + ", creationDate=" + f.format(creationDate.getTime())+ ", power="+ power + "]-";
		return toString;
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
	
	public Jutsu getJutsu(String name){
		Jutsu actual=firstJutsu;
		boolean found=false;
		while((actual!=null) && !found){
			if(actual.getName().equals(name))
				found=true;
			else
				actual=actual.getNextJutsu();
		}
		return actual;
	}
	
	//Sort
	public void sortJutsuFactor(){
		int size=jutsuSize();
		for(int i=1;i<size;i++){
			for(int j=i;(j>0)&&(getJutsu(j-1).compareTo(getJutsu(j))>0);j--){
				Jutsu actual=getJutsu(j);
				setJutsu(j, getJutsu(j-1));
				setJutsu(j-1, actual);
			}
		}
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
	
	//List-------------------------------------------------------------
	public int jutsuSize(){
		int size=0;
		Jutsu actual=firstJutsu;
		while(actual!=null){
			size++;
			actual=actual.getNextJutsu();
		}
		return size;
	}
	
	public boolean setJutsu(int index, Jutsu tempJutsu){
		Jutsu jutsu=new Jutsu(tempJutsu.getName(), tempJutsu.getFactor());
		
		boolean possible=true;
		Jutsu previous=null;
		Jutsu actual=firstJutsu;
		try{
			for(int i=0;i<index;i++){
				previous=actual;
				actual=actual.getNextJutsu();
			}
			if(index==0){
				jutsu.setNextJutsu(actual.getNextJutsu());//Next
				this.firstJutsu=jutsu;
			}
			else{
				jutsu.setNextJutsu(actual.getNextJutsu());//Next
				previous.setNextJutsu(jutsu);
			}
		}
		catch(NullPointerException e){
			try{
				throw new ListIndexOutOfBoundsException();
			}
			catch(ListIndexOutOfBoundsException l){
				possible=false;
			}
		}
		return possible;
	}
	
	public Jutsu getJutsu(int index){
		Jutsu jutsu=firstJutsu;
		try{
			for(int i=0;i<index;i++){
				jutsu=jutsu.getNextJutsu();
			}
		}
		catch(NullPointerException e){
			try{
				throw new ListIndexOutOfBoundsException();
			}
			catch(ListIndexOutOfBoundsException l){
				jutsu=null;
			}
		}
		return jutsu;
	}
	//-----------------------------------------------------------------
	
	//Set
	public void setName(String name){
		this.name=name;
	}
	
	//Update----
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
