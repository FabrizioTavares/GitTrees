package binary;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue; //implements linkedlist
import java.util.ArrayList;
import utils.TreePrinter;

public class BinaryTree<T> {
    
    private Node root;
    public int userinput;
    public int userinputint;
    
    public BinaryTree(){
        
        this.root = null;
        this.userinput = -2;
        this.userinputint = 0;
        
    }
    
    public void doMenu(){ // creates a menu to interact with the tree.
        Scanner readinput = new Scanner(System.in); // needed to read the user's input
        System.out.println("Using binary tree. Do 9 for list of commands");
        while (this.userinput != -1) {
            
            this.update(); // print diagram of the tree every loop
            System.out.print("Option: ");
            this.userinput = readinput.nextInt();
            
            switch(this.userinput){ // checks and matches the input to available functions
                
                case 9:
                    System.out.println("\nAvailable commands:"
                            + "\n'0': create a new root"
                            + "\n'-1': quits the program"
                            + "\n'1': recursively inserts new node with value"
                            + "\n'2': select left or right child"
                            + "\n'3': print information about the tree");
                    break;
                    
                case 0: // starts the tree, creating or replacing the root.
                    System.out.print(">>> Root Value: ");
                    this.userinputint = readinput.nextInt();
                    this.createRoot(userinputint);
                    break;
                    
                case -1: // quit.
                    System.out.println("Stopping.");
                    break;
                  
                case 4: 
                    System.out.print(">>> Value of node to be fetched: ");
                    this.userinputint = readinput.nextInt();
                    this.info(this.fetch(userinputint));
                    break;
                
                case 2: //remover certo valor
                    System.out.print(">>> Value to be removed: ");
                    this.userinputint = readinput.nextInt();
                    this.remove(root, userinputint);
                    break;
                    
                case 1: // insere objeto no conte??do do n??.
                    System.out.print(">>> Value: ");
                    this.userinputint = readinput.nextInt();
                    this.addRecursive(root, userinputint);
                    break;
                     
                case 3: // imprimir informa????es da ??rvore
                    this.calculateNodes(root);
                    System.out.println("Pre Order NLR:");
                    this.traversePreOrder(root);
                    System.out.println("\nPost Order LRN:");
                    this.traversePostOrder(root);
                    System.out.println("\nIn Order LNR:");
                    this.traverseInOrder(root);
                    break;
  
            }
        }
        
    }
    
    public void update(){
        try {
            TreePrinter.print(root);
        } catch (Exception e) {
            System.out.println("[Alert] Could not print diagram!");
        }
    }
    
    public Node fetch(int target){
        Node current = root;
        while (current != null) {
            if (current.getValue() == target) {
                break;
            }
            current = current.getValue() < target ? current.getRight() : current.getLeft();
        }
        return current;
    }
    
    public void info(Node target){
        System.out.println("Grau: " + target.grau() + ", Endpoint?: " + target.StringEndpoint());
        System.out.println("Profundidade: " + target.doLevel(root));
        System.out.println("N??vel: " + target.doLevel(root));
        System.out.println("Altura: " + (target.doHeight()));
    }

    public Node getRoot() {
        return root;
    }
    
    public void createRoot(int value){ //criar raiz
        Node newNode = new Node();
        newNode.setValue(value);
        if (this.root == null) {
            this.root = newNode;
            System.out.println("Root Created. Pointing to root.");
        }
        else {
            this.root = newNode;
            System.out.println("Root REPLACED. Pointing to root.");
        }
        
    }
    
    public int calculateNodes(Node root) { //calcular n??mero de n??s
        Queue<Node> queue = new LinkedList<>(); // cria uma lista para saber quais os pr??ximos n??s a serem visitados
        queue.add(root); //come??a pela raiz.
        int num = 0; //n??mero de n??s contados
        while(!queue.isEmpty()) //mantem o c??digo rodando at?? n??o sobrarem n??s para percorrer
        {
            Node temp = queue.remove(); //remover n??s da lista, pois este j?? foi percorrido, retorna o n?? que foi removido para servir como eixo.
            num++; //adicionar ?? contagem
            if(temp.getLeft() !=null) // ir pra esquerda se existir
                queue.add(temp.getLeft()); // repete o processo com o filho esquerdo
            if(temp.getRight() !=null) // ir pra direita se existir
                queue.add(temp.getRight()); // repete o processo com o filho direito
        }
        System.out.println("Total Size (Recursive function): " + num);
        return num;
    }
    
    
    // ####################
    // METODOS DE NAVEGA????O (OK)
    // ####################
    
    public void traversePreOrder(Node node) { // NLR
        if (node != null) {
            System.out.print(node.getValue() + " | ");
            traversePreOrder(node.getLeft());
            traversePreOrder(node.getRight());
        }
    }
    
