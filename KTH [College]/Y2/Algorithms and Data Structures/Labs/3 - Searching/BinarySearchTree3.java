import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

class BinarySearchTree3 {
    private static class BST<Key extends Comparable<Key>, Value> {
        private Node root;
        
        private class Node {
            private Key key;
            private Value value;
            private Node left, right;
            private int n;
            
            public Node(Key key, Value value, int n) {
                this.key = key;
                this.value = value;
                this.n = n;
            }
        }
        
        public int size() {
            return size(root);
        }
        
        private int size(Node x) {
            if(x == null) return 0;
            else return x.n;
        }
        
        public Value get(Key key) {
            return get(root, key);
        }
        
        public Value get(Node node, Key key) {
            if(node == null) return null;
            int compare = key.compareTo(node.key);
            if(compare < 0) return get(node.left, key);
            else if(compare > 0) return get(node.right, key);
            else return node.value;
        }
        
        public void put(Key key, Value value) {
            root = put(root, key, value);
        }
        
        public Node put(Node node, Key key, Value value) {
            if(node == null) return new Node(key, value, 1);
            int compare = key.compareTo(node.key);
            if(compare < 0) node.left = put(node.left, key, value);
            else if(compare > 0) node.right = put(node.right, key, value);
            else node.value = value;
            node.n = size(node.left) + size(node.right) + 1;
            return node;
        }
        
        public Iterable<Key> keys() {
            return keys(min(), max());
        }
        
        public Iterable<Key> keys(Key low, Key high) {
            Queue<Key> queue = new LinkedList<>();
            keys(root, queue, low, high);
            return queue;
        }
        
        private void keys(Node node, Queue<Key> queue, Key low, Key high) {
            if(node == null) return;
            int compareLow = low.compareTo(node.key);
            int compareHigh = high.compareTo(node.key);
            if(compareLow < 0) keys(node.left, queue, low, high);
            if(compareLow <= 0 && compareHigh >= 0) queue.add(node.key);
            if(compareHigh > 0) keys(node.right, queue, low, high);
        }
        
        public Key min() {
            if(root == null) throw new IndexOutOfBoundsException();
            Node node = min(root);
            return node.key;
        }
        
        public Node min(Node node) {
            if(node.left == null) return node;
            return min(node.left);
        }
        
        public Key max() {
            if(root == null) throw new IndexOutOfBoundsException();
            Node node = max(root);
            return node.key;
        }
        
        public Node max(Node node) {
            if(node.right == null) return node;
            return max(node.right);
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        final int WORD_LIMIT = 1000;
        final int N_OF_TESTS = 10;
        final double NANO_CONVERSION = 1000000000.0;
        long startTime, endTime;
        
        double totalTime = 0;
        
        for(int i = 0; i < N_OF_TESTS; i++) {
            startTime = System.nanoTime();
            int wordsRead = 0;

            BST<String, Integer> st = new BST<>();

            while(sc.hasNext() && wordsRead < WORD_LIMIT) {
                String word = sc.next();
                if(st.get(word) == null) {
                    st.put(word, 1);
                }
                else st.put(word, st.get(word) + 1);
                wordsRead++;
            }

            String max = "";
            st.put(max, 0);
            for(String word : st.keys())
                if(st.get(word) > st.get(max))
                    max = word;
            
            endTime = System.nanoTime();
            
            totalTime += (endTime - startTime) / NANO_CONVERSION;
        }
        
        System.out.printf("Average time for %d tests: %fs", N_OF_TESTS, (double)(totalTime/N_OF_TESTS));
    }
}