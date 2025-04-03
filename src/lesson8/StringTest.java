package lesson8;

import java.util.Arrays;

public class StringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String	x = "vassya";
//		"vassya".length();

		System.err.println("Length="+x.length());
		System.err.println("Char at[0]="+x.charAt(0));
		System.err.println("HashCode="+x.hashCode());
		System.err.println("Equals="+x.equals("vassya"));
		System.err.println("Equals="+(x == "vassya"));

		switch(x/*.hashCode()*/) {
			case "vassya"/*.hashCode()*/ :
				/* if ("vassya".equals(x) */
				System.err.println("Yes");
				/* } */
				break;
			default :
				System.err.println("No");
				break;
		}
		
		System.err.println("Index of="+x.indexOf("ss"));
		System.err.println("Index of="+x.lastIndexOf("ss"));
		System.err.println("Index of="+x.indexOf('a', 3));
		System.err.println("Index of="+x.lastIndexOf('a'));

		System.err.println("Starts with="+x.startsWith("va"));
		System.err.println("Ends with="+x.endsWith("ya"));
		System.err.println("Contains="+x.contains("ss"));

		System.err.println("Equals="+x.equalsIgnoreCase("VaSsYa"));
		System.err.println("Compare="+x.compareTo("petya"));
		System.err.println("Compare="+x.compareToIgnoreCase("PeTyA"));
		
		System.err.println("Lower ="+x.toLowerCase());
		System.err.println("Upper ="+x.toUpperCase());
		System.err.println("Substring=<"+x.substring(2,2)+">");
		System.err.println("Substring="+x.substring(2,3));
		System.err.println("Replace="+x.replace("s","S"));
		System.err.println("Replace="+x.replace('s','S'));
		System.err.println("Replace="+x.replace("s",""));
		System.err.println("Split="+Arrays.toString(x.split("a")));
/*		new StringBuilder().append("Split=").append(Arrays.toString(x.split("a"))).toString();*/
		StringBuilder	sb = new StringBuilder();
		
		String result = sb.append("Split=").append(Arrays.toString(x.split("a"))).toString();
		
	}

}
