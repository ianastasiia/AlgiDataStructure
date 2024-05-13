public class AATree {

    private Node root = null;

    public static Node nullNode = new Node(0, 0, null, null);
    public int iterationCount = 0;

    public AATree() {
        root = null;
    }
    public void clear() {
        root = null;
    }
    public Node skew(Node t){
        iterationCount++;
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        else if (t.left.level == t.level)
            return new Node(t.left.value, t.left.level, t.left.left, new Node(t.value, t.level, t.left.right, t.right));
        else
            return t;
    }
    public Node split(Node t){
        iterationCount++;
        if(t == null)  return null;
        else if(t.right == null || t.right.right == null)  return t;
        else if(t.level == t.right.right.level) {
            return new Node(t.right.value, t.right.level + 1, new Node(t.value, t.level, t.left, t.right.left), t.right.right);
        }
        else return t;
    }
    public void insert( int value ) {
        iterationCount = 0;
        root = insert( value, root );
    }
    private Node insert(int value, Node t) {
        iterationCount++;
        if (t == null) {
            t = new Node(value, 1, null, null);
            iterationCount++;
        }
        else if (value < t.value) {
            t.left = insert(value, t.left);
        }
        else if (value > t.value) {
            t.right = insert(value, t.right);
        }

        t = skew(t);
        t = split(t);
        return t;
    }
    public Node decreaseLevel(Node t){
        if (t.right != null && t.left != null) {
            int shouldBe = Math.min(t.left.level, t.right.level) + 1;
            if (shouldBe < t.level) {
                t.level = shouldBe;
                if (shouldBe < t.right.level)
                    t.right.level = shouldBe;
            }
        }
        return t;
    }
    public boolean search(int val) {
        iterationCount = 0;
        return search(root, val);
    }
    private boolean search(Node t, int value) {
        iterationCount++;
        if (t == null) return false;
        else if (t.value == value) return true;
        else if (value > t.value) return search(t.right, value);
        else if (value < t.value) return search(t.left, value);
        else return false;
    }
    public void remove(int value) {
        iterationCount = 0;
        Node deletedNode = nullNode;
        root = remove( value, root );
    }

    private Node remove(int value, Node t ) {
        iterationCount++;
        if (t == null) return t;
        else if (value > t.value) t.right = remove(value, t.right);
        else if (value < t.value) t.left = remove(value, t.left);
        else {
            if (isLeaf(t)) return null;
            else if (t.left == null) {
                Node l = successor(t);
                t.right = remove(l.value, t.right);
                t.value = l.value;
            }
            else {
                Node l = predecessor(t);
                t.left = remove(l.value, t.left);
                t.value = l.value;
            }
        }
        t = decreaseLevel(t);
        t = skew(t);
        t.right = skew(t.right);
        if (t.right != null) t.right.right = skew(t.right.right);
        t = split(t);
        t.right = split(t.right);
        return t;

    }

    public void print(){
        print(root,0);
        System.out.println();
    }
    private void print(Node t, int level){
        for (int i = 0; i < level; i++) {
            System.out.print("-");
        }
        System.out.println(t.value +" ("+t.level+")");
        if(t.left != null){
            print(t.left, level+1);
        }
        if(t.right != null){
            print(t.right, level+1);
        }
    }
    public Node predecessor(Node t) {
        iterationCount++;
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        } else {
            return predecessor(t.left);
        }
    }
    public Node successor(Node t) {
        iterationCount++;
        if (t == null) {
            return null;
        } else if (t.right == null) {
            return t;
        } else {
            return predecessor(t.right);
        }
    }

    public boolean isLeaf(Node t) {
        return (t.left == null && t.right == null);
    }

}
