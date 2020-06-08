package com.miguel.pruebaMicroservicio.entity;

import java.io.Serializable;

import javax.persistence.*;



@Entity
@Table(name = "tbl_categories")
public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4783999449318512115L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	
	public Category() {
	}
	public Category(Long id) {
		this.id = id;
	}
	public Category(Long id, String name) {
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
