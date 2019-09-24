package model;

import java.io.Serializable;
import java.util.Calendar;

public class Clan implements Serializable, Comparable<Clan>{
	
	//Constant
	private static final long serialVersionUID = -6581382413801875940L;
	
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
	
	//Update
	public boolean updateShinobiName(String name, Shinobi shinobi){
		 boolean exist=searchShinobi(name);
		 if(!exist)
			 shinobi.setName(name);
		 return !exist;
	}
	
	//Print
	public String printShinobis(){
		String shinobis="";
		
		if(shinobiSize()!=0){
			Shinobi actual=firstShinobi;
			while(actual!=null){
				shinobis+=actual+"\n";
				actual=actual.getNextShinobi();
			}
		}
		else{
			shinobis="Ø";
		}

		return shinobis;
	}
	
	public String toString() {
		return "-[name=" + name + "]-";
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
	
	public Shinobi getShinobi(String name){
		Shinobi actual=firstShinobi;
		boolean found=false;
		while((actual!=null) && !found){
			if(actual.getName().equals(name))
				found=true;
			else
				actual=actual.getNextShinobi();
		}
		return actual;
	}
	
	//Sort
	public long sortShinobiName(){//Selection
		long startTime=System.nanoTime();
		
		int size=shinobiSize();
		for(int i=0;i<(size-1);i++){
			Shinobi min=getShinobi(i);
			int minPos=i;
			for(int j=(i+1);j<size;j++){
				if(getShinobi(j).compareTo(min)<0){
					min=getShinobi(j);
					minPos=j;
				}
			}
			Shinobi actual=getShinobi(i);
			setShinobi(i,min);
			setShinobi(minPos,actual);
		}
		
		long endTime=System.nanoTime();
		long deltaTime=endTime-startTime;
		return deltaTime;
	}
	
	public long sortShinobiPower(){//Bubble
		long startTime=System.nanoTime();
		
		int size=shinobiSize();
		for(int i=0; i<size;i++){
			for(int j=0; j<(size-(i+1));j++){
				if(getShinobi(j+1).compare(getShinobi(j+1),getShinobi(j))<0){
					Shinobi actual=getShinobi(j);
					setShinobi(j, getShinobi(j+1));
					setShinobi(j+1, actual);
				}
			}
		}
		
		long endTime=System.nanoTime();
		long deltaTime=endTime-startTime;
		return deltaTime;
	}
	
	public long sortShinobiCreationDate(){//Insertion
		long startTime=System.nanoTime();
		
		int size=shinobiSize();
		for(int i=1;i<size;i++){
			for(int j=i;(j>0)&&(getShinobi(j-1).compareCreationDate(getShinobi(j))>0);j--){
				Shinobi actual=getShinobi(j);
				setShinobi(j, getShinobi(j-1));
				setShinobi(j-1, actual);
			}
		}
		
		long endTime=System.nanoTime();
		long deltaTime=endTime-startTime;
		return deltaTime;
	}
	
	//Compare
	public int compareTo(Clan clan){//Name
		int delta=name.compareTo(clan.name);
		return delta;
	}

	//List-------------------------------------------------------------
	public int shinobiSize(){
		int size=0;
		Shinobi actual=firstShinobi;
		while(actual!=null){
			size++;
			actual=actual.getNextShinobi();
		}
		return size;
	}
	
	public boolean setShinobi(int index, Shinobi tempShinobi){
		int size=shinobiSize();
		boolean possible=true;
		
		try{
			Shinobi shinobi=new Shinobi(tempShinobi.getName(), tempShinobi.getPersonality(), tempShinobi.getCreationDate(), tempShinobi.getPower());
			
			Shinobi actual=firstShinobi;
			for(int i=0;i<index;i++){
				actual=actual.getNextShinobi();
			}
			if(index==0){
				shinobi.setNextShinobi(actual.getNextShinobi());//Next
				if(size!=1)
					actual.getNextShinobi().setPrevShinobi(shinobi);
				this.firstShinobi=shinobi;
			}
			else if(index==(size-1)){
				actual.getPrevShinobi().setNextShinobi(shinobi);
				shinobi.setPrevShinobi(actual.getPrevShinobi());//Prev
				
			}
			else{
				shinobi.setNextShinobi(actual.getNextShinobi());//Next
				actual.getNextShinobi().setPrevShinobi(shinobi);
				shinobi.setPrevShinobi(actual.getPrevShinobi());//Prev
				actual.getPrevShinobi().setNextShinobi(shinobi);
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
	
	public Shinobi getShinobi(int index){
		Shinobi shinobi=firstShinobi;
		try{
			for(int i=0;i<index;i++){
				shinobi=shinobi.getNextShinobi();
			}
		}
		catch(NullPointerException e){
			try{
				throw new ListIndexOutOfBoundsException();
			}
			catch(ListIndexOutOfBoundsException l){
				shinobi=null;
			}
		}
		return shinobi;
	}
	//-----------------------------------------------------------------
	
	//Set
	public void setName(String name){
		this.name=name;
	}
	
	public void setFirstShinobi(Shinobi firstShinobi){
		this.firstShinobi=firstShinobi;
	}
	
	//Get
	public String getName(){
		return name;
	}
	
	public Shinobi getFirstShinobi(){
		return firstShinobi;
	}
	
}
