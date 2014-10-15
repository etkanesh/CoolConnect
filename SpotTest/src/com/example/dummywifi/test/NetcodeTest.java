package com.example.dummywifi.test;

import com.example.dummywifi.Messenger.ChatSession;
import com.example.dummywifi.util.Connection;

import junit.framework.TestCase;

public class NetcodeTest extends TestCase {

	ChatSession session;
	
	protected void setUp() throws Exception {
		
		super.setUp();
		
		this.session = new ChatSession();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testChatSessionMessaging() {
		StringBuffer queueBuffer = new StringBuffer();
		int token = 0;
		session.fetchMessages(token, queueBuffer);
		
		//No messages should be in the queue at the beginning
		assertTrue(queueBuffer.length() == 0);
		
		session.queueMessage("Test message");
		token = session.fetchMessages(token, queueBuffer);
		
		// one message must be read, and the token must be incremented to reflect this
		assertTrue(("Test message" + ChatSession.messageDelim).equals(queueBuffer.toString()) && token == 1);
				
		
	}
	
	public void testSendLimits() {
		StringBuffer queueBuffer = new StringBuffer();
		session = new ChatSession();
		int token = 0;
		
		String shortMessage = "2c"; // 2 characters
		char[] bigString = new char[Connection.MAX_READ_SIZE - 1]; // max length - 1 characters (so the two messages add up to more than the send limit)
		
		for (int i = 0; i < Connection.MAX_READ_SIZE - 1; i++) {
			bigString[i] = 'x'; // fill it with characters
		}
		
		session.queueMessage(shortMessage);
		session.queueMessage(new String(bigString));
		
		token = session.fetchMessages(token, queueBuffer);
		assertTrue(token == 1); // only 1 message should have been read since the queue held more than the limit
		
		assertTrue((shortMessage + ChatSession.messageDelim).equals(queueBuffer.toString()));
		
		queueBuffer = new StringBuffer();
		token = session.fetchMessages(token, queueBuffer);
		
		assertTrue(token == 2); // the next message was read
		assertTrue((new String(bigString) + ChatSession.messageDelim).equals(queueBuffer.toString()));
	}

}
