import java.util.Scanner;

class EvenHashcode {
    public final static int M = 17, WORD_LIMIT = 1000;
    
    static int[] hashedWords = new int[M];
    
    private static int hash(String key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
              
        int wordsRead = 0;
        while(sc.hasNext() && wordsRead < WORD_LIMIT) {
            int index = hash(sc.next());
            hashedWords[index] += 1;
            wordsRead++;
        }

        for(int i = 0; i < hashedWords.length; i++) {
            for(int j = 0; j < hashedWords[i]; j++)
                System.out.print("*");
            System.out.println(" " + hashedWords[i]);
        }
    }
}