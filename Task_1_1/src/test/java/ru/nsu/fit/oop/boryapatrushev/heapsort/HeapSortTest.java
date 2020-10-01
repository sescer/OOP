package ru.nsu.fit.oop.boryapatrushev.heapsort;
import org.junit.Test;
import org.junit.Assert;

public class  HeapSortTest {

    /**
     * To create random-filled array
     * @param size is an array size to create
     * @return array, which is created
     */
    public static int[] createArray (int size) {

        int [] res = new int [size];

        for (int i = 0; i < size; i++){
            res[i] = (int)(Math.random() * size-(size/2));
        }

        return res;
    }

    /**
     * To check if the array is sorted or not
     * @param array is input array
     * @return true if array sorted; otherwise - false
     */
    private static boolean isSorted(int[] array) {

        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i-1])
                return false;
        }
        return true;
    }

    @Test
    public void test1() {

        for (int i = 300; i < 350; i++) {
            int[] array = createArray(i);
            HeapSort.sort(array);
            Assert.assertTrue(isSorted(array));
        }

    }

    @Test
    public void test2() {
        int[] array ={};
        HeapSort.sort(array);
        Assert.assertTrue(isSorted(array));
    }

    @Test
    public void test3() {
        int[] array ={1,2,3,4,5,6,7,8,9,10};
        int[] ans_array ={1,2,3,4,5,6,7,8,9,10};
        HeapSort.sort(array);
        Assert.assertArrayEquals(array, ans_array);
    }

    @Test
    public void test4() {
        int[] array ={10,9,8,7,6,5,4,3,2,1};
        int[] ans_array ={1,2,3,4,5,6,7,8,9,10};
        HeapSort.sort(array);
        Assert.assertArrayEquals(array, ans_array);
    }

    @Test
    public void test5() {
        int[] array ={-10,-100,-345,0,1000,50,23,-1};
        int[] ans_array ={-345,-100,-10,-1,0,23,50,1000};
        HeapSort.sort(array);
        Assert.assertArrayEquals(array, ans_array);
    }

}