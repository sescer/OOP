/**
 * @class HeapSort is for implementation of Heap Sort
 *
 * Heap sort is comparison based sorting algorithm.
 *
 * Heap sort is considered as improved selection sort,
 * lit divides the input into sorted and unsorted region.
 *
 * The improvement from selection sort is to use Heap Data Structure
 * instead of using linear search algorithm to reduce of the time complexity.
 */

public class HeapSort
{
    /**
     * To sort array
     *
     * @param arr is an input array to sort
     */
    public static void sort(int arr[])
    {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n-1; i >= 0; i--)
        {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i,0);
        }
    }

    /**
     * To heapify a subtree
     *
     * @param arr is array to sort
     * @param n size of heap
     * @param i node; index in arr
     */
    public static void heapify(int arr[], int n, int i)
    {
        int largest = i;
        int l = 2*i + 1;
        int r = 2*i + 2;

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i)
        {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, n, largest);
        }
    }

}