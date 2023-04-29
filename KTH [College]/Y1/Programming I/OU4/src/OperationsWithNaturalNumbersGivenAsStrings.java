import java.util.*;
import static java.lang.System.out;

public class OperationsWithNaturalNumbersGivenAsStrings {
	public  static  void  main (String [] args) {
		out.println("OPERATIONS ON NATURAL NUMBERS IN CHARACTER STRINGS");
		
		// enter two natural nums
		Scanner in = new Scanner(System.in);
		out.println("two natural numbers:");
		String tal1 = in.next();
		String tal2 = in.next();
		out.println();
		
		// add the numbers and show the result
		String sum = add(tal1, tal2);
		
		show(tal1, tal2, sum, '+');
		
		// subtract the numbers and show the result
		String difference = subtract(tal1, tal2);
		
		show(tal1, tal2, difference, '-');
	}
	
	public static String add(String num1, String num2) {
		String s = "";
		String append = "";
		
		num1 = "0"+num1;
		num2 = "0"+num2;
		
		int diff = num1.length() - num2.length();
		for(int i = 0; i < Math.abs(diff); i++)
			append += 0;
		
		if(diff < 0) num1 = append + num1;
		else num2 = append + num2;
		
		int carry = 0;
		for(int i = num1.length()-1; i >= 0; i--) {
			int n = num1.charAt(i)-48 + num2.charAt(i)-48 + carry;
			if(n > 9) {
				carry = 1;
				s = (n-10) + s;
			}
			else {
				carry = 0;
				s = n + s;
			}
		}
		
		if(s.charAt(0) == '0')
			s = s.substring(1);
		return s;
	}
	
	public static String subtract(String num1, String num2) {
		String s = "";
		String append = "";
		
		int diff = num1.length() - num2.length();
		for(int i = 0; i < Math.abs(diff); i++)
			append += 0;
		
		num2 = append + num2;
		
		for(int i = num1.length()-1; i >= 0; i--) {
			int c1 = num1.charAt(i)-48;
			int c2 = num2.charAt(i)-48;
			
			if(c1 < c2) {
				for(int j = i-1; j >= 0; j--) {
					if(num1.charAt(j) == '0') {
						num1 = num1.substring(0, j+1) + "9" + num1.substring(j+2);
						System.out.println("L " + num1.length());
					}
					else {
						num1 = "" + num1.substring(0, j) + (num1.charAt(j)-49) + num1.substring(j+2);
					}
				}
				
				
				s = (10 + c1 - c2) + s;
			}
			else s = (c1 - c2) + s;
		}
		
		return s;
	}
	
	public static void show(String num1, String num2, String result, char operator) {
		// set an appropriate length on numbers and result
		int len1 = num1.length();
		int len2 = num2.length();
		int len = result.length();
		int maxLen = Math.max(Math.max(len1, len2), len);
		num1 = setLen(num1, maxLen - len1);
		num2 = setLen(num2, maxLen - len2);
		result = setLen(result, maxLen - len);
		
		// show the expression
		out.println(" " + num1);
		out.println("" + operator + " " + num2);
		for(int i = 0; i < maxLen + 2; i++)
			out.println("-");
		out.println();
		out.println(" " + result + "\n");
	}
	
	public static String setLen(String s, int nofSpaces) {
		StringBuilder sb = new StringBuilder(s);
		for(int i = 0; i < nofSpaces; i++)
			sb.insert(0, " ");
		return sb.toString();
	}
}
