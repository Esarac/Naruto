package model;

public class Jutsu implements Comparable<Jutsu>{

	//Attribute
	private String name;
	private double factor;
	//Code
	private Jutsu nextJutsu;
	
	//Constructor
	public Jutsu(String name, double factor){
		this.name=name;
		this.factor=factor;
	}
	
	//Compare
	public int compareTo(Jutsu jutsu){//Factor
		int delta=(int)((factor*1000)-(jutsu.factor*1000));
		return delta;
	}
	
	//Set
	//Update----
	public void setName(String name){
		this.name=name;
	}
	
	public void setFactor(double factor){
		this.factor=factor;
	}
	//----------
	
	public void setNextJutsu(Jutsu nextJutsu) {
		this.nextJutsu = nextJutsu;
	}
	
	//Get
	public String getName(){
		return name;
	}
	
	public Jutsu getNextJutsu() {
		return nextJutsu;
	}
	
	public String toString() {
		return "Jutsu [name=" + name + ", factor=" + factor + "]";
	}
}
