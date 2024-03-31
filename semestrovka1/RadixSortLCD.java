import java.util.Arrays;

public class RadixSortLCD {
    public static int iterationCount = 0;
    public static int[] countingSort(int array[], int size, int place) {
        int count[] = new int[10];
        int pow = (int) Math.pow(10, place - 1);
        int[] output = new int[size];
        for(int i = 0; i < size; i++) {
            count[array[i] / pow % 10]++;

            iterationCount++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];

            iterationCount++;
        }

        for(int i = size - 1; i >= 0; i-- ) {
            output[count[array[i] / pow % 10] - 1] = array[i];
            count[array[i] / pow % 10]--;

            iterationCount++;
        }
        return output;
    }

    public static int getMax(int array[], int n) {
        int max = array[0];
        for (int i = 1; i < n; i++) {
            if (array[i] > max)
                max = array[i];

            iterationCount++;
        }
        return max;
    }
    public static int getCountNumberPlace(int n) {
        int a = 0;
        while(n != 0) {
            a++;
            n /= 10;

            iterationCount++;
        }
        return a;
    }
    public static int[] radixSort(int array[], int size) {
        iterationCount = 0;
        int maxx = getMax(array, size);
        int iteration = getCountNumberPlace(maxx);

        for(int i = 0; i < iteration; i++) {
            array = countingSort(array, size, i + 1);

            iterationCount++;
        }

        return array;
    }

}
