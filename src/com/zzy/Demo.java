package com.zzy;

import java.util.*;


import com.utils.AllNeed;
import sun.security.krb5.internal.KdcErrException;

import javax.swing.*;

/**
 * @Author YongZhiZhang
 */
public class Demo {
    /**
     * 无穷大
     */
    private static final Integer POSITIVE_INFINITY = Integer.MAX_VALUE;
    /**
     * 有向图
     */
    private static final HashMap<String,String[]> directedGraph = new HashMap<String, String[]>(){
        {
            put("YongZhiZhang", new String[]{"HongZhiLiu", "LinYanLiu", "JiaQin"});
            put("HongZhiLiu", new String[]{"Girlfriend", "HaiChangLiu"});
            put("JiaQin", new String[]{"XiJiaWu"});
            put("XiJiaWu", new String[]{"HangXianChen"});
            put("HaiChangLiu", new String[]{});
            put("Girlfriend", new String[]{});
            put("HangXianChen", new String[]{});
            put("LinYanLiu", new String[]{});
        }

    };
    /**
     * 有向权图
     */
    private static final HashMap<String, HashMap<String, Integer>> directedWeightGraph = new HashMap<String, HashMap<String, Integer>>(){
        {
            put("start", new HashMap<String, Integer>(){{put("A",2); put("B",5);}});
            put("A", new HashMap<String, Integer>(){{put("B",8); put("D", 7);}});
            put("B", new HashMap<String, Integer>(){{put("C",4); put("D", 2);}});
            put("C", new HashMap<String, Integer>(){{put("D",6); put("end", 3);}});
            put("D", new HashMap<String, Integer>(){{put("end",1);}});
        }
    };
    /**
     * 父节点map
     */
    private static final HashMap<String,String> parents = new HashMap<String,String>(){
        {
            put("A","start");
            put("B","start");
            put("end",null);
        }
    };

    /**
     * 二分查找
     * @param list
     * @param item
     * @return
     */
    public static int binarySearch(int[] list, int item){
        //待查找数组长度
        int length = list.length;
        //高低索引
        int low = 0, high = length - 1;
        //当存在至少一个元素是执行
        while(low <= high){
            int mid = (low + high) / 2;
            //中间元素刚好为目标
            if(list[mid] == item){
                return mid;
            }
            //中间元素大于目标元素
            else if(list[mid] > item){
                high = mid - 1;
            }
            else{
                ++low;
            }
        }
        return -1;
    }

    /**
     * 递归-二分查找
     * @param a
     * @param start
     * @param end
     * @param item
     * @return
     */
    public static int binarySearchRecursion(List<Integer> a, int start, int end, int item){
        //0 1 2 3 4     (4-2)/2 + 2 = 3  这种写法防止溢出
        int mid = (end - start ) / 2 + start;
        //基线条件
        if(a.get(mid) == item){
            return mid;
        }
        if(start >= end){
            return -1;
        //递归条件
        }else if(a.get(mid) > item){
            return binarySearchRecursion(a, start, mid-1, item);
        }else if(a.get(mid) < item){
            return binarySearchRecursion(a, mid+1, end, item);
        }
        return -1;


    }
    /**
     * 测试二分查找
     * @param a
     */
    public static void binarySearchTest(int[] a){
        for (int i : a) {
            System.out.println(i);
        }
        System.out.printf("结果是：" + binarySearch(a, 3));
    }

    /**
     * 选择排序
     * @param a
     * @return
     */
    public static int[] selectSort(List<Integer> a){
        List<Integer> temp = new ArrayList<>();
        int size = a.size();
        for (int i = 0; i < size; i++) {
            int smallestIndex = AllNeed.findSmallestIndexList(a);
            temp.add(a.get(smallestIndex));
            a.remove(smallestIndex);
        }
        int[] result = temp.stream().mapToInt(Integer::valueOf).toArray();
        return result;
    }

    /**
     * 递归-倒计时
     * @param i
     */
    public static void countDown(int i){
        //基线条件
        if (i < 0){
            return;
        }
        System.out.println(i);
        //递归条件
        countDown(--i);
    }

    /**
     * 递归-集合求和
     * @param a
     */
    public static int sumOfArray(List<Integer> a){
        int sum = 0;
        if(a.size() == 0){
            return 0;
        }else if(a.size() == 1){
            return a.get(0);
        }
        int temp = a.get(0);
        a.remove(0);
        sum = temp + sumOfArray(a);
        return sum;
    }

    /**
     * 递归-求集合元素数
     * @param a
     * @return
     */
    public static int numberOfArray(List<Integer> a){
        int number = 0;
        int temp = 0;
        //基线条件
        if(a.size() == 0){
            return 0;
        }else if(a.size() == 1){
            return 1;
        }
        a.remove(0);
        ++temp;
        //递归条件
        number = temp + numberOfArray(a);
        return number;
    }

    /**
     * 递归-求集合中最大值
     * @param a
     * @return
     */
    public static int biggestOfArray(List<Integer> a){
        if(a.size() == 0){
            return -1;
        }else if(a.size() == 1){
            return a.get(0);
        }
        int biggest = a.get(0);
        a.remove(0);
        /*//不能这样写，在第一层的时候未进入循环就直接返回了
        if( biggest < biggestOfArray(a)){
            biggest = biggestOfArray(a);
        }
        return biggest;*/
        return Math.max(biggest, biggestOfArray(a));
    }

