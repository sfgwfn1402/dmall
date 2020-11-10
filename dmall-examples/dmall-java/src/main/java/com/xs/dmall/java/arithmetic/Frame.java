package com.xs.dmall.java.arithmetic;

/**
 * 框架
 * <p>
 *      对于任何数据结构，其基本操作无非遍历 + 访问，再具体一点就是：增删查改。
 *      数据结构种类很多，但它们存在的目的都是在不同的应用场景，尽可能高效地增删查改
 *      各种数据结构的遍历 + 访问无非两种形式：线性的和非线性的。
 *      线性就是 for/while 迭代为代表，非线性就是递归为代表。
 * </p>
 */
public class Frame {

    /**
     * 数组遍历框架，典型的线性迭代结构 ------------------------------
     * @param arr
     */
    void traverse(int[] arr){
        for (int i = 0; i < arr.length; i++){
            System.out.println(i);
        }
    }

    /**
     * 链表遍历框架，兼具迭代和递归结构 ------------------------------
     */
    class ListNode{
        int val;
        ListNode next;
    }
    // 迭代访问p.val
    void traverse(ListNode head){
        for (ListNode p = head; p !=null; p = p.next){
            System.out.println(p.val);
        }
    }
    //递归访问
    void traverseRecursion(ListNode head){
        traverseRecursion(head.next);
    }

    /**
     * 二叉树遍历框架，典型的非线性递归遍历结构 ------------------------------
     */
    class TreeNode{
        int val;
        TreeNode left, right;
    }
    //递归访问
    void traverse(TreeNode root){
        traverse(root.left);
        traverse(root.right);
    }

    /**
     * N叉树的遍历框架
     */
    class TreeNNode{
        int val;
        TreeNNode[] children;
    }

    void traverse(TreeNNode root){
        for (TreeNNode child : root.children){
            traverse(child);
        }
    }

}
