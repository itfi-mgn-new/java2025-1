package leson11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class SystemTEst {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try(final Reader		rdr = new InputStreamReader(System.in);
			final BufferedReader	brdr = new BufferedReader(rdr);) {
	
			String	line;
			
			while ((line = brdr.readLine()) != null) {
				System.out.println("Line="+line);
			}
		}
	}

}
