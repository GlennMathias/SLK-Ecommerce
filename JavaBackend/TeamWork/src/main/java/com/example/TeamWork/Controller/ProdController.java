package com.example.TeamWork.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.TeamWork.DAO.ProductDAO;
import com.example.TeamWork.model.Product;

@CrossOrigin(origins="*")
@RestController
public class ProdController {
	
	@Autowired
	public ProductDAO prodDao;
	
	
	@GetMapping("/getProducts")
	public List<Product> getAllProds()
	{
		return prodDao.getAllProducts();
	}
	
	@PostMapping("/addProduct")
	public void addProduct(@RequestBody Product p)
	{
		prodDao.addProduct(p);
	}
	
	@GetMapping("/getProductsByCat/{id}")
	public List<Product> getProdFromCatId(@PathVariable ("id") int prodCatId)
	{
		return prodDao.getProdFromCat(prodCatId);
	}
	
	@GetMapping("/getProduct/{id}")
	public Product getProduct(@PathVariable ("id") int prodId)
	{
		return prodDao.getProduct(prodId);
	}

}
