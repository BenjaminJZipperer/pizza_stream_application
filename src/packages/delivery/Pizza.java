package packages.delivery;
import java.lang.reflect.Field;
import java.util.*;

public class Pizza 
{
	private String pizzaName;
	/*
	 * like "Quadro Formaggi, Speciale, Pizza Napoli etc"
	 */
	
	private Date orderDate;
	
	private String deliveryAddress; // street, streetnumber, city code, city

	public Pizza(String pn,Date od, String da)
	{
		this.pizzaName = pn;
		//this.orderDate = od;
		this.deliveryAddress = da;
	}
	
	public void basicTest() {
		System.out.println("basic test: empty pizza");
		
	}

	public void showAll() {
		  System.out.print("Schwelle A ");
		  StringBuilder result = new StringBuilder();
		  String newLine = System.getProperty("line.separator");

		  result.append( this.getClass().getName() );
		  result.append( " Object {" );
		  result.append(newLine);
		  
		  //determine fields declared in this class only (no fields of superclass)
		  Field[] fields = this.getClass().getDeclaredFields();
		  System.out.print("Schwelle B ");
		  //print field names paired with their values
		  for ( Field field : fields  ) {
		    result.append("  ");
		    try {
		      result.append( field.getName() );
		      result.append(": ");
		      //requires access to private field:
		      result.append( field.get(this) );
		    } catch ( IllegalAccessException ex ) {
		      System.out.println(ex);
		    }
		    result.append(newLine);
		  }
		  result.append("}");

		  System.out.println(result.toString());
		
	}
	
	
	
	
}
