
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
    
    public void copyToSelf(NodeBal source){
        this.value = source.getValue();
        this.balance = source.balance;
        this.left = source.getLeft();
        this.right = source.getRight();
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
    
    public int doLevel(NodeBal current) { // buscar nível
        
        int count = 0;
        
        if (this.value == current.value) { // este nó foi achado. retornar valor.
            return count++;
        }
        
        if (this.value < current.getValue()) { // navegar até à esquerda, em busca do nó
            count = 1 + doLevel(current.getLeft());
        }

        else if (this.value > current.getValue()) { // navegar até à direita, em busca do nó
            count = 1 + doLevel(current.getRight());
        }

        return count;
        
    }
    
    @Override
    public int doHeight() // calcular altura
    {
        
        if(((left == null) && (right == null))) // não há filhos = 0.
            return 0;

        int auxLeft = 0, auxRight = 0; // contadores
        
        if(left != null)
            auxLeft = left.doHeight(); 

        if(right != null)
            auxRight = right.doHeight();
        
        return 1 + Math.max(auxLeft, auxRight);
        // máximo da contagem tanto da esquerda quanto da direita, achando assim o caminho mais distante.
        
    }
    
    @Override
    public String getText() {
        return String.valueOf(this.getValue()+":"+this.balance);
    }

}
