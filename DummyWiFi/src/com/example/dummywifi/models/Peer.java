package com.example.dummywifi.models;

import java.io.IOException;



public interface Peer {
	public void sendMessage(byte[] Message) throws IOException;
	public byte[] receiveMessage() throws IOException;
	
}
