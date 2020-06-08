package com.miguelMercado.project3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miguelMercado.project3.entity.Invoice;
import com.miguelMercado.project3.service.InvoiceService;

@RestController
@RequestMapping("/invoices")
public class InvoiceRest {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(InvoiceRest.class);
	
	@Autowired
	InvoiceService invoiceService;
	
	@GetMapping
	public ResponseEntity<List<Invoice>> listAllInvoices(){
		List<Invoice> invoices = invoiceService.findInvoiceAll();
		if(invoices.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(invoices);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Invoice> getInvoice(@PathVariable("id")Long id){
		log.info("Fetching Invoice with id {}");
		Invoice invoice = invoiceService.getInvoice(id);
		if(null == invoice) {
			log.error("Invoice with id {} not found", id);
			return ResponseEntity.notFound().build();			
		}
		
		return ResponseEntity.ok(invoice);
		} 
	@PostMapping
    public ResponseEntity<Invoice> createInvoice(@Valid @RequestBody Invoice invoice, BindingResult result) {
        log.info("Creating Invoice : {}", invoice);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Invoice invoiceDB = invoiceService.createInvoice (invoice);

        return  ResponseEntity.status( HttpStatus.CREATED).body(invoiceDB);
    }
	 @PutMapping(value = "/{id}")
	    public ResponseEntity<?> updateInvoice(@PathVariable("id") long id, @RequestBody Invoice invoice) {
	        log.info("Updating Invoice with id {}", id);

	        invoice.setId(id);
	        Invoice currentInvoice=invoiceService.updateInvoice(invoice);

	        if (currentInvoice == null) {
	            log.error("Unable to update. Invoice with id {} not found.", id);
	            return  ResponseEntity.notFound().build();
	        }
	        return  ResponseEntity.ok(currentInvoice);
	    }

	    // ------------------- Delete a Invoice-----------------------------------------
	    @DeleteMapping(value = "/{id}")
	    public ResponseEntity<Invoice> deleteInvoice(@PathVariable("id") long id) {
	        log.info("Fetching & Deleting Invoice with id {}", id);

	        Invoice invoice = invoiceService.getInvoice(id);
	        if (invoice == null) {
	            log.error("Unable to delete. Invoice with id {} not found.", id);
	            return  ResponseEntity.notFound().build();
	        }
	        invoice = invoiceService.deleteInvoice(invoice);
	        return ResponseEntity.ok(invoice);
	    }

	    private String formatMessage( BindingResult result){
	        List<Map<String,String>> errors = result.getFieldErrors().stream()
	                .map(err ->{
	                    Map<String,String> error =  new HashMap<>();
	                    error.put(err.getField(), err.getDefaultMessage());
	                    return error;

	                }).collect(Collectors.toList());
	        ErrorMessage errorMessage = new ErrorMessage("1", errors);
	        
	        ObjectMapper mapper = new ObjectMapper();
	        String jsonString="";
	        try {
	            jsonString = mapper.writeValueAsString(errorMessage);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
	        return jsonString;
	    }
}
