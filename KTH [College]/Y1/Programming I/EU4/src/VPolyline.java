import java.util.Iterator;

public class VPolyline implements Polyline {
	class PolylineIterator implements Iterator<Point> {
		private int current = -1;
		
		public PolylineIterator() {
			if(VPolyline.this.vertices.length > 0)
				current = 0;
		}
		
		public Point next() {
			//if(current < 0 || current > VPolyline.this.vertices.length - 1)
			//	return null;
			return VPolyline.this.vertices[current++];
		}

		public boolean hasNext() {
			return VPolyline.this.vertices.length > current;
		}
	}
	
	private Point[] vertices = {};
	private String colour = "black";
	private int width = 1;
	
	public Point[] getVertices() {
		return this.vertices;
	}
	
	public String getColour() {
		return this.colour;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public double length() {
		double len = 0;
		for(int i = 1; i < this.vertices.length; i++)
			len += this.vertices[i-1].distance(this.vertices[i]);
		return len;
	}
	
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void add(Point vertex) {
		Point[] h = new Point[this.vertices.length + 1];
		int i = 0;
		for(i = 0; i < this.vertices.length; i++)
			h[i] = this.vertices[i];
		h[i] = new Point(vertex);
		
		this.vertices = h;
	}
	
	public void insertBefore(Point vertex, String vertexName) {
		Point[] h = new Point[this.vertices.length + 1];
		int i = 0;
		for(i = 0; i < this.vertices.length; i++) {
			if(this.vertices[i].getName().equals(vertexName))
				break;
			h[i] = new Point(this.vertices[i]);
		}
		h[i] = vertex;
		for(i++; i < h.length; i++) {
			h[i-1] = new Point(this.vertices[i]);
		}
		this.vertices = h;
	}
	
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

	@Override
	public Iterator<Point> iterator() {
		return new PolylineIterator();
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
} 
