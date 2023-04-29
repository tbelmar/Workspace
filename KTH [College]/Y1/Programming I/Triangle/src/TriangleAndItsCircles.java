public class TriangleAndItsCircles {
	public static void main(String[] args) {
		System.out.println("Triangle with side lengths 5, 4, 3:");
		System.out.println("Perimeter: " + Triangle.perimeter(5, 4, 3));
		System.out.println("Circumradius: " + Triangle.circumradius(5, 4, 3));
		System.out.println("Inradius: " + Triangle.inradius(5, 4, 3));
	}
}
