package it.polito.tdp.PremierLeague.model;

public class Adiacenze implements Comparable<Adiacenze>{

	private Integer m1;
	private Integer m2;
	private Double peso;
	
	public Adiacenze(Integer m1, Integer m2, Double peso) {
		super();
		this.m1 = m1;
		this.m2 = m2;
		this.peso = peso;
	}
	
	public Integer getM1() {
		return m1;
	}
	
	public void setM1(Integer m1) {
		this.m1 = m1;
	}
	
	public Integer getM2() {
		return m2;
	}
	
	public void setM2(Integer m2) {
		this.m2 = m2;
	}
	
	public Double getPeso() {
		return peso;
	}
	
	public void setPeso(Double peso) {
		this.peso = peso;
	}

	@Override
	public int compareTo(Adiacenze o) {
		return - this.peso.compareTo(o.getPeso());
	}

	@Override
	public String toString() {
		return m1+ " " +m2+ " (" +peso+ ") \n";
	}
	
	
}
