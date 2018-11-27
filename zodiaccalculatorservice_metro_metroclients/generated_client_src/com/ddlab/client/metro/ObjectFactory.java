
package com.ddlab.client.metro;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ddlab.client.metro package. 
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

    private final static QName _InvalidZodiacSignException_QNAME = new QName("http://server.metro.webservice.ddlab.com/", "InvalidZodiacSignException");
    private final static QName _GetZodiacSign_QNAME = new QName("http://server.metro.webservice.ddlab.com/", "getZodiacSign");
    private final static QName _GetZodiacSignResponse_QNAME = new QName("http://server.metro.webservice.ddlab.com/", "getZodiacSignResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ddlab.client.metro
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InvalidZodiacSignException }
     * 
     */
    public InvalidZodiacSignException createInvalidZodiacSignException() {
        return new InvalidZodiacSignException();
    }

    /**
     * Create an instance of {@link GetZodiacSign }
     * 
     */
    public GetZodiacSign createGetZodiacSign() {
        return new GetZodiacSign();
    }

    /**
     * Create an instance of {@link GetZodiacSignResponse }
     * 
     */
    public GetZodiacSignResponse createGetZodiacSignResponse() {
        return new GetZodiacSignResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidZodiacSignException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.metro.webservice.ddlab.com/", name = "InvalidZodiacSignException")
    public JAXBElement<InvalidZodiacSignException> createInvalidZodiacSignException(InvalidZodiacSignException value) {
        return new JAXBElement<InvalidZodiacSignException>(_InvalidZodiacSignException_QNAME, InvalidZodiacSignException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetZodiacSign }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.metro.webservice.ddlab.com/", name = "getZodiacSign")
    public JAXBElement<GetZodiacSign> createGetZodiacSign(GetZodiacSign value) {
        return new JAXBElement<GetZodiacSign>(_GetZodiacSign_QNAME, GetZodiacSign.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetZodiacSignResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.metro.webservice.ddlab.com/", name = "getZodiacSignResponse")
    public JAXBElement<GetZodiacSignResponse> createGetZodiacSignResponse(GetZodiacSignResponse value) {
        return new JAXBElement<GetZodiacSignResponse>(_GetZodiacSignResponse_QNAME, GetZodiacSignResponse.class, null, value);
    }

}
