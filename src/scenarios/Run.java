
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
                System.out.println("Testando inserção e rotações.");
                System.out.println("Inserir: 5, 6, 7:");
                rb.insertElement(rb.root, 5);
                rb.insertElementBinary(rb.root, 6);
                rb.insertElementBinary(rb.root, 7);
                rb.update();
                System.out.println("Inserir 5, 4, 3:");
                rb.root = null;
                rb.insertElement(rb.root, 5);
                rb.insertElementBinary(rb.root, 4);
                rb.insertElementBinary(rb.root, 3);
                rb.update();
                System.out.println("Inserir 20, 10, 15:");
                rb.root = null;
                rb.insertElement(rb.root, 20);
                rb.insertElementBinary(rb.root, 10);
                rb.insertElementBinary(rb.root, 15);
                rb.update();
                System.out.println("Inserir 40, 70, 60:");
                rb.root = null;
                rb.insertElement(rb.root, 40);
                rb.insertElementBinary(rb.root, 70);
                rb.insertElementBinary(rb.root, 60);
                rb.update();
                System.out.println("Testando Consultas:");
                rb.root = null;
                rb.insertElement(rb.root, 40);
                rb.insertElement(rb.root, 30);
                rb.insertElement(rb.root, 50);
                rb.insertElement(rb.root, 60);
                rb.insertElement(rb.root, 10);
                rb.insertElement(rb.root, 25);
                rb.update();
                System.out.println("Pre Order NLR:");
                rb.traversePreOrder(rb.root);
                System.out.println("\nPost Order LRN:");
                rb.traversePostOrder(rb.root);
                System.out.println("\nIn Order LNR:");
                rb.traverseInOrder(rb.root);
                System.out.println("\nConsultar nó (10):");
                rb.info(rb.fetch(rb.root, 10));
                System.out.println("Testando Remoção:");
                rb.update();
                System.out.println("Remover 60:");
                rb.delete(rb.root, 60);
                rb.update();
                System.out.println("Remover 25:");
                rb.delete(rb.root, 25);
                rb.update();
                System.out.println("Remover 10:");
                rb.delete(rb.root, 10);
                rb.update();
                //rb.recolor(rb.fetch(rb.root, 80));
                rb.doMenu();
        }
        
        
        
    }
    
}
