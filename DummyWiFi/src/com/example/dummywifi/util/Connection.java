package com.example.dummywifi.util;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import android.util.Log;

/*
 * Abstraction layer over socket
 * Useful for sending & receiving data with less code & no need to understand sockets
*/
public class Connection {
	public static final int MAX_READ_SIZE = 2048;
	
	private Socket connectionSocket;
	
	public Connection(Socket source) {
		this.connectionSocket = source;
		try {
			source.setSoTimeout(750);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			//Log.d("gowat", "exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private static String trimNullBytes(byte[] in) {
    	StringBuffer sb = new StringBuffer();
    	
    	for (int i = 0; i < in.length; i++) {
    		if (in[i] == 0) break;
    		sb.append((char)in[i]);
    	}
    	
    	return sb.toString();
    }
	
	public boolean isOpen() {
		return (!connectionSocket.isClosed()) && connectionSocket.isConnected() && (!connectionSocket.isInputShutdown()) && (!connectionSocket.isOutputShutdown());
	}
	
	/*
	 * Returns true if the data was successfully sent to the destination
	 */
	public boolean sendData(byte[] data) {
		if (!this.isOpen()) {
			return false;
		}
		
		try {
			connectionSocket.getOutputStream().write(data);
			connectionSocket.getOutputStream().flush();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public boolean receiveData(byte[] buffer) {
		if (!this.isOpen()) {
			return false;
		}
		
		int result;
		try {
			result = connectionSocket.getInputStream().read(buffer, 0, MAX_READ_SIZE - 1);
			//Log.d("gowat", "read operation completed");
		} catch (IOException e) {
			//Log.d("gowat", "exception: " + e.getMessage());
			// Probably a timeout
			return false;
		}
		
		if (result == -1) {
			return false; // no data was read
		}
		
		return true; // data was read and put into buffer		
	}

	// wrapper for sending text
	public boolean sendText(String text) {
		return sendData(text.getBytes());
	}
	
	public String receiveString() {
		byte[] buffer = new byte[MAX_READ_SIZE];
		if (receiveData(buffer)) {
			String strvalue = trimNullBytes(buffer);
			return strvalue;
		} else {
			return null;
		}
	}
	
	public void close() {
		try {
			connectionSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
