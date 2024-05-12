public class Node {
    int value;
    int level;
    Node right;
    Node left;
    public Node(int value, int level, Node left, Node right) {
        this.value = value;
        this.level = level;
        this.right = right;
        this.left = left;
    }


}
