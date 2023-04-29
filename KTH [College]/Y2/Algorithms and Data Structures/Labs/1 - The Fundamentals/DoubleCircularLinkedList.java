import java.util.Iterator;
import java.util.Scanner;

class DoubleCircularLinkedList<Item> implements Iterable<Item> {
    private Node sentinel = new Node();
    
    DoubleCircularLinkedList() {
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
    
    void add(Item item, boolean atBeginning) {
        Node node = new Node(item);
        
        if(isEmpty()) {
            sentinel.next = node;
            sentinel.previous = node;
            node.next = sentinel;
            node.previous = sentinel;
            return;
        }
        
        if(atBeginning) {
            node.previous = sentinel;
            node.next = sentinel.next;
            node.next.previous = node;
            sentinel.next = node;
            return;
        }
        
        node.next = sentinel;
        node.previous = sentinel.previous;
        node.previous.next = node;
        sentinel.previous = node;
        
    }
    
    Item remove(boolean atBeginning) {
        if(isEmpty()) throw new IllegalArgumentException("List is empty");
        
        Item returnItem;
        
        if(atBeginning)
            returnItem = sentinel.next.item;
        else
            returnItem = sentinel.previous.item;
        
        if(sentinel.next == sentinel.previous) {
            sentinel.next = sentinel;
            sentinel.previous = sentinel;
            
            return returnItem;
        }
        
        if(atBeginning) {
            sentinel.next = sentinel.next.next;
            sentinel.next.previous = sentinel;
            return returnItem;
        }
        
        sentinel.previous = sentinel.previous.previous;
        sentinel.previous.next = sentinel;
        return returnItem;
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
        DoubleCircularLinkedList<Integer> queue = new DoubleCircularLinkedList<>();
        Scanner sc = new Scanner(System.in);
        
        boolean isDone = false;
        while(!isDone) {
            System.out.println("Would you like to enqueue, dequeue, push, pop, or exit? (enqueue/dequeue/push/pop/exit)");
            switch(sc.nextLine().toLowerCase()) {
                case "enqueue":
                    System.out.println("Enter an integer.");
                    queue.add(sc.nextInt(), true);
                    System.out.println(queue);
                    break;
                case "dequeue":
                    queue.remove(true);
                    System.out.println(queue);
                    break;
                case "push":
                    System.out.println("Enter an integer.");
                    queue.add(sc.nextInt(), false);
                    System.out.println(queue);
                    break;
                case "pop":
                    queue.remove(false);
                    System.out.println(queue);
                    break;
                case "exit":
                    isDone = true;
                    break;
            }
        }
    }
}