// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JAX-RPC Standard Implementation (1.1.3, build R1)
// Generated source version: 1.1.3

package com.ddlab.client.jaxrpc;

import com.sun.xml.rpc.encoding.*;
import com.sun.xml.rpc.encoding.literal.DetailFragmentDeserializer;
import com.sun.xml.rpc.encoding.simpletype.*;
import com.sun.xml.rpc.encoding.soap.SOAPConstants;
import com.sun.xml.rpc.encoding.soap.SOAP12Constants;
import com.sun.xml.rpc.streaming.*;
import com.sun.xml.rpc.wsdl.document.schema.SchemaConstants;
import javax.xml.namespace.QName;

public class InvalidZodiacSignException_SOAPSerializer extends ObjectSerializerBase implements Initializable {
    private static final javax.xml.namespace.QName ns1_message_QNAME = new QName("", "message");
    private static final javax.xml.namespace.QName ns2_string_TYPE_QNAME = SchemaConstants.QNAME_TYPE_STRING;
    private CombinedSerializer ns2_myns2_string__java_lang_String_String_Serializer;
    private static final int myMESSAGE_INDEX = 0;
    
    public InvalidZodiacSignException_SOAPSerializer(QName type, boolean encodeType, boolean isNullable, String encodingStyle) {
        super(type, encodeType, isNullable, encodingStyle);
    }
    
    public void initialize(InternalTypeMappingRegistry registry) throws java.lang.Exception {
        ns2_myns2_string__java_lang_String_String_Serializer = (CombinedSerializer)registry.getSerializer(SOAPConstants.NS_SOAP_ENCODING, java.lang.String.class, ns2_string_TYPE_QNAME);
    }
    
    public java.lang.Object doDeserialize(SOAPDeserializationState state, XMLReader reader,
        SOAPDeserializationContext context) throws java.lang.Exception {
        com.ddlab.client.jaxrpc.InvalidZodiacSignException instance = null;
        java.lang.Object messageTemp = null;
        com.ddlab.client.jaxrpc.InvalidZodiacSignException_SOAPBuilder builder = null;
        java.lang.Object member;
        boolean isComplete = true;
        javax.xml.namespace.QName elementName;
        
        reader.nextElementContent();
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_message_QNAME)) {
                member = ns2_myns2_string__java_lang_String_String_Serializer.deserialize(ns1_message_QNAME, reader, context);
                if (member instanceof SOAPDeserializationState) {
                    if (builder == null) {
                        builder = new com.ddlab.client.jaxrpc.InvalidZodiacSignException_SOAPBuilder();
                    }
                    state = registerWithMemberState(instance, state, member, myMESSAGE_INDEX, builder);
                    isComplete = false;
                } else {
                    messageTemp = member;
                }
                reader.nextElementContent();
            }
        }
        if (isComplete) {
            instance = new com.ddlab.client.jaxrpc.InvalidZodiacSignException((java.lang.String)messageTemp);
        } else {
            builder.setMessage((java.lang.String)messageTemp);
        }
        
        XMLReaderUtil.verifyReaderState(reader, XMLReader.END);
        return (isComplete ? (java.lang.Object)instance : (java.lang.Object)state);
    }
    
    public void doSerializeAttributes(java.lang.Object obj, XMLWriter writer, SOAPSerializationContext context) throws java.lang.Exception {
        com.ddlab.client.jaxrpc.InvalidZodiacSignException instance = (com.ddlab.client.jaxrpc.InvalidZodiacSignException)obj;
        
    }
    
    public void doSerializeInstance(java.lang.Object obj, XMLWriter writer, SOAPSerializationContext context) throws java.lang.Exception {
        com.ddlab.client.jaxrpc.InvalidZodiacSignException instance = (com.ddlab.client.jaxrpc.InvalidZodiacSignException)obj;
        
        ns2_myns2_string__java_lang_String_String_Serializer.serialize(instance.getMessage(), ns1_message_QNAME, null, writer, context);
    }
}
