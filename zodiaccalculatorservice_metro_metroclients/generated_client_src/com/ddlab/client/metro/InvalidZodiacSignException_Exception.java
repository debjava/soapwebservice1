
package com.ddlab.client.metro;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.1-hudson-28-
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "InvalidZodiacSignException", targetNamespace = "http://server.metro.webservice.ddlab.com/")
public class InvalidZodiacSignException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private InvalidZodiacSignException faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public InvalidZodiacSignException_Exception(String message, InvalidZodiacSignException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public InvalidZodiacSignException_Exception(String message, InvalidZodiacSignException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.ddlab.client.metro.InvalidZodiacSignException
     */
    public InvalidZodiacSignException getFaultInfo() {
        return faultInfo;
    }

}
