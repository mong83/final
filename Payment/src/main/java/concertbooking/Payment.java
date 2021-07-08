package concertbooking;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;


@Entity
@Table(name="Payment_table")
public class Payment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long payId;
    private Long bookingId;
    private Long ccId;
    private String ccName;
    private String bookingStatus;

    @PrePersist
    public void onPrePersist(){
        this.setBookingStatus("payment ok");
    }

    @PostPersist
    public void onPostPersist(){
        
        StatusChanged statusChanged = new StatusChanged();         
        BeanUtils.copyProperties(this, statusChanged);
        statusChanged.publishAfterCommit();

    }
    
    @PostUpdate
    public void onPostUpdate(){

        StatusChanged statusChanged = new StatusChanged();         
        BeanUtils.copyProperties(this, statusChanged);
        statusChanged.publishAfterCommit();
    }
   

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
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
    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }




}
