package com.robomq.jdbc;

import java.sql.*;
import java.util.Scanner;

public class CustomerDatabase {
	Connection con;
	PreparedStatement pre;
	ResultSet res; 
	Statement stat;
	int id;
	String fName;
	String lName;
	String address;
	String email;
	Scanner sc;
	
	public CustomerDatabase() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root", "Deepanshu@21");
			System.out.println("Connected....");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void insert() {
		try {
			sc = new Scanner(System.in);
			System.out.println("Enter Customer id:");
			id = sc.nextInt();
			System.out.println("Enter Customer FistName");
			fName = sc.next();
			System.out.println("Enter Customer lastName");
			lName = sc.next();
			System.out.println("Enter Customer address");
			address = sc.next();
			System.out.println("Enter Customer email");
			email = sc.next();
			
			pre=con.prepareStatement("insert into customer values(?,?,?,?,?)");
			pre.setInt(1, id);
			pre.setString(2,fName);
			pre.setString(3, lName);
			pre.setString(4, address);
			pre.setString(5, email);
			
			int ra=pre.executeUpdate();
			if(ra>0)
				System.out.println("Student Details Committed..");
			else
				System.out.println("Student Details are Not Committed..");
			pre.close();
			
			
			
		}catch(Exception e){e.printStackTrace();}
		
	}
	
	public void delete()
	{
		try
		{
			pre=con.prepareStatement("delete from customer where id=?");
			System.out.println("Enter the id of the customer to be deleted");
			int x = sc.nextInt();
			pre.setInt(1,x);
			
			int ra=pre.executeUpdate();
			if(ra>0)
				System.out.println("Record Deleted...");
			else
				System.out.println("Record has not Deleted...");
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void update()
	{
		try
		{ 
			sc = new Scanner(System.in);
			System.out.println("Enter id of the customer to be updated");
			id = sc.nextInt();
			System.out.println("Enter Customer updated FistName");
			fName = sc.next();
			System.out.println("Enter Customer updated lastName");
			lName = sc.next();
			System.out.println("Enter Customer updated address");
			address = sc.next();
			System.out.println("Enter Customer updated email");
			email = sc.next();
			
			pre=con.prepareStatement("update customer set fName=?, lName=?, address=?, email=?  where id=?");
			
			pre.setString(1, fName);
			pre.setString(2, lName);
			pre.setString(3, address);
			pre.setString(4, email);
			pre.setInt(5, id);
			
			int ra=pre.executeUpdate();
			if(ra>0)
				System.out.println("Details updated ");
			else
				System.out.println("Details are not updated..");
			pre.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void display()
	{
		try
		{
			stat=con.createStatement();
			res=stat.executeQuery("select * from customer");
			while(res.next())
			{
				System.out.println("id : "+res.getInt("id"));
				System.out.println("fName : "+res.getString("fName"));
				System.out.println("lName : "+res.getString("lName"));
				System.out.println("address : "+res.getString("address"));
				System.out.println("email : "+res.getString("email"));
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		CustomerDatabase c = new CustomerDatabase();
		Scanner sc1 = new Scanner(System.in);
		
		while(true) {
			System.out.println("Welcome to Customer Database System");
			System.out.println("Enter");
			System.out.println("1 - to insert");
			System.out.println("2 - to delete");
			System.out.println("3 - update");
			System.out.println("4 - view table");
			System.out.println("5 - exit");
			
			int choice = sc1.nextInt();
			switch(choice) {
			case 1:
				c.insert();
				break;
			case 2:
				c.delete();
				break;
			case 3:
				c.update();
				break;
			case 4:
				c.display();
				break;
			case 5:
				System.exit(0);
			default:
				System.out.println("invalid choice");
			
			}
			
		
			
		}
		
		}

}
