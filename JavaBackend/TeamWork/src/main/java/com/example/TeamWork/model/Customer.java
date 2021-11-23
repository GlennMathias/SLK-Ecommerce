package com.example.TeamWork.model;

public class Customer {
	
	int custid;
	String custname;
	String custusername;
	String custpassword;
	String custphone;
	String custemail;
	String custaddress;
	
	
	public int getCustid() {
		return custid;
	}
	public void setCustid(int custid) {
		this.custid = custid;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getCustusername() {
		return custusername;
	}
	public void setCustusername(String custusername) {
		this.custusername = custusername;
	}
	public String getCustpassword() {
		return custpassword;
	}
	public void setCustpassword(String custpassword) {
		this.custpassword = custpassword;
	}
	public String getCustphone() {
		return custphone;
	}
	public void setCustphone(String custphone) {
		this.custphone = custphone;
	}
	public String getCustemail() {
		return custemail;
	}
	public void setCustemail(String custemail) {
		this.custemail = custemail;
	}
	public String getCustaddress() {
		return custaddress;
	}
	public void setCustaddress(String custaddress) {
		this.custaddress = custaddress;
	}
	public Customer(int custid, String custname, String custusername, String custpassword, String custphone,
			String custemail, String custaddress) {
		super();
		this.custid = custid;
		this.custname = custname;
		this.custusername = custusername;
		this.custpassword = custpassword;
		this.custphone = custphone;
		this.custemail = custemail;
		this.custaddress = custaddress;
	}
	
	public Customer() {
		super();
	}
	@Override
	public String toString() {
		return "Customer [custid=" + custid + ", custname=" + custname + ", custusername=" + custusername
				+ ", custpassword=" + custpassword + ", custphone=" + custphone + ", custemail=" + custemail
				+ ", custaddress=" + custaddress + "]";
	}
	
	
	
	
	

}
