import java.util.Arrays;
import java.util.Random;

class ThreeSorts {
    static void insertionSort(int[] arr) {
        for(int i = 0; i < arr.length; i++)
            for(int j = i; j > 0 && arr[j] < arr[j-1]; j--)
                swap(arr, j, j-1);
    }
    
    static void mergeSort(int[] arr) {
        int[] aux = new int[arr.length];
        mergeSort(arr, aux, 0, arr.length - 1);
    }
    
    private static void mergeSort(int[] arr, int[] aux, int low, int high) {
        if(high <= low) return;
        int mid = low + (high - low) / 2;
        mergeSort(arr, aux, low, mid);
        mergeSort(arr, aux, mid+1, high);
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
    
    static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }
    
    private static void quickSort(int[] arr, int low, int high) {
        if(high <= low) return;
        int j = partition(arr, low, high);
        quickSort(arr, low, j-1);
        quickSort(arr, j+1, high);
    }
    
    private static int partition(int[] arr, int low, int high) {
        int i = low, j = high+1;
        int v = arr[low];
        while(true) {
            while(arr[++i] < v) if(i == high) break;
            while(v < arr[--j]) if(j == low) break;
            if(i >= j) break;
            swap(arr, i, j);
        }
        swap(arr, low, j);
        return j;
    }
    
    private static void swap(int[] arr, int i, int j) {
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
        int[] array;
        int size;
        long startTime, endTime;
        double nanoConversion = 1000000000.0;
        
        for(size = 1000; size <= 1000000; size *= 10) {
            System.out.printf("Arrays of Size %d%n%n", size);
            System.out.println("Times Elapsed for Quicksort (s)");

            array = createRandomArray(size);
            startTime = System.nanoTime();
            quickSort(array);
            endTime = System.nanoTime();
            System.out.println((endTime - startTime) / nanoConversion);

            array = createRandomArray(size);
            startTime = System.nanoTime();
            quickSort(array);
            endTime = System.nanoTime();
            System.out.println((endTime - startTime) / nanoConversion);

            array = createRandomArray(size);
            startTime = System.nanoTime();
            quickSort(array);
            endTime = System.nanoTime();
            System.out.println((endTime - startTime) / nanoConversion);

            System.out.println("\nTimes Elapsed for Mergesort (s)");

            array = createRandomArray(size);
            startTime = System.nanoTime();
            mergeSort(array);
            endTime = System.nanoTime();
            System.out.println((endTime - startTime) / nanoConversion);

            array = createRandomArray(size);
            startTime = System.nanoTime();
            mergeSort(array);
            endTime = System.nanoTime();
            System.out.println((endTime - startTime) / nanoConversion);

            array = createRandomArray(size);
            startTime = System.nanoTime();
            mergeSort(array);
            endTime = System.nanoTime();
            System.out.println((endTime - startTime) / nanoConversion);

            System.out.println("\nTimes Elapsed for Insertion Sort (s)");

            array = createRandomArray(size);
            startTime = System.nanoTime();
            insertionSort(array);
            endTime = System.nanoTime();
            System.out.println((endTime - startTime) / nanoConversion);

            array = createRandomArray(size);
            startTime = System.nanoTime();
            insertionSort(array);
            endTime = System.nanoTime();
            System.out.println((endTime - startTime) / nanoConversion);

            array = createRandomArray(size);
            startTime = System.nanoTime();
            insertionSort(array);
            endTime = System.nanoTime();
            System.out.println((endTime - startTime) / nanoConversion);
            System.out.println("");
        }
    }
}