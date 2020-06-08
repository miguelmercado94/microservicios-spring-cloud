package com.miguelMercado.project2.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name= "tbl_regions")
public class Region implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8699976620744898092L;
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String name;
	
	public Region() {
	}

	public Region(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
