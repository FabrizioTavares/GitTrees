
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
        
                avltree.doMenu();
                break;
        }
        
        
        
    }
    
}
