package fr.iut.allonounou.modelAdapter;

public class Nanny {
	private String name;
	private String place;
	private String address;
	private String picture;
	
	
	public Nanny(String name, String place, String address, String picture) {
		super();
		this.name = name;
		this.place = place;
		this.address = address;
		this.picture = picture;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
}
