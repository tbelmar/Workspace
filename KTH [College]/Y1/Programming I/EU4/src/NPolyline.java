import java.util.Iterator;

public class NPolyline implements Polyline {
	private static class Node {
		public Point vertex;
		public Node nextNode;
		
		public Node(Point vertex) {
			this.vertex = vertex;
			nextNode = null;
		}
	}
	
	public class PolylineIterator implements Iterator<Point> {
		private Node current = new Node(new Point("0", 0, 0));
		
		public PolylineIterator() {
			if(vertices != null) {
				current.nextNode = vertices;
			}
		}
		
		public Node nextNode() {
			current = current.nextNode;
			return current;
		}
		
		public Point next() {
			return this.nextNode().vertex;
		}

		public boolean hasNext() {
			return current.nextNode != null;
		}
	}
	
	private Node vertices;
	private String colour = "black";
	private int width = 1;
	
	public NPolyline() {
		this.vertices = null;
	}
	
	public NPolyline(Point[] vertices) {
		if(vertices.length > 0) {
			Node node = new Node(new Point(vertices[0]));
			this.vertices = node;
			int pos = 1;
			while(pos < vertices.length) {
				node.nextNode = new Node(new Point(vertices[pos++]));
				node = node.nextNode;
			}
		}
	}
	
	public Point[] getVertices() {
		PolylineIterator pIterator = new PolylineIterator();
		
		int iter = 0;
		for(iter = 0; pIterator.hasNext(); iter++)
			iter++;
		Point[] h = new Point[iter];
		for(int i = 0; pIterator.hasNext(); i++)
			h[i] = pIterator.next();
		
		return h;
	}
	
	public String getColour() {
		return this.colour;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public double length() {
		PolylineIterator pIterator = new PolylineIterator();
		double len = 0;
		
		while(pIterator.hasNext()) {
			len += pIterator.current.vertex.distance(pIterator.next());
		}
		
		return len;
	}
	
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void add(Point vertex) {
		if(vertices == null) {
			vertices = new Node(vertex);
			return;
		}
		
		PolylineIterator pIterator = new PolylineIterator();
		Node last = vertices;
		while(pIterator.hasNext()) {
			last = pIterator.nextNode();
		}
		last.nextNode = new Node(vertex);
	}
	
	public void insertBefore(Point vertex, String vertexName) {
		Node newNode = new Node(vertex);
		
		PolylineIterator pIterator = new PolylineIterator();
		if(vertexName.equals(this.vertices.vertex.getName())) {
			newNode.nextNode = this.vertices;
			this.vertices = newNode;
			return;
		}
		
		while(pIterator.hasNext()) {
			Node prev = pIterator.current;
			if(pIterator.next().getName().equals(vertexName)) {
				prev.nextNode = newNode;
				newNode.nextNode = pIterator.current;
			}
		}
	}
	
	public void remove(String vertexName) {
		PolylineIterator pIterator = new PolylineIterator();
		if(vertexName.equals(this.vertices.vertex.getName())) {
			vertices = vertices.nextNode;
			return;
		}
		
		while(pIterator.hasNext()) {
			Node prev = pIterator.current;
			if(vertexName.equals(pIterator.next().getName()))
				prev.nextNode = pIterator.nextNode();
		}
	}
	
	@Override
	public String toString() {
		PolylineIterator pIterator = new PolylineIterator();
		String s = "{[";
		while(pIterator.hasNext())
			s = s + pIterator.next();
		s = s + "], " + colour + ", " + width + "}";
		return s;
	}

	@Override
	public Iterator<Point> iterator() {
		return new PolylineIterator();
	}
}
