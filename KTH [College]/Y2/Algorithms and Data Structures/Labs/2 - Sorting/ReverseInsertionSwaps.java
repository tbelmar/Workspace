import java.util.Arrays;

class ReverseInsertionSwaps {
    static int sort(int[] arr) {
        int swaps = 0;
        
        for(int i = 0; i < arr.length; i++)
            arr[i] *= -1;
        
        for(int i = 0; i < arr.length; i++)
            for(int j = i; j > 0 && arr[j] < arr[j-1]; j--) {
                swaps++;
                swap(arr, j, j-1);
            }
        
        for(int i = 0; i < arr.length; i++)
            arr[i] *= -1;
        
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
        int[] array = {5, 8, 2, 27, 15, 32, 1, 7};
        
        System.out.println("Unsorted: " + Arrays.toString(array));
        System.out.println("Number of Swaps: " + sort(array));
        System.out.println("Sorted: " + Arrays.toString(array));
    }
}