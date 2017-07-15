package com.pluralsight.cxfdemo.orders;

import com.pluralsight.schema.order.OrderInquiryResponseType;

/**
 * Created by jingshanyin on 7/13/17.
 */
public interface OrderService {

    OrderInquiryResponseType processOrder(int uniqueOrderId, int orderQuantity, int accountId, long ean13);
}
