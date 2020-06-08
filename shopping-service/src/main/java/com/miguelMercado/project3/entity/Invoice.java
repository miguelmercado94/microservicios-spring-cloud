package com.miguelMercado.project3.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_invoices")
public class Invoice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "number_invoice")
	private String numberInvoice;
	
	private String description;
	
	@Column(name = "customer_id")
	private Long customerId;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	@Valid
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "invoice_id")
	private List<InvoiceItem> items;
	
	private String state;
	
	private Invoice() {
		items = new ArrayList<>();
	}
	
	
	public Invoice(Long id, String numberInvoice, String description, Long customerId, Date createAt,
			@Valid List<InvoiceItem> item, String state) {
		this.id = id;
		this.numberInvoice = numberInvoice;
		this.description = description;
		this.customerId = customerId;
		this.createAt = createAt;
		this.items = item;
		this.state = state;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNumberInvoice() {
		return numberInvoice;
	}


	public void setNumberInvoice(String numberInvoice) {
		this.numberInvoice = numberInvoice;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public Date getCreateAt() {
		return createAt;
	}


	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}


	public List<InvoiceItem> getItems() {
		return items;
	}


	public void setItems(List<InvoiceItem> item) {
		this.items = item;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	@PrePersist
	public void prePersist() {
		
		this.createAt = new Date() ;
	}
	
}
