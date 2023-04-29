import java.util.Scanner;

class BSTPrint7 {
    final static int WORD_LIMIT = 200;
    
    private Node root;

    private class Node {
        private String value;
        private Node left, right;
        private int n;

        public Node(String value, int n) {
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

    public void put(String value) {
        root = put(root, value);
    }

    public Node put(Node node, String value) {
        if(node == null) return new Node(value, 1);
        int compare = value.compareTo(node.value);
        if(compare < 0) node.left = put(node.left, value);
        else if(compare > 0) node.right = put(node.right, value);
        else node.value = value;
        node.n = size(node.left) + size(node.right) + 1;
        return node;
    }
    
    void printInOrder() {
        printInOrder(root);
    }
    
    void printInOrder(Node node) {
        if(node == null) return;
        printInOrder(node.left);
        System.out.println(node.value);
        printInOrder(node.right);
    }
    
    void printInReverseOrder() {
        printInReverseOrder(root);
    }
    
    void printInReverseOrder(Node node) {
        if(node == null) return;
        printInReverseOrder(node.right);
        System.out.println(node.value);
        printInReverseOrder(node.left);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        BSTPrint7 bst = new BSTPrint7();
        int wordsRead = 0;
        
        while(sc.hasNext() && wordsRead < WORD_LIMIT) {
            bst.put(sc.next());
            wordsRead++;
        }
        
        System.out.println("In Alphabetical Order:");
        bst.printInOrder();
        
        System.out.println("In Reverse Order:");
        bst.printInReverseOrder();
    }
}