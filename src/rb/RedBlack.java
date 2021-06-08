
package rb;

// this time around, using public encapsulation

import java.util.Scanner;
import utils.TreePrinter;


public class RedBlack {
    
    public NodeRB root;
    public int userinput, userinput2;
    
    public void doMenu(){ // creates a menu to interact with the tree.
        Scanner readinput = new Scanner(System.in); // needed to read the user's input
        System.out.println("Using Red Black Tree. 9 for list of commands");
        while (this.userinput != -1) {
            
            this.update(); // every loop, print diagram of the tree
            
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
                     
                case -1: // quit.
                    System.out.println("Stopping.");
                    break;
                
                case 0: // Empty all
                    System.out.print("Removing all.");
                    this.root = null;
                    break;
                  
                case 1: // insere objeto no conteúdo do nó.
                    System.out.print(">>> New Value: ");
                    this.userinput2 = readinput.nextInt();
                    this.insertElement(this.root, userinput2);
                    break;
                    
                case 2: //remover certo valor
                    System.out.print(">>> Value to be removed: ");
                    this.userinput2 = readinput.nextInt();
                    this.delete(root, userinput2);
                    break;
                             
                case 3: // imprimir informações da árvore

                    //this.calculateNodes(root);
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
                    this.userinput2 = readinput.nextInt();
                    this.info(this.fetch(root, userinput2));
                    break;
                
          
            }
            
        }
        
    }
    
    // -----------------------------
    // Updating and Printing Methods
    // -----------------------------
    
    public void update(){
        try {
            TreePrinter.print(root);
        } catch (Exception e) {
            System.out.println("[Alert] Could not print diagram!");
        }
    }
    
    public void info(NodeRB target){
        
        NodeRB parent, uncle, grandparent;
        parent = fetchParentOf(target);
        grandparent = fetchParentOf(parent);
        if (grandparent != null)
            uncle = (grandparent.left == parent) ? grandparent.right : grandparent.left;
        else
            uncle = null;
        
        if (target != null) {
            System.out.println("Object: " + target);
            System.out.println("Value: " + target.value);
            System.out.println("Color: " + target.color);
            System.out.print("Parent: ");
            try {
                System.out.println(parent.value);
            } catch (Exception e) {
                System.out.println("null");
            }
            System.out.print("Grandparent: ");
            try {
                System.out.println(grandparent.value);
            } catch (Exception e) {
                System.out.println("null");
            }
            System.out.print("Uncle: ");
            try {
                System.out.println(uncle.value);
            } catch (Exception e) {
                System.out.println("null");
            }
            //System.out.println("Grau: " + target.grau() + ", Endpoint?: " + target.StringEndpoint());
            //System.out.println("Profundidade: " + target.doLevel(root));
            //System.out.println("Nível: " + target.doLevel(root));
            //System.out.println("Altura: " + (target.doHeight()));
        } else {
            System.out.println("[info()]: Node does not exists.");
        }
        
    }
    
    public void recolor(NodeRB node){
        node.color = (node.color == 'r') ? 'b' : 'r';
    }
    
    public void rebalance(NodeRB node){
       
        // NOTE: Leaf (null) pointers are considered BLACK.
        // even if there is no uncle, suppose uncle exists and has color black.
        
        if (node == null){ //failsafe. probably not needed.
            return;
        }

        NodeRB parent = null, uncle = null, grandparent = null;
        parent = fetchParentOf(node); // attempt to fetch the parent. if null, nothing happens.
        
        if (parent != null){ // must have a parent to have a grandparent. A parent is needed for rebalancing.
            
            grandparent = fetchParentOf(parent); // attempt to fetch the grandparent. if null, nothing happens.
            
            if (grandparent != null && parent.color != 'b'){ // grandparent is also required for rebalancing.
                
                //attempt to find an uncle. can return null. in this case, see the first 'if' statement.
                uncle = (grandparent.left == parent) ? grandparent.right : grandparent.left;

                if (uncle == null){ // Phantom, used for reference. assumes the role of a black null leaf.
                    //System.out.println("[i] Uncle is NULL. Assuming as black null leaf.");
                    uncle = new NodeRB(0);
                    uncle.color = 'b';
                }

                if (uncle.color == 'r'){ // case 0
                    System.out.println("[i] Case 0: Recolor.");
                    recolor(parent);
                    recolor(uncle);
                    recolor(grandparent);
                    return;
                }

                if (uncle.color == 'b'){

                    //case 1: simple right
                    if (parent.left == node && grandparent.left == parent){
                        System.out.println("[i]: Simple Right (RR) Rotation...");
                        rotateRight(grandparent);
                        recolor(parent);
                        recolor(grandparent);
                    }

                    //case 2: simple left
                    else if (parent.right == node && grandparent.right == parent){
                        System.out.println("[i]: Simple left (LL) Rotation...");
                        rotateLeft(grandparent);
                        recolor(parent);
                        recolor(grandparent);
                    }

                    //case 3: double left
                    else if (parent.left == node && grandparent.right == parent){
                        System.out.println("[i]: Double Left (RL) Rotation...");
                        rotateRight(parent);
                        rotateLeft(grandparent);
                        recolor(node);
                        recolor(grandparent);
                    }

                    //case 4: double right
                    else if (parent.right == node && grandparent.left == parent){
                        System.out.println("[i]: Double Right (LR) Rotation...");
                        rotateLeft(parent);
                        rotateRight(grandparent);
                        recolor(node);
                        recolor(grandparent);
                    }

                }

            }
        }        
    }
    
    // -----------------
    // Fetching Methods
    // -----------------
    
    public NodeRB fetch(NodeRB current, int target){
        
        if (current != null) {
            if (current.value == target) {
                return current;
            } else if (current.value > target) { //left
                return fetch(current.left, target);
            } else if (current.value < target) { //right
                return fetch(current.right, target);
            } else {
                return null;
            }
        }
        return current;
        
    }
    
    private NodeRB fetch(NodeRB current, NodeRB target){
        
        if (current != null && target != null) { // "&& target != null" is a failsafe. probably not needed.
            if (current == target) {
                return current;
            } else if (current.value > target.value) { //left
                return fetch(current.left, target);
            } else if (current.value < target.value) { //right
                return fetch(current.right, target);
            } else {
                return null;
            }
        }
        return current;
        
    }
    
    public NodeRB fetchParentOf(int target){
        NodeRB current = root;
        NodeRB parent = null;
        while (current != null) {
            
            if (current.left != null){
                if (current.left.value == target){
                    parent = current;
                    return parent;
                }
            }
            
            if (current.right != null){
                if (current.right.value == target){
                    parent = current;
                    return parent;
                }
            }
            
            if (current.value > target){ //left
                current = current.left;
            }
            
            else if (current.value < target){ // right
                current = current.right;
            }
            
            else {
                break;
            }

        }
        return parent;
    }
    
    private NodeRB fetchParentOf(NodeRB target){
        NodeRB current = root;
        NodeRB parent = null;
        while (current != null && target != null) { // "&& target != null" is a failsafe. probably not needed.
            
            if (current.left != null){
                if (current.left == target){
                    parent = current;
                    return parent;
                }
            }
            
            if (current.right != null){
                if (current.right == target){
                    parent = current;
                    return parent;
                }
            }
            
            if (current.value > target.value){ //left
                current = current.left;
            }
            
            else if (current.value < target.value){ // right
                current = current.right;
            }
            
            else {
                break;
            }

        }
        return parent;
    }
    
    // -----------------
    // Insertion Methods
    // -----------------
    
    public void insertElement(NodeRB current, int integer){

        if (this.root == null){
            this.root = new NodeRB(integer);
            root.color = 'b';
        }
        
        else{
            insertElementBinary(this.root, integer);
            rebalance(fetch(this.root, integer));
        }

    }
    
    public NodeRB insertElementBinary(NodeRB current, int integer) {
        
        if (current == null) {
            current = new NodeRB(integer);
            return current;
        } else if (current.value > integer) {
            current.left = insertElementBinary(current.left, integer);
        } else if (current.value < integer) {
            current.right = insertElementBinary(current.right, integer);
        } else {
            System.err.println("[Alert] Duplicated Value.");
        }
        
        return (current);
        
    }
    
    // ---------------
    // Removal Methods
    // ---------------
    
    public NodeRB delete(NodeRB node, int key) {
        if (node == null) {
            return node;
        } else if (node.value > key) {
            node.left = delete(node.left, key);
        } else if (node.value < key) {
            node.right = delete(node.right, key);
        } else { // value has been found
            if (node.left == null || node.right == null) { // case: has one child or two childs
                node = (node.left == null) ? node.right : node.left; // fetch and set this node as existing child

            } else { // has two children
                NodeRB biggestAntecessor = biggestValue(node.left); // search left subtree
                node.value = biggestAntecessor.value;
                node.left = delete(node.left, biggestAntecessor.value);
            }
        }
        if (node != null) {
            //node = rebalance(node);
        }
    return node;
    }
    
    private NodeRB biggestValue(NodeRB current) { //pegar o maior valor (o mais a direita). ajuda na remoção do terceiro caso
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }
    
    // ------------------
    // Navigation Methods
    // ------------------
    
    public void traversePreOrder(NodeRB node) { // NLR
        if (node != null) {
            System.out.print(node.value + " | ");
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }
    
    public void traverseInOrder(NodeRB node) { // LNR
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(node.value + " | ");
            traverseInOrder(node.right);
        }
    }
    
    public void traversePostOrder(NodeRB node) { // LRN
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            System.out.print(node.value + " | ");
        }
    }
    
    // ----------------
    // Rotation Methods
    // ----------------
    
    public NodeRB rotateLeft(NodeRB y) {
        /*
        
            P              P
           /              /
          Y              X
           \            / 
            X  --->    Y
           /            \
          C              C
        
        */
        //System.out.println("[!] Rotating left. Pivot: " + y.value);
        NodeRB x = y.right;
        //System.out.println("[...] Right of the pivot: " + x.value);
        NodeRB c = x.left;
        NodeRB p = fetchParentOf(y.value);
        //System.out.println(y.value + " is now the left of " + x.value);
        if (y == this.root){
            this.root = x;
        }
        x.left = y;
        y.right= c;
        if (p != null){
            //System.out.println("[....] The pivot has a parent " + p.value + ". Setting " + x.value + " to its right.");
            if (p.getRight() == y){
                //System.out.println("right.");
                p.right = x;
            }
            else{
                //System.out.println("left.");
                p.left = x;
            }
        }
        //updateBalance(y);
        //updateBalance(x);
        //updateOnCourse(root, y.value);
        return x;
    }
    
    public NodeRB rotateRight(NodeRB y) {
        /*
        
             [P]            [P]
            \or/           \or/
             Y              X
            /                \ 
           X     --->        Y
            \               /
             C             C  
        
        */
        //System.out.println("[!] Rotating right. Pivot: " + y.value);
        NodeRB x = y.left;
        //System.out.println("[...] Left of the pivot: " + x.value);
        NodeRB c = x.right;
        NodeRB p = fetchParentOf(y.value);
        //System.out.println(y.value + " is now the right of " + x.value);
        if (y == this.root){
            this.root = x;
        }
        x.right = y;
        y.left = c;
        if (p != null){
            //System.out.print("[....] The pivot has a parent " + p.value + ". Setting " + x.value + " to its ");
            if (p.getRight() == y){
                //System.out.println("right.");
                p.right = (x);
            }
            else{
                //System.out.println("left.");
                p.left = (x);
            }
        }
        //updateBalance(y);
        //updateBalance(x);
        //updateOnCourse(root, y.getValue());
        return x;
    }
    
}


