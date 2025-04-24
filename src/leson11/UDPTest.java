package leson11;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try (final DatagramSocket	ds = new DatagramSocket(10000)) {
			byte[]	content = new byte[1024];
			
			DatagramPacket	rcv = new DatagramPacket(content, content.length);
			ds.receive(rcv);
			System.err.println("Len: "+rcv.getLength());
			System.err.println("Data: "+new String(content, 0, rcv.getLength()));
		}
	}

}
