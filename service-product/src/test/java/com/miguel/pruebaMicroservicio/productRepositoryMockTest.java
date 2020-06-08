package com.miguel.pruebaMicroservicio;

import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.miguel.pruebaMicroservicio.repository.ProductRepository;
import com.miguel.pruebaMicroservicio.entity.Category;
import com.miguel.pruebaMicroservicio.entity.Product;

@DataJpaTest
public class productRepositoryMockTest {

	@Autowired
	private ProductRepository productRepository;
	
	@Test
	public void whenFindByCategory_thenReturnListProduct() {
		Product producto01 = new Product();
		
		producto01.setName("computer");
		producto01.setCategory(new Category (1L));
		producto01.setDescription("");
		producto01.setStock(Double.parseDouble("10"));
		producto01.setPrice(Double.parseDouble("1240.99"));
		producto01.setStatus("Created");
		producto01.setCreateAt(new Date());
		
		productRepository.save(producto01);
		
		List<Product> founds = productRepository.findByCategory(producto01.getCategory());
		
		Assertions.assertThat(founds.size()).isEqualTo(2);
		
	}
	
}
