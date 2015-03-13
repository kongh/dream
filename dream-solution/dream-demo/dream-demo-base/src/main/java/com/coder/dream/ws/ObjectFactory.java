
package com.coder.dream.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.coder.dream.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _NotifyServiceConsume_QNAME = new QName("http://ws.dream.coder.com/", "notifyServiceConsume");
    private final static QName _NotifyServiceConsumeResponse_QNAME = new QName("http://ws.dream.coder.com/", "notifyServiceConsumeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.coder.dream.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NotifyServiceConsume }
     * 
     */
    public NotifyServiceConsume createNotifyServiceConsume() {
        return new NotifyServiceConsume();
    }

    /**
     * Create an instance of {@link NotifyServiceConsumeResponse }
     * 
     */
    public NotifyServiceConsumeResponse createNotifyServiceConsumeResponse() {
        return new NotifyServiceConsumeResponse();
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link ServiceConsumeNotifyReq }
     * 
     */
    public ServiceConsumeNotifyReq createServiceConsumeNotifyReq() {
        return new ServiceConsumeNotifyReq();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotifyServiceConsume }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dream.coder.com/", name = "notifyServiceConsume")
    public JAXBElement<NotifyServiceConsume> createNotifyServiceConsume(NotifyServiceConsume value) {
        return new JAXBElement<NotifyServiceConsume>(_NotifyServiceConsume_QNAME, NotifyServiceConsume.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotifyServiceConsumeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dream.coder.com/", name = "notifyServiceConsumeResponse")
    public JAXBElement<NotifyServiceConsumeResponse> createNotifyServiceConsumeResponse(NotifyServiceConsumeResponse value) {
        return new JAXBElement<NotifyServiceConsumeResponse>(_NotifyServiceConsumeResponse_QNAME, NotifyServiceConsumeResponse.class, null, value);
    }

}
