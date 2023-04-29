import java.util.Scanner;

class AlphaFilter1 {
    public static String alphaFilter(String input) {
        String s = "";
        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '\n')
                s += c;
            else
                s += ' ';
        }
        return s;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input your text:");
        String input = sc.nextLine();
        System.out.println(alphaFilter(input));
    }
}