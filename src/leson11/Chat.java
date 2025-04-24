package leson11;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Chat {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try (final DatagramSocket	ds = new DatagramSocket(10000)) {
			byte[]	content = new byte[65536];
			
			for (;;) {
				final DatagramPacket	rcv = new DatagramPacket(content, content.length);
				
				ds.receive(rcv);
				System.err.println("From "+rcv.getAddress()+": "
						+new String(content, 0, rcv.getLength()));
			}
		}
	}

}
