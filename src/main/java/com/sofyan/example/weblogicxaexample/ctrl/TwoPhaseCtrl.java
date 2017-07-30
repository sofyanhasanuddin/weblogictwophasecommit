package com.sofyan.example.weblogicxaexample.ctrl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sofyan.example.weblogicxaexample.ds1.model.Product;
import com.sofyan.example.weblogicxaexample.ds2.model.Category;
import com.sofyan.example.weblogicxaexample.service.MainService;

@RestController
public class TwoPhaseCtrl {
	
	@Autowired
	private MainService mainService;
	
	@PostMapping(value="/save")
	public Map<String, String> save(
			@RequestParam(value="productname", required=true)String productname,
			@RequestParam(value="categoryname", required=true)String categoryname ) {
		
		if( StringUtils.isEmpty(productname) || StringUtils.isEmpty(categoryname) )
			throw new IllegalArgumentException();
		
		Product p = new Product();
		p.setName( productname );
		
		Category o = new Category();
		o.setName( categoryname );
		
		this.mainService.savePersonAndOrder(p, o);
		
		return returnMessage("Success");
		
	}
	
	@ExceptionHandler(UnexpectedRollbackException.class)
	public Map<String, String> handleRollbackException() {
		
		return returnMessage("Exception during insert");
		
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public Map<String, String> handleArgumentException() {
		
		return returnMessage("Parameter should not be empty");
		
	}
	
	private Map<String, String> returnMessage(String message) {
		
		Map<String, String> result = new HashMap<String, String>();
		result.put("message", message);
		
		return result;
		
	}
	

}
