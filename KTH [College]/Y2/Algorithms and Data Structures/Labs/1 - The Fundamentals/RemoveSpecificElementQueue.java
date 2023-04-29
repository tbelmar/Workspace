import java.util.Scanner;

class RemoveSpecificElementQueue<Item> {
    Node first, last;
    int size = 0;
    
    private class Node {
        Node next;
        Item item;
        
        Node() {}
        
        Node(Item item) {
            this.item = item;
        }
        
        Node(Node next, Item item) {
            this.next = next;
            this.item = item;
        }
    }
    
    void enqueue(Item item) {
        size++;
        Node node = new Node(item);
        
        if(isEmpty()) {
            first = node;
            last = node;
            return;
        }
        
        last.next = node;
        last = node;
    }
    
    Item dequeue() {
        if(isEmpty()) throw new IllegalArgumentException("List is empty");
        
        size--;
        
        Item firstItem = first.item;
        first = first.next;
        
        return firstItem;
    }
    
    Item removeKthItem(int k) {
        if(k > size) throw new IndexOutOfBoundsException();
        if(k == 1) return dequeue();
        
        Node previous = first;
        Node current = first.next;
        
        int i = 2;
        while(current != null) {
            if(i++ == k) previous.next = current.next;
            previous = previous.next;
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
            str += "[" + current.item + "], ";
            current = current.next;
        }
        
        if(str.length() > 0)
            str = str.substring(0, str.length()-2);
        
        return str;
    }
    
    public static void main(String args[]) {
        RemoveSpecificElementQueue<Integer> queue = new RemoveSpecificElementQueue<>();
        Scanner sc = new Scanner(System.in);
        
        boolean isDone = false;
        while(!isDone) {
            System.out.println("Would you like to enqueue, dequeue, remove an item at the kth position, or exit? (enqueue/dequeue/remove/exit)");
            String input = sc.nextLine();
            
            switch(input.toLowerCase()) {
                case "enqueue":
                    System.out.println("Please input an integer.");
                    queue.enqueue(sc.nextInt());
                    System.out.println(queue);
                    break;
                case "dequeue":
                    queue.dequeue();
                    System.out.println(queue);
                    break;
                case "remove":
                    System.out.println("Please input k");
                    queue.removeKthItem(sc.nextInt());
                    System.out.println(queue);
                    break;
                case "exit":
                    isDone = true;
                    break;
            }
        }
    }
}