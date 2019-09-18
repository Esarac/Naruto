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
	
	public int compareTo(Jutsu jutsu){
		int delta=(int)((factor*1000)-(jutsu.factor*1000));
		return delta;
	}
	
	//Set
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
	
	@Override
	public String toString() {
		return "Jutsu [name=" + name + ", factor=" + factor + "]";
	}
}