    /**
     * 掌握思想，用了不止一个集合
     * @param a
     * @return
     */
    public static List<Integer> quickSort(List<Integer> a){
        if(a.size() < 2){
            return a;
        }
        int pivot = a.get(0);
        List<Integer> smaller = new ArrayList<>();
        List<Integer> bigger = new ArrayList<>();
        for (int i = 1; i < a.size(); i++) {
            if(a.get(i) <= pivot){
                smaller.add(a.get(i));
            }else{
                bigger.add(a.get(i));
            }
        }
        List<Integer> temp = quickSort(smaller);
        temp.add(pivot);
        temp.addAll(quickSort(bigger));
        return temp;
    }

    /**
     * 快速排序-不需要额外空间的
     * @param a
     * @param left
     * @param right
     * @return
     */
    public static int[] quickSortTwo(int[] a, int left, int right){
        if(left < right){
            int partitionIndex = getPartitionIndex(a, left, right);
            quickSortTwo(a, left, partitionIndex-1);
            quickSortTwo(a, partitionIndex+1, right);
        }
        return a;
    }

    /**
     * 找到部分的中间索引
     * @param a
     * @param left
     * @param right
     * @return
     */
    public static int getPartitionIndex(int[] a, int left, int right){
        int pivot = left;
        int index = pivot + 1;
        //一定是 <= 因为传进来的是索引
        for (int i = index; i <= right ; i++) {
            if(a[i] < a[pivot]){
                AllNeed.swapArray(a, i , index++);
            }
        }
        AllNeed.swapArray(a, pivot, --index);
        return index;

    }

    /**
     * 广度优先-找是否存在某个人
     * @param target
     * @return
     */
    public static String search(String target){
        //待检查队列
        Deque<String> checkedDeque = new LinkedList<>();
        //记录检查过的人
        List<String> searched = new ArrayList<>();
        //在YongZhiZhang的关系网寻找
        Collections.addAll(checkedDeque, directedGraph.get("YongZhiZhang"));
        while(!checkedDeque.isEmpty()){
            String person = checkedDeque.removeFirst();
            if(!searched.contains(person)){
                if(target.equals(person)){
                    return "Find You, " + person;
                }else{
                    Collections.addAll(checkedDeque, directedGraph.get(person));
                    searched.add(person);
                }
            }
        }
        return "没找到";
    }

    /**
     * Dijkstra 算法
     */
    public static void dijkstra(){
        List<String> processed = new ArrayList<>();
        HashMap<String, Integer> costs = new HashMap<String, Integer>(){
            {
                put("A",2);
                put("B",5);
                put("C",POSITIVE_INFINITY);
                put("D",POSITIVE_INFINITY);
                put("end",POSITIVE_INFINITY);
            }
        };
        String node = findLowestNode(costs, processed);
        while(node != null){
            int cost = costs.get(node);
            HashMap<String, Integer> neighbors = directedWeightGraph.get(node);
            //注意判读，当遍历到没有邻居点是，直接标记为已处理，并重新寻找距离最短点
            if(neighbors == null){
                processed.add(node);
                node = findLowestNode(costs,processed);
                continue;
            }
            Set<Map.Entry<String, Integer>> neighborEntry = neighbors.entrySet();
            for (Map.Entry<String, Integer> neighbor : neighborEntry) {
                int newCost = cost + neighbor.getValue();
                //如果开销表中记录的开销大于新计算的开销，更新表记录
                if(costs.get(neighbor.getKey()) > newCost){
                    costs.put(neighbor.getKey(),newCost);
                    parents.put(neighbor.getKey(), node);
                }
            }
            processed.add(node);
            node = findLowestNode(costs,processed);
        }

        System.out.println(costs);
    }

    /**
     * 寻找未处理且开销最少节点
     * @param costs
     * @param processed
     * @return
     */
    public static String findLowestNode(HashMap<String, Integer> costs, List<String> processed){
        int lowestCost = POSITIVE_INFINITY;
        String lowestCostNode = null;
        for (Map.Entry<String, Integer> node : costs.entrySet()) {
            if(node.getValue() < lowestCost && !processed.contains(node.getKey())){
                lowestCost = node.getValue();
                lowestCostNode = node.getKey();
            }
        }
        return lowestCostNode;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = AllNeed.backList(scanner);
        int[] a = list.stream().mapToInt(Integer::valueOf).toArray();
        //List<Integer> list1 = new ArrayList<>(Arrays.asList(8,2,1));
        //binarySearchTest(a);
        //int[] result = selectSort(list);
        //countDown(8);
        //System.out.println(binarySearchRecursion(list,0, list.size()-1, 5 ));
        //AllNeed.printList(quickSort(list));
        //AllNeed.printArray(quickSortTwo(a, 0, a.length-1));
        //System.out.println(search("HaiChangLiu"));
        //scanner.close();
        dijkstra();
    }
}
