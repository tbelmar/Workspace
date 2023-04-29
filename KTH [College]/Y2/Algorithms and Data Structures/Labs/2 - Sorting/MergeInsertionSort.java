import java.util.Arrays;

class MergeInsertionSort {
    static void sort(int[] arr) {
        int[] aux = new int[arr.length];
        mergeSort(arr, aux, 0, arr.length - 1, 0);
    }
    
    static void sort(int[] arr, int cutoff) {
        int[] aux = new int[arr.length];
        mergeSort(arr, aux, 0, arr.length - 1, cutoff);
    }
    
    private static void mergeSort(int[] arr, int[] aux, int low, int high, int cutoff) {
        if(high <= low) return;
        
        if(high + 1 - low <= cutoff) {
            insertionSort(arr, low, high);
            return;
        }
        
        int mid = low + (high - low) / 2;
        mergeSort(arr, aux, low, mid, cutoff);
        mergeSort(arr, aux, mid+1, high, cutoff);
        merge(arr, aux, low, mid, high);
    }
    
    private static void merge(int[] arr, int[] aux, int low, int mid, int high) {
        for(int k = low; k <= high; k++)
            aux[k] = arr[k];
        
        int i = low, j = mid+1;
        for(int k = low; k <= high; k++) {
            if(i > mid) arr[k] = aux[j++];
            else if(j > high) arr[k] = aux[i++];
            else if(aux[j] < aux[i]) arr[k] = aux[j++];
            else arr[k] = aux[i++];
        }
    }
    
    static void insertionSort(int[] arr, int low, int high) {
        for(int i = low; i < high; i++)
            for(int j = i; j > low && arr[j] < arr[j-1]; j--)
                swap(arr, j, j-1);
    }
    
    static void swap(int[] arr, int i, int j) {
        if(i > arr.length-1 || j > arr.length-1) throw new IndexOutOfBoundsException();
        if(i == j) return;
        
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    static int[] createRandomArray(int size) {
        int[] arr = new int[size];
        
        for(int i = 0; i < size; i++) {
            arr[i] = (int)(Math.random() * Integer.MAX_VALUE);
            if(Math.random() > 0.5) arr[i] *= -1;
        }
        
        return arr;
    }
    
    public static void main(String[] args) {
        int size;
        int[] array;
        long startTime, endTime;
        double nanoConversion = 1000000000.0;
        
        for(int i = 0; i <= 30; i += 5) {
            size = 1000;
            
            System.out.printf("Array Size: %d%nInsertion Sort Cutoff: %d%n", size, i);
            for(int j = 0; j < 3; j++) {
                array = createRandomArray(size);
                startTime = System.nanoTime();
                sort(array, i);
                endTime = System.nanoTime();
                System.out.println((endTime - startTime) / nanoConversion);
            }
            
            size = 100000;
            System.out.printf("%nArray Size: %d%nInsertion Sort Cutoff: %d%n", size, i);
            for(int j = 0; j < 3; j++) {
                array = createRandomArray(size);
                startTime = System.nanoTime();
                sort(array, i);
                endTime = System.nanoTime();
                System.out.println((endTime - startTime) / nanoConversion);
            }
            
            size = 1000000;
            System.out.printf("%nArray Size: %d%nInsertion Sort Cutoff: %d%n", size, i);
            for(int j = 0; j < 3; j++) {
                array = createRandomArray(size);
                startTime = System.nanoTime();
                sort(array, i);
                endTime = System.nanoTime();
                System.out.println((endTime - startTime) / nanoConversion);
            }
            
            System.out.println("\n-----------------------------------------------\n");
        }
    }
}