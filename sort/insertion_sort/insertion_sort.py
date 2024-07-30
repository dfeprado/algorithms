from typing import List


def insertionSort(A: List[int]) -> None:
    for i in range(1, len(A)):
        key = A[i]
        j = i - 1
        while j >= 0 and A[j] > key:
            A[j + 1] = A[j]
            j -= 1

        A[j + 1] = key


if __name__ == "__main__":
    array = [9, 6, 10, 8, 5, 3, 23, 99, 5, 62]
    expectedArray = [3, 5, 5, 6, 8, 9, 10, 23, 62, 99]

    insertionSort(array)

    assert array == expectedArray