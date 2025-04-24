package leson11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class URILest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		final URI	uri = URI.create("http://mail.ru");
		final URL	url = uri.toURL(); //new URL("http://mail.ru");
		final HttpURLConnection	conn = (HttpURLConnection)url.openConnection();
		
		conn.setRequestMethod("POST");
		conn.setRequestProperty("a,dfjldjf", "dfljdslfk");
		
		try(final InputStream	is = conn.getInputStream();
			final Reader		rdr = new InputStreamReader(is);
			final BufferedReader	brdr = new BufferedReader(rdr)) {
			
			String	line;
			
			while ((line = brdr.readLine()) != null) {
				System.out.println("Line="+line);
			}			
		}
	}

}
