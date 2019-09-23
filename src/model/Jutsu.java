package model;

import java.io.Serializable;

public class Jutsu implements Serializable, Comparable<Jutsu>{

	//Constant
	private static final long serialVersionUID = -1883292939489506193L;
	
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
	
	//Print
	public String toString() {
		return "-[name=" + name + ", factor=" + factor + "]-";
	}
	
	//Compare
	public int compareTo(Jutsu jutsu){//Factor
		int delta=(int)((factor*1000)-(jutsu.factor*1000));
		return delta;
	}
	
	//Set
	public void setName(String name){
		this.name=name;
	}
	
	public void setFactor(double factor){
		this.factor=factor;
	}
	
	public void setNextJutsu(Jutsu nextJutsu) {
		this.nextJutsu = nextJutsu;
	}
	
	//Get
	public String getName(){
		return name;
	}
	
	public double getFactor(){
		return factor;
	}
	
	public Jutsu getNextJutsu() {
		return nextJutsu;
	}
	
}
