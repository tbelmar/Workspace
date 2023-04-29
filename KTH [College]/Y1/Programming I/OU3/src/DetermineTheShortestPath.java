import java.util.*;

public class DetermineTheShortestPath {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("How many stations in Zone 2?");
		int m = sc.nextInt();
		System.out.println("How many stations in Zone 4?");
		int n = sc.nextInt();
		
		double[] a = new double[m], 
				c = new double[n];
		double[][] b = new double[m][n];
		
		for(int i = 1; i <= m; i++) {
			System.out.println("Enter the length between station X and station U" + i);
			a[i-1] = sc.nextInt();
			for(int j = 1; j <= n; j++) {
				System.out.println("Enter the length between station U" + i + " and station V" + j);
				b[i-1][j-1] = sc.nextInt();
				System.out.println("Enter the length between station V" + j + " and station Y");
				c[j-1] = sc.nextInt();
			}
		}
		
		System.out.println("The shortest path from X to Y is " + TheShortestPath.length(a, b, c) + "kms long.");
		
		int[] path = TheShortestPath.intermediateStations(a, b, c);
		System.out.println("The path is the following:");
		System.out.println("X -> U" + (path[0]+1) + " -> V" + (path[1]+1) + " -> Y");
	}
}
