
package concertbooking;

public class DeliveryCanceled extends AbstractEvent {

    private Long deliveryId;
    private String deliveryStatus;
    private Long customerId;
    private String customerName;
    private Integer qty;
    private Long bookingId;
    private String customerAdd;

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }
    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
    public String getCustomerAdd() {
        return customerAdd;
    }

    public void setCustomerAdd(String customerAdd) {
        this.customerAdd = customerAdd;
    }
}

