package com.utils;

import java.util.*;

public class AllNeed {

    public static final String findBiggest = "找最大";
    public static final String findSmallest = "找最小";

    //标准化录入数据，返回list
    public static List<Integer> backList(Scanner scanner){
        List<Integer> list = new ArrayList<>();
        String s = scanner.nextLine();
        String[] s1 = s.split(" ");
        for (int i = 0; i < s1.length; i++) {
            list.add(Integer.parseInt(s1[i]));
        }
        return  list;
    }
    //找到最小/最大元素索引
    public static int findSmallestIndex(int[] a, String type){
        int smallest = a[0];
        int smallest_index = 0;
        int biggest = a[a.length-1];
        int biggest_index = a[0];
        if(findSmallest.equals(type)){
            for (int j = 0; j < a.length; j++) {
                if(a[j] < smallest){
                    smallest = a[j];
                    smallest_index = j;
                }
        }
            return smallest_index;
        }else if(findBiggest.equals(type)){
            for (int i = 0; i < a.length; i++) {
                if(a[i] > biggest){
                    biggest = a[i];
                    biggest_index = i;
                }
            }
            return biggest_index;
        }
        return -1;
    }

    public static int findSmallestIndexList(List<Integer> a){
        Integer min = Collections.min(a);
        int index = a.indexOf(min);
        return index;
    }
    /**
     * 移除数组指定元素
     */
    public static int[] removeIndex(int[] a, int index){
        int[] result = new int[a.length-1];
        for (int i = 0, k= 0; i < a.length; i++) {
            if(i != index){
                result[k++] = a[i];
            }
        }
        return result;
    }
    /**
     * 打印数组
     */
    public static void printArray(int[] a){
        for (int i : a) {
            System.out.println(i);
        }
    }
    /**
     * 打印集合
     */
    public static void printList(List<Integer> a){
        for (Integer integer : a) {
            System.out.println(integer);
        }
    }

    /**
     * 交换数组中的两个元素
     * @param a
     * @param i
     * @param j
     */
    public static void swapArray(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
