package com.miguelMercado.project2.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tbl_customers")
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="El numero de documento no puede ser vacio")
	@Size(min = 8,max = 8, message="El tamño del numero de documento es 8")
	@Column(name ="number_id", unique= true, length = 8, nullable = false)
	private String numberID;
	
	@NotEmpty(message="El nombre no puede ser vacio")
	@Column(name = "first_name", nullable=false)
	private String firstName;
	
	@NotEmpty(message = "El apellido no puede ser vacio")
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@NotEmpty(message = "el correo no puede estar vacio")
	@Email(message = "No es una direccion de correo bien formada")
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(name = "photo_url")
	private String photoUrl;

	@NotNull(message = "la region no puede ser vacia")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "region_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Region region;
	
	private String state;

	public Customer() {
		
	}

	public Customer(Long id,
			@NotEmpty(message = "El numero de documento no puede ser vacio") @Size(min = 8, max = 8, message = "El tamño del numero de documento es 8") String numberID,
			@NotEmpty(message = "El nombre no puede ser vacio") String firstName,
			@NotEmpty(message = "El apellido no puede ser vacio") String lastName,
			@NotEmpty(message = "el correo no puede estar vacio") @Email(message = "No es una direccion de correo bien formada") String email,
			String photoUrl, @NotNull(message = "la region no puede ser vacia") Region region, String state) {
		this.id = id;
		this.numberID = numberID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.photoUrl = photoUrl;
		this.region = region;
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumberID() {
		return numberID;
	}

	public void setNumberID(String numberID) {
		this.numberID = numberID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}
