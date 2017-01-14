import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSideCore {

	int productId;
	String productname;
	String productDescription;
	double productPrice;
	int categoryId;
	String categoryName;
	String message;
	
	public ClientSideCore (String categoryName){
		this.productId = 0;
		this.productname = "";
		this.productDescription = "";
		this.productPrice = 1;
		this.categoryId = 1;
		this.categoryName = categoryName;
		this.message = "CreateCategory";
	}

	public ClientSideCore(int productId, String productname, String productDescription, double productPrice,
			int categoryId, String categoryName, String message) {
		this.productId = productId;
		this.productname = productname;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.message = message;
	}
	
	
	public Object Send(){
		Product inputObject = new Product();
		try{
			Product p = new Product();
			p.setProductId(productId);
			p.setProductname(productname);
			p.setProductDescription(productDescription);
			p.setProductPrice(productPrice);
			p.setCategoryId(categoryId);
			p.setCategoryName(categoryName);
			p.setMessage(message);
			
			Socket socketToServer = new Socket("192.168.1.11",3000);
			
			ObjectOutputStream outputStream = new ObjectOutputStream(socketToServer.getOutputStream());
			ObjectInputStream inputStream = new ObjectInputStream(socketToServer.getInputStream());
			
			outputStream.writeObject(p);
			
			inputObject = (Product) inputStream.readObject();
			
			outputStream.close();
			socketToServer.close();
			inputStream.close();
			
			
		}catch(Exception e){
			System.out.println(e);
		}
		
		return inputObject;
		
	}
}
