package com.br.ac.rp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.br.ac.rp.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
}
