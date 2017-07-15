package com.pluralsight.cxfdemo.orders;

import com.pluralsight.schema.order.ObjectFactory;
import com.pluralsight.schema.order.OrderInquiryResponseType;
import com.pluralsight.schema.order.OrderInquiryType;
import com.pluralsight.service.orders.Orders;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.ws.soap.SOAPFaultException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by jingshanyin on 7/13/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-beans.xml")
public class DefaultOrdersEndpointTest {


    private Orders ordersService;
    private OrderInquiryType orderInquiryType;

    @Autowired
    private JaxWsProxyFactoryBean testOrdersClient;

    @Before
    public void setUp() throws Exception {
        ordersService = testOrdersClient.create(Orders.class);
        ObjectFactory factory = new ObjectFactory();
        orderInquiryType = factory.createOrderInquiryType();
        orderInquiryType.setAccountId(999);
        orderInquiryType.setEan13(1234567890123L);
        orderInquiryType.setOrderQuantity(4);
        orderInquiryType.setUniqueOrderId(12345);


    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testProcessOrderPlacementSuccess() {
//        fail("not yet implemented");
        OrderInquiryResponseType response = ordersService.processOrderPlacement(orderInquiryType);
        assertTrue(response.getAccount().getAccountId() == 999);

    }

    @Test(expected = SOAPFaultException.class)
    public void testProcessOrderPlacementFailureInvalidParameter() {
        ordersService.processOrderPlacement(null);
    }
}
