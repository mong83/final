package concertbooking;

import concertbooking.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired DeliveryRepository deliveryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverStatusChanged_DeliveryStart(@Payload StatusChanged statusChanged){

        if(!statusChanged.validate()) return;

        System.out.println("\n\n##### listener DeliveryStart : " + statusChanged.toJson() + "\n\n");

        // Sample Logic //
        Delivery delivery = new Delivery();
        deliveryRepository.save(delivery);
            
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverStatusChanged_DeliveryCancel(@Payload StatusChanged statusChanged){

        if(!statusChanged.validate()) return;

        System.out.println("\n\n##### listener DeliveryCancel : " + statusChanged.toJson() + "\n\n");

        // Sample Logic //
        Delivery delivery = new Delivery();
        deliveryRepository.save(delivery);
            
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
