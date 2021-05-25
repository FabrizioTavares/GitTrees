
package avl;

public class NodeBal extends binary.Node{
    
    private int value;
    public int balance;
    private NodeBal left;
    private NodeBal right;
    
    public NodeBal(){
        this.value = 0;
        this.balance = 0;
        this.left = null;
        this.right = null;
    }

    public NodeBal(int value){
        this.value = value;
        this.balance = 0;
        this.left = null;
        this.right = null;
    }
    
    public NodeBal(String name, int value){
        this.value = value;
        this.balance = 0;
        this.left = null;
        this.right = null;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }
    
    @Override
    public NodeBal getLeft() {
        return left;
    }

    public void setLeft(NodeBal left) {
        this.left = left;
    }
    
    @Override
    public NodeBal getRight() {
        return right;
    }

    public void setRight(NodeBal right) {
        this.right = right;
    }
    
    
    @Override
    public String getText() {
        return String.valueOf(this.getValue()+":"+this.balance);
    }

}
