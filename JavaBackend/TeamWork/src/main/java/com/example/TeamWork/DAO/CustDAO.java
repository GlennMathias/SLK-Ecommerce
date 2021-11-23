package com.example.TeamWork.DAO;

import org.springframework.stereotype.Repository;

import com.example.TeamWork.model.Customer;

@Repository
public interface CustDAO {
	
	public String getName(int id);
	public boolean updatePassword(String oldpassword,String newpassword,int id);
	public boolean signup(Customer c);
	int loginValidation(String username, String password);
	boolean updateprofile(Customer c);
	Customer getProfile(int userId);

}
