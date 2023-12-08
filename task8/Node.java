package task8;

public class Node {
    String left;
    String right;

    Node(String left, String right){
        this.left = left;
        this.right = right;
    }

    Node(){

    }

    public String decide(Character direction){
        if(direction.equals('L')){
            return left;
        } else {
            return right;
        }
    }
}
