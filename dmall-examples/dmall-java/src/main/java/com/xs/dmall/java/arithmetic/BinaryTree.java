package com.xs.dmall.java.arithmetic;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 二叉树
 * <p>
 * 先序/根遍历DLR：根   左子树     右子树
 * 中序/根遍历LDR：左子树   根     右子树
 * 后根/序遍历LRD：左子树     右子树  根
 *
 * 总结
 *

     1. 二叉树算法设计的总路线：把当前节点要做的事做好，其他的交给递归框架，不用当前节点操心。
     2. 如果当前节点会对下面的子节点有整体影响，可以通过辅助函数增长参数列表，借助参数传递信息。
     3. 在二叉树框架之上，扩展出一套 BST 遍历框架：

     void BST(TreeNode root, int target) {
     if (root.val == target)
     // 找到目标，做点什么
     if (root.val < target)
     BST(root.right, target);
     if (root.val > target)
     BST(root.left, target);
     }

     4. 掌握了 BST 的基本操作。

 *
 */
public class BinaryTree {

    private Node tree;

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        for (int i = 0; i < 10; i++) {
            bt.insert(ThreadLocalRandom.current().nextInt(20));
        }
        System.out.println(bt);
        System.out.println("结点数量：" + bt.size(bt.tree));
        System.out.println("树的高度：" + bt.getHeight(bt.tree));
        Node node1 = bt.find(11, bt.tree);
        Node node2 = bt.find(bt.tree, 11);

        if (node1 == null) {
            System.out.println("两边搜没有找到节点" + 11);
        } else {
            System.out.println("两边搜查找节点" + 11 + "，返回节点" + node1.data);
        }

        if (node2 == null) {
            System.out.println("二分查找没有找到节点" + 11);
        } else {
            System.out.println("二分查找节点" + 11 + "，返回节点" + node2.data);
        }
    }


    /**
     * 节点
     */
    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }


    /**
     * 插入值到二叉树中
     *
     * @param data
     */
    public void insert(int data) {
        if (tree == null) {
            tree = new Node(data);
            return;
        }
        //从根开始查找合适的插入位置
        Node p = tree;
        while (p != null) {
            //小于当前节点值，则向左子树查找
            if (data < p.data) {
                if (p.left == null) {
                    p.left = new Node(data);
                    return;
                }
                p = p.left;
            } else {
                if (p.right == null) {
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            }
        }
    }

    /**
     * 两边搜索
     *
     * @param value 要找的值
     * @param root  查找的入口节点
     * @return
     */
    public Node find(int value, Node root) {
        if (root == null) {
            return null;
        }
        if (root.data == value) {
            return root;
        } else {
            Node l = find(value, root.left);
            Node r = find(value, root.right);
            return l != null ? l : r != null ? r : null;
        }
    }

    /**
     * 二分查找
     *
     * @param root  查找的入口节点
     * @param value 要找的值
     * @return
     */
    public Node find(Node root, int value) {
        if (root == null) {
            return null;
        }
        if (root.data == value) {
            return root;
        } else {
            Node r = null, l = null;
            if (value > root.data) {
                r = find(root.right, value);
            } else {
                l = find(root.left, value);
            }
            return l != null ? l : (r != null ? r : null);
        }
    }

    /**
     * 删除一个节点
     * 框架先行，先找再改
     *
     * @param root
     * @param value
     * @return
     */
    public Node delete(Node root, int value) {
        if (root == null) {
            return null;
        }
        if (root.data == value) {
            //进行删除操作
            //情况1：value所在节点恰好是末端节点，两个子节点都为空，那么它可以当场去世了。
            if (root.left == null && root.right == null) {
                return null;
            }
            //情况2：value所在节点只有一个非空子节点，那么它要让这个孩子接替自己的位置
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            //情况3：有两个子节点，麻烦了，为了不破坏 BST 的性质，该值的节点必须找到左子树中最大的那个节点，或者右子树中最小的那个节点来接替自己。
            //找到右子树的最小节点
            Node minNode = getMin(root.right);
            //把root改成minNode
            root.data = minNode.data;//一般不会通过 root.val = minNode.val 修改节点内部的值来交换节点，而是通过一系列略微复杂的链表操作交换 root 和 minNode 两个节点
            //转而去删除minNode
            root.right = delete(root.right, minNode.data);

        } else if (root.data > value) {//当前节点数据比要删除的数据大，左查询
            root.left = delete(root.left, value);
        } else if (root.data < value) {
            root.right = delete(root.right, value);
        }
        return root;
    }

    /**
     * 获取值最小的节点
     *
     * @param node
     * @return
     */
    private Node getMin(Node node) {
        //BST最左边的就是最小的
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


    /**
     * 判断两棵树是否完全相同
     *
     * @param root1 树1
     * @param root2 树2
     * @return
     */
    boolean isSameTree(Node root1, Node root2) {
        //都为空，显然相同
        if (root1 == null && root2 == null) {
            return true;
        }
        //一个为空，一个非空，显然不同
        if (root1 == null || root2 == null) {
            return false;
        }
        //两个都非空，但val不一样也不行
        if (root1.data != root2.data) {
            return false;
        }
        //root1和root2该比的都比完了
        return isSameTree(root1.left, root2.left) && isSameTree(root1.right, root2.right);
    }

    /**
     * 二叉树的节点数
     * 运用递归的思想，二叉树结点树 = 左子树结点数量 + 右子树结点数量 + 1
     *
     * @return
     */
    public int size(Node root) {
        if (root == null) {
            return 0;
        } else {
            int l = size(root.left);
            int r = size(root.right);
            return l + r + 1;
        }
    }

    /**
     * 二叉树的深度（高度）
     * 在左右子树同一级上取一个最大值，返给上级，上级的上级会根据返回的值比较左右树，再次取最大值返给它的上级。
     *
     * @param root 根节点
     * @return
     */
    public int getHeight(Node root) {
        if (root == null) {
            return 0;
        } else {
            //获取左子树的高度
            int l = getHeight(root.left);
            //获取右子树的高度
            int r = getHeight(root.right);
            //返回左子树、右子树较大高度并加1
            return Math.max(l, r) + 1;
        }
    }

    /**
     * 校验树是否是有效二叉树
     * 满足左节点小于根、右节点
     *
     * @param root
     * @return
     */
    boolean isValidBST(Node root) {
        return isValidBST(root, null, null);
    }

    /**
     * @param root 左/右节点
     * @param min  表示根节点，要与右节点比较大小
     * @param max  表示根节点，要与左节点比较大小
     * @return
     */
    private boolean isValidBST(Node root, Node min, Node max) {
        if (root == null) {
            return true;
        }
        //右节点root与根节点min比较
        if (min != null && root.data <= min.data) {
            return false;
        }
        //左节点root与根节点max比较
        if (max != null && root.data >= max.data) {
            return false;
        }

        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }


    public boolean isEmpty() {
        return tree == null;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("tree", tree)
                .toString();
    }
}
