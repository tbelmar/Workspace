import java.util.Iterator;
import java.util.Scanner;

class DoubleLinkedCircularQueue<Item> implements Iterable<Item> {
    private Node sentinel = new Node();
    
    DoubleLinkedCircularQueue() {
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
    }
    
    private class Node {
        Node previous, next;
        Item item;
        
        Node() {}
        
        Node(Item item) {
            this.item = item;
        }
        
        Node(Node previous, Node next, Item item) {
            this.previous = previous;
            this.next = next;
            this.item = item;
        }
    }
    
    private class QueueIterator implements Iterator<Item> {
        private Node current = sentinel.next;
        
        @Override
        public boolean hasNext() {
            return current != sentinel;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    
    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }
    
    void enqueue(Item item) {
        Node node = new Node(item);
        node.next = sentinel;
        node.previous = sentinel.previous;
        sentinel.previous.next = node;
        sentinel.previous = node;
        
        if(isEmpty()) {
            sentinel.next = node;
            return;
        }
    }
    
    Item dequeue() {
        if(isEmpty()) throw new IllegalArgumentException("List is empty");
        
        Item firstItem = sentinel.next.item;
        
        if(sentinel.next == sentinel.previous) {
            sentinel.next = sentinel;
            sentinel.previous = sentinel;
            
            return firstItem;
        }
        
        sentinel.next = sentinel.next.next;
        sentinel.next.previous = sentinel;
        
        return firstItem;
    }
    
    boolean isEmpty() {
        return sentinel.next == sentinel;
    }
    
    int size() {
        int count = 0;
        for(Item item : this)
            count++;
        return count;
    }
    
    @Override
    public String toString() {
        String str = "";
        
        for(Object obj : this)
            str += "[" + obj + "], ";
        
        if(str.length() > 0)
            str = str.substring(0, str.length()-2);
        
        return str;
    }
    
    public static void main(String args[]) {
        DoubleLinkedCircularQueue<Integer> queue = new DoubleLinkedCircularQueue<>();
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