import java.util.Arrays;

class MedianOfThree {
    static void quickSort(int[] arr, boolean medianOfThree) {
        quickSort(arr, 0, arr.length - 1, medianOfThree);
    }
    
    private static void quickSort(int[] arr, int low, int high, boolean medianOfThree) {
        if(high <= low) return;
        int j = partition(arr, low, high, medianOfThree);
        quickSort(arr, low, j-1, medianOfThree);
        quickSort(arr, j+1, high, medianOfThree);
    }
    
    private static int partition(int[] arr, int low, int high, boolean medianOfThree) {
        int i = low, j = high+1, mid = low + (high-low)/2;
        
        if(medianOfThree) {
            if((arr[low] < arr[mid] && arr[mid] < arr[high]) || (arr[high] < arr[mid] && arr[mid] < arr[low]))
                swap(arr, low, mid);
            else if((arr[low] < arr[high] && arr[high] < arr[mid]) || (arr[mid] < arr[high] && arr[high] < arr[low]))
                swap(arr, low, high);
        }
        
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
        int size;
        int[] array;
        long startTime, endTime;
        double nanoConversion = 1000000000.0;
        
        // TESTS FOR ARRAYS OF SIZE 100,000
        size = 100000;
        System.out.printf("Arrays of Size %d%n", size);
        System.out.println("Time Elapsed Without Median of Three Partitioning (s)");
        for(int i = 0; i  < 3; i++) {
            array = createRandomArray(size);
            startTime = System.nanoTime();
            quickSort(array, false);
            endTime = System.nanoTime();
            System.out.println((endTime - startTime) / nanoConversion);
        }
        
        System.out.printf("%nArrays of Size %d%n", size);
        System.out.println("Time Elapsed With Median of Three Partitioning (s)");
        for(int i = 0; i  < 3; i++) {
            array = createRandomArray(size);
            startTime = System.nanoTime();
            quickSort(array, true);
            endTime = System.nanoTime();
            System.out.println((endTime - startTime) / nanoConversion);
        }
        System.out.println("\n----------------------------------\n");
        
        // TESTS FOR ARRAYS OF SIZE 1,000,000
        size = 1000000;
        System.out.printf("Arrays of Size %d%n", size);
        System.out.println("Time Elapsed Without Median of Three Partitioning (s)");
        for(int i = 0; i  < 3; i++) {
            array = createRandomArray(size);
            startTime = System.nanoTime();
            quickSort(array, false);
            endTime = System.nanoTime();
            System.out.println((endTime - startTime) / nanoConversion);
        }
        
        System.out.printf("%nArrays of Size %d%n", size);
        System.out.println("Time Elapsed With Median of Three Partitioning (s)");
        for(int i = 0; i  < 3; i++) {
            array = createRandomArray(size);
            startTime = System.nanoTime();
            quickSort(array, true);
            endTime = System.nanoTime();
            System.out.println((endTime - startTime) / nanoConversion);
        }
        System.out.println("\n----------------------------------\n");
        
        // TESTS FOR ARRAYS OF SIZE 10,000,000
        size = 10000000;
        System.out.printf("Arrays of Size %d%n", size);
        System.out.println("Time Elapsed Without Median of Three Partitioning (s)");
        for(int i = 0; i  < 3; i++) {
            array = createRandomArray(size);
            startTime = System.nanoTime();
            quickSort(array, false);
            endTime = System.nanoTime();
            System.out.println((endTime - startTime) / nanoConversion);
        }
        
        System.out.printf("%nArrays of Size %d%n", size);
        System.out.println("Time Elapsed With Median of Three Partitioning (s)");
        for(int i = 0; i  < 3; i++) {
            array = createRandomArray(size);
            startTime = System.nanoTime();
            quickSort(array, true);
            endTime = System.nanoTime();
            System.out.println((endTime - startTime) / nanoConversion);
        }
        System.out.println("\n----------------------------------\n");
    }
}