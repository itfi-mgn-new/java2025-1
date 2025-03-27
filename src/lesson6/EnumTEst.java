package lesson6;

import java.util.Arrays;

public class EnumTEst {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyEnum	en = MyEnum.FRIDAY;
		
		if (en == MyEnum.SATURDAY) {
			
		}
		switch (en/*.ordinal()*/) {
			case FRIDAY/*.ordinal()*/ :
				//l;dfgkl;kfdlg;kf
				break;
			case MONDAY/*.ordinal()*/ :
				//l;dfgkl;kfdlg;kf
				break;
//			default :
//				//l;dfgkl;kfdlg;kf
//				break;
			case SATURDAY:
			case SUNDAY:
			case THURSDAY:
			case TUESDAY:
			case WEDNEDSAY:
				break;
			default:
				throw new UnsupportedOperationException("Type ["+en+"] is not supported yet");
		}
		Class	cl = EnumTEst.class;
		
		System.err.println("Methods: "+Arrays.toString(cl.getDeclaredMethods()));
		
		
	}

}
