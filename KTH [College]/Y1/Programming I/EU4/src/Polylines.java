public class Polylines {
	public static Polyline minYellow(Polyline[] polys) {
		Polyline shortest = null;
		for(Polyline poly: polys) {
			if((shortest == null || poly.length() < shortest.length()) && poly.getColour().equals("yellow"))
				shortest = poly;
		}
		
		return shortest;
	}
}
