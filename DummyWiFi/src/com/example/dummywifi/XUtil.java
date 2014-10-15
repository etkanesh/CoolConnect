package com.example.dummywifi;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Enumeration;
import java.net.NetworkInterface;
import android.text.format.Formatter;
import java.net.SocketException;

import android.util.Log;

/*
 * I wanted a place to put extraneous utility classes
 * Many of these I adapt from other plaes or rip them outright
 * I call it XUtil because Util would probably conflict and XUtil sounds a bit spicier
 */

public class XUtil {

	//http://stackoverflow.com/questions/12946463/how-to-get-the-ip-address-of-a-non-group-owner-in-wifi-direct
	public static byte[] getLocalIPAddress() {
	    try {
	        for (Enumeration<NetworkInterface> en = NetworkInterface
	                .getNetworkInterfaces(); en.hasMoreElements();) {
	            NetworkInterface intf = en.nextElement();
	            for (Enumeration<InetAddress> enumIpAddr = intf
	                    .getInetAddresses(); enumIpAddr.hasMoreElements();) {
	                InetAddress inetAddress = enumIpAddr.nextElement();
	                if (!inetAddress.isLoopbackAddress()) {
	                    if (inetAddress instanceof Inet4Address) {
	                        return inetAddress.getAddress();
	                    }
	                }
	            }
	        }
	    } catch (SocketException ex) {
	        // Log.e("AndroidNetworkAddressFactory", "getLocalIPAddress()", ex);
	    } catch (NullPointerException ex) {
	        // Log.e("AndroidNetworkAddressFactory", "getLocalIPAddress()", ex);
	    }
	    return null;
	}

	public static String getDottedDecimalIP(byte[] ipAddr) {
	    if (ipAddr != null) {
	        String ipAddrStr = "";
	        for (int i = 0; i < ipAddr.length; i++) {
	            if (i > 0) {
	                ipAddrStr += ".";
	            }
	            ipAddrStr += ipAddr[i] & 0xFF;
	        }
	        return ipAddrStr;
	    } else {
	        return "null";
	    }
	}
}
