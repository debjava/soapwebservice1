package com.ddlab.webservice.jaxrpc.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ZodiacCalculator extends Remote
{
	public String getZodiacSign( int date , int month , int year ) throws InvalidZodiacSignException,RemoteException;

}
