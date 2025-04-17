package lesson10;

import java.io.File;
import java.util.Arrays;

public class FileTEst {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File	f = new File("c:/");
		
		System.err.println("exists? "+f.exists());
		System.err.println("Is file? "+f.isFile());
		System.err.println("Is directory? "+f.isDirectory());
		System.err.println("Can read? "+f.canRead());
		System.err.println("Can write? "+f.canWrite());

		System.err.println("List: "+Arrays.toString(f.listFiles()));

		File	f1 = new File(f, "Program Files (x86)");
		System.err.println("exists: "+f1.exists());
		System.err.println("parent: "+f1.getParentFile());

		File	parent = new File(System.getProperty("java.io.tmpdir"));
		File	f2 = new File(parent, "test");
		System.err.println("exists: "+f2.exists());
		System.err.println("create: "+f2.mkdirs());
		System.err.println("Located: "+f2.getAbsoluteFile());
		
		System.err.println("Delete: "+f2.delete());
		System.err.println("exists: "+f2.exists());
		System.err.println("Located: "+f2.getAbsoluteFile());

		
		
		
	
	}

}