    public void traverseInOrder(Node node) { // LNR
        if (node != null) {
            traverseInOrder(node.getLeft());
            System.out.print(node.getValue() + " | ");
            traverseInOrder(node.getRight());
        }
    }
    
    public void traversePostOrder(Node node) { // LRN
        if (node != null) {
            traversePostOrder(node.getLeft());
            traversePostOrder(node.getRight());
            System.out.print(node.getValue() + " | ");
        }
    }
    
    // ---------------
    // Writing methods (OK)
    // ---------------
    
    public void WritePostOrder(Node node, ArrayList output) { // NLR - Escrever valor dos n??s em um array. (Principal)
        if (node != null) {
            output.add(node.getValue());
            WritePostOrder(node.getLeft(), output);
            WritePostOrder(node.getRight(), output);
        }
    }
    
    public void WriteInOrder(Node node, ArrayList output) { // LNR - Escrever valor dos n??s em um array.
        if (node != null) {
            WriteInOrder(node.getLeft(), output);
            output.add(node.getValue());
            WriteInOrder(node.getRight(), output);
        }
    }
    
    public void WritePreOrder(Node node, ArrayList output) { // LRN - Escrever valor dos n??s em um array.
        if (node != null) {
            WritePreOrder(node.getLeft(), output);
            WritePreOrder(node.getRight(), output);
            output.add(node.getValue());
        }
    }
    
    
    // ##################################
    // METODOS DE ??RVORE BIN??RIA DE BUSCA
    // ##################################
    
    public Node addRecursive(Node current, int integer) { // adicionar recursivamente
        
        if (current == null) { // achou espa??o vazio? adicionar.
            current = new Node(integer);
            return new Node(integer);
        }

        if (integer < current.getValue()) { // navegar at?? ?? esquerda, em busca de espa??o vazio.
            current.setLeft(addRecursive(current.getLeft(), integer));
        }

        else if (integer > current.getValue()) { // navegar at?? ?? direita, em busca de espa??o vazio.
            current.setRight(addRecursive(current.getRight(), integer));
        }

        else {
            // caso valor j?? exista
            System.err.println("[Alert] Value already exists");
            return current;
        }

        return current;
        
    }
    
    
    private Node biggestValue(Node current) { //pegar o maior valor (o mais a direita). ajuda na remo????o do terceiro caso
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current;
    }
    
    
    public Node remove(Node current, int value) {
        // chave n??o encontrada na ??rvore
        if (current == null) {
            return current;
        }

        // valor menor, procurar na sub-??rvore esquerda
        if (value < current.getValue()) {
            current.setLeft(remove(current.getLeft(), value));
        } else if (value > current.getValue()) {
            // valor maior, procurar na sub-??rvore direita
            current.setRight(remove(current.getRight(), value));
        } else { // valor encontrado
            // caso 1: n?? ?? uma folha (n??o tem filhos)
            if (current.getLeft() == null && current.getRight() == null) {
                // remove-o (seta a "raiz" deste n?? para null)
                current = null;
                return null;
            } else if (current.getLeft() != null && current.getRight() != null) {
                // caso 3: n?? tem 2 filhos
                // buscar na subarvore esquerda o maior valor
                Node maiorAntecessor = biggestValue(current.getLeft());

                // este maior valor da subarvore esquerda ocupa a posi????o do n?? deletado.
                // considera????es: os antigos la??os devem ser mantidos.
                current.setValue(maiorAntecessor.getValue());
                
                // remove o antecessor recursivamente
                current.setLeft(remove(current.getLeft(), maiorAntecessor.getValue()));
            } else {
                // caso 2: n?? s?? tem um filho
                // utiliza operador tern??rio. condi????o verdadeira, parte '?' ?? executada. caso contr??rio, parte ':'. similar ao if/else.
                Node child = (current.getLeft() != null) ? current.getLeft() : current.getRight();
                current = child;
            }
        }

        return current;
    }
   
    public ArrayList importToArray() { // NLR - n??s da ??rvore para arraylist (BST to Array)
        // Talvez seja necess??rio reformular para isto -> 'Sorted Array to Balanced Search Tree'
        ArrayList outputarray = new ArrayList();
        this.WritePostOrder(root, outputarray); // NLR
        
        System.out.println("Import: " + outputarray.toString());
        return outputarray;
        

    }
    
    public void arrayToBST(ArrayList importArray){ // utiliza o arraylist para criar uma BST (Potencialmente) DESBALANCEADA.
        
        this.createRoot(0);
        this.root.setValue(Integer.valueOf(importArray.remove(0).toString()));
        for (int k = 0; k < importArray.size(); k++) {
            this.addRecursive(this.getRoot(), Integer.valueOf(importArray.get(k).toString()));
        }
     
    }

    public void convert(){
        this.arrayToBST(this.importToArray());
    }
    
}