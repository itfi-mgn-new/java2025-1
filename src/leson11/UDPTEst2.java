package leson11;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPTEst2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try (final DatagramSocket	ds = new DatagramSocket(10001)) {
			byte[]	content = "test string".getBytes();
			
			DatagramPacket	rcv = new DatagramPacket(content, content.length, 
									new InetSocketAddress("localhost",10000));
			ds.send(rcv);
		}
	}

}
