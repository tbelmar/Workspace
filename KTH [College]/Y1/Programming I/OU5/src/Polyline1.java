public class Polyline1 {
	private Point[] vertices;
	private String colour = "black";
	private int width = 1;
	
	public Polyline1() {
		this.vertices = new Point[0];
	}
	
	public Polyline1(Point[] vertices) {
		this.vertices = new Point[vertices.length];
		for(int i = 0; i < vertices.length; i++)
			this.vertices[i] = vertices[i];
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
	
	public double length() {
		double len = 0;
		for(int i = 1; i < this.vertices.length; i++)
			len += this.vertices[i-1].distance(this.vertices[i]);
		return len;
	}
	
	public void addLast(Point vertex) {
		Point[] h = new Point[this.vertices.length + 1];
		int i = 0;
		for(i = 0; i < this.vertices.length; i++)
			h[i] = this.vertices[i];
		h[i] = vertex;
		
		this.vertices = h;
	}
	
	public void addBefore(Point vertex, String vertexName) {
		Point[] h = new Point[this.vertices.length + 1];
		int i = 1;
		for(i = 1; i <= this.vertices.length; i++)
			h[i] = this.vertices[i-1];
		h[0] = vertex;
		h[0].setName(vertexName);
		
		this.vertices = h;
	}
	
	public void remove(String vertexName) {
		Point[] h = new Point[this.vertices.length - 1];
		int i = 0;
		for(i = 0; i < this.vertices.length; i++) {
			if(!this.vertices[i].getName().equals(vertexName)) {
				h[i] = this.vertices[i];
				continue;
			}
			break;
		}
		for(i++; i < this.vertices.length; i++) {
			h[i-1] = this.vertices[i];
		}
		this.vertices = h;
	}
}
