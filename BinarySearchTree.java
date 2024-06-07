import java.util.Scanner;

class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}

public class BinarySearchTree {
    Node root;

    BinarySearchTree() {
        root = null;
    }

    void insert(int key) {
        root = insertRec(root, key);
        System.out.println(key + " inserted into BST.");
        //printArray();
    }

    Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        return root;
    }

    void delete(int key) {
        root = deleteRec(root, key);
        System.out.println(key + " deleted from BST.");
        printArray();
    }

    Node deleteRec(Node root, int key) {
        if (root == null)
            return root;

        if (key < root.key)
            root.left = deleteRec(root.left, key);
        else if (key > root.key)
            root.right = deleteRec(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.key = minValue(root.right);

            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }

    int minValue(Node root) {
        int minValue = root.key;
        while (root.left != null) {
            minValue = root.left.key;
            root = root.left;
        }
        return minValue;
    }

    boolean search(int key) {
        return searchRec(root, key);
    }

    boolean searchRec(Node root, int key) {
        if (root == null || root.key == key)
            return root != null;

        if (key < root.key)
            return searchRec(root.left, key);
        return searchRec(root.right, key);
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);

        // Input: Get 20 numbers from the user and store in an array
        System.out.println("Enter the 20 elements :");
        int[] userNumbers = new int[20];
        for (int i = 0; i < 20; i++) {
            System.out.print("Enter a number at index "+ i + ":");
            userNumbers[i] = scanner.nextInt();
            bst.insert(userNumbers[i]);
        }

        // Perform operations
        // 1. Insert an element into BST
        System.out.print("Enter a number to insert into the BST: ");
        int insertElement = scanner.nextInt();
        bst.insert(insertElement);

        // 11. Delete an element from BST
        System.out.print("Enter a number to delete from the BST: ");
        int deleteElement = scanner.nextInt();
        bst.delete(deleteElement);

        // 111. Search an element in BST
        System.out.print("Enter a number to search in the BST: ");
        int searchElement = scanner.nextInt();
        if (bst.search(searchElement)) {
            System.out.println(searchElement + " is found in the BST.");
        } else {
            System.out.println(searchElement + " is not found in the BST.");
        }
    }

    void printArray() {
        System.out.print("The elements in array are : ");
        inOrder(root);
        System.out.println();
    }

    void inOrder(Node root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.key + " ," + " ");
            inOrder(root.right);
        }
    }
}