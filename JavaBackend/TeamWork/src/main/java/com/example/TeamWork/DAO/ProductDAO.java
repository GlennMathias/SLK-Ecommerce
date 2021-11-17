package com.example.TeamWork.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.TeamWork.model.Product;


@Repository
public interface ProductDAO {
	
	public List<Product> getAllProducts();
	
	public void addProduct(Product p);

	public List<Product> getProdFromCat(int catId);
	
	public Product getProduct(int prodId);

}
