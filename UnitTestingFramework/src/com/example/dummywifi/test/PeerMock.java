package com.example.dummywifi.test;

import java.io.IOException;
import java.io.OutputStream;

import com.example.dummywifi.models.Peer;

/*
 * Mock object for testing peer connections & data transmission
 */
public class PeerMock implements Peer {
	private boolean isResponsive; // The peer will actually send responses, for testing unresponsive nodes
	private String alias; // You can set the alias of the peer for appearing in chat protocol
	private long connectionLatency;
	private ConnectionMock connection;
	
	// Parrots the message sent to it if the peer is a responsive peer
	// Later this can be used to test proper message acknowledgments 
	public void sendMessage(byte[] msg) throws IOException {
		if (isResponsive) {
			connection.createResponse(msg);
			respond(msg);			
		}
	}
	
	
	private void respond(byte[] response) throws IOException {
		connection.createResponse(response);		
	}
	
	public byte[] receiveMessage() throws IOException {
		return connection.receive();
	}
	
	public PeerMock() {
		this.connection = new ConnectionMock(100); // 100ms latency
		
	}
	
	// Do nothing in the mock object
	public void connect() {
		
	}
		
}
