
package avl;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue; //implements linkedlist
import java.util.ArrayList;
import utils.TreePrinter;

public class AVL extends binary.BinaryTree{
    
    public NodeBal root;

    
    public AVL(){
        
        this.root = null;
        this.userinput = -2;
        this.userinputint = 0;
        
    }
    
    @Override
    public void doMenu(){ // creates a menu to interact with the tree.
        Scanner readinput = new Scanner(System.in); // needed to read the user's input
        System.out.println("Using AVL Tree. 9 for list of commands");
        while (this.userinput != -1) {
            
            this.update(); // every loop, print diagram of the tree
            System.out.print("Root: ");
            try {
                System.out.println(this.root.getValue());
            } catch (Exception e) {
                System.out.println("?");
            }
            
            System.out.print("Option: ");
            this.userinput = readinput.nextInt(); // input. Await user.
            
            switch(this.userinput){ // checks and matches the input to available functions
    
                case 9:
                    System.out.println("\nAvailable commands:"
                            + "\n'0': create a new empty root"
                            + "\n'-1': quits the program"
                            + "\n'1': recursively inserts new node with value"
                            + "\n'2': remove a node"
                            + "\n'3': print information about the tree"
                            + "\n'4': print information about a node"
                            + "\n'9': print this help section"
                            );
                    break;
                    
                case 0: // starts the tree, creating or replacing the root.
                    System.out.print(">>> Root Value: ");
                    this.userinputint = readinput.nextInt();
                    this.createRoot(userinputint);
                    break;
                    
                case -1: // quit.
                    System.out.println("Stopping.");
                    break;
                  
                case 1: // insere objeto no conteúdo do nó.
                    System.out.print(">>> New Value: ");
                    this.userinputint = readinput.nextInt();
                    this.insertElement(root, userinputint);
                    break;
                    
                case 2: //remover certo valor
                    System.out.print(">>> Value to be removed: ");
                    this.userinputint = readinput.nextInt();
                    this.delete(root, userinputint);
                    break;
                             
                case 3: // imprimir informações da árvore
                    updateAllBalances(root);
                    this.calculateNodes(root);
                    System.out.println("Pre Order NLR:");
                    this.traversePreOrder(root);
                    System.out.println("\nPost Order LRN:");
                    this.traversePostOrder(root);
                    System.out.println("\nIn Order LNR:");
                    this.traverseInOrder(root);
                    System.out.println("\n[!] Finished printing tree information\n");
                    break;
                    
                case 4: 
                    System.out.print(">>> Value of node to be fetched: ");
                    this.userinputint = readinput.nextInt();
                    this.info(this.fetch(root, userinputint));
                    break;
          
            }
            
        }
        
    }
    
    @Override
    public void update(){
        try {
            TreePrinter.print(root);
        } catch (Exception e) {
            System.out.println("[Alert] Could not print diagram!");
        }
    }
    
    public void updateAllBalances(NodeBal node) { // NLR
        if (node != null) {
            updateBalance(node);
            updateAllBalances(node.getLeft());
            updateAllBalances(node.getRight());
        }
    }
    
    public void updateOnCourse(NodeBal current, int target){
        
        if (current != null) {
            updateBalance(current);
            if (current.getValue() > target) { //left
                updateOnCourse(current.getLeft(), target);
            } else if (current.getValue() < target) { //right
                updateOnCourse(current.getRight(), target);
            }
        }
        
    }

    @Override
    public NodeBal getRoot() {
        return root;
    }

    public void setRoot(NodeBal root) {
        this.root = root;
    }

    @Override
    public void createRoot(int value){ //criar raiz
        NodeBal newNode = new NodeBal();
        newNode.setValue(value);
        if (this.root == null) {
            this.root = newNode;
            System.out.println("Root Created.");
        }
        else {
            this.root = newNode;
            System.out.println("Root REPLACED.");
        }
    }
    
    public NodeBal fetch(NodeBal current, int target){
        
        if (current != null) {
            if (current.getValue() == target) {
                return current;
            } else if (current.getValue() > target) { //left
                return fetch(current.getLeft(), target);
            } else if (current.getValue() < target) { //right
                return fetch(current.getRight(), target);
            } else {
                return null;
            }
        }
        return current;
        
    }
    
    public NodeBal fetchPivotOf(int target){
        NodeBal current = root;
        NodeBal pivot = null;
        while (current != null) {

            if (current.balance != 0) {
                pivot = current;
            }
            
            if (current.getValue() > target){ //left
                current = current.getLeft();
            }
            
            else if (current.getValue() < target){ // right
                current = current.getRight();
            }
            
            else { // value reached
                break;
            }

        }
        return pivot;
    }
    
    public NodeBal fetchParentOf(int target){
        NodeBal current = root;
        NodeBal parent = null;
        while (current != null) {
            
            if (current.getLeft() != null){
                if (current.getLeft().getValue() == target){
                    parent = current;
                }
            }
            
            if (current.getRight() != null){
                if (current.getRight().getValue() == target){
                    parent = current;
                }
            }
            
            if (current.getValue() > target){ //left
                current = current.getLeft();
            }
            
            else if (current.getValue() < target){ // right
                current = current.getRight();
            }
            
            else {
                break;
            }

        }
        return parent;
    }
   
    public void info(NodeBal target){
        if (target != null) {
            System.out.println("Object: " + target);
            System.out.println("Value: " + target.getValue());
            System.out.println("Balance: " + getBalance(target));
            System.out.println("Parent: " + fetchParentOf(target.getValue()));
            System.out.println("Nearest Unbalance: " + fetchPivotOf(target.getValue()));
            System.out.println("Grau: " + target.grau() + ", Endpoint?: " + target.StringEndpoint());
            System.out.println("Profundidade: " + target.doLevel(root));
            System.out.println("Nível: " + target.doLevel(root));
            System.out.println("Altura: " + (target.doHeight()));
        } else {
            System.out.println("[info()]: Node does not exists.");
        }
        
    }
    
    public int calculateNodes(NodeBal root) { //calcular número de nós
        Queue<NodeBal> queue = new LinkedList<>(); // cria uma lista para saber quais os próximos nós a serem visitados
        queue.add(root); //começa pela raiz.
        int num = 0; //número de nós contados
        while(!queue.isEmpty()) //mantem o código rodando até não sobrarem nós para percorrer
        {
            NodeBal temp = queue.remove(); //remover nós da lista, pois este já foi percorrido, retorna o nó que foi removido para servir como eixo.
            num++; //adicionar à contagem
            if(temp.getLeft() !=null) // ir pra esquerda se existir
                queue.add(temp.getLeft()); // repete o processo com o filho esquerdo
            if(temp.getRight() !=null) // ir pra direita se existir
                queue.add(temp.getRight()); // repete o processo com o filho direito
        }
        System.out.println("Total Size (Recursive function): " + num);
        return num;
    }
    
    
    // ------------------
    // Navigation Methods
    // ------------------
    
    public void traversePreOrder(NodeBal node) { // NLR
        if (node != null) {
            System.out.print(node.getValue() + " | ");
            traversePreOrder(node.getLeft());
            traversePreOrder(node.getRight());
        }
    }
    
    public void traverseInOrder(NodeBal node) { // LNR
        if (node != null) {
            traverseInOrder(node.getLeft());
            System.out.print(node.getValue() + " | ");
            traverseInOrder(node.getRight());
        }
    }
    
    public void traversePostOrder(NodeBal node) { // LRN
        if (node != null) {
            traversePostOrder(node.getLeft());
            traversePostOrder(node.getRight());
            System.out.print(node.getValue() + " | ");
        }
    }
    
    // ---------------
    // Writing methods
    // ---------------
    
    public void WritePostOrder(NodeBal node, ArrayList output) { // NLR - Escrever valor dos nós em um array. (Principal)
        if (node != null) {
            output.add(node.getValue());
            WritePostOrder(node.getLeft(), output);
            WritePostOrder(node.getRight(), output);
        }
    }
    
    public void WriteInOrder(NodeBal node, ArrayList output) { // LNR - Escrever valor dos nós em um array.
        if (node != null) {
            WriteInOrder(node.getLeft(), output);
            output.add(node.getValue());
            WriteInOrder(node.getRight(), output);
        }
    }
    
