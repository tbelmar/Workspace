import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.lang.NoSuchFieldException;

class DirectedBFS6 {
    private final int V;
    private LinkedList<Integer>[] adj;
    
    public boolean[] visited;
    private int[] edgeTo;
    
    private class LinkedList<Item> {
        Node root;
        
        public void add(Item item) {
            if(root == null) {
                root = new Node(item);
                return;
            }
            
            Node current = root;
            while(current.next != null)
                current = current.next;
            current.next = new Node(item);
        }
        
        public Item remove() {
            if(isEmpty()) throw new NoSuchElementException();
            
            Item item = (Item)root.value;
            root = root.next;
            return item;
        }
        
        public boolean isEmpty() {
            return root == null;
        }
    }
    
    private class Node<Item> {
        Node next;
        Item value;
        
        Node(Item value) {
            this.value = value;
        }
    }
    
    public DirectedBFS6(int V) {
        this.V = V;
        adj = (LinkedList<Integer>[])new LinkedList[V];
        visited = new boolean[V];
        edgeTo = new int[V];
        
        for(int v = 0; v < V; v++)
            adj[v] = new LinkedList<>();
    }
    
    public DirectedBFS6(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner fileScanner = new Scanner(file);
        
        int nOfV = fileScanner.nextInt();
        this.V = nOfV;
        
        fileScanner.next();
        
        adj = (LinkedList<Integer>[])new LinkedList[V];
        visited = new boolean[V];
        edgeTo = new int[V];
        
        for(int v = 0; v < V; v++)
            adj[v] = new LinkedList<>();
        
        while(fileScanner.hasNextInt()) {
            addEdge(fileScanner.nextInt(), fileScanner.nextInt());
            fileScanner.next();
        }
    }
    
    public void addEdge(int v, int w) {
        adj[v].add(w);
    }
    
    public LinkedList<Integer> adj(int v) {
        return adj[v];
    }
    
    public void findPathIntermediate(int x, int y, int z) throws NoSuchFieldException {
        findPath(x, y);
        findPath(y, z);
    }
    
    private void findPath(int x, int y) throws NoSuchFieldException {
        edgeTo[x] = x;
        
        LinkedList<Integer> q = new LinkedList<>();
        q.add(x);
        visited[x] = true;
        boolean foundPath = false;
        
        while(!q.isEmpty()) {
            int v = q.remove();
            Node current = adj[v].root;
            
            while(current != null) {
                int nodeValue = (Integer)current.value;
                if(!visited[nodeValue] || nodeValue == x) {
                    q.add(nodeValue);
                    visited[nodeValue] = true;
                    edgeTo[nodeValue] = v;
                    if(nodeValue == y) foundPath = true;
                }
                current = current.next;
            }
        }
        
        for(int i = 0; i < visited.length; i++)
            visited[i] = false;
        
        if(foundPath) {
            String[] path = new String[V-1];
            int i = 0;
            
            int currentVert = y;
            while(edgeTo[currentVert] != x) {
                path[i++] = edgeTo[currentVert] + " -> " + currentVert;
                currentVert = edgeTo[currentVert];
            }
            path[i++] = edgeTo[currentVert] + " -> " + currentVert;
            
            for(i = path.length-1; i >= 0; i--)
                if(path[i] != null)
                    System.out.println(path[i]);
        }
        else throw new NoSuchFieldException("No path exists.");
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        DirectedBFS6 graph = new DirectedBFS6(args[0]);
        
        System.out.println("Input the three points you would like to traverse.");
        try {
            graph.findPathIntermediate(sc.nextInt(), sc.nextInt(), sc.nextInt());
        }
        catch(NoSuchElementException e) {
            System.out.println("Invalid argument.");
        }
        catch(NoSuchFieldException e) {
            System.out.println("No path found.");
        }
    }
}