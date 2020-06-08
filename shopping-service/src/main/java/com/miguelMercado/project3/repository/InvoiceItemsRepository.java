package com.miguelMercado.project3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miguelMercado.project3.entity.InvoiceItem;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem,Long>{

}
