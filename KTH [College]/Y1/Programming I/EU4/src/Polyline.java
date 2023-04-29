public interface Polyline extends java.lang.Iterable<Point> {
	
	Point[] getVertices ();
	
	String getColour();
	
	int getWidth();
	
	double length();
	
	void setColour(String colour);
	
	void setWidth(int width);
	
	void add(Point vertex);
	
	void insertBefore(Point vertex, String vertexName);
	
	void remove(String vertexName);
	
	java.util.Iterator<Point> iterator();
}
