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
                
                /*    
                case "info": // informação do nó selecionado
                    System.out.println("Grau: " + this.targetedNode.grau() + ", Endpoint?: " +this.targetedNode.StringEndpoint());
                    System.out.println("Profundidade: " + this.targetedNode.doLevel(root));
                    System.out.println("Nível: " + this.targetedNode.doLevel(root));
                    System.out.println("Altura: " + (this.targetedNode.doHeight()));
                    break;
                */
                    
                     
                case 2: //remover certo valor
                    System.out.print(">>> Value to be removed: ");
                    this.userinputint = readinput.nextInt();
                    this.remove(root, userinputint);
                    break;
                    
                case 1: // insere objeto no conteúdo do nó.
                    System.out.print(">>> Value: ");
                    this.userinputint = readinput.nextInt();
                    this.addRecursive(root, userinputint);
                    break;
                     
                case 3: // imprimir informações da árvore
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
    
    public int calculateNodes(Node root) { //calcular número de nós
        Queue<Node> queue = new LinkedList<>(); // cria uma lista para saber quais os próximos nós a serem visitados
        queue.add(root); //começa pela raiz.
        int num = 0; //número de nós contados
        while(!queue.isEmpty()) //mantem o código rodando até não sobrarem nós para percorrer
        {
            Node temp = queue.remove(); //remover nós da lista, pois este já foi percorrido, retorna o nó que foi removido para servir como eixo.
            num++; //adicionar à contagem
            if(temp.getLeft() !=null) // ir pra esquerda se existir
                queue.add(temp.getLeft()); // repete o processo com o filho esquerdo
            if(temp.getRight() !=null) // ir pra direita se existir
                queue.add(temp.getRight()); // repete o processo com o filho direito
        }
        System.out.println("Total Size (Recursive function): " + num);
        return num;
    }
    
    
    // ####################
    // METODOS DE NAVEGAÇÃO (OK)
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
    
    public void WritePostOrder(Node node, ArrayList output) { // NLR - Escrever valor dos nós em um array. (Principal)
        if (node != null) {
            output.add(node.getValue());
            WritePostOrder(node.getLeft(), output);
            WritePostOrder(node.getRight(), output);
        }
    }
    
    public void WriteInOrder(Node node, ArrayList output) { // LNR - Escrever valor dos nós em um array.
        if (node != null) {
            WriteInOrder(node.getLeft(), output);
            output.add(node.getValue());
            WriteInOrder(node.getRight(), output);
        }
    }
    
    public void WritePreOrder(Node node, ArrayList output) { // LRN - Escrever valor dos nós em um array.
        if (node != null) {
            WritePreOrder(node.getLeft(), output);
            WritePreOrder(node.getRight(), output);
            output.add(node.getValue());
        }
    }
    
    
    // ##################################
    // METODOS DE ÁRVORE BINÁRIA DE BUSCA
    // ##################################
    
    public Node addRecursive(Node current, int integer) { // adicionar recursivamente
        
        if (current == null) { // achou espaço vazio? adicionar.
            current = new Node(integer);
            return new Node(integer);
        }

        if (integer < current.getValue()) { // navegar até à esquerda, em busca de espaço vazio.
            current.setLeft(addRecursive(current.getLeft(), integer));
        }

        else if (integer > current.getValue()) { // navegar até à direita, em busca de espaço vazio.
            current.setRight(addRecursive(current.getRight(), integer));
        }

        else {
            // caso valor já exista
            System.err.println("[Alert] Value already exists");
            return current;
        }

        return current;
        
    }
    
    
    private Node biggestValue(Node current) { //pegar o maior valor (o mais a direita). ajuda na remoção do terceiro caso
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current;
    }
    
    
    public Node remove(Node current, int value) {
        // chave não encontrada na árvore
        if (current == null) {
            return current;
        }

        // valor menor, procurar na sub-árvore esquerda
        if (value < current.getValue()) {
            current.setLeft(remove(current.getLeft(), value));
        } else if (value > current.getValue()) {
            // valor maior, procurar na sub-árvore direita
            current.setRight(remove(current.getRight(), value));
        } else { // valor encontrado
            // caso 1: nó é uma folha (não tem filhos)
            if (current.getLeft() == null && current.getRight() == null) {
                // remove-o (seta a "raiz" deste nó para null)
                System.out.println("DEBUG: " + (current.equals(root)));
                current = null;
                return null;
            } else if (current.getLeft() != null && current.getRight() != null) {
                // caso 3: nó tem 2 filhos
                // buscar na subarvore esquerda o maior valor
                Node maiorAntecessor = biggestValue(current.getLeft());

                // este maior valor da subarvore esquerda ocupa a posição do nó deletado.
                // considerações: os antigos laços devem ser mantidos.
                current.setValue(maiorAntecessor.getValue());
                
                // remove o antecessor recursivamente
                current.setLeft(remove(current.getLeft(), maiorAntecessor.getValue()));
            } else {
                // caso 2: nó só tem um filho
                // utiliza operador ternário. condição verdadeira, parte '?' é executada. caso contrário, parte ':'. similar ao if/else.
                Node child = (current.getLeft() != null) ? current.getLeft() : current.getRight();
                current = child;
            }
        }

        return current;
    }
   
    public ArrayList importToArray() { // NLR - nós da árvore para arraylist (BST to Array)
        // Talvez seja necessário reformular para isto -> 'Sorted Array to Balanced Search Tree'
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