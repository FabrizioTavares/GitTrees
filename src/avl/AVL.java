
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
                            + "\n'5': Force right rotation"
                            + "\n'6': Force left rotation"
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
                  
                case 1: // insere objeto no conte??do do n??.
                    System.out.print(">>> New Value: ");
                    this.userinputint = readinput.nextInt();
                    this.insertElement(root, userinputint);
                    break;
                    
                case 2: //remover certo valor
                    System.out.print(">>> Value to be removed: ");
                    this.userinputint = readinput.nextInt();
                    this.delete(root, userinputint);
                    break;
                             
                case 3: // imprimir informa????es da ??rvore
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
                
                case 5:
                    System.out.print(">>> Manual Rotation [RIGHT]: ");
                    this.userinputint = readinput.nextInt();
                    this.rotateRight(fetch(root, userinputint));
                    break;
                
                case 6:
                    System.out.print(">>> Manual Rotation [LEFT]: ");
                    this.userinputint = readinput.nextInt();
                    this.rotateLeft(fetch(root, userinputint));
                    break;
                
                case 7:
                    System.out.print(">>> Manual Balancing: ");
                    this.userinputint = readinput.nextInt();
                    this.rebalance2(fetch(root, userinputint));
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
            
            if (current.balance > 1 || current.balance < -1) {
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
            System.out.println("N??vel: " + target.doLevel(root));
            System.out.println("Altura: " + (target.doHeight()));
        } else {
            System.out.println("[info()]: Node does not exists.");
        }
        
    }
    
    public int calculateNodes(NodeBal root) { //calcular n??mero de n??s
        Queue<NodeBal> queue = new LinkedList<>(); // cria uma lista para saber quais os pr??ximos n??s a serem visitados
        queue.add(root); //come??a pela raiz.
        int num = 0; //n??mero de n??s contados
        while(!queue.isEmpty()) //mantem o c??digo rodando at?? n??o sobrarem n??s para percorrer
        {
            NodeBal temp = queue.remove(); //remover n??s da lista, pois este j?? foi percorrido, retorna o n?? que foi removido para servir como eixo.
            num++; //adicionar ?? contagem
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
    
    public void WritePostOrder(NodeBal node, ArrayList output) { // NLR - Escrever valor dos n??s em um array. (Principal)
        if (node != null) {
            output.add(node.getValue());
            WritePostOrder(node.getLeft(), output);
            WritePostOrder(node.getRight(), output);
        }
    }
    
    public void WriteInOrder(NodeBal node, ArrayList output) { // LNR - Escrever valor dos n??s em um array.
        if (node != null) {
            WriteInOrder(node.getLeft(), output);
            output.add(node.getValue());
            WriteInOrder(node.getRight(), output);
        }
    }
    
    public void WritePreOrder(NodeBal node, ArrayList output) { // LRN - Escrever valor dos n??s em um array.
        if (node != null) {
            WritePreOrder(node.getLeft(), output);
            WritePreOrder(node.getRight(), output);
            output.add(node.getValue());
        }
    }
    
    
    // ##################################
    // AVL TREE METHODS
    // ##################################
    
    int updateBalance(NodeBal node) {
        return (node.balance = getBalance(node));
        //node.balance = 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    int height(NodeBal node) {
        if (node == null){
            return 0;
        }
        else{
            return node.doHeight();
        }
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
    
    public NodeBal insertElementBinary(NodeBal current, int integer) {
        
        if (current == null) {
            current = new NodeBal(integer);
            return current;
        } else if (current.getValue() > integer) {
            current.setLeft(insertElementBinary(current.getLeft(), integer));
        } else if (current.getValue() < integer) {
            current.setRight(insertElementBinary(current.getRight(), integer));
        } else {
            System.err.println("[Alert] Duplicated Value.");
        }
        
        return (current);
        
    }
    
    public void insertElement(NodeBal current, int integer) {
        
        insertElementBinary(current, integer);
        updateOnCourse(root, integer);
        rebalance2(fetch(root, integer));
        
    }
    
    public void deleteStep(NodeBal node, int key){
        
        delete(node, key);
        updateOnCourse(root, key);
        rebalance2(fetch(root, key));
        
    }
    
    public NodeBal rebalance2(NodeBal current) {
        
        updateBalance(current);
        //updateOnCourse(root, current.getValue());
        //System.out.println("[i] Balancing " + current.getValue());
        NodeBal pivot = fetchPivotOf(current.getValue());

        if (pivot != null){
            
            System.out.println("[i] The pivot of " + current.getValue() + " is " + pivot.getValue());
            int balance = pivot.balance;
            
            if (balance > 1) { // heavy left
                
                System.out.println("[!] Balance of " + pivot.getValue() + " is: " + pivot.balance + ". Needs balancing to the Right.");
                
                if ((getBalance(pivot.getLeft()) < 0)) { // heavy right (LR)
                    System.out.println("[i] Double Right");
                    pivot.setLeft(rotateLeft(pivot.getLeft()));
                    pivot = rotateRight(pivot);
                } else {
                    System.out.println("[i] Simple Right");
                    pivot = rotateRight(pivot);
                }
   
            } else if (balance < -1) { // heavy right
                
                System.out.println("[!] Balance of " + pivot.getValue() + " is: " + pivot.balance + ". Needs balancing to the left.");


                if (getBalance(pivot.getRight()) > 0) { // heavy left (RL)
                    System.out.println("[i] Double Left");
                    pivot.setRight(rotateRight(pivot.getRight()));
                    pivot = rotateLeft(pivot);
                } else {
                    System.out.println("[i] Simple Left");
                    pivot = rotateLeft(pivot);
                }
 
            }
            
        }
        return current;
    }
    
    public NodeBal rotateLeft(NodeBal y) {
        /*
        
            P              P
           /              /
          Y              X
           \            / 
            X  --->    Y
           /            \
          C              C
        
        */
        System.out.println("[!] Rotating left. Pivot: " + y.getValue());
        NodeBal x = y.getRight();
        System.out.println("[...] Right of the pivot: " + x.getValue());
        NodeBal c = x.getLeft();
        NodeBal p = fetchParentOf(y.getValue());
        System.out.println(y.getValue() + " is now the left of " + x.getValue());
        if (y == this.root){
            this.root = x;
        }
        x.setLeft(y);
        y.setRight(c);
        if (p != null){
            System.out.println("[....] The pivot has a parent " + p.getValue() + ". Setting " + x.getValue() + " to its right.");
            if (p.getRight() == y){
                System.out.println("right.");
                p.setRight(x);
            }
            else{
                System.out.println("left.");
                p.setLeft(x);
            }
        }
        //updateBalance(y);
        //updateBalance(x);
        updateOnCourse(root, y.getValue());
        return x;
    }
    
    public NodeBal rotateRight(NodeBal y) {
        /*
        
             [P]            [P]
            \or/           \or/
             Y              X
            /                \ 
           X     --->        Y
            \               /
             C             C  
        
        */
        System.out.println("[!] Rotating right. Pivot: " + y.getValue());
        NodeBal x = y.getLeft();
        System.out.println("[...] Left of the pivot: " + x.getValue());
        NodeBal c = x.getRight();
        NodeBal p = fetchParentOf(y.getValue());
        System.out.println(y.getValue() + " is now the right of " + x.getValue());
        if (y == this.root){
            this.root = x;
        }
        x.setRight(y);
        y.setLeft(c);
        if (p != null){
            System.out.print("[....] The pivot has a parent " + p.getValue() + ". Setting " + x.getValue() + " to its ");
            if (p.getRight() == y){
                System.out.println("right.");
                p.setRight(x);
            }
            else{
                System.out.println("left.");
                p.setLeft(x);
            }
        }
        //updateBalance(y);
        //updateBalance(x);
        updateOnCourse(root, y.getValue());
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
                  
                node = (node.getLeft() == null) ? node.getRight() : node.getLeft();

            } else {
                NodeBal mostLeftChild = mostLeftChild(node.getRight());
                node.setValue(mostLeftChild.getValue());
                node.setRight(delete(node.getRight(), node.getValue()));
            }
        }
        if (node != null) {
            node = rebalance2(node);
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
    
}