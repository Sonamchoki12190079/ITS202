import java.util.*;

public class Solution<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST

    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    /**
     * Initializes an empty symbol table.
     */
    public Solution() {
        root = null;
    }

    /**
     * Returns true if this symbol table is empty.
     * @return {@code true} if this symbol table is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        if(size() == 0){
            return true;
        }
        else{
            return false;
        }   
       
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return size(root);   
    }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null){
            return 0;
        }
        return 1 + size(x.left) + size(x.right); 
        
       
    }

    /**
     * Does this symbol table contain the given key?
     *
     * @param  key the key
     * @return {@code true} if this symbol table contains {@code key} and
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    // public boolean contains(Key key) {
       
    // }

    /**
     * Returns the value associated with the given key.
     *
     * @param  key the key
     * @return the value associated with the given key if the key is in the symbol table
     *         and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
         //key should not be null in BST
        if(key == null){
            throw new IllegalArgumentException("key should not be null");
        }
        //Size should not be zero in BST
        //if(count() == 0){
            //throw new NullPointerException("size should not be null");
        //}
        else{
            return get(root, key);
        }
        
    }

    private Value get(Node x, Key key) {
        x = root;
        while(x != null){
            int cmp;
            cmp = key.compareTo(x.key);
            if(cmp < 0) x = x.left;
            else if(cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
       
        
        
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old 
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     *
     * @param  key the key
     * @param  val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val) {
        if(root == null){
            root = new Node(key, val,1);
            return;
        }
        Node newest = root;
        while(true){
            if(key.compareTo(newest.key) < 0){
                if(newest.left == null){
                    newest.left = new Node(key, val, 1);
                    return;
                }
                else
                    newest = newest.left;
            }
            else if(key.compareTo(newest.key) > 0){
                if(newest.right == null){
                    newest.right = new Node(key, val, 1);
                    return;
                }
                else
                    newest = newest.right;
            }
            else{
                newest.val = val;
                return;
            }
        }
       
    }

    // private Node put(Node x, Key key, Value val) {
        
    // }

    /**
     * Returns the smallest key in the symbol table.
     *
     * @return the smallest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key min() {
        return min(root).key;
       
    } 

    private Node min(Node x) {
        x = root;
        while(x.left == null)
            return x;
        return x;
         
    } 

   

    /**
     * Returns the largest key in the symbol table less than or equal to {@code key}.
     *
     * @param  key the key
     * @return the largest key in the symbol table less than or equal to {@code key}
     * @throws NoSuchElementException if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key floor(Key key) {
        if(key == null){
            throw new IllegalArgumentException("Key should not be null");
        }
        if(isEmpty()){
            throw new NoSuchElementException("BTS should not be null");
        }
        Node x = floor(root, key);
        if(x == null){
            return null;
        }
        else{
            return x.key;
        }
     
        
    } 

    private Node floor(Node x, Key key) {
        Node smallest = null;
        while(x !=null){
            int com = key.compareTo(x.key);
            if(com == 0)return x;
            if(com > 0){
                smallest = x;
                x = x.right;
            }
            else{
                x = x.left;
            }
        }
        return smallest;
       
       
    } 

    
    

    /**
     * Return the key in the symbol table whose rank is {@code k}.
     * This is the (k+1)st smallest key in the symbol table.
     *
     * @param  k the order statistic
     * @return the key in the symbol table of rank {@code k}
     * @throws IllegalArgumentException unless {@code k} is between 0 and
     *        <em>n</em>–1
     */
    public Key select(int k) {
        if(k < 0 || k >= size())throw new IllegalArgumentException("The rank should be 0 and its size");

        Node curnode = select(root, k);
        if(curnode != null)return curnode.key;
        return null;
        

        
    }

    //Return key of rank k. 
    private Node select(Node x, int k) {
        while(x!=null){
            int size = size(x.left);

            if(size > k)x = x.left;


            else if(size < k){
                x = x.right;

                k = k - size -1;
            }
            else return x;
        }
        return null;
        
        
         
    } 

    

    /**
     * Returns all keys in the symbol table in the given range,
     * as an {@code Iterable}.
     *
     * @param  lo minimum endpoint
     * @param  hi maximum endpoint
     * @return all keys in the symbol table between {@code lo} 
     *         (inclusive) and {@code hi} (inclusive)
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *         is {@code null}
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if(lo == null || hi == null)throw new IllegalArgumentException("The key should not be null or empty");
        
        Queue<Key>queue_link = new LinkedList<Key>();
        keys(root, queue_link, lo, hi); 
        return queue_link;

     
    } 

   private void keys(Node x,Queue<Key> queue_link, Key lo, Key hi) { 
     if (x == null) return;  
        
        Node node0 = x;  
        while (node0 != null){  
      
            int cmp = node0.key.compareTo(hi);
            int com = node0.key.compareTo(lo);

            
            if (node0.left == null){   
                if (cmp <= 0 && com >= 0) queue_link.offer(node0.key);
                node0 = node0.right;

            }else{  
                Node node1 = node0.left;
                
                while (node1.right != null && node1.right != node0)  
                    node1 = node1.right;  
        
                
                if (node1.right == null){  
                    node1.right = node0;  
                    node0 = node0.left; 
            

                }else{  
                    node1.right = null;    
                    if (cmp <= 0 && com >= 0) queue_link.offer(node0.key);  
                    node0 = node0.right;  
                }  
            }  
        }   
    }


        
   

   
    /* Run the program by giving the approriate command obtained from
    input files through input.txt files. The output should be displayed
    exactly like the file output.txt shows it to be.*/
  
    
}