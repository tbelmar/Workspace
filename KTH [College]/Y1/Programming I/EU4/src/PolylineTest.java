import java.util.*;

public class PolylineTest {
	public static void main(String[] args) {
		Polyline vPolyline = new VPolyline();
		Polyline nPolyline = new NPolyline();
		
		Point p1 = new Point("A", 3, 4);
		Point p2 = new Point("B", 5, 6);
		Point p3 = new Point("C", 9, 6);
		Point p4 = new Point("D", 3, 7);
		Point p5 = new Point("E", 16, 5);	
		Point p6 = new Point("F", 7, 3);
		Point p7 = new Point("G", 8, 6);
		Point p8 = new Point("H", 11, 3);
		Point p9 = new Point("I", 10, 8);
		Point p10 = new Point("J", 8, 12);
		
		System.out.println("VPolyline Methods:");
		// testing vPolyline method
		vPolyline.add(p1);
		vPolyline.add(p3);
		vPolyline.add(p6);
		vPolyline.add(p4);
		vPolyline.add(p8);
		
		System.out.println("Add:");
		System.out.println(vPolyline);
		
		vPolyline.remove("C");
		vPolyline.setColour("yellow");
		vPolyline.setWidth(4);
		
		System.out.println("Remove:");
		System.out.println(vPolyline);
		
		vPolyline.insertBefore(p3, "E");
		
		System.out.println("Insert Before:");
		System.out.println(vPolyline);
		
		System.out.println("\nNPolyline Methods:");
		// testing nPolyline methods
		nPolyline.add(p1);
		nPolyline.add(p3);
		nPolyline.add(p6);
		nPolyline.add(p4);
		nPolyline.add(p8);
		
		System.out.println("Add:");
		System.out.println(nPolyline);
		
		nPolyline.remove("C");
		nPolyline.setColour("yellow");
		nPolyline.setWidth(4);
		
		System.out.println("Remove:");
		System.out.println(nPolyline);
		
		nPolyline.insertBefore(p3, "E");
		
		System.out.println("Insert Before:");
		System.out.println(nPolyline + "\n");
		
		// testing minYellow method
		Random r = new Random();
		Point[] points = {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10};
		String[] colours = {"blue", "red", "green", "yellow"};
		
		int nofPolys = r.nextInt(7) + 1;
		Polyline[] polys = new Polyline[nofPolys];
		
		for(int i = 0; i < nofPolys; i++) {
			Polyline nPoly = new NPolyline();
			int nofPoints = r.nextInt(10) + 1;
			
			for(int j = 0; j < nofPoints; j++) {
				nPoly.add(points[r.nextInt(10)]);
			}
			
			nPoly.setColour(colours[r.nextInt(4)]);
			polys[i] = nPoly;
		}
		
		System.out.println("Random NPolylines:");
		for(Polyline poly: polys) {
			System.out.println(poly);
		}
		
		System.out.println("Shortest yellow: " + Polylines.minYellow(polys));
		if(Polylines.minYellow(polys) != null)
			System.out.println("This polyline had length: " + Polylines.minYellow(polys).length());
		System.out.println();
		
		nofPolys = r.nextInt(7) + 1;
		polys = new Polyline[nofPolys];
		
		for(int i = 0; i < nofPolys; i++) {
			Polyline vPoly = new VPolyline();
			int nofPoints = r.nextInt(10) + 1;
			for(int j = 0; j < nofPoints; j++) {
				vPoly.add(points[r.nextInt(10)]);
			}
			
			vPoly.setColour(colours[r.nextInt(4)]);
			polys[i] = vPoly;
		}
		
		System.out.println("Random VPolylines:");
		for(Polyline poly: polys) {
			System.out.println(poly);
		}
		
		System.out.println("Shortest yellow: " + Polylines.minYellow(polys));
		if(Polylines.minYellow(polys) != null)
			System.out.println("This polyline had length: " + Polylines.minYellow(polys).length());
		System.out.println();
		
		nofPolys = r.nextInt(7) + 1;
		polys = new Polyline[nofPolys];
		
		for(int i = 0; i < nofPolys; i++) {
			Polyline vPoly = new VPolyline();
			Polyline nPoly = new NPolyline();
			Polyline[] polyTypes = {vPoly, nPoly};
			int whichPoly = r.nextInt(2);
			int nofPoints = r.nextInt(10) + 1;
			
			for(int j = 0; j < nofPoints; j++) {
				polyTypes[whichPoly].add(points[r.nextInt(10)]);
			}
			
			polyTypes[whichPoly].setColour(colours[r.nextInt(4)]);
			polys[i] = polyTypes[whichPoly];
		}
		
		System.out.println("Random Mixed-Type Polylines:");
		for(Polyline poly: polys) {
			System.out.println(poly);
		}
		
		System.out.println("Shortest yellow: " + Polylines.minYellow(polys));
		if(Polylines.minYellow(polys) != null)
			System.out.println("This polyline had length: " + Polylines.minYellow(polys).length());
	}
}
