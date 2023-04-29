import java.util.*;

public class SelectPolyline {
	public static final Random rand = new Random();
	public static final int NOF_POLYLINES = 10;
	
	public static void main(String[] args) {
		// Create a random number of polylines
		Polyline[] polylines = new Polyline[NOF_POLYLINES];
		for(int i = 0; i < NOF_POLYLINES; i++)
			polylines[i] = randomPolyline();
		
		// Show the polylines
		for(int i = 0; i < polylines.length; i++)
			System.out.println(polylines[i]);
		
		// Determine the shortest yellow polyline
		Polyline p = null;
		for(int i = 0; i < polylines.length; i++) {
			if(!polylines[i].getColour().equals("yellow"))
				continue;
			if(p == null || polylines[i].length() < p.length())
				p = polylines[i];
		}
		// Show the selected polyline
		if(p == null)
			System.out.println("There are no yellow Polylines.");
		else
			System.out.println("The shortest yellow Polyline is: " + p + " with length " + p.length());
	}
	
	// The  randomPoint  method  returns a new  Point  with a name
	// randomly  chosen  from  the  single  letters A--Z. Coordinates
	// are  random.
	public static Point randomPoint() {
		String n = "" + (char)(65 + rand.nextInt(26));
		int x = rand.nextInt(11);
		int y = rand.nextInt(11);
		
		return new Point(n, x, y);
	}
	
	// The  method  randomPolyline  returns a random  polyline ,
	// with a colour  either  blue , red , or  yellow. The  names
	// of the  vertices  are  single  letters  from  the  set A--Z.
	// Two  vertices  can  not  have  the  same  name.
	public static Polyline randomPolyline() {
		// Create an empty polyline and add vertices
		Polyline polyline = new Polyline();
		int nofVertices = 2 + rand.nextInt(7);
		int nofSelectedVertices = 0;
		boolean[] selectedNames = new boolean[26];
		
		// Two vertices can not have the same name
		Point chosenPoint = null;
		while(nofSelectedVertices < nofVertices) {
			chosenPoint = randomPoint();
			while(selectedNames[chosenPoint.getName().charAt(0) - 65])
				chosenPoint = randomPoint();
			selectedNames[chosenPoint.getName().charAt(0) - 65] = true;
			
			polyline.addLast(chosenPoint);
			
			nofSelectedVertices++;
		}
		
		// Assign a colour
		String[] colours = {"blue", "red", "yellow"};
		polyline.setColour(colours[rand.nextInt(3)]);
		
		return polyline;
	}
}
