
package rb;

// this time around, using public encapsulation
// nodes are single/simple linked

import utils.TreePrinter;


public class NodeRB implements utils.TreePrinter.PrintableNode {
    
    public int value;
    public char color;
    public NodeRB left;
    public NodeRB right;

    public static final String B = "\u001B[47m";
    public static final String R = "\u001B[41m";
    public static final String X = "\u001B[0m";
    
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
        //return ((this.color == 'r') ? (R+String.valueOf(value)+X) : (B+String.valueOf(value)+X) );
    }
    
}
