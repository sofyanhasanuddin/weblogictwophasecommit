package com.sofyan.example.weblogicxaexample.ds1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sofyan.example.weblogicxaexample.ds1.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
