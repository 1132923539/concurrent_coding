package cn.luxinhuo.concurrent_coding.stage1.sync;

import java.util.LinkedList;
import java.util.List;

public class LinkedListSorted {
    private static List<Integer> sSorted = new LinkedList<>();

    public static void main(String[] args) {

        sortedAdd(40);
        sortedAdd(4);
        sortedAdd(3);
        sortedAdd(10);
        sortedAdd(10);
        sortedAdd(100);
        System.out.println(sSorted);
    }

    private static void sortedAdd(Integer i) {

        int size = sSorted.size();
        int addPoint = 0;
        boolean maxFlag = true;
        for (int j = 0; j < size; j++) {
            if (i < sSorted.get(j)) {
                addPoint = j;
                maxFlag = false;
                break;
            }
        }
        if (maxFlag) addPoint = size;

        sSorted.add(addPoint, i);
    }
}
