import java.util.Arrays;
import java.util.Scanner;

class InsertionCountInversions {
    static void sort(int[] arr) {
        for(int i = 0; i < arr.length; i++)
            for(int j = i; j > 0 && arr[j] < arr[j-1]; j--) {
                System.out.println(Arrays.toString(arr));
                swap(arr, j, j-1);
            }
        System.out.println(Arrays.toString(arr));
    }
    
    static void swap(int[] arr, int i, int j) {
        if(i > arr.length-1 || j > arr.length-1) throw new IndexOutOfBoundsException();
        if(i == j) return;
        
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    static int inversions(int[] arr) {
        int inversions = 0;
        for(int i = 0; i < arr.length-1; i++)
            for(int j = i; j < arr.length; j++)
                if(arr[j] < arr[i]) {
                    inversions++;
                    System.out.printf("[%d, %d], [%d, %d]%n", i, arr[i], j, arr[j]);
                }
        return inversions;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("What is the size of your input?");
        int size = sc.nextInt();
        
        System.out.println("Please input your numbers, one line each.");
        int[] array = new int[size];
        for(int i = 0; i < size; i++)
            array[i] = sc.nextInt();
        
        System.out.println("Unsorted: " + Arrays.toString(array));
        System.out.println("Number of Inversions: " + inversions(array));
        sort(array);
        System.out.println("Sorted: " + Arrays.toString(array));
    }
}