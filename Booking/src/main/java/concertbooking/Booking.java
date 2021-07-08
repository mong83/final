package concertbooking;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

import concertbooking.external.Payment;

@Entity
@Table(name="Booking_table")
public class Booking {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long bookingId;
    private Long ccId;
    private String ccName;
    private Integer qty;
    private Long customerId;
    private String bookingStatus;

    @PostPersist
    public void onPostPersist() throws Exception{
        
         /*
       boolean rslt = BookingApplication.applicationContext.getBean(concertbooking.external.ConcertService.class)
            .checkAndBookStock(this.getCcId(), this.getQty());

              if (rslt) {
                Booked booked = new Booked();
                BeanUtils.copyProperties(this, booked);
                booked.publishAfterCommit();
            }  
            else{
                throw new Exception("Out of Stock Exception Raised.");
            }      

       */
    
        Payment payment = new Payment();
        payment.setBookingId(this.bookingId);
        payment.setBookingStatus("booking ok");
        payment.setCcId(this.ccId);
        payment.setCcName(this.ccName);
        
        BookingApplication.applicationContext.getBean(concertbooking.external.PaymentService.class)
            .pay(payment);    

                Booked booked = new Booked();
                BeanUtils.copyProperties(this, booked);
                booked.publishAfterCommit();
        

    }
    
    @PostUpdate
    public void onPostUpdate(){

        BookingCancelled bookingCancelled = new BookingCancelled();
        BeanUtils.copyProperties(this, bookingCancelled);
        bookingCancelled.publishAfterCommit();

    }
    
    
    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
    public Long getCcId() {
        return ccId;
    }

    public void setCcId(Long ccId) {
        this.ccId = ccId;
    }
    public String getCcName() {
        return ccName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName;
    }
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

}
