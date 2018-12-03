package com.company;

import java.util.ArrayList;
import java.util.List;

public class People {

    private  String name;
    private int age;
    private Address address;
    private Gender gender;
    public List<String> phones = new ArrayList<>();
    int ID;
    public People(int id, String name, int age, Gender gender, City city, String street, ArrayList<String> phones) {
            this.name = name;
            this.age = age;
            this.gender=gender;
            address=new Address(city, street);
            this.phones = phones;
            this.ID = id;
    }
    public int getID() { return ID; }
    public String getName() {return name;}
    public List<String> getPhones() {return phones;}
    public void setName(String name) {this.name = name;}
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
    public Address getAddress() {return address;}
    public void setAddress(Address address) {this.address = address;}
    public Gender getGender() {return gender;}
    public void setGender(Gender gender) {this.gender = gender;}
    @Override
    public String toString() {return name + "-" + age + " city="+this.address.getCity();}
    
	public enum City {
	       A,B,C,D,E,F,G,H
	}
	
	public enum Gender {
	       M,F
	}

	public class Address {
	       private City city;
	       private String street;

	       public Address(City city, String street) {
	             this.city = city;
	             this.street = street;
	       }
	       public City getCity() {return city;}
	       public void setCity(City city) {this.city = city;}
	       public String getStreet() {return street;}
	       public void setStreet(String street) {this.street = street;}
	       @Override
	       public String toString() {return city.toString();}
	}
    
}


