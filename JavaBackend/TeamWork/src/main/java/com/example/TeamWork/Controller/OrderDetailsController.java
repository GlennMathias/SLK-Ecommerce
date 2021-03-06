package com.example.TeamWork.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public boolean addCart(@PathVariable ("ordId") int ordId,@PathVariable ("prodId") int ProdId,@PathVariable ("qty") int qty)
	{
		//System.out.println("Inside Get Cart");
		return cartDao.addToCart(ordId,ProdId,qty);
		
	}
	
	@GetMapping("/viewCart/{id}")
	public List<Cart> viewCart(@PathVariable ("id") int ordId)
	{
		return cartDao.viewCart(ordId);
	}
	
	
	
	@DeleteMapping("/remove/{ordId}")
	public void flush(@PathVariable ("ordId") int ordId)
	{
		System.out.println("Flushing Cart.");
		cartDao.payOut(ordId);
	}
	
	
	@DeleteMapping("/removeCart/{pid}/{oid}")
	public void removeCart(@PathVariable ("pid") int ProdId,@PathVariable ("oid") int ordId)
	{
		System.out.println("Removing from Cart.");
		cartDao.removeFromCart(ProdId,ordId);
	}
	
	
	@GetMapping("/updateCart/{ordId}/{prodId}/{qty}")
	public boolean changeQty(@PathVariable ("ordId") int ordId ,@PathVariable ("prodId") int prodId , @PathVariable ("qty") int qty)
	{
		System.out.println(qty+" "+prodId+" "+ ordId);
		return cartDao.updateCart(qty, prodId, ordId);
	}
	
	@GetMapping("/getTotal/{ordId}")
	public int getTotal(@PathVariable ("ordId") int ordId)
	{
		return cartDao.getOrderTotal(ordId);
	}

}
