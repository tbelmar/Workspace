import java.util.Scanner;
import java.util.Arrays;

class BinarySearchSymbolTable2<Key extends Comparable<Key>, Value> {
    final static int WORD_LIMIT = 10000;
    final static int N_OF_TESTS = 10;
    final static double NANO_CONVERSION = 1000000000.0;
    
    private Key[] keys;
    private Value[] vals;
    private int n;
    
    public BinarySearchSymbolTable2(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }
    
    public int size() {
        return n;
    }
    
    public Value get(Key key) {
        if(size() == 0) return null;
        int i = rank(key);
        if(i < n && keys[i].compareTo(key) == 0) return vals[i];
        else return null;
    }
    
    public int rank(Key key) {
        int low = 0, high = n-1;
        while(low <= high) {
            int mid = low + (high-low) / 2;
            int compare = key.compareTo(keys[mid]);
            if(compare < 0) high = mid-1;
            else if(compare > 0) low = mid+1;
            else return mid;
        }
        return low;
    }
    
    public void put(Key key, Value val) {
        int i = rank(key);
        if(i < n && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        
        for(int j = n; j > i; j--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        
        keys[i] = key;
        vals[i] = val;
        n++;
    }
    
    public Iterable<Key> keys() {
        return Arrays.asList(keys);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long[] startTime = new long[3], endTime = new long[3];
        
        double[] totalTime = new double[3];
        
        for(int i = 0; i < N_OF_TESTS; i++) {
            
            int wordsRead = 0;
            
            startTime[0] = System.nanoTime();
            BinarySearchSymbolTable2<String, Integer> bsst = new BinarySearchSymbolTable2<>(WORD_LIMIT);
            

            while(sc.hasNext() && wordsRead < WORD_LIMIT) {
                String word = sc.next();
                if(bsst.get(word) == null) {
                    bsst.put(word, 1);
                }
                else bsst.put(word, bsst.get(word) + 1);
                wordsRead++;
            }
            endTime[0] = System.nanoTime();
            
            String max = "";
            bsst.put(max, 0);
            
            
            startTime[1] = System.nanoTime();
            for(String word : bsst.keys()) {
                if(word == null) continue;
                if(bsst.get(word) > bsst.get(max))
                    max = word;
            }
            endTime[1] = System.nanoTime();
            
            startTime[2] = System.nanoTime();
            bsst.get("this");
            endTime[2] = System.nanoTime();
            
            totalTime[0] += (endTime[0] - startTime[0]) / NANO_CONVERSION;
            totalTime[1] += (endTime[1] - startTime[1]) / NANO_CONVERSION;
            totalTime[2] += (endTime[2] - startTime[2]) / NANO_CONVERSION;
        }
        
        
        
        System.out.printf("Average time for building %d tests: %fs%n", N_OF_TESTS, (double)(totalTime[0]/N_OF_TESTS));
        System.out.printf("Average time for iterating over the BSST %d tests: %fs%n", N_OF_TESTS, (double)(totalTime[1]/N_OF_TESTS));
        System.out.printf("Average time for simple lookup %d tests: %fs%n", N_OF_TESTS, (double)(totalTime[2]/N_OF_TESTS));
    }
}