package com.example.TeamWork.DAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.TeamWork.DAO.CartDAO;
import com.example.TeamWork.Util.DBUtil;
import com.example.TeamWork.model.Cart;


@Repository
public class CartDAOImpl implements CartDAO{
	
	Connection connection;
	
	ProductDAOImpl prodDaoImpl = new ProductDAOImpl();
//	int ordId=0;

	public CartDAOImpl() {
		connection = DBUtil.getConnection();
		System.out.println("connection " + connection);
		
		System.out.println("In constructor");
	}
	
	public int createOrder(int CustId)
	{
		String getordIdQuery ="select max(OrdId) from orders;";
		int id = 0;
		
		
		try {
			Statement stmt=connection.createStatement();
			ResultSet rs = stmt.executeQuery(getordIdQuery);
			System.out.println("Try block running");
			
			while (rs.next()) {
				id=rs.getInt(1);
				
			}
			id++;
			System.out.println(id);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			id=0;
			System.out.println(id);
		}
		
		
		String orderDate="1";
		int total =0;
		String insertOrderQuerry = "insert into orders values ("+id+",'"+orderDate+"',"+CustId+","+total+");";
		
		System.out.println(insertOrderQuerry);
		Statement stmt;
		try {
			stmt=connection.createStatement();
			stmt.executeUpdate(insertOrderQuerry);
			
			}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
//		this.ordId=id;	
		
		
//		this.ordId=id;
		
		return id;
		
	}
	
	public void addToCart(int ordId,int prodId,int qty)
	{
			
		String insertOrderDetailsQuerry = "insert into orddetails values ("+ordId+",'"+prodId+"',"+qty+","+"(select ProPrice from product where ProId="+prodId+")*"+qty+");";
		
		
		//String insertOrderQuerry = "insert into orders values ("+ordId+",'"+orderDate+"',"+custId+","+total+");";
		
		System.out.println(insertOrderDetailsQuerry);
		Statement stmt;
		try {
			stmt=connection.createStatement();
			stmt.executeUpdate(insertOrderDetailsQuerry);
			
			}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
String updateOrdersQuerry = "update orders set total=(select sum(price) from orddetails where ordId="+ordId+") where ordId="+ordId+";";
		
		
		//String insertOrderQuerry = "insert into orders values ("+ordId+",'"+orderDate+"',"+custId+","+total+");";
		
		System.out.println(updateOrdersQuerry);
		try {
			stmt=connection.createStatement();
			stmt.executeUpdate(updateOrdersQuerry);
			
			}
		catch (SQLException e) {
			// TODO Auto-generated catch block

			System.out.println(e);
		}
		
		
		
		
	}
	
	
	public void payOut()
	{
		String dropCartQuerry="delete from orddetails";
		
		Statement stmt;
		try {
			stmt=connection.createStatement();
			stmt.executeUpdate(dropCartQuerry);
			}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public List<Cart> viewCart(int ordId) {
		
		String getordIdQuery ="select * from orddetails where OrdId ="+ordId+";";
		List <Cart> cartList = new ArrayList<Cart>();
		
		
		try {
			Statement stmt=connection.createStatement();
			ResultSet rs = stmt.executeQuery(getordIdQuery);
			
			while (rs.next()) {
				Cart cart = new Cart();
				
				cart.setOrdid(rs.getInt(1));
				cart.setProdid((rs.getInt(2)));
				cart.setQty(rs.getInt(3));
				cart.setPrice(rs.getInt(4));
				cart.setProd(prodDaoImpl.getProduct( rs.getInt(2) ));
				cartList.add(cart);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		return cartList;
	}
	
	
	@Override
	public void removeFromCart(int ProdId, int ordId) {
String removeOrderDetailsQuerry = "delete from orddetails where ProId= "+ProdId+" and ordId="+ordId+";";
		
		
		//String insertOrderQuerry = "insert into orders values ("+ordId+",'"+orderDate+"',"+custId+","+total+");";
		
		System.out.println(removeOrderDetailsQuerry);
		Statement stmt;
		try {
			stmt=connection.createStatement();
			stmt.executeUpdate(removeOrderDetailsQuerry);
			
			}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
String updateOrdersQuerry = "update orders set total=(select sum(price) from orddetails where ordId="+ordId+") where ordId="+ordId+";";
		
		
		//String insertOrderQuerry = "insert into orders values ("+ordId+",'"+orderDate+"',"+custId+","+total+");";
		
		System.out.println(updateOrdersQuerry);
		try {
			stmt=connection.createStatement();
			stmt.executeUpdate(updateOrdersQuerry);
			
			}
		catch (SQLException e) {
			// TODO Auto-generated catch block

			System.out.println(e);
		}
		
	}
	
	@Override
	public void updateCart(int qty, int proId, int ordId)
	{
		String updateOrderDetailsQuerry = "update orddetails set Qty="+qty+", price= "+qty+"*(select ProPrice from product where ProId="+proId+") where ordId = "+ordId+" and proId = "+proId+" ;";
		
		
		//String insertOrderQuerry = "insert into orders values ("+ordId+",'"+orderDate+"',"+custId+","+total+");";
		
		System.out.println(updateOrderDetailsQuerry);
		Statement stmt;
		try {
			stmt=connection.createStatement();
			stmt.executeUpdate(updateOrderDetailsQuerry);
			
			}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public int getOrderTotal(int ordId)
	{
		String getTotalQuery ="select total from orders where OrdId ="+ordId+";";
		int total=0;
		
		try {
			Statement stmt=connection.createStatement();
			ResultSet rs = stmt.executeQuery(getTotalQuery);
			
			while (rs.next()) {
				total=rs.getInt(1);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		return total;
	}

}
