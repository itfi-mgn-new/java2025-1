package leson11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClientTest {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		try(final Socket		s = new Socket("localhost", 10000);
			final InputStream	is = s.getInputStream();
			final OutputStream	os = s.getOutputStream();
			final Reader		rdr = new InputStreamReader(is);
			final BufferedReader	brdr = new BufferedReader(rdr);
			final PrintStream	ps = new PrintStream(os)) {
		
			ps.println("hello world");
			ps.flush();
			System.err.println("Line="+brdr.readLine());
		}
	}

}
