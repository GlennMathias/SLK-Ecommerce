package com.example.TeamWork.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TeamWork.DAO.CartDAO;
import com.example.TeamWork.model.Cart;

@CrossOrigin(origins="*")
@RestController
public class OrderDetailsController {
	int i=1;
	
	@Autowired
	CartDAO cartDao;
	
	@GetMapping("/userLogin/{id}")
	public int userLogin(@PathVariable ("id") int custId)
	{
		return cartDao.createOrder(custId);
	}
	
	@GetMapping("/addCart/{ordId}/{prodId}/{qty}")
	public void addCart(@PathVariable ("ordId") int ordId,@PathVariable ("prodId") int ProdId,@PathVariable ("qty") int qty)
	{
		//System.out.println("Inside Get Cart");
		cartDao.addToCart(ordId,ProdId,qty);
		
	}
	
	@GetMapping("/viewCart/{id}")
	public List<Cart> viewCart(@PathVariable ("id") int ordId)
	{
		return cartDao.viewCart(ordId);
	}
	
	
	
	@DeleteMapping("/remove")
	public void flush()
	{
		
		cartDao.payOut();
	}
	
	
	@DeleteMapping("/removeCart/{pid}/{oid}")
	public void removeCart(@PathVariable ("pid") int ProdId,@PathVariable ("oid") int ordId)
	{
		System.out.println("Removing from Cart.");
		cartDao.removeFromCart(ProdId,ordId);
	}
	
	
	@PutMapping("/updateCart")
	public void changeQty(Cart crt)
	{
		cartDao.updateCart(crt.getQty(), crt.getProdid(), crt.getOrdid());
	}
	
	@GetMapping("/getTotal/{ordId}")
	public int getTotal(@PathVariable ("ordId") int ordId)
	{
		return cartDao.getOrderTotal(ordId);
	}

}
