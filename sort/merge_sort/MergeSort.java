class MergeSortImpl {
    public static void sort(int[] A) {
        recursiveMergeSort(A, 0, A.length - 1);
    }

    private static void recursiveMergeSort(int[] A, int lowerIdx, int upperIndex) {
        if (lowerIdx >= upperIndex) {
            return; // no element to sort
        }

        int middleIdx = Math.floorDiv(lowerIdx + upperIndex, 2);
        recursiveMergeSort(A, lowerIdx, middleIdx);
        recursiveMergeSort(A, middleIdx + 1, upperIndex);

        merge(A, lowerIdx, middleIdx, upperIndex);
    }

    private static void merge(int[] A, int lowerIdx, int middleIdx, int upperIndex) {
        // Copying left side and right side of A to L and R respectively
        int lsz = middleIdx - lowerIdx + 1;
        int rsz = upperIndex - middleIdx;
        int[] L = new int[lsz];
        int[] R = new int[rsz];
        for (int i = 0; i < lsz; i++) {
            L[i] = A[lowerIdx + i];
        }
        for (int i = 0; i < rsz; i++) {
            R[i] = A[middleIdx + i + 1];
        }

        // Sorting each element at time, copying back to A the smaller one
        int lIdx = 0;
        int rIdx = 0;
        int aIdx = lowerIdx;
        while (lIdx < lsz && rIdx < rsz) {
            if (L[lIdx] <= R[rIdx]) {
                A[aIdx++] = L[lIdx++];
            } else {
                A[aIdx++] = R[rIdx++];
            }
        }

        // Copying back left numbers
        while (lIdx < lsz) {
            A[aIdx++] = L[lIdx++];
        }
        while (rIdx < rsz) {
            A[aIdx++] = R[rIdx++];
        }
    }
}

public class MergeSort {
    public static void main(String[] args) {
        // run with -ea (enable assertions) for jvm
        int[] array = new int[] {9, 6, 10, 8, 5, 3, 23, 99, 5, 62};
        int[] expectedArray = new int[] {3, 5, 5, 6, 8, 9, 10, 23, 62, 99};

        MergeSortImpl.sort(array);

        for (int i = 0; i < array.length; i++) {
            assert(array[i] == expectedArray[i]);
        }
    }
}
