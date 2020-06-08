package com.miguelMercado.project3.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "tbl_invoce_items")
public class InvoiceItem implements Serializable{

	
	private static final long serialVersionUID = 3201780472713050346L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Positive(message = "El stock debe ser mayor que cero")
	private Double quantity;
	
	private Double price;
	
	@Column(name = "product_id")
	private Long productId;
	
	@Transient
	private Double subTotal;
	
	private InvoiceItem() {
		
	}

	public InvoiceItem(Long id, @Positive(message = "El stock debe ser mayor que cero") Double quantity, Double price,
			Long productId, Double subTotal) {
		this.id = id;
		this.quantity = quantity;
		this.price = price;
		this.productId = productId;
		this.subTotal = subTotal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Double getSubTotal() {
		
		if(this.price > 0 && this.quantity >0) {
			return this.price*this.quantity;
		}else {
		
		return (double) 0;
		}
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
	
	
	
}
