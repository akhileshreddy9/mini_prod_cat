

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCClass
{
	Connection con;
	Statement st;

	JDBCClass()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dec2016","root","password");
		}
		catch (Exception e) {

			e.printStackTrace();
		}
	}


	public void addProductCatagory(String categoryName) 
	{

		try {
			if(con!=null)
			{
				PreparedStatement ps1 = con.prepareStatement("select c_name from product_category where c_name = ?");
				ps1.setString(1, categoryName);
				ResultSet rs = ps1.executeQuery();
				if (rs.next())
				{
					System.out.println("The entered catagory already exists");
				}
				else
				{
					PreparedStatement ps = con.prepareStatement("Insert into product_category(c_name) values (?)");
					ps.setString(1, categoryName);	
					int rowsInserted = ps.executeUpdate();
					if (rowsInserted > 0) 
					{
						System.out.println("A new catagory was inserted successfully!");
					}
				}
			} 
		}

		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}


	public void addProduct() 
	{
		Scanner sc = new Scanner(System.in);
		categoryList();
		System.out.println("(1) Select a category  from above list");
		System.out.println("(2) Create your own category");
		int value = sc.nextInt();
		if(value == 2)
		{
			System.out.println("Please enter the category you want to create");
			String categoryName = sc.next();
			addProductCatagory(categoryName);
			addProduct();
		}
		else if (value==1)
		{
	
			try {
				System.out.println("Please enter the Category ID from the above list");
				int id = sc.nextInt();
				System.out.println("Please enter the product name");
				String productName = sc.next();
				System.out.println("Please enter the product description");
				String productDesc = sc.next();
				System.out.println("Please enter the product price");
				Double productPrice = sc.nextDouble();
				PreparedStatement ps;
				ps = con.prepareStatement("Insert into product(p_name, p_desc,p_price,c_id) values (?,?,?,?)");
				ps.setString(1, productName);	
				ps.setString(2, productDesc);	
				ps.setDouble(3, productPrice);	
				ps.setInt(4, id);	
				int rowsInserted = ps.executeUpdate();
				if (rowsInserted > 0) 
				{
					System.out.println("A new product was inserted successfully!");
				}
		
			}
			catch (SQLException e) 
			{
				System.out.println("Error: "+e.getMessage());
			}		
		}
	}


	public void viewProductDescription()
	{
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the product name");
			String productName = sc.next();
			PreparedStatement ps2 = con.prepareStatement("select * from product where p_name = ? ");
			ps2.setString(1, productName);
			ResultSet rs;
			rs = ps2.executeQuery();
			System.out.println( "ID  Product Name Product Description Price Category ID   ");
			while(rs.next()) 
	         {
				System.out.println( rs.getInt(1)+"       "+ rs.getString(2)+"       "+ rs.getString(3)+"      "+ rs.getDouble(4)+"    "+ rs.getInt(5));   
	         }
		} 
		catch (SQLException e) 
		{
			System.out.println("Error: "+e.getMessage());
		}
	}


	public void categoryList() 
	{
		try {
				PreparedStatement ps2 = con.prepareStatement("select * from product_category ");
				ResultSet rs;
				rs = ps2.executeQuery();
				System.out.println( "ID  Category Name     ");
				while(rs.next()) 
				{
					System.out.println( rs.getInt(1)+"    "+ rs.getString(2));  
				}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
         
	}

	public void productOfParticularCategory()
	{
		try {
				Scanner sc = new Scanner(System.in);
				categoryList();
				System.out.println("Enter the Category id");
				int category_id= sc.nextInt();
				PreparedStatement ps2 = con.prepareStatement("select * from product where c_id = ? ");
				ps2.setInt(1, category_id);
				ResultSet rs;
				rs = ps2.executeQuery();
				System.out.println( "ID  Product Name Product Description Price Category ID   ");
				while(rs.next()) 
				{
	             System.out.println( rs.getInt(1)+"       "+ rs.getString(2)+"       "+ rs.getString(3)+"      "+ rs.getDouble(4)+"    "+ rs.getInt(5));   
				}
		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}

	}

	public void avgNumberOfProducts() 
	{


	}


	public void displayLargestProductDesc() 
	{


	}


	public void delCategory() 
	{
		try {
				Scanner sc = new Scanner(System.in);
				categoryList();
				System.out.println("Enter the Category id you want to delete");
				int category_id= sc.nextInt();
				PreparedStatement ps2 = con.prepareStatement("Delete from product_category where c_id = ? ");
				ps2.setInt(1, category_id);
				int categorydel = ps2.executeUpdate();
				if(categorydel>0)
				{
					System.out.println("The Category has been succesfully deleted");
				}
				else
				{
					System.out.println("You entered invalid category id");
				}
			}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}


	public void delProduct() 
	{
		try
		{
			System.out.println("Enter the product name you want to delete");
			Scanner sc = new Scanner(System.in);
			String del_prod= sc.next();
			PreparedStatement ps2 = con.prepareStatement("Delete from product where p_name = ? ");
			ps2.setString(1, del_prod);
			int del_prod1 = ps2.executeUpdate();
			if(del_prod1>0)
			{
				System.out.println("The product has been succesfully deleted");
			}
			else
			{
				System.out.println("You entered invalid product name");
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}


	public void removeProductFromCategory() 
	{
		try
		{
			System.out.println("Enter the product name you want to delete");
			Scanner sc = new Scanner(System.in);
			String rem_prod= sc.next();
			PreparedStatement ps2 = con.prepareStatement("delete from product where p_name = ? ");
			ps2.setString(1, rem_prod);
			int rem_prod1 = ps2.executeUpdate();
			if(rem_prod1>0)
			{
				System.out.println("The product has been succesfully deleted");
			}
			else
			{
				System.out.println("You entered invalid product name");
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

	}


	public void mostRecentProducts()
	{


	}

}
