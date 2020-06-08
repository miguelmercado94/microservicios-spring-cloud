package com.miguel.pruebaMicroservicio.service;

import java.util.List;

import com.miguel.pruebaMicroservicio.entity.Category;
import com.miguel.pruebaMicroservicio.entity.Product;

public interface ProductService {

	public List<Product> listAllProduct();
	public Product getProduct(Long id);
 	public Product createProduct(Product product);
 	public Product updateProduct(Product product);
 	public Product deleteProduct(Long id);
 	public List<Product> findByCategory(Category categort);
 	public Product updateStock(Long id, Double quantity);

}
