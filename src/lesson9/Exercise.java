package lesson9;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.io.IOException;

public class Exercise {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		byte[]  source = {1,2,3,4,5};
		
		try(Duration	d = new Duration();
			InputStream	is = new ByteArrayInputStream(source)) {
			System.err.println("Value: "+is.read());
			System.err.println("Value: "+is.read());
			System.err.println("Value: "+is.read());
			System.err.println("Value: "+is.read());
			System.err.println("Value: "+is.read());
			System.err.println("Value: "+is.read());
		}
//		is.close();
		

		try(Duration	d = new Duration();
			InputStream	is = new ByteArrayInputStream(source)) {
			byte[] content = new byte[10];
			
			System.err.println("Length: "+is.read(content));
			System.err.println("Value: "+Arrays.toString(content));
			System.err.println("Length: "+is.read(content));
		}
		
		try(OutputStream	os = new ByteArrayOutputStream()) {
			os.write(1);
			os.write(2);
			os.write(3);
			os.write(4);
			os.write(5);
			os.flush();
			System.err.println("Content: "+Arrays.toString(((ByteArrayOutputStream)os).toByteArray()));
		}
		
		
	}

}

class Duration implements AutoCloseable {
	private final long	startTime = System.nanoTime();
	
	public Duration() {
	}
	
	@Override
	public void close() throws RuntimeException {
		System.err.println("Duration="+(System.nanoTime()-startTime));
	}
}

