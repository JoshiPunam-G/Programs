package com.bridgelabz.inventory;

import java.io.Serializable;
import java.util.List;

public class Inventory implements Serializable{
	
	private String name;
	private long weight;
	private long price;

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getWeight() {
		return weight;
	}
	public void setWeight(long weight) {
		this.weight = weight;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		price = price;
	}
	


	@Override
	public String toString() {
		return "Inventory [name=" + name + ", weight=" + weight + ", Price=" + price + "]";
	}
	
	

}
