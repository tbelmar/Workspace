import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class WordsOccurence {
    private static class HashTable<Key> {
        private int m;
        private SymbolTable<Key>[] st;
        
        public HashTable() {
            this(997);
        }
        
        public HashTable(int m) {
            this.m = m;
            st = (SymbolTable<Key>[]) new SymbolTable[m];
            for(int i = 0; i < m; i++)
                st[i] = new SymbolTable();
        }
        
        private int hash(Key key) {
            return (key.hashCode() & 0x7fffffff) % m;
        }
        
        public int get(Key key) {
            if(st[hash(key)].get(key) == null) return 0;
            return st[hash(key)].get(key).value;
        }
        
        public void put(Key key) {
            st[hash(key)].put(key);
        }
    }
    
    private static class SymbolTable<Key> {
        Node root;
        
        void put(Key key) {
            Node check = get(key);
            if(check != null) {
                check.value = check.value + 1;
                return;
            }
                
            Node x = new Node(key);
            if(root != null) x.next = root;
            root = x;
        }
        
        Node get(Key key) {
            Node current = root;
            
            
            while(current != null) {
                if(current.key.equals(key)) return current;
                current = current.next;
            }
            return null;
        }
        
        private class Node {
            Key key;
            int value;
            Node next;
            
            Node(Key key) {
                this.key = key;
                this.value = 1;
            }
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input the file name:");
        
        String fileName = sc.next();
        File file = new File(fileName);
        Scanner fileScanner = new Scanner(file);
       
        HashTable<String> ht = new HashTable<>();
        
        while(fileScanner.hasNext())
            ht.put(fileScanner.next());
        
        while(true) {
            System.out.println("Enter the word you are looking for, or type EXIT if you'd like to stop.");
            String word = sc.next();
            if(word.equals("EXIT")) break;
            
            System.out.println("Looking for occurences of the word \"" + word + "\"");
            System.out.println("\"" + word + "\" shows up " + ht.get(word) + " times in words.txt");
        }
    }
}