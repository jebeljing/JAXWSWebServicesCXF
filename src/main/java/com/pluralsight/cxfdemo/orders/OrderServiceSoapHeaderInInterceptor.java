package com.pluralsight.cxfdemo.orders;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;

/**
 * Created by jingshanyin on 7/15/17.
 */
public class OrderServiceSoapHeaderInInterceptor extends AbstractSoapInterceptor {

    public OrderServiceSoapHeaderInInterceptor() {
        super(Phase.USER_PROTOCOL);

    }

    @Override
    public void handleMessage(SoapMessage soapMessage) throws Fault {
        QName qname = new QName("http://www.pluralsight.com/service/Orders/", "apikey");
        SoapHeader header = (SoapHeader) soapMessage.getHeader(qname);
        String apikey = ((Element)header.getObject()).getTextContent();
        System.err.println("API Key = " + apikey);
    }
}
