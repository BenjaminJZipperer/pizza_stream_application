package packages.delivery;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
public class PizzaSerializer implements Serializer {

	@Override
	public void close() {
		// TODO Auto-generated from Kafka
		
	}

	@Override
	public void configure(Map arg0, boolean arg1) {
		// TODO Auto-generated from Kafka
		
	}

	@Override
	public byte[] serialize(String arg0, Object arg1) {
		// TODO Auto-generated from Kafka
		Pizza input = (Pizza)arg1;
		
		byte[] retVal = null;
	    ObjectMapper objectMapper = new ObjectMapper();
	    try {
	      retVal = objectMapper.writeValueAsString(arg1).getBytes();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return retVal;
		
		

	}

	 

	}