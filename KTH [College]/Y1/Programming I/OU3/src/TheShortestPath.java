 public class TheShortestPath {
	public static int[] intermediateStations(double[] a, double[][] b, double[] c) {
		double shortestLength = a[0] + b[0][0] + c[0];
		int[] shortestPath = new int[2];
		
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < c.length; j++) {
				if(a[i] + b[i][j] + c[j] < shortestLength) {
					shortestLength = a[i] + b[i][j] + c[j];
					shortestPath[0] = i;
					shortestPath[1] = j;
				}
			}
		}
		
		return shortestPath;
	}
	
	public static double length(double[] a, double[][] b, double[] c) {
		int[] path = TheShortestPath.intermediateStations(a, b, c);
		return a[path[0]] + b[path[0]][path[1]] + c[path[1]];
	}
}