    public void WritePreOrder(NodeBal node, ArrayList output) { // LRN - Escrever valor dos nós em um array.
        if (node != null) {
            WritePreOrder(node.getLeft(), output);
            WritePreOrder(node.getRight(), output);
            output.add(node.getValue());
        }
    }
    
    
    // ##################################
    // AVL TREE METHODS
    // ##################################
    
    void updateBalance(NodeBal node) {
        node.balance = getBalance(node);
        //node.balance = 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    int height(NodeBal node) {
        return node.doHeight();
    }

    int getBalance(NodeBal node) {
        
        if (node == null) {
            return 0;
        }
        
        int auxLeft = 0, auxRight = 0; // contadores
        
        if(node.getLeft() != null)
            auxLeft = node.getLeft().doHeight() + 1;
        if(node.getRight() != null)
            auxRight = node.getRight().doHeight() + 1;
        return auxLeft - auxRight;
        

    }
    
    public NodeBal insertElement(NodeBal current, int integer) {
        if (current == null) {
            current = new NodeBal(integer);
            return current;
        } else if (current.getValue() > integer) {
            current.setLeft(insertElement(current.getLeft(), integer));
        } else if (current.getValue() < integer) {
            current.setRight(insertElement(current.getRight(), integer));
        } else {
            System.err.println("[Alert] Duplicated Value.");
        }
        return rebalance(current);
    }
    
    
    public NodeBal rebalance(NodeBal current) {
        updateOnCourse(root, current.getValue());
        int balance = current.balance;
        NodeBal pivot = fetchPivotOf(current.getValue());
        if (pivot != null){
            System.out.println("Debug: " + pivot.getValue());
        }
        
        
        
        
        
        
        /*
        if (balance > 1) {
            System.out.println("Balance of " + current.getValue() + " is: " + current.balance);
            if (height(current.getRight().getRight()) > height(current.getRight().getLeft())) {
                System.out.println("Simple Left");
                current = rotateLeft(current);
            } else {
                System.out.println("Double Right");
                current.setRight(rotateRight(current.getRight()));
                current = rotateLeft(current);
            }
        } else if (balance < -1) {
            System.out.println("Balance of " + current.getValue() + " is: " + current.balance);
            if (height(current.getLeft().getLeft()) > height(current.getLeft().getRight())) {
                System.out.println("Simple Right");
                current = rotateRight(current);
            } else {
                System.out.println("Double Left");
                current.setLeft(rotateLeft(current.getLeft()));
                current = rotateRight(current);
            }
        }
        */
    return current;
    
    }
    
    public NodeBal rotateLeft(NodeBal y) {
        /*
        
          Y             X
           \          /
            X  --->  Y
           /          \
          Z            Z
        
        */
        System.out.println("[!] Rotating left. Pivot: " + y.getValue());
        NodeBal x = y.getRight();
        NodeBal c = x.getLeft();
        NodeBal p = fetchParentOf(y.getValue());
        x.setLeft(y);
        y.setRight(c);
        if (p != null){
            p.setRight(x);
        }
        updateBalance(y);
        updateBalance(x);
        return x;
    }
    
    
    
    public NodeBal rotateRight(NodeBal y) {
        /*
                     Z     
                    / \                 (Y)
                  (Y)  A                / \
                  / \        --->      X   Z
                 X   B                / \ / \
                / \                  D  C B  A
               D   C
        */
        /*
        
        */
        System.out.println("[!] Rotating right. Pivot: " + y.getValue());
        NodeBal x = y.getLeft();
        NodeBal c = x.getRight();
        NodeBal p = fetchParentOf(y.getValue());
        x.setRight(y);
        y.setLeft(c);
        if (p != null){
            p.setLeft(x);
        }
        updateBalance(y);
        updateBalance(x);
        return x;
    }

    NodeBal delete(NodeBal node, int key) {
        if (node == null) {
            return node;
        } else if (node.getValue() > key) {
            node.setLeft(delete(node.getLeft(), key));
        } else if (node.getValue() < key) {
            node.setRight(delete(node.getRight(), key));
        } else {
            if (node.getLeft() == null || node.getRight() == null) {
                System.out.println("delete() DEBUG: " + (node == this.root));
                node = (node.getLeft() == null) ? node.getRight() : node.getLeft();
            } else {
                NodeBal mostLeftChild = mostLeftChild(node.getRight());
                node.setValue(mostLeftChild.getValue());
                node.setRight(delete(node.getRight(), node.getValue()));
            }
        }
        if (node != null) {
            node = rebalance(node);
        }
    return node;
    }
    
