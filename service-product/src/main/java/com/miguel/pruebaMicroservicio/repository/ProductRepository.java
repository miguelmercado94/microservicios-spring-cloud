package com.miguel.pruebaMicroservicio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miguel.pruebaMicroservicio.entity.Category;
import com.miguel.pruebaMicroservicio.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long>  {

	public List<Product> findByCategory(Category categoy);
	
}
