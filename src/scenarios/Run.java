
package scenarios;

import avl.*;
import binary.*;
import rb.*;
import java.util.Scanner;

public class Run {

    
    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        int choice;
        System.out.println("Insert tree type: (1) BST, (2) AVL, (3) Red Black. ");
        choice = scan.nextInt();
        
        switch(choice){
            case 1:
                BinaryTree Btree = new BinaryTree();
                
                Btree.createRoot(4);
                Btree.getRoot().addChild();
                Btree.getRoot().getLeft().setValue(2);
                Btree.getRoot().addChild();
                Btree.getRoot().getRight().setValue(6);
                Btree.getRoot().getLeft().addChild();
                Btree.getRoot().getLeft().getLeft().setValue(1);
                Btree.getRoot().getLeft().addChild();
                Btree.getRoot().getLeft().getRight().setValue(3);
                Btree.getRoot().getRight().addChild();
                Btree.getRoot().getRight().getLeft().setValue(5);
                Btree.getRoot().getRight().addChild();
                Btree.getRoot().getRight().getRight().setValue(7);
                
                Btree.doMenu();
                
                break;
            case 2:
                AVL avltree = new AVL();
                avltree.createRoot(5);
                avltree.insertElement(avltree.getRoot(), 7);
                avltree.insertElement(avltree.getRoot(), 6);
                avltree.doMenu();
                break;
            
            case 3:
                RedBlack rb = new RedBlack();
                rb.insertElement(rb.root, 46);
                rb.insertElement(rb.root, 85);
                rb.insertElement(rb.root, 92);
                rb.insertElement(rb.root, 15);
                rb.insertElement(rb.root, 96);
                rb.insertElement(rb.root, 72);
                rb.insertElement(rb.root, 18);
                rb.update();
                rb.doMenu();
        }
        
        
        
    }
    
}