    private NodeBal mostLeftChild(NodeBal node) {
        NodeBal current = node;
        /* loop down to find the leftmost leaf */
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }
    
    
    
    /*
    
    public NodeBal insertElement(NodeBal current, int integer) { // adicionar recursivamente
        //this.insertElement(root, userinputint);
        //check whether the node is null or not  
        if (current == null) {
            current = new NodeBal(integer);
        }
        //insert a node in case when the given element is lesser than the element of the root node 
        else if (integer < current.getValue()) {  
            current.setLeft(insertElement(current.getLeft(), integer)); 
            if( getHeight( current.getLeft() ) - getHeight( current.getRight() ) == 2 )  
                if ( integer < current.getLeft().getValue() )  
                    current = rotateWithLeftChild( current );  
                else  
                    current = doubleWithLeftChild( current );  
        }  
        else if (integer > current.getValue()) {  
            current.setRight(insertElement(current.getRight(), integer));  
            if( getHeight( current.getRight() ) - getHeight( current.getLeft() ) == 2 )   {  
                if( integer > current.getRight().getValue()) {
                    current = rotateWithRightChild( current );
                }
                else {
                    current = doubleWithRightChild( current );
                }
            }
        }  
        else;
        // if the element is already present in the tree, we will do nothing
        
        current.balance = getMaxHeight( getHeight( current.getLeft() ), getHeight( current.getRight() ) ) + 1; 
          
        return current;  
            
    }
    
    // creating rotateWithLeftChild() method to perform rotation of binary tree node with left child        
    private NodeBal rotateWithLeftChild(NodeBal target)  
    {  
        System.out.println("target: " + target.getValue());
        NodeBal node1 = target.getLeft();
        System.out.println("debug: " + target.getLeft().getValue());
        target.setLeft(node1.getRight());
        System.out.println("left of " + target.getLeft() + " is now: " + node1.getRight());
        node1.setRight(target);  
        target.balance = getMaxHeight(getHeight(target.getLeft()), getHeight(target.getRight())) + 1;  
        node1.balance = getMaxHeight(getHeight(node1.getLeft()), target.balance ) + 1;  
        System.out.println("##### ROTATE LEFT");
        return node1;  
    }  
  
    // creating rotateWithRightChild() method to perform rotation of binary tree node with right child        
    private NodeBal rotateWithRightChild(NodeBal node1)  
    {  
        NodeBal node2 = node1.getRight();  
        node1.setRight(node2.getLeft());  
        node2.setLeft(node1);  
        node1.balance = getMaxHeight(getHeight( node1.getLeft()), getHeight(node1.getRight())) + 1;  
        node2.balance = getMaxHeight(getHeight( node2.getRight()), node1.balance ) + 1;
        System.out.println("##### ROTATE RIGHT");
        return node2;  
    }  
  
    //create doubleWithLeftChild() method to perform double rotation of binary tree node. This method first rotate the left child with its right child, and after that, node3 with the new left child  
    private NodeBal doubleWithLeftChild(NodeBal node3)  
    {  
        node3.setLeft(rotateWithRightChild(node3.getLeft()));  
        System.out.println("##### ROTATE DOUBLE LEFT");
        return rotateWithLeftChild(node3);  
    }  
  
    //create doubleWithRightChild() method to perform double rotation of binary tree node. This method first rotate the right child with its left child and after that node1 with the new right child  
    private NodeBal doubleWithRightChild(NodeBal node1)  
    {  
        node1.setRight(rotateWithLeftChild(node1.getRight()));
        System.out.println("##### ROTATE DOUBLE RIGHT");
        return rotateWithRightChild(node1);  
    }
    
    private int getHeight(NodeBal node ) {  
        return node == null ? -1 : node.balance;  
    }
    
    private int getMaxHeight(int leftNodeHeight, int rightNodeHeight) {  
        return leftNodeHeight > rightNodeHeight ? leftNodeHeight : rightNodeHeight;  
    }
    */
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
