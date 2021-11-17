package com.example.TeamWork.DAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.TeamWork.DAO.ProductDAO;
import com.example.TeamWork.Util.DBUtil;
import com.example.TeamWork.model.Product;


@Repository
public class ProductDAOImpl implements ProductDAO {
	
	static List<Product> prodList = new ArrayList<Product>(); 
	
	Connection connection;

	public ProductDAOImpl() {
		connection = DBUtil.getConnection();
		System.out.println("connection " + connection);
	}

	@Override
	public List<Product> getAllProducts() {
		
		prodList.clear();
		
		
		String viewProdsQuerry = "select * from product";
		
		Statement stmt;
		try {
			stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(viewProdsQuerry);
			while (rs.next()) {
				Product prod = new Product();
				System.out.println("Inside while");
				prod.setProId(rs.getInt(1));
				prod.setProCatId(rs.getInt(2));				
				prod.setProName(rs.getString(3));
				prod.setProDes(rs.getString(4));
				prod.setProPrice(rs.getInt(5));
				prod.setProdImg(rs.getString(6));
				
				prodList.add(prod);
				System.out.println(prodList);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return prodList;
	}

	@Override
	public void addProduct(Product p) {
		// TODO Auto-generated method stub
		
String addProdQuerry = "insert into  product values ("+p.getProId()+","+p.getProCatId()+",'"+p.getProName()+"','"+p.getProDes()+"',"+p.getProPrice()+",'"+p.getProdImg()+"');";
		
		Statement stmt;
		try {
			stmt=connection.createStatement();
			stmt.executeUpdate(addProdQuerry);
			
			}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} 
	
	@Override
	public List<Product> getProdFromCat(int catId)
	{
		String getProdQuerry="SELECT * FROM product where ProCatId="+catId;
		List prodList= new ArrayList<Product>();
		
		
		Statement stmt;
		try {
			stmt=connection.createStatement();
			
			
			ResultSet rs= stmt.executeQuery(getProdQuerry);
			
			while(rs.next())
			{
				Product p = new Product();
				p.setProId(rs.getInt(1));
				p.setProCatId(rs.getInt(2));
				p.setProName(rs.getString(3));
				p.setProDes(rs.getString(4));
				p.setProPrice(rs.getInt(5));
				p.setProdImg(rs.getString(6));
				System.out.println(p);
				prodList.add(p);
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return prodList;
		
	}

	@Override
	public Product getProduct(int prodId) {
		// TODO Auto-generated method stub
		String getProdQuerry="SELECT * FROM product where ProId="+prodId;
		Product p = new Product();
		
		Statement stmt;
		try {
			stmt=connection.createStatement();
			
			
			ResultSet rs= stmt.executeQuery(getProdQuerry);
			
			while(rs.next())
			{
				p.setProId(rs.getInt(1));
				p.setProCatId(rs.getInt(2));
				p.setProName(rs.getString(3));
				p.setProDes(rs.getString(4));
				p.setProPrice(rs.getInt(5));
				p.setProdImg(rs.getString(6));
				System.out.println(p);
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return p;
	}
	
	
	
	
	
	
		
		
	}

//ProId, ProCatId, ProName, ProDes, ProPrice, ProdImg

