package concertbooking;

import concertbooking.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


@Service
public class PolicyHandler{

    @Autowired PaymentRepository paymentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverBookingCancelled_CancelPayment(@Payload BookingCancelled bookingCancelled){

        if(!bookingCancelled.validate()) return;

        System.out.println("\n\n##### listener CancelPayment : " + bookingCancelled.toJson() + "\n\n");

        Payment payment = paymentRepository.findByBookingId(bookingCancelled.getBookingId()); 

        
        payment.setBookingStatus("payment cancle");
        paymentRepository.save(payment);
        System.out.println("\n\n##### listener CancelPayment22: " + payment.getBookingId() + " " +payment.getPayId() + " "  + "\n\n");
            
    }


   
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
