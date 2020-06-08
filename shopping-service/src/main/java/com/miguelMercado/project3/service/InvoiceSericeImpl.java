package com.miguelMercado.project3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miguelMercado.project3.entity.Invoice;
import com.miguelMercado.project3.repository.InvoiceItemsRepository;
import com.miguelMercado.project3.repository.InvoiceRepository;

@Service
public class InvoiceSericeImpl implements InvoiceService {
	
	@Autowired
	InvoiceRepository invoiceRepository;
	
	@Autowired
	InvoiceItemsRepository invoiceItemsRepository;

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(InvoiceSericeImpl.class);


	@Override
	public List<Invoice> findInvoiceAll() {
		return invoiceRepository.findAll();
	}

	@Override
	public Invoice createInvoice(Invoice invoice) {
		Invoice invoiceDB = invoiceRepository.findByNumberInvoice(invoice.getNumberInvoice());
		if(invoiceDB != null) return invoiceDB;
		
		invoice.setState("CREATED");
		
		return invoiceRepository.save(invoice);
	}

	@Override
	public Invoice updateInvoice(Invoice invoice) {

		Invoice invoiceDB = getInvoice(invoice.getId());
		
		if(invoiceDB == null) return null;
		
		invoiceDB.setCustomerId(invoice.getCustomerId());
		invoiceDB.setDescription(invoice.getDescription());
		invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
		invoiceDB.getItems().clear();
		invoiceDB.setItems(invoice.getItems());
		
		return invoiceRepository.save(invoiceDB);
	}

	@Override
	public Invoice deleteInvoice(Invoice invoice) {

		Invoice invoiceDB = getInvoice(invoice.getId());
		
		if(invoiceDB == null) return null;
		
		invoiceDB.setState("DELETE");
		
		invoiceRepository.save(invoiceDB);
		
		return invoiceDB;
	}

	@Override
	public Invoice getInvoice(Long id) {
		
		return invoiceRepository.findById(id).orElse(null);
	}
	

}
