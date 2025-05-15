package leson11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Chat {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try (final DatagramSocket	ds = new DatagramSocket(10000)) {
			new Thread(()->sender(ds)).start();
			
			byte[]	content = new byte[65536];
			
			for (;;) {
				final DatagramPacket	rcv = new DatagramPacket(content, content.length);
				
				ds.receive(rcv);
				System.err.println("From "+rcv.getAddress()+": "
						+new String(content, 0, rcv.getLength()));
			}
		}
	}

	private static void sender(final DatagramSocket ds) {
		try(final Reader		rdr = new InputStreamReader(System.in);
			final BufferedReader	brdr = new BufferedReader(rdr);) {
	
			String	line;
			
			while ((line = brdr.readLine()) != null) {
				System.out.println("Line="+line);
				byte[]	content = line.getBytes();
				
				DatagramPacket	rcv = new DatagramPacket(content, content.length, 
						new InetSocketAddress("localhost",10000));
				ds.send(rcv);
			}
		} catch (IOException e) {
		}
	}
	
}
