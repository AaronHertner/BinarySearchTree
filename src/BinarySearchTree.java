public class BinarySearchTree{

    //the nodes we'll be using to keep track of data
    class Node{
        int x;
        Node left;  //left subtree
        Node right; //right subtree
        Node(int x){
            this.x = x;
            left = null;
            right = null;
        }
    }

    //keeps track of the top most node
    Node root;

    //returns a boolean indicating the presence of a value
    public boolean contains(Node current, int x){
        if(current == null){
            return false;
        }

        if(x == current.x){
            return true;
        }
        else{
            return x < current.x
            ? contains(current.left, x)
            : contains(current.right, x);
        }
    }

    //calls the recursive add method to add given element
    //x in the correct position
    public void add(int x){
        root = addRec(root, x);
    }

    //recursive method for adding an element x
    public Node addRec(Node current, int x){
        if(current == null){
            return new Node(x);
        }

        if(x < current.x){
            current.left = addRec(current.left, x);
        }else if(x > current.x){
            current.right = addRec(current.right, x);
        } else{
            return current;
        }

        return current;
    }

    //calls the recursive delete method for deleting the
    //given element x
    public void delete(int x){
        root = deleteRec(root, x);
    }

    //deletes the value x
    public Node deleteRec(Node current, int x){
        if(current == null){
            return null;
        }

        if(x == current.x){
            int i = findLowest(current);
            if(i < 0){current = null;}
            else{
                current.x = i;
            }
        }
        else if(x < current.x){
            current.left = deleteRec(current.left, x);
            return current;
        }
        else {
            current.right = deleteRec(current.right, x);
            return current;
        }
        return current;
    }

    //finds the lowest value in the subtree set of a given node
    public int findLowest(Node current){

        //current node has no subtrees
        if(current.right == null && current.left == null){return -1;}

        //find the smallest value in the right subtree
        else if (current.right != null){
            current = current.right;
            while(current.left.left != null){
                current = current.left;
            }
            int t = current.left.x;
            current.left = null;
            return t;
        }

        //no right subtree, find largest value in left subtree
        else{
            current = current.left;
            while(current.right.right != null){
                current = current.right;
            }
            int t = current.right.x;
            current.right = null;
            return t;
        }
    }
}
