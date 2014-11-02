package com.example.dummywifi.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.UUID;

import android.util.Log;

import com.example.dummywifi.models.ChatMessage;

/*
 * Abstraction layer over socket
 * Useful for sending & receiving data with less code & no need to understand sockets
*/
public class Connection {
	public static final int MAX_READ_SIZE = 2048;
	
	private final Socket connectionSocket;
    private ObjectInputStream inputStream = null;
    private ObjectOutputStream outputStream = null;

	public Connection(Socket source) {
		this.connectionSocket = source;

		try {
			source.setSoTimeout(750);
		} catch (SocketException e) {
			Log.e("connection", "failed to set timeout: " + e);
		}
	}
	
	public boolean isOpen() {
		return (!connectionSocket.isClosed()) && connectionSocket.isConnected() && (!connectionSocket.isInputShutdown()) && (!connectionSocket.isOutputShutdown());
	}

    private synchronized boolean sendMessage(ChatMessage message) {
        if (!this.isOpen()) return false;
        // initialize the stream on first use, not possible in constructor because the socket is not yet connected
        if (outputStream == null){
            try {
                outputStream = new ObjectOutputStream(connectionSocket.getOutputStream());
            } catch (IOException e) {
                Log.e("connection","failed to initialize outputStream" + e);
                return false;
            }
        }

        try {
            outputStream.writeObject(message);
            outputStream.flush();
        } catch (IOException e) {
            Log.w("connection", "IO error while writing message " + e.getMessage());
            return false;
        }
        return true;
    }

    private synchronized ChatMessage receiveMessage() {
        if (!this.isOpen()) return null;
        // initialize the stream on first use, not possible in constructor because the socket is not yet connected
        if (inputStream == null){
            try {
                inputStream = new ObjectInputStream(connectionSocket.getInputStream());
            } catch (IOException e) {
                Log.e("connection","failed to initialize inputStream" + e);
            }
        }

        try {
            Object result = inputStream.readObject();
            ChatMessage message =  (ChatMessage) result;

            return message;
        } catch (ClassCastException e) {
            Log.e("connection","received message of unexpected type " + e);
            return null;
        } catch (StreamCorruptedException e){
            Log.w("connection", "stream corrupted on receive message: " + e);
            return null;
        } catch (SocketTimeoutException e) {
            // this is totally expected, since we are just polling on the inStream all the time
            return null;
        }catch (IOException e) {
            Log.w("connection", "IO error when receiving message " + e);
            return null;
        } catch (ClassNotFoundException e) {
            Log.e("connection", "class not found when receiving message " + e);
            return null;
        }
    }

	// wrapper for sending text
	public boolean sendText(String text) {
        return sendMessage(new ChatMessage(text));
	}
    // wrapper for sending commands
    public boolean sendCommand(String text) { return sendMessage(new ChatMessage(text, ChatMessage.Types.COMMAND)); }

    private boolean sendAck(UUID ackId) { return sendMessage(new ChatMessage(ackId.toString(), ChatMessage.Types.ACK)); }
	
	public ChatMessage receiveString() {
        ChatMessage message = receiveMessage();
        if (message != null) {
        	switch (message.getType()) {
                case MESSAGE:
                    // acknowledge the message
                    // TODO this should be moved out to whatever ACK manager we create, so it is done asynchronously
                    sendAck(message.getId());
                    return message;
                case ACK:
                    // TODO do something with acks
                    Log.d("connection", "recieved ACK for Id: " + message.getText());
                    return null;
                case COMMAND:
                    return message;
            }
        }
        return null;
	}
	
	public void close() {
		Log.i("connection","closing socket");
        try {
			outputStream.close();
            inputStream.close();
            connectionSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
