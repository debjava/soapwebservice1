package com.ddlab.jaxrpc.client.test;

import com.ddlab.client.jaxrpc.ZodiacCalculatorService_Impl;

public class TestZodiacCalculatorJaxrpc 
{
	public static void main(String[] args) throws Exception 
	{
		ZodiacCalculatorService_Impl zcsImpl = new ZodiacCalculatorService_Impl();
		String responseResult = zcsImpl.getZodiacCalculatorPort().getZodiacSign(27, 07, 1977);
		System.out.println("Resonse Result :::"+responseResult);
	}

}
