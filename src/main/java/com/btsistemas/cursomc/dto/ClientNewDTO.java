package com.btsistemas.cursomc.dto;

import java.io.Serializable;

public class ClientNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;
	
    //Client (Domain)
	private String name;
	private String email;
	private String document;
	private Integer typeClient;
	
	//Address (Domain)
	private String place;
    private String number;
    private String Complement;
    private String neighborhood;
    private String zipcode;
    
    private String phone1;
    private String phone2;
    private String phone3;
 
    private Integer cityId;
    
    public ClientNewDTO() {
    	
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public Integer getTypeClient() {
		return typeClient;
	}

	public void setTypeClient(Integer typeClient) {
		this.typeClient = typeClient;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return Complement;
	}

	public void setComplement(String complement) {
		Complement = complement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
   	
}
