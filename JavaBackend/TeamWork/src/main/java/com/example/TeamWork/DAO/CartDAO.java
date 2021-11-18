package com.example.TeamWork.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.TeamWork.model.Cart;

@Repository
public interface CartDAO {
	
	public int createOrder(int CustId);
	
	public void addToCart(int ordId,int prodId,int qty);
	public List<Cart> viewCart(int CustId);
	public void removeFromCart(int ProdId,int ordId);
	public void updateCart(int qty, int proId, int ordId);
	
	public void payOut();
}
