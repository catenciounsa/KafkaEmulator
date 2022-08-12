package health.kafkaservice.serializer;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import health.entities.RegisterMeasured;

public class RegisterSerializer implements Serializer<RegisterMeasured>{
	
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public byte[] serialize(String topic, RegisterMeasured registerMeasured) {
		try {
			if( registerMeasured == null ) {
				System.err.println("Null received at serializing");
				return null;
			}
			return objectMapper.writeValueAsBytes(registerMeasured);
		}catch(Exception e) {
			System.err.println("Error trying to serializing");
		}
		return null;
	}
}
