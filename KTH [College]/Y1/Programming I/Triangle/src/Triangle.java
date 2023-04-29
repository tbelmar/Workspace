public class Triangle {
	public static double bisector(double b, double c, double alpha) {
		double p = 2 * b * c * Math.cos(alpha/2);
		double bis = p / (b+c);
		
		return bis;
	}
	
	public static double area(double b, double h) {
		double a = b * h / 2;
		
		return a;
	}
	
	public static double perimeter(double a, double b, double c) {
		double p = a + b + c;
		
		return p;
	}
	
	public static double circumradius(double a, double b, double c) {
		return Math.sqrt((Math.pow(a*b*c, 2)) / ((a + b + c) * (b + c - a) * (a - b + c) * (a + b - c)));
	}
	
	public static double inradius(double a, double b, double c) {
		return Math.sqrt(((b + c - a)*(a - b + c)*(a + b - c)) / (4*(a + b + c)));
	}
}
