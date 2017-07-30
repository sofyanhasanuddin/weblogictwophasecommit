package com.sofyan.example.weblogicxaexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sofyan.example.weblogicxaexample.ds1.Repository.ProductRepository;
import com.sofyan.example.weblogicxaexample.ds1.model.Product;
import com.sofyan.example.weblogicxaexample.ds2.model.Category;
import com.sofyan.example.weblogicxaexample.ds2.repository.CategoryRepository;
import com.sofyan.example.weblogicxaexample.exception.NoRollBackException;
import com.sofyan.example.weblogicxaexample.exception.RollBackException;

@Service
@Transactional(readOnly=true)
public class MainService {
	
	@Autowired ProductRepository productRepository;
	@Autowired CategoryRepository categoryRepository;
	
	public long countPerson() {
		return this.productRepository.count();
	}
	
	public long countOrder() {
		return this.categoryRepository.count();
	}
	
	@Transactional(readOnly=false)
	public void saveOnlyProduct(Product p) {
		this.productRepository.save( p );
	}
	
	@Transactional(readOnly=false)
	public void saveOnlyCategory(Category c) {
		this.categoryRepository.save( c );
	}
	
	@Transactional(readOnly=false)
	public void savePersonAndOrder(Product p, Category c) {
		
		this.productRepository.save( p );
		this.categoryRepository.save( c );
		
	}
	
	@Transactional(readOnly=false,rollbackFor=RollBackException.class)
	public void savePersonAndOrderWithRollback(Product p, Category c) {
		
		this.productRepository.save( p );
		this.categoryRepository.save( c );
		
		throw new RollBackException("Should be rollback");
		
	}
	
	@Transactional(readOnly=false,rollbackFor=RollBackException.class,noRollbackFor=NoRollBackException.class)
	public void savePersonAndOrderWithNoRollback(Product p, Category c) {
		
		this.productRepository.save( p );
		this.categoryRepository.save( c );
		
		throw new NoRollBackException("Should be no rollback");
		
	}

}
