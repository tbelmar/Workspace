import java.io.*;

public class PolylineTest {
	public static void main(String[] args) {
		PrintWriter out = new PrintWriter(System.out, true);
		
		Point p1 = new Point("A", 3, 4);
		Point p2 = new Point("B", 5, 6);
		Point p3 = new Point("C", 9, 6);
		Point p4 = new Point("D", 3, 7);
		Point p5 = new Point("E", 10, 5);
		
		Point[] points = {p2, p3, p4};
		
		Polyline poly = new Polyline(points);
		
		out.println(poly);
		out.println("Length: " + poly.length());
		out.println();
		
		poly.addBefore(p1, "A");
		out.println(poly);
		out.println("Length: " + poly.length());
		out.println();
		
		poly.addLast(p5);
		out.println(poly);
		out.println("Length: " + poly.length());
		out.println();
		
		poly.remove("C");
		out.println(poly);
		out.println("Length: " + poly.length());
		out.println();
		
		poly.setWidth(2);
		poly.setColour("pink");
		out.println(poly);
		out.println("Length: " + poly.length());
		
		System.out.println("\nIndividual vertices of the Polyline:");
		Polyline.PolylineIterator polyIterator = poly.new PolylineIterator();
		while(polyIterator.hasVertex()) {
			System.out.println(polyIterator.vertex());
			polyIterator.advance();
		}
	}
}
