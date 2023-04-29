import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

class FindLoop5 {
    private final int V;
    private LinkedList<Integer>[] adj;
    
    public String[] values;
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
    
    public FindLoop5(int V) {
        this.V = V;
        adj = (LinkedList<Integer>[])new LinkedList[V];
        values = new String[V];
        visited = new boolean[V];
        edgeTo = new int[V];
        
        for(int v = 0; v < V; v++)
            adj[v] = new LinkedList<>();
    }
    
    public FindLoop5(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner fileScanner = new Scanner(file);
        int nOfMaxStates = 0;
        while(fileScanner.hasNext()) {
            nOfMaxStates++;
            fileScanner.next();
        }
        
        String[] usedNames = new String[nOfMaxStates];
        int nOfDistinctWords = 0;
        
        fileScanner = new Scanner(file);
        
        while(fileScanner.hasNext()) {
            String word = fileScanner.next();
            
            boolean cont = false;
            for(String name : usedNames)
                if(word.equals(name)) cont = true;
            if(cont) continue;
            
            usedNames[nOfDistinctWords++] = word;
        }
        
        this.V = nOfDistinctWords;
        
        adj = (LinkedList<Integer>[])new LinkedList[V];
        values = new String[V];
        visited = new boolean[V];
        edgeTo = new int[V];
        
        for(int i = 0; i < values.length; i++)
            values[i] = usedNames[i];
        
        for(int v = 0; v < V; v++)
            adj[v] = new LinkedList<>();
        
        fileScanner = new Scanner(file);
        while(fileScanner.hasNextLine()) addEdge(fileScanner.next(), fileScanner.next());
    }
    
    public Integer findIndex(String value) {
        for(int i = 0; i < values.length; i++)
            if(values[i].equals(value))
                return i;
        throw new NoSuchElementException();
    }
    
    public void addEdge(String val1, String val2) {
        Integer v = findIndex(val1);
        Integer w = findIndex(val2);
        assert(v != null && w != null);
        
        addEdge(v, w);
    }
    
    public void addEdge(int v, int w) {
        adj[v].add(w);
    }
    
    public LinkedList<Integer> adj(int v) {
        return adj[v];
    }
    
    public void findPath(String val1, String val2) {
        int v = findIndex(val1), w = findIndex(val2);
        
        findPath(v, w);
    }
    
    private void findPath(int x, int y) {
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
        
        if(foundPath) {
            String[] path = new String[V-1];
            int i = 0;
            
            int currentVert = y;
            while(edgeTo[currentVert] != x) {
                path[i++] = values[edgeTo[currentVert]] + " -> " + values[currentVert];
                currentVert = edgeTo[currentVert];
            }
            path[i++] = values[edgeTo[currentVert]] + " -> " + values[currentVert];
            
            for(i = path.length-1; i >= 0; i--)
                if(path[i] != null)
                    System.out.println(path[i]);
        }
        else System.out.println("No path found.");;
    }
    
    public void findLoop(String val) {
        findPath(val, val);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        FindLoop5 graph = new FindLoop5(args[0]);
        
        System.out.println("Input state that you want to find the loop for.");
        graph.findLoop(sc.next());
    }
}