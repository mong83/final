package concertbooking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


 @RestController
 public class PaymentController {

    @Autowired
    PaymentRepository paymentRepository;

    @RequestMapping(value = "/",    method = RequestMethod.POST, produces = "application/json;charset=UTF-8")

    public void pay(HttpServletRequest request, HttpServletResponse response)
    throws Exception {
    System.out.println("##### /payment/pay  called #####");

    Long ccId = Long.valueOf(request.getParameter("ccId"));
    Long bookingId = Long.valueOf(request.getParameter("bookingId"));

    System.out.println("##### ccid #####" + ccId + "##### bookingId" + bookingId);
    Optional<Concert> concert = concertRepository.findById(ccId);


    }   

 }
