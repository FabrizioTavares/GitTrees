
package rb;

// this time around, using public encapsulation

import utils.TreePrinter;


public class NodeRB implements utils.TreePrinter.PrintableNode {
    
    public int value;
    public char color;
    public NodeRB left;
    public NodeRB right;

    public NodeRB(int value) {
        this.value = value;
        this.color = 'r';
        this.right = null;
        this.left = null;
    }

    @Override
    public TreePrinter.PrintableNode getLeft() {
        return left;
    }

    @Override
    public TreePrinter.PrintableNode getRight() {
        return right;
    }

    @Override
    public String getText() {
        return String.valueOf(value+":"+color);
    }
    
}
