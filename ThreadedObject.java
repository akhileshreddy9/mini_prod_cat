import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ThreadedObject extends Thread{
	Socket incoming;
	Product p;
	
	private int productId;
	private String productname;
	private String productDescription;
	private double productPrice;
	private int categoryId;
	private String categoryName;
	private String message;
	
	public ThreadedObject(Socket i){
		incoming = i;
	}
	
	public void run(){
		try{
			String url ="127.0.0.1:3306/dec2016"; 
			String user = "root"; //Username
			String password ="password"; //Password
			
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance(); // Driver URL
			}catch(Exception e){
				e.printStackTrace();
			}
			ObjectInputStream in = new ObjectInputStream(incoming.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(incoming.getOutputStream());
			p = (Product) in.readObject();
			
			productId = p.getProductId();
			productname=p.getProductname();
			productDescription = p.getProductDescription();
			productPrice = p.getProductPrice();
			categoryId = p.getCategoryId();
			categoryName = p.getCategoryName();
			message = p.getMessage();
			
			Connection conn;
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/dec2016","root","password");
			
			//Write shit here
			if(message.equals("CreateCategory")){ // Set the message and do the required task
				try {
					if(conn!=null)
					{
						PreparedStatement ps1 = conn.prepareStatement("select c_name from product_category where c_name = ?");
						ps1.setString(1, categoryName);
						ResultSet rs = ps1.executeQuery();
						if (rs.next())
						{
							System.out.println("The entered catagory already exists");
						}
						else
						{
							PreparedStatement ps = conn.prepareStatement("Insert into product_category(c_name) values (?)");
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
			}else if(message.equals("")){
				
			}
			out.writeObject(p);
			out.close();
			conn.close();
			in.close();
			incoming.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
