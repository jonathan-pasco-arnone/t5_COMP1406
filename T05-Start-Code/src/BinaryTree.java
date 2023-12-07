import java.util.ArrayList;
import java.util.Objects;

public class BinaryTree {
    private String	   	data;
    private BinaryTree	leftChild;
    private BinaryTree	rightChild;

    // A constructor that makes a Sentinel node
    public BinaryTree() {
        data = null;
        leftChild = null;
        rightChild = null;
    }

    // This constructor now uses sentinels for terminators instead of null
    public BinaryTree(String d) {
        data = d;
        leftChild = new BinaryTree();
        rightChild = new BinaryTree();
    }

    // This constructor is unchanged
    public BinaryTree(String d, BinaryTree left, BinaryTree right) {
        data = d;
        leftChild = left;
        rightChild = right;
    }

    // Get methods
    public String getData() { return data; }
    public BinaryTree getLeftChild() { return leftChild; }
    public BinaryTree getRightChild() { return rightChild; }

    // Set methods
    public void setData(String d) { data = d; }
    public void setLeftChild(BinaryTree left) { leftChild = left; }
    public void setRightChild(BinaryTree right) { rightChild = right; }

    // Return a list of all data within the leaves of the tree
    public ArrayList<String> leafData()  {
        ArrayList<String>	result = new ArrayList<String>();

        if (data != null) {
            if ((leftChild.data == null) && (rightChild.data == null))
                result.add(data);
            result.addAll(leftChild.leafData());
            result.addAll(rightChild.leafData());
        }
        return result;
    }

    // Determines the height of the tree, which is the number of branches
    // in the path from the root to the deepest leaf.
    public int height()  {
        // Check if this is a sentinel node
        if (data == null)
            return 0;

        return 1 + Math.max(leftChild.height(),
                rightChild.height());
    }

    public boolean contains(String d) {
        return containsRecursive(d, this);
    }

    private boolean containsRecursive(String d, BinaryTree treeTop) {
        if (treeTop == null) {
            return false;

        // This if statements uses a type of comparison that was recommended by intellij that is "null safe"
        // However, the null safety is not required as the previous if statement takes care of it
        } else if (Objects.equals(treeTop.data, d)) {
            return true;
        }

        // Get the two sides data
        boolean leftSide = false;
        if (treeTop.leftChild != null) {
            leftSide = containsRecursive(d, treeTop.leftChild);
        }

        boolean rightSide = false;
        // The "&& !leftSide" makes the program run very slightly quicker as it allows it to skip the right side of the
        // tree if the left side has already found the string
        if (treeTop.leftChild != null && !leftSide) {
            rightSide = containsRecursive(d, treeTop.rightChild);
        }
        // Return true if any of them are true
        return leftSide || rightSide;
    }
}
