package com.example.dummywifi.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.example.dummywifi.util.Connection;

public class ConnectionMock extends Connection {
	private ByteArrayOutputStream connectionOutputBuffer;
	private ByteArrayInputStream connectionInputBuffer;
	final byte[] defaultResponse = {1,2,3,4};
	private long latencyMilliseconds;
	
	public void send(byte[] message) throws IOException {
		connectionOutputBuffer.write(message);
		connectionOutputBuffer.flush();		
	}
	
	
	// This will allow you to tell the connection mock how to respond
	public void createResponse(byte[] response) {
		this.connectionInputBuffer = new ByteArrayInputStream(response);
	}
	
	public byte[] receive() throws IOException {
		try {
		Thread.sleep(latencyMilliseconds);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		byte[] buffer = new byte[1024];
		connectionInputBuffer.read(buffer);
		
		return buffer;
	}
	
	public ConnectionMock(long latency) {
		super(null);
		
		latencyMilliseconds = latency;
		this.connectionOutputBuffer = new ByteArrayOutputStream();
		this.connectionInputBuffer = new ByteArrayInputStream(defaultResponse);
		
	}
}
