package concertbooking;

import concertbooking.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class MypageViewHandler {


    @Autowired
    private MypageRepository mypageRepository;

    // 예매 했을 때
    @StreamListener(KafkaProcessor.INPUT)
    public void whenbooked_then_CREATE(@Payload Booked booked) {
        try {

            if (!booked.validate()) return;

            // view 객체 생성
            Mypage mypage = new Mypage();
            // view 객체에 이벤트의 Value 를 set 함
            mypage.setBookingId(booked.getBookingId());
            mypage.setCcId(booked.getCcId());
            mypage.setCcName(booked.getCcName());
            mypage.setQty(booked.getQty());
            mypage.setStatus(booked.getBookingStatus());
            // view 레파지 토리에 save
            mypageRepository.save(mypage);
        
        }catch (Exception e){
            e.printStackTrace();
        }
    }    

    // 예매 취소 했을 때
    @StreamListener(KafkaProcessor.INPUT)
    public void whenbookingCancelled_then_UPDATE(@Payload BookingCancelled bookingCancelled) {
        try {

            if (!bookingCancelled.validate()) return;
//Optional<Concert> concert = concertRepository.findById(bookingCancelled.getCcId()); 
            Optional<Mypage> mypageOptional = mypageRepository.findById(bookingCancelled.getBookingId());
            if( mypageOptional.isPresent()) {
                Mypage mypage = mypageOptional.get();
                
                mypage.setStatus(bookingCancelled.getBookingStatus());
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
            }
        
        }catch (Exception e){
            e.printStackTrace();
        }
    }    
    



}