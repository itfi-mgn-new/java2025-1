package lesson9;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipInputStream;

public class ZipTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		final String	dir = System.getProperty("java.io.tmpdir");
		System.err.println("Dir="+dir);

		try(final OutputStream	os = new FileOutputStream(dir+"test.zip");
			final ZipOutputStream	zos = new ZipOutputStream(os)) {
			
			ZipEntry	ze = new ZipEntry("test/part1.txt");
			
			ze.setMethod(ZipEntry.DEFLATED);
			zos.putNextEntry(ze);
			
			Writer  wr = new OutputStreamWriter(zos);
			
			wr.write("test string");
			wr.flush();
//			zos.closeEntry();

			ze = new ZipEntry("test/part2.txt");
			
			ze.setMethod(ZipEntry.DEFLATED);
			zos.putNextEntry(ze);
			
			Writer  wr2 = new OutputStreamWriter(zos);
			
			wr2.write("test string 2");
			wr2.flush();
//			zos.closeEntry();
//			zos.finish();
		}
		
		try(final InputStream	is = new FileInputStream(dir+"test.zip");
			final ZipInputStream	zis = new ZipInputStream(is)) {
			ZipEntry	ze;

			
			while ((ze = zis.getNextEntry()) != null) {
				System.err.println("Name="+ze.getName());
				Reader		rdr = new InputStreamReader(zis); 
				BufferedReader	brdr = new BufferedReader(rdr); 
				System.err.println("Content="+brdr.readLine());
			}
		}		
		
	}

}
