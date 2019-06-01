package packages.delivery;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
public class PizzaDeserializer implements Deserializer{

	@Override
	public void close() {
	
		
	}

	@Override
	public void configure(Map arg0, boolean arg1) 
	{
		
		
	}

	@Override
	public Object deserialize(String arg0, byte[] arg1) 
	{
		 ObjectMapper mapper = new ObjectMapper();
		    Pizza current = null;
		    try {
		      current = mapper.readValue(arg1, Pizza.class);
		    } catch (Exception e) {

		      e.printStackTrace();
		    }
		    return current;
		  
	}

	
}
