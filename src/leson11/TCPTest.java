package leson11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try(final ServerSocket		ss = new ServerSocket(10000)) {
			for (;;) {
				final Socket	s = ss.accept();
					
				System.out.println("Connected...");
				new Thread(()->processConnection(s)).start();
			}
		}
	}
		
	static void processConnection(final Socket s) {
		System.out.println("Start thread...");
		try(
			final InputStream	is = s.getInputStream();
			final OutputStream	os = s.getOutputStream();
			final Reader		rdr = new InputStreamReader(is);
			final BufferedReader	brdr = new BufferedReader(rdr);
			final Writer		ps = new OutputStreamWriter(os)) {

			System.out.println("Wait...");
			System.out.flush();
		
			String	line;
			
			while ((line = brdr.readLine()) != null) {
				System.out.println("Line="+line);
				ps.write(line.toUpperCase()+System.lineSeparator());
				ps.flush();
			}
		} catch (IOException exc) {
		} finally {
			try {
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
