import java.io.*;
import java.util.Scanner;

public class ReverseRecursive {
	public static void main(String args[]) {
            Scanner sc = new Scanner(System.in);
            
            System.out.println("Enter your String:");
            String word = sc.nextLine();
            
            reverse(word, 0);
	}
	
	public static void reverse(String word, int index) {
            if(index == word.length())
                return;
            reverse(word, index+1);
            System.out.print(word.charAt(index));
	}
}