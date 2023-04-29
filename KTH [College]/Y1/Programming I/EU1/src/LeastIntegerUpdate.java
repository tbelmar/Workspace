public class LeastIntegerUpdate {
	public static int min(int[] elements) throws IllegalArgumentException {
		if(elements.length == 0)
			throw new IllegalArgumentException("empty collection");
		
		int min = elements[0];
		for(int i = 1; i < elements.length; i++)
			if(elements[i] < min)
				min = elements[i];
		
		return min;
	}
}