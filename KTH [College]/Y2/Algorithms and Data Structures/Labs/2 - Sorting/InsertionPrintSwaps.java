import java.util.Arrays;
import java.util.Scanner;

class InsertionPrintSwaps {
    static int sort(int[] arr) {
        int swaps = 0;
        
        for(int i = 0; i < arr.length; i++)
            for(int j = i; j > 0 && arr[j] < arr[j-1]; j--) {
                System.out.println(Arrays.toString(arr));
                swaps++;
                swap(arr, j, j-1);
            }
        System.out.println(Arrays.toString(arr));
        return swaps;
    }
    
    static void swap(int[] arr, int i, int j) {
        if(i > arr.length-1 || j > arr.length-1) throw new IndexOutOfBoundsException();
        if(i == j) return;
        
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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
        sort(array);
        System.out.println("Sorted: " + Arrays.toString(array));
    }
}