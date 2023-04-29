import java.util.Scanner;

class OrderedQueue {
    Node first;
    int size = 0;
    
    private class Node implements Comparable {
        Node next;
        int value;
        
        Node() {}
        
        Node(int item) {
            this.value = item;
        }
        
        Node(Node next, int value) {
            this.next = next;
            this.value = value;
        }

        @Override
        public int compareTo(Object other) {
            return value - (int)other;
        }
    }
    
    void enqueue(int item) {
        size++;
        Node node = new Node(item);
        
        if(isEmpty()) {
            first = node;
            return;
        }
        
        Node current = first;
        
        int i = 0;
        while(current != null && item > current.value) {
            i++;
            current = current.next;
        }
        
        this.insert(item, i);
    }
    
    int dequeue() {
        if(isEmpty()) throw new IllegalArgumentException("List is empty");
        
        size--;
        
        int firstItem = first.value;
        first = first.next;
        
        return firstItem;
    }
    
    private void insert(int item, int pos) {
        if(pos > size) throw new IndexOutOfBoundsException();
        size++;
        
        Node node = new Node(item);
        Node current = first;
        
        if(pos == 0) {
            node.next = first;
            first = node;
            return;
        }
        
        while(current != null) {
            if(--pos == 0) {
                node.next = current.next;
                current.next = node;
                break;
            }
            current = current.next;
        }
    }
    
    private Integer valueAtPos(int k) {
        Node current = first;
        while(current != null) {
            if(k-- == 0) return current.value;
            current = current.next;
        }
        return null;
    }
    
    boolean isEmpty() {
        return first == null;
    }
    
    int size() {
        return size;
    }
    
    @Override
    public String toString() {
        String str = "";
        
        Node current = first;
        
        while(current != null) {
            str += "[" + current.value + "], ";
            current = current.next;
        }
        
        if(str.length() > 0)
            str = str.substring(0, str.length()-2);
        
        return str;
    }
    
    public static void main(String[] args) {
        OrderedQueue queue = new OrderedQueue();
        Scanner sc = new Scanner(System.in);
        
        boolean isDone = false;
        while(!isDone) {
            System.out.println("Would you like to enqueue, dequeue or exit? (enqueue/dequeue/exit)");
            switch(sc.nextLine().toLowerCase()) {
                case "enqueue":
                    System.out.println("Enter an integer.");
                    queue.enqueue(sc.nextInt());
                    System.out.println(queue);
                    break;
                case "dequeue":
                    queue.dequeue();
                    System.out.println(queue);
                    break;
                case "exit":
                    isDone = true;
                    break;
            }
        }
    }
}