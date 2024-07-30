public class InsertionSort {
    public static void main(String[] args) {
        // run with -ea (enable assertions) for jvm
        int[] array = new int[] {9, 6, 10, 8, 5, 3, 23, 99, 5, 62};
        int[] expectedArray = new int[] {3, 5, 5, 6, 8, 9, 10, 23, 62, 99};

        insertionSort(array);

        for (int i = 0; i < array.length; i++) {
            assert(array[i] == expectedArray[i]);
        }
    }

    private static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j--];
            }
            array[j + 1] = key;
        }
    }
}



