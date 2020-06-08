package com.miguel.pruebaMicroservicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.miguel.pruebaMicroservicio.service.ProductService;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miguel.pruebaMicroservicio.entity.Category;
import com.miguel.pruebaMicroservicio.entity.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> listProduct(@RequestParam(name="categoryId", required = false) Long categoryId){
		
		List<Product> products = new ArrayList<>();
		
		if(categoryId == null) {
			products = productService.listAllProduct();
		
			if(products.isEmpty()){
				return ResponseEntity.noContent().build();
			}
		
		}else {
			products = productService.findByCategory(new Category(categoryId));
			if(products.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			
		}
	
		return ResponseEntity.ok(products);
	}
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
		
		Product product = productService.getProduct(id);
		
		if(null == product) {
			
			return ResponseEntity.notFound().build();
			
		}
		
		return ResponseEntity.ok(product);
	}
	
	@PostMapping
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result){
		if(result.hasErrors()) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
			
		}
		
		Product createproduct = productService.createProduct(product);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createproduct);
		
		
	}

    @PutMapping(value="/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id,@RequestBody Product product){
    	 product.setId(id);
    	 Product productDB = productService.updateProduct(product);
    	 
    	 if(productDB == null) {
    		 return ResponseEntity.notFound().build();
    	 }
    	 
    	return ResponseEntity.ok(productDB);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
    	
    	Product productDB = productService.deleteProduct(id);
    	
    	 if(productDB == null) {
    		 return ResponseEntity.notFound().build();
    	 }
    	 
    	
    	return ResponseEntity.ok(productDB);
    }
    
    @GetMapping(value= "/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable("id")  Long id, @RequestParam(name="quantity", required=true) Double quantity){
    	
    	Product productDB = productService.updateStock(id, quantity);
    	if(productDB == null) {
   		 return ResponseEntity.notFound().build();
   	 }
    	
    	return ResponseEntity.ok(productDB);
    }
    private String formatMessage(BindingResult result) {
    	List<Map<String,String>> errors = result.getFieldErrors().stream()
    			.map(err -> {
    				Map<String, String> error = new HashMap<>();
    				error.put(err.getField(), err.getDefaultMessage());
    				return error;  
    			}).collect(Collectors.toList());
    	
    	ErrorMessage errorMessage = new ErrorMessage("01",errors);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonString="";
    	try {
    		jsonString = mapper.writeValueAsString(errorMessage);
    	}catch(JsonProcessingException e) {
    		e.printStackTrace();
    		
    	}
    	
    	return jsonString;
    }
}
