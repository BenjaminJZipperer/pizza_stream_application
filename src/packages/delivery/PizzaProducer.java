package packages.delivery;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class PizzaProducer {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ProducerThread("pizzas",false);
	   
	try{
	     Thread.sleep(100000);
	} catch (InterruptedException e) {
	      System.out.println("Unterbrechung Main thread");
	}
	      System.out.println("Abschluss Main thread");
	 }
	
	

}
class ProducerThread implements Runnable {
	  private final String topic;
	    private final Boolean isAsync;
	String name ="CLIENT-ID 007";
	Thread t;
	Producer <String, Pizza> drain= null; // Pzza is "value"
	
	
	public static final String KAFKA_SERVER_URL = "localhost";
    public static final int KAFKA_SERVER_PORT = 9092;
    public static final String CLIENT_ID = "SampleProducer"; 
    public static Properties props = new Properties();
    
    ProducerThread (String topic, Boolean isAsync){
    
    t = new Thread(this, name);
    System.out.println("Neuer Thread: " + t);
    t.start();

    props.put("bootstrap.servers", KAFKA_SERVER_URL + ":" + KAFKA_SERVER_PORT);
    props.put("client.id", CLIENT_ID);
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    //https://blog.knoldus.com/kafka-sending-object-as-a-message/
    props.put("value.serializer", "packages.delivery.PizzaSerializer");
    drain = new KafkaProducer<String,Pizza>(props); // Producer = new KafkaProducer stimmt
    /*https://kafka.apache.org/0100/javadoc/index.html?org/apache/kafka/clients/producer/KafkaProducer.html*/
    
    this.topic = topic;
    this.isAsync = isAsync;
    
    }
    
    // Run Methode
    public void run() {
    	try {
    			//test();
    			// Testmethode
    			antwort();
    	}
        catch (InterruptedException e) 
    	{
    		System.out.println(name + "Interrupted");
    	}
    	System.out.println(name + " exiting.");
	}
    private void antwort() throws InterruptedException
    {
    	System.out.println("working on producer");
    	   
    	 // Erzeuge eine Instanz eines Test-Pizzaobjekts und liefere Ausgabe
    		Pizza pizzafrisch = new Pizza("Pizza Speciale",new java.util.Date(),"Ringstrasse,12,71155,Altdorf");
    		// Lade Properties zur Laufzeit antwort():
    		pizzafrisch.showAll();
    		
    		
    		 int messageNo = 1;
    	        while (messageNo < 7) {
    	            String messageStr = "Message_" + messageNo;
    	            long startTime = System.currentTimeMillis();
    	            if (true) { // Send asynchronously
    	            	 drain.send(new ProducerRecord<String, Pizza>("pizzas", pizzafrisch));
    	            	 
    	            	 /**
    	            	  * You have to use ProducerRecord 
    	            	  * (instead of KeyedMessage) with constructor ProducerRecord
    	            	  * (String topic, K key, V value)
						Producer<String, String> producer = new KafkaProducer<>(props);
						producer.send(new ProducerRecord<String, String>("my-topic", "key", "value"));
    	            	  */
    	            	   System.out.println("Message sent !!");
    	            } 
    	            else 
    	            { // Send synchronously
    	               /* try {
    	                drain.send(new ProducerRecord<>("pizzas",
    	                            messageNo,
    	                            messageStr)).get();
    	                    System.out.println("Sent message: (" + messageNo + ", " + messageStr + ")");
    	                } 
    	                catch (Exception  e) {
    	                    e.printStackTrace();
    	                    // handle the exception
    	                }*/
    	            }
    	            ++messageNo;
    	        }
    }
	private void test() throws InterruptedException
	{
		for(int i = 5; i > 0; i--) {
    		System.out.println(name + ": " + i);
    		Thread.sleep(1000);}
	}
}
    
