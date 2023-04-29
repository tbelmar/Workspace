public class Polyline {
	
	public class PolylineIterator {
		private int current = -1;
		
		public PolylineIterator() {
			if(Polyline.this.vertices.length > 0)
				current = 0;
		}
		
		public boolean hasVertex() {
			return current != -1;
		}
		
		public Point vertex() throws java.util.NoSuchElementException {
			if(!this.hasVertex())
				throw new java.util.NoSuchElementException("End of iteration.");
			Point vertex = Polyline.this.vertices[current];
			
			return vertex;
		}
		
		public void advance() {
			if(current >= 0 && current < Polyline.this.vertices.length - 1)
				current++;
			else
				current = -1;
		}
	}
	
	private Point[] vertices;
	private String colour = "black";
	private int width = 1;
	
	public Polyline() {
		this.vertices = new Point[0];
	}
	
	public Polyline(Point[] vertices) {
		this.vertices = new Point[vertices.length];
		for(int i = 0; i < vertices.length; i++)
			this.vertices[i] = new Point(vertices[i]);
	}
	
	public String toString() {
		String s = "{[";
		for(int i = 0; i < this.vertices.length; i++) {
			s = s + this.vertices[i];
		}
		s = s + "], " + colour + ", " + width + "}";
		return s;
	}
	
	public Point[] getVertices() {
		return this.vertices;
	}
	
	public String getColour() {
		return this.colour;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	// Returns the length of the Polyline
	// Length of a Polyline is defined as the sum of the distance between all adjacent points.
	public double length() {
		double len = 0;
		for(int i = 1; i < this.vertices.length; i++)
			len += this.vertices[i-1].distance(this.vertices[i]);
		return len;
	}
	
	// Adds a Point to the end of the Polyline.
	public void addLast(Point vertex) {
		Point[] h = new Point[this.vertices.length + 1];
		int i = 0;
		for(i = 0; i < this.vertices.length; i++)
			h[i] = this.vertices[i];
		h[i] = new Point(vertex);
		
		this.vertices = h;
	}
	
	// Adds a Point to the beginning of the Polyline.
	public void addBefore(Point vertex, String vertexName) {
		Point[] h = new Point[this.vertices.length + 1];
		int i = 1;
		for(i = 1; i <= this.vertices.length; i++)
			h[i] = this.vertices[i-1];
		h[0] = new Point(vertexName, vertex.getX(), vertex.getY());
		
		this.vertices = h;
	}
	
	// Removes the first Point in the Polyline with a specific name.
	public void remove(String vertexName) {
		Point[] h = new Point[this.vertices.length - 1];
		int i = 0;
		for(i = 0; i < this.vertices.length; i++) {
			if(!this.vertices[i].getName().equals(vertexName)) {
				h[i] = new Point(this.vertices[i]);
				continue;
			}
			break;
		}
		for(i++; i < this.vertices.length; i++) {
			h[i-1] = new Point(this.vertices[i]);
		}
		this.vertices = h;
	}
	
	
}
