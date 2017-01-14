import java.util.Scanner;
public class EntryPointClass 
{
	public static void main(String[] args) 
	{
		int productId;
		String productname;
		String productDescription;
		double productPrice;
		int categoryId;
		String categoryName;
		String message;
		
		Scanner input = new Scanner(System.in);
		//JDBCClass jdbcClass = new JDBCClass();
		Product p;
		int userOpt;
		do {
			System.out.println("");
			System.out.println("----- List of Options------");
			System.out.println("(1)Add a new Category to the category list. ");
			System.out.println("(2)Add a new product under a specific category. ");
			System.out.println("(3)View specific product's description and other details");
			System.out.println("(4)Listing of categories");
			System.out.println("(5)Listing of all the products of a particular category");
			System.out.println("(6)Display Average number of products among all categories");
			System.out.println("(7)Display the product which has largest description");
			System.out.println("(8)Delete Category");
			System.out.println("(9)Delete Product");
			System.out.println("(10)Remove Product from a category");
			System.out.println("(11)Display most recent 5 products");
			System.out.println("(12)Exit. ");
			System.out.println("Please enter an input of the required task to be performed ");

			userOpt = input.nextInt();  

			switch(userOpt)
			{
			case 1: System.out.println("Please enter the category you want to create");
					categoryName = input.next();
					ClientSideCore newCat = new ClientSideCore(categoryName);
					p = (Product) newCat.Send();
					System.out.println(p.getMessage());
					continue;
			case 2: //jdbcClass.addProduct();
				
					continue;
			case 3: //jdbcClass.viewProductDescription();
					continue;
			case 4: ///jdbcClass.categoryList();
					continue;
			case 5: //jdbcClass.productOfParticularCategory();
					continue;
			case 6: //jdbcClass.avgNumberOfProducts();
					continue;
			case 7: //jdbcClass.displayLargestProductDesc();
					continue;
			case 8: //jdbcClass.delCategory();
					continue;
			case 9: //jdbcClass.delProduct();
					continue;
			case 10: //jdbcClass.removeProductFromCategory();
					continue;
			case 11: //jdbcClass.mostRecentProducts();
					continue;
			case 12: System.out.println("Application Exit!");
				break;
			}

		}while (userOpt != 12);
	}
	
	
 
}
