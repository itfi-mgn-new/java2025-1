package lesson10;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SerializeTEst {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		final String	fileName = System.getProperty("java.io.tmpdir")+"test.bin";
		Address addr = new Address("Lenina", 68);
				
		try(final OutputStream		os = new FileOutputStream(fileName);
			final ObjectOutputStream	dos = new ObjectOutputStream(os)) {
			
			dos.writeBoolean(false);
			dos.writeInt(123);
			dos.writeDouble(123.456);
			dos.writeUTF("test string");
			dos.writeObject(new int[]{10,20,30});
			dos.writeObject(new Person("ivanoff","ivan","ivanovich",21, addr));
			
			dos.reset();
			
			dos.writeObject(new Person("petroff","petr","petrovich",25, addr));
			dos.flush();
		}
		
		try(final InputStream		is = new FileInputStream(fileName);
			final ObjectInputStream	dis = new ObjectInputStream(is)) {
			
			System.err.println(dis.readBoolean());
			System.err.println(dis.readInt());
			System.err.println(dis.readDouble());
			System.err.println(dis.readUTF());
			System.err.println(Arrays.toString((int[])dis.readObject()));
			Person	p1 = (Person)dis.readObject();
			Person	p2 = (Person)dis.readObject();
			System.err.println(p1);
			System.err.println(p2);
			System.err.println(p1.addr == p2.addr);
			System.err.println(p1.addr == addr);
		}
		
	}
}
