package lesson9;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		final String	dir = System.getProperty("java.io.tmpdir");
		System.err.println("Dir="+dir);
		
		byte[] buf;
		
		try(OutputStream	os = new FileOutputStream(dir+"test.txt");
			OutputStream	gzos = new  GZIPOutputStream(os);
			Writer			wr = new OutputStreamWriter(gzos)) {
			
			wr.write("test string"/*.getBytes()*/);
			wr.flush();
			gzos.flush();
			//buf = ((ByteArrayOutputStream)os).toByteArray();
		}

		try(InputStream	is = new FileInputStream(dir+"test.txt");
			InputStream	gzis = new GZIPInputStream(is);
			Reader		rdr = new InputStreamReader(gzis);
			BufferedReader	brdr = new BufferedReader(rdr)) {
//			byte[]	buffer = new byte[100];
//			int 	length = is.read(buffer);
//			char[]	buffer = new char[100];
//			int 	length = rdr.read(buffer);
			
//			System.err.print(new String(buffer, 0, length));
			System.err.print(brdr.readLine());
		}
	}

}
