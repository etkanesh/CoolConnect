/**
 * 
 */
package com.example.dummywifi.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import android.test.*;
import android.util.Log;

import com.example.dummywifi.models.Peer;

/**
 * @author Jeff
 *
 */
public class TestHarness extends AndroidTestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void failingTest() {
		fail("This test will always fail!");
	}
	
	@Test
	public void testPeerDiscovery() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testMessageRepeating() throws IOException {
		
		byte[] message = { 1, 2, 3, 4 };
		Peer testPeer = new PeerMock();
		testPeer.sendMessage(message);
		
		byte[] response = testPeer.receiveMessage();
		Log.i("testing", "response[0]: " + response[0]);
		Assert.assertArrayEquals(message, response);
		
		
	}

}
