package lesson8;

public class EXercise {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "abcdefg";
		// s = "AbCdEfG";
		StringBuilder sb = new StringBuilder();

		for(int index = 0; index < s.length(); index++) {
			if (index % 2 == 0) {
				sb.append(s.substring(index, index+1).toUpperCase());
			}
			else {
				sb.append(s.substring(index, index+1).toLowerCase());
			}
		}
		System.err.println("Value="+sb.toString());

		sb.setLength(0);
		for(int index = 0; index < s.length()-1; index+=2) {
			sb.append(Character.toUpperCase(s.charAt(index)))
			  .append(Character.toLowerCase(s.charAt(index+1)));
		}
		if (s.length() % 2 == 1) {
			sb.append(Character.toUpperCase(s.charAt(s.length()-1)));
		}
		System.err.println("Value="+sb.toString());
		
		s = "12+44+8+17";
		
		int sum = 0;
		for (String item : s.split("\\+")) {
			sum += Integer.valueOf(item);
		}
		System.err.println("Sum="+sum);
		
		s = "-12+44-8+17";

		sum = 0;
		for (String item : s.replace("-", "+-").split("\\+")) {
			if (!item.isEmpty()) {
				sum += Integer.valueOf(item);
			}
		}
		System.err.println("Sum="+sum);
	
		s = "12*44+8*17";

		sum = 0;
		for (String item : s.split("\\+")) {
			int mul = 1;
			for (String item2 : item.split("\\*")) {
				if (!item.isEmpty()) {
					mul *= Integer.valueOf(item2);
				}
			}
			sum += mul;
		}
		System.err.println("Sum="+sum);
	
	}

}
