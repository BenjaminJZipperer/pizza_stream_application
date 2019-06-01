package packages.delivery;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * https://dzone.com/articles/java-thread-tutorial-creating-threads-and-multithr
 * @author CC-Student
 *
 */
public class PizzaConsumer {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ConsumerThread("PIZZA_ALTDORF");
	   
	try{
	     Thread.sleep(10000);
	} catch (InterruptedException e) {
	      System.out.println("Unterbrechung Main thread");
	}
	      System.out.println("Abschluss Main thread");
	 }
	
	

}
class ConsumerThread implements Runnable {
	// Consumer Eigenschaften
	String name;
	Thread t;
	KafkaConsumer <String, Pizza> kafkaconsumer = null; // Pzza is "value"
	
	// Konstruktor
    ConsumerThread (String threadname){
    name = threadname; 
    t = new Thread(this, name);
    System.out.println("Neuer Thread: " + t);
    t.start();
    }
    
    // Run Methode
    public void run() {
    	try {
    			//test();
    			// Testmethode
    			antwort();
    	}
        catch (InterruptedException e) {
    		System.out.println(name + "Interrupted");
    	}
    	System.out.println(name + " exiting.");
	}
    private void antwort() throws InterruptedException
    {
    	// Erzeuge eine Instanz eines Test-Pizzaobjekts und liefere Ausgabe
    	Pizza pizza1 = new Pizza("Pizza Napoli",new java.util.Date(),"Ringstrasse,12,71155,Altdorf");
    	// Lade Properties zur Laufzeit antwort():
    	Properties props = new Properties();
    	
    	//many => props.put("bootstrap.servers", "broker1:9092,broker2:9092");
    	props.put("bootstrap.servers", "broker:9092"); // ...no resolvable bootstrap serves => start server!!!
    	// only one
    	
    	props.put("group.id", "PizzaCounty");
    	props.put("key.deserializer",
    	    "packages.delivery.UserDeserializer");
    	props.put("value.deserializer",
    	    "org.apache.kafka.common.serialization.StringDeserializer");
    	
    	//pizza1.basicTest();
    	
    	// specific test
    	pizza1.showAll();
    	// if tests success:
    	kafkaconsumer =
    		    new KafkaConsumer<String, Pizza>(props);
        if(kafkaconsumer != null) System.out.println(
        "======================================\n\t\tKAFKA - CONSUMENT "
        + "ist initialisiert!\n======================================");
    
        /* TOPIC "pizzas" muss angelegt sein */
        kafkaconsumer.subscribe(Collections.singletonList("pizzas")); 
        System.out.println("Consument "+name+" hat sich angemeldet für das topic "+"pizzas.");
    }
	private void test() throws InterruptedException
	{
		for(int i = 5; i > 0; i--) {
    		System.out.println(name + ": " + i);
    		Thread.sleep(1000);}
	}
}
