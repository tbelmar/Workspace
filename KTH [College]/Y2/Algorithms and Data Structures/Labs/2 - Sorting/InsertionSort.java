import java.util.Arrays;

class InsertionSort {
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
    
    public static void main(String[] args) {
        
    }
}