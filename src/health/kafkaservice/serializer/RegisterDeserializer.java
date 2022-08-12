package health.kafkaservice.serializer;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import health.entities.RegisterMeasured;

public class RegisterDeserializer implements Deserializer<RegisterMeasured> {
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public RegisterMeasured deserialize(String arg0, byte[] registerMeasured) {
		try {
			if( registerMeasured == null) {
				System.err.println("Null received at deserializaing");
				return null;
			}
			return objectMapper.readValue(registerMeasured, RegisterMeasured.class);
		} catch( Exception e ) {
			System.err.println("Error trying to deserializing");
		}
		return null;
	}

}
