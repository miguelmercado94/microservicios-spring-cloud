package com.miguel.pruebaMicroservicio.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "tbl_products")
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7558169812173955978L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "no debe ser vacio")
	private String name;
	
	private String description;
	
	@Positive(message="El stock debe ser mayor que cero")
	private Double stock;
	
	private Double price;
	
	private String status;
	
	@Column(name="create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@NotNull(message="no puede ser nulo")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Category category;
	
	

	public Product() {
	}
	

	public Product(Long id, String name, String descripcion, Double stock, Double price, String status, Date createAt,
			Category category) {
		this.id = id;
		this.name = name;
		this.description = descripcion;
		this.stock = stock;
		this.price = price;
		this.status = status;
		this.createAt = createAt;
		this.category = category;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String descripcion) {
		this.description = descripcion;
	}

	public Double getStock() {
		return stock;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
	
}
