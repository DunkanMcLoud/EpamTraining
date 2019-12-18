package common.solutions.utils;

import java.io.ObjectStreamClass;

public final class ArrayUtils {

    private ArrayUtils() {
    }

    public static void copyArray(Object[] src, Object[] dest) {
        System.arraycopy(src, 0, dest, 0, src.length);
    }

    public static void printArray(Object[] arr) {
        for (Object obj : arr) {
            if (obj != null) {
                System.out.println(obj);
            }
        }
    }

    public static void removeElement(Object[] arr, int index) {
        System.arraycopy(arr, index + 1, arr, index, arr.length - 1 - index);
    }

    public static Object[] removeNullsFromArray(Object[] arr) {
        int counter = 0;
        for (Object object : arr) {
            if (object != null) {
                counter++;
            } else {
                break;
            }
        }
        Object[] answer = new Object[counter];
        System.arraycopy(arr, 0, answer, 0, counter);
        return  answer;
    }


}