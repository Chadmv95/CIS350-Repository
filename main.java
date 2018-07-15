import java.util.Scanner;

public class main {
    
    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);
        String userString;
        int userInt;
        
        System.out.println("==============================");
        System.out.println("======Brainstorm Helper!======");
        System.out.println("==============================");
        
        System.out.println("Please enter a string for the start");
        userString = input.nextLine();
        
        BPlusTree tree = new BPlusTree(userString);
        
        System.out.println("Enter another string");
        userString = input.nextLine();
        System.out.println("Enter a child number");
        userInt = input.nextInt();
        input.nextLine();
        
        Node u1 = new Node(userString);
        
        System.out.println("Enter another string");
        userString = input.nextLine();
        Node u2 = new Node(userString);
        
        System.out.println("Enter another string");
        userString = input.nextLine();
        Node u3 = new Node(userString);
        
        System.out.println("Enter another string");
        userString = input.nextLine();
        Node u4 = new Node(userString);
        
        System.out.println("Enter another string");
        userString = input.nextLine();
        Node u5 = new Node(userString);
        
        tree.add(u1, userInt-1);
        tree.add(u2);
        tree.add(u3);
        tree.add(u4);
        tree.add(u5);
        
        tree.add(u2, new Node("Grandbaby!"));
        tree.add(u2.getChildren(0), new Node("Great Grandbaby!"));
        
        tree.add(u5, new Node("More"));
        tree.add(u5, new Node("Grand"));
        tree.add(u5, new Node("Children"));
        
        tree.printTree();
        
        input.close();
        
    }
}
