
package scenarios;

import avl.*;
import binary.*;
import java.util.Scanner;

public class Run {
    
   
    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        int choice;
        System.out.println("Insert tree type: (1) BST, (2) AVL. ");
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
                
                // rotate right test (OK)
//                avltree.createRoot(60);
//                avltree.insertElement(avltree.getRoot(), 40);
//                avltree.insertElement(avltree.getRoot(), 80);
//                avltree.insertElement(avltree.getRoot(), 20);
//                avltree.insertElement(avltree.getRoot(), 50);
//                avltree.insertElement(avltree.getRoot(), 90);
//                avltree.insertElement(avltree.getRoot(), 10);
//                avltree.insertElement(avltree.getRoot(), 30);
//                avltree.update();
//                avltree.insertElement(avltree.getRoot(), 5);
//                
//                avltree.rotateRight(avltree.fetch(avltree.getRoot(), 40));
//                
//                avltree.update();
                
                /* rotate left test (OK)
                avltree.createRoot(60);
                avltree.insertElement(avltree.getRoot(), 50);
                avltree.insertElement(avltree.getRoot(), 70);
                avltree.insertElement(avltree.getRoot(), 40);
                avltree.insertElement(avltree.getRoot(), 65);
                avltree.insertElement(avltree.getRoot(), 80);
                avltree.insertElement(avltree.getRoot(), 75);
                avltree.insertElement(avltree.getRoot(), 90);
                avltree.insertElement(avltree.getRoot(), 95);
                */ 
                
                avltree.createRoot(60);
                avltree.insertElement(avltree.getRoot(), 20);
                avltree.insertElement(avltree.getRoot(), 80);
                avltree.insertElement(avltree.getRoot(), 10);
                avltree.insertElement(avltree.getRoot(), 40);
                avltree.insertElement(avltree.getRoot(), 90);
                avltree.insertElement(avltree.getRoot(), 5);
                avltree.insertElement(avltree.getRoot(), 30);
                avltree.insertElement(avltree.getRoot(), 50);
                avltree.update();
                System.out.println("INSERT 25");
                //avltree.insertElement(avltree.getRoot(), 25);
                //avltree.rotateLeft(avltree.fetch(avltree.getRoot(), 20));
                //avltree.rotateRight(avltree.fetch(avltree.getRoot(), 60));
                //avltree.update();
                //avltree.rotateRight(avltree.fetch(avltree.getRoot(), 60));
                avltree.update();
                avltree.doMenu();
                break;
        }
        
        
        
    }
    
}
