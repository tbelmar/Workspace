public class LeastIntController {
	public static void main(String[] args) {
		int[] nums = {67, 23, 65, 38, 95, 18, 5, 27, 16, 58, 64, 23, 67, 234, 2456, 92, 1, 43, 867};
		System.out.println("Least Integer: " + LeastInteger.min(nums));
		
		System.out.println("Least Integer Update: " + LeastIntegerUpdate.min(nums));
	}
}
