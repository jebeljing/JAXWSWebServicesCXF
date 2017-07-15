package com.pluralsight.cxfdemo.orders;

import com.pluralsight.schema.order.AccountType;
import com.pluralsight.schema.order.ObjectFactory;
import com.pluralsight.schema.order.OrderInquiryResponseType;
import com.pluralsight.schema.order.OrderInquiryType;
import com.pluralsight.service.orders.Orders;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;

/**
 * Created by jingshanyin on 7/10/17.
 */
@WebService(portName = "OrdersSOAP", serviceName="Orders",
endpointInterface = "com.pluralsight.service.orders.Orders",
targetNamespace = "http://www.pluralsight.com/service/Orders/")
public class DefaultOrdersEndpoint implements Orders {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderInquiryResponseType processOrderPlacement(OrderInquiryType orderInquiry) {
//        ObjectFactory factory = new ObjectFactory();
//        OrderInquiryResponseType response = factory.createOrderInquiryResponseType();
//        AccountType account = factory.createAccountType();
//        account.setAccountId(1);
//        response.setAccount(account);
//        return response;
        OrderInquiryResponseType response = orderService.processOrder(orderInquiry.getUniqueOrderId(),
                orderInquiry.getOrderQuantity(), orderInquiry.getAccountId(), orderInquiry.getEan13());
        return response;
    }
}
