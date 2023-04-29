import java.util.Arrays;

class ReverseInsertionInversions {
    static void sort(int[] arr) {
        for(int i = 0; i < arr.length; i++)
            arr[i] *= -1;
            
        for(int i = 0; i < arr.length; i++)
            for(int j = i; j > 0 && arr[j] < arr[j-1]; j--)
                swap(arr, j, j-1);
        
        for(int i = 0; i < arr.length; i++)
            arr[i] *= -1;
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
        int[] array = {5, 8, 2, 27, 15, 32, 1, 7};
        
        
        System.out.println("Unsorted: " + Arrays.toString(array));
        
        int nOfInversions = inversions(array);
        System.out.printf("Number of Inversions: %d%n%n", nOfInversions);
        
        sort(array);
        
        System.out.println("Sorted: " + Arrays.toString(array));
    }
}