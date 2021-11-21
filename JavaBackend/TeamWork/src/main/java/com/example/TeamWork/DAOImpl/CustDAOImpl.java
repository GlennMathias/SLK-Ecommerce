package com.example.TeamWork.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;


import com.example.TeamWork.DAO.CustDAO;
import com.example.TeamWork.Util.DBUtil;
import com.example.TeamWork.model.Customer;


@Repository
public class CustDAOImpl implements CustDAO {
	
	Connection connection;

	public CustDAOImpl() {
		connection = DBUtil.getConnection();
		System.out.println("connection " + connection);
	}
	
	@Override
	public String getName(int id) {
		String name=null;
		
		try {
			

			PreparedStatement stmt = connection.prepareStatement("select CustName from customer where CustId="+id+"");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				name=rs.getString(1);
			}
		} catch (Exception e) {
		}
		return name;
		
	}
	
	@Override
	public boolean updatePassword(String oldpassword,String newpassword,int id)
	{
		String password=null;
		String retriveQuerry ="select CustPassword from customer where CustId="+id+"";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(retriveQuerry);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				password=rs.getString(1);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if (password.equals(oldpassword))
		{
			String updateQuerry ="update customer set CustPassword ='"+newpassword+"' where CustId="+id+"";
			try {
				Statement stmt=connection.createStatement();
				stmt.executeUpdate(updateQuerry);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		return true;
	}

	@Override
	public boolean signup(Customer c) {
		// TODO Auto-generated method stub
		
String addCustQuerry = "insert into  customer (CustName, CustUserName, CustPassword, CustPhone, CustEmail, CustAddress ) values "
		+ "('"+c.getCustname()+"','"+c.getCustusername()+"','"+c.getCustpassword()+"',"+c.getCustphone()+",'"+c.getCustemail()+"','"+c.getCustaddress()+"');";
		

System.out.println(addCustQuerry);
		Statement stmt;
		try {
			stmt=connection.createStatement();
			stmt.executeUpdate(addCustQuerry);
			return true;
			
			}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
																																//CustId, CustName, CustUserName, CustPassword, CustPhone, CustEmail, CustAddress 
		return false;
	}
	
	@Override
	public int loginValidation(String username ,String password) {
		// TODO Auto-generated method stub
		
		String custtValidationQuerry = "select CustId,CustName,CustUserName,CustPassword from customer where CustUserName='"+username+"' and Custpassword='"+password+"' ;";
		String CustName = null;
		int custId=-1;
		
		try {
			

			PreparedStatement stmt = connection.prepareStatement(custtValidationQuerry);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				custId =rs.getInt(1);
				CustName = rs.getString(2);
				String CustUserName = rs.getString(3);
				String CustPassword = rs.getString(4);
				System.out.println(CustPassword);	
			}
			if (custId != -1)
			{
				
				return custId;
			}
			else
			{
				return -1;
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	
	@Override
	public boolean updateprofileold(int CustId, String name, String username, int phoneno, String email, String address) 
	{
		
		String updateprofQuery = "update customer set CustName = '"+name+"' , CustUserName='"+username+"' , CustPhone="+phoneno+" , CustEmail='"+email+"' , CustAddress='"+address+"' where CustId="+CustId+";";
		
		
		Statement st;
		
		try {
			st=connection.createStatement();
			st.executeUpdate(updateprofQuery);
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public boolean updateprofile(Customer c) 
	{
		
		Customer customer= getProfile(c.getCustid());
		
		System.out.println("From databse: "+customer);
		System.out.println("From User: "+c);
		if (c.getCustid() == 0)
		{
			return false;
		}
		
		if (c.getCustname() == null || c.getCustname() == "")
		{
			c.setCustname(customer.getCustname());
			
		}
		if(c.getCustusername() == null || c.getCustusername() == "") 
		{
			c.setCustusername(customer.getCustusername());
		}
		if(c.getCustphone() == 0) 
		{
			c.setCustphone(customer.getCustphone());
		}
		if(c.getCustemail() == null || c.getCustemail() == "") 
		{
			c.setCustemail(customer.getCustemail());
		}
		if(c.getCustaddress() == null || c.getCustaddress() == "") 
		{
			c.setCustaddress(customer.getCustaddress());
		}
		
		
		String updateprofQuery = "update customer set CustName = '"+c.getCustname()+"' , CustUserName='"+c.getCustusername()+"' , CustPhone="+c.getCustphone()+" , CustEmail='"+c.getCustemail()+"' , CustAddress='"+c.getCustaddress()+"' where CustId="+c.getCustid()+";";
		System.out.println(updateprofQuery);
		
		Statement st;
		
		//int CustId, String name, String username, int phoneno, String email, String address
		
		try {
			st=connection.createStatement();
			st.executeUpdate(updateprofQuery);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	
	@Override
	public Customer getProfile(int userId)
	{
		String getProfileQuerry="SELECT * FROM customer where CustId = "+userId+";";
		Customer cust = new Customer();
		
		try
		{
			Statement stmt = connection.createStatement();
			
			ResultSet rs =stmt.executeQuery(getProfileQuerry);
			while(rs.next())
			{
				cust.setCustid(rs.getInt(1));
				cust.setCustname(rs.getString(2));
				cust.setCustusername(rs.getString(3));
				cust.setCustpassword(rs.getString(4));
				cust.setCustphone(rs.getInt(5)); 
				cust.setCustemail(rs.getString(6));
				cust.setCustaddress(rs.getString(7));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Customer: "+cust);
		
		return cust;
		
		
		
	}

}
