package com.zzy;

import com.utils.data.TreeNode;

import java.sql.Array;
import java.util.*;

public class LeetCode {

    /**
     * leetcode200 岛屿数量
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int num = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1'){
                    ++num;
                    infect(grid,i,j);
                }
            }
        }
        return num;
    }
    /**
     * 感染函数
     * @param grid
     * @param i
     * @param j
     */
    public static void infect(char[][] grid, int i, int j){
        if(i < 0 || i>= grid.length
        || j < 0 || j>= grid[0].length || grid[i][j] != '1'){
            return;
        }
        grid[i][j] = '2';
        //下
        infect(grid,i+1,j);
        //上
        infect(grid,i-1,j);
        //左
        infect(grid,i,j-1);
        //右
        infect(grid,i,j+1);
    }

    /**
     * 中序遍历二叉树-递归
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inOrder(root,res);
        return res;
    }
    public void inOrder(TreeNode root, List<Integer> res){
        if(root == null){
            return;
        }
        inOrder(root.left, res);
        res.add(root.val);
        inOrder(root.right, res);
    }

    /**
     * 中序遍历二叉树-迭代
     * @param root
     * @return
     */
    public List<Integer> inorderTraversalTwo(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while( root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    /**
     * 前序遍历二叉树-递归
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preOrder(root, res);
        return res;
    }
    public void preOrder(TreeNode root, List<Integer> res){
        if(root == null){
            return;
        }
        res.add(root.val);
        preOrder(root.left, res);
        preOrder(root.right, res);
    }

    /**
     * 前序遍历二叉树-迭代
     * @param root
     * @return
     */
    public List<Integer> preorderTraversalTwo(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while(root != null || !stack.isEmpty()){
            while(root != null){
                res.add(root.val);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
        return res;
    }

    /**
     * 后序遍历二叉树-递归
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postOrder(root, res);
        return res;
    }
    public void postOrder(TreeNode root, List<Integer> res){
        if(root == null){
            return;
        }
        postOrder(root.left, res);
        postOrder(root.right, res);
        res.add(root.val);
    }

    /**
     * 后序遍历二叉树-迭代
     * @param root
     * @return
     */
    public List<Integer> postorderTraversalTwo(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null;
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            //判读是否存在右孩子
            if(root.right == null || root.right == prev){
                res.add(root.val);
                prev = root;
                root = null;
            }else{
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }

    /**
     * 课程表-207
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //邻接矩阵
        List<List<Integer>> adjacency = new ArrayList<>();
        //[ [2,1], [4,5] ] -> [ null, 2 , null, null, 5]
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        for (int[] cp : prerequisites) {
            adjacency.get(cp[1]).add(cp[0]);
        }
        //标志数组 0：干净，未被dfs， -1：，1：
        int[] flags = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if(!dfs(adjacency,flags,i)){
                return false;
            }
        }
        return true;
    }

    public boolean dfs(List<List<Integer>> adjacency, int[] flags, int i){
        if(flags[i] == 1) {
            return false;
        }
        if(flags[i] == -1){
            return true;
        }
        flags[i] = 1;
        for (Integer integer : adjacency.get(i)) {
            if(!dfs(adjacency, flags, integer)){
                return false;
            }
        }

        flags[i] = -1;
        return true;
    }

    /**
     * 反转二叉树-226
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return root;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    /**
     * 完全二叉树的节点个数-222
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        if(left == right){
            return (1 << left) + countNodes(root.right);
        }else {
            return (1 << right) + countNodes(root.left);
        }

    }
    public int countLevel(TreeNode root){
        int level = 0;
        while(root != null){
            level++;
            root = root.left;
        }
        return level;
    }

    /**
     * TODO 二叉树的右视图-199
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        res.add(root.val);
        root = root.right;
        while(root != null){
            root = root.right;
        }
    }

    /**
     * 二叉树的所有路径-257
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        dfs(root, path, paths);
        return paths;
    }

    public void dfs(TreeNode root,StringBuilder path, List<String> paths){
        if(root == null){
            return;
        }
        int beforeLength = path.length();
        path.append(root.val);
        if(root.left == null && root.right == null){
            paths.add(path.toString());
        }
        path.append("->");
        dfs(root.left, path , paths);
        dfs(root.right, path , paths);
        path.delete(beforeLength, path.length());

    }

    /**
     * 单值二叉树-965
     * @param root
     * @return
     */
    public boolean isUnivalTree(TreeNode root) {
        if(root == null){
            return true;
        }
        int value = root.val;
        int[] res = new int[]{1};
        dfs(root,value,res);
        if(res[0] == 0){
            return false;
        }
        return true;
    }
    public void dfs(TreeNode root ,int value, int[] res){
        if(root == null){
            return;
        }
        if(root.val != value){
            res[0] = 0;
            return;
        }
        dfs(root.left,value,res);
        dfs(root.right,value,res);
    }

    /**
     * 截断句子-1816
     * @param s
     * @param k
     * @return
     */
    public String truncateSentence(String s, int k) {
        String[] wordList = s.split(" ");
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < k; i++) {
            res.append(wordList[i] + " ");
        }
        res.deleteCharAt(res.length()-1);
        return res.toString();
    }

    /**
     * 相同的树
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(q == null && p == null){
            return true;
        }else if(q ==null || p == null){
            return false;
        }else if( q.val != p.val){
            return false;
        }
        return isSameTree(q.left, p.left) && isSameTree(q.right,p.right);

    }

    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;

        return dfs(left,right);
    }


    public boolean dfs(TreeNode left, TreeNode right){
        /*TreeNode temp = left;
        left = right;
        right = temp;*/
        if(left == null && right ==null){
            return true;
        }else if (left == null || right == null){
            return false;
        }else if(left.val != right.val){
            return false;
        }
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }
}
