
/**
 * InvalidZodiacSignExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package com.ddlab.webservice.client;

public class InvalidZodiacSignExceptionException extends java.lang.Exception{
    
    private com.ddlab.webservice.client.ZodiacCalculatorImplServiceStub.InvalidZodiacSignExceptionE faultMessage;

    
        public InvalidZodiacSignExceptionException() {
            super("InvalidZodiacSignExceptionException");
        }

        public InvalidZodiacSignExceptionException(java.lang.String s) {
           super(s);
        }

        public InvalidZodiacSignExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public InvalidZodiacSignExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.ddlab.webservice.client.ZodiacCalculatorImplServiceStub.InvalidZodiacSignExceptionE msg){
       faultMessage = msg;
    }
    
    public com.ddlab.webservice.client.ZodiacCalculatorImplServiceStub.InvalidZodiacSignExceptionE getFaultMessage(){
       return faultMessage;
    }
}
    