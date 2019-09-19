package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class HagoromoSekai {

	//Constant
	public final static String FILE_PATH="dat/clans.al";
	
	//Attribute
	private ArrayList<Clan> clans;
	
	public HagoromoSekai(){
		clans=new ArrayList<Clan>();
	}
	
	//Add
	public boolean addClan(String name){
		boolean exist=searchClan(name);
		
		if(!exist){
			Clan newClan=new Clan(name);
			boolean found=false;
			for(int i=0;(i<clans.size()) && !found; i++){
				if(newClan.compareTo(clans.get(i))<0){
					clans.add(i,new Clan(name));
					found=true;
				}
			}
			if(!found)
				clans.add(new Clan(name));
		}
		
		return !exist;
	}
	
	//Delete
	public boolean deleteClan(String name){
		Clan temp=new Clan(name);
		
		boolean found=false;
		int start=0;
		int end=clans.size()-1;
		
		while((start<=end) && !found){
			int middle=(start+end)/2;
			if(clans.get(middle).compareTo(temp)==0){
				clans.remove(middle);
				found=true;
			}
			else if(clans.get(middle).compareTo(temp)>0){
				end=middle-1;
			}
			else{
				start=middle+1;
			}
		}
		
		return found;
	}
	
	//Search
	public boolean searchClan(String name){
		Clan temp=new Clan(name);
		
		boolean found=false;
		int start=0;
		int end=clans.size()-1;
		
		while((start<=end) && !found){
			int middle=(start+end)/2;
			if(clans.get(middle).compareTo(temp)==0){
				found=true;
			}
			else if(clans.get(middle).compareTo(temp)>0){
				end=middle-1;
			}
			else{
				start=middle+1;
			}
		}
		
		return found;
	}

	//Print
	public String printClans(){//Testeo?
		String clans="";
		for(int i=0;i<this.clans.size();i++){
			clans+=this.clans.get(i);
		}
		return clans;
	}
	
	//Save
	public boolean save(){//[File]
		boolean possible=true;
		try {
			FileOutputStream file=new FileOutputStream(FILE_PATH);
			ObjectOutputStream creator=new ObjectOutputStream(file);
			creator.writeObject(clans);
			creator.close();
		}
		catch (IOException e) {possible=false;}
		return possible;
	}
	
	//Load
	public boolean load(){//[File]
		boolean possible=true;
		try{
			FileInputStream file=new FileInputStream(FILE_PATH);
			ObjectInputStream creator=new ObjectInputStream(file);
			this.clans=(ArrayList<Clan>)creator.readObject();
			creator.close();
		}
		catch (IOException e) {save();} 
		catch (ClassNotFoundException e) {possible=false;}
		return possible;
	}
	
	//Set
	public void setClans(ArrayList<Clan> clans) {
		this.clans = clans;
	}
	
	//Get
	public ArrayList<Clan> getClans() {
		return clans;
	}
	
}
