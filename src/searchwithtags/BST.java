/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchwithtags;

/**
 *
 * @author Raiyan
 */
class BSTNode<T> {

    public String key;
    public T data;
    public BSTNode<T> left, right;

    public BSTNode(String key, T data) {
        this.key = key;
        this.data = data;
        left = right = null;
    }
}

public class BST<T> {

    private BSTNode<T> root, current;
    private int countComparison;

    public BST() {
        current = root = null;
    }

    public void clear() {
        current = root = null;
    }

    public boolean empty() {
        return root == null;
    }

    public boolean full() {
        return false;
    }

    public T retrieve() {
        return current.data;
    }
    
     public int getCountComparison() {
        return countComparison;
    }

    public void setCountComparison(int countComparison) {
        this.countComparison = countComparison;
    }

    public boolean findKey(String k) {
        BSTNode<T> p = root;
        while (p != null) {
            setCountComparison(this.countComparison + 1);
            current = p;
            if (k.equals(p.key)) {
                return true;
            } else if (k.compareTo(p.key) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return false;
    }

    public boolean insert(String k, T val) {
        if (root == null) {
            current = root = new BSTNode<T>(k, val);
            return true;
        }

        BSTNode<T> p = current;
        if (findKey(k)) {
            current = p;
            return false;
        }

        BSTNode<T> tmp = new BSTNode<T>(k, val);
        if (k.compareTo(current.key) < 0) {
            current.left = tmp;
        } else {
            current.right = tmp;
        }
        current = tmp;
        return true;
    }

    public boolean removeKey(String k) {

        // Search for k
        String k1 = k;
        BSTNode<T> p = root;
        BSTNode<T> q = null; // Parent of p
        while (p != null) {

            if (k1.compareTo(p.key) < 0) {
                q = p;
                p = p.left;
            } else if (k1.compareTo(p.key) > 0) {
                q = p;
                p = p.right;
            } else {
                if ((p.left != null) && (p.right != null)) {
                    BSTNode<T> min = p.right;
                    q = p;
                    while (min.left != null) {
                        q = min;
                        min = min.left;
                    }
                    p.key = min.key;
                    p.data = min.data;
                    k1 = min.key;
                    p = min;
                }

                if (p.left != null) {
                    p = p.left;
                } else {
                    p = p.right;
                }

                if (q == null) {
                    root = p;
                } else {
                    if (k1.compareTo(q.key) < 0) {
                        q.left = p;
                    } else {
                        q.right = p;
                    }
                }
                current = root;
                return true;

            }
        }

        return false;
    }
}
