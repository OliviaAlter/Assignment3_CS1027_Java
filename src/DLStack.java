public class DLStack<T> implements DLStackADT<T> {

    // Instance variables
    private DoubleLinkedNode<T> top;
    private int numItems;

    /**
     * Constructor for DLStack, initializes the top to null and numItems to 0.
     * @author Anos176 a.k.a. Anh Duc Vu
     */
    public DLStack() {
        top = null;
        numItems = 0;
    }

    /**
     * Adds an item to top of the stack.
     * @param dataItem the item to be added
     * @author Anos176 a.k.a. Anh Duc Vu
     */
    @Override
    public void push(T dataItem) {
        // Adds the dataItem to the top of the stack.
        if (top == null) {
            top = new DoubleLinkedNode<T>(dataItem);
        } else {
            DoubleLinkedNode<T> newNode = new DoubleLinkedNode<T>(dataItem); // Creates a new node by passing the dataItem to the constructor
            newNode.setPrevious(top); // Sets the pointer of new node to the top of the stack
            top.setNext(newNode); // Sets the pointer of the old top of the stack to the new node
            top = newNode; // Sets the top of the stack to the new node
            newNode.setNext(null); // Sets the pointer of the new node to null
        }
        numItems++;
    }

    /**
     * Returns the top of the stack and removes it from the stack.
     * @return the top of the stack
     * @throws EmptyStackException if the stack is empty
     * @author Anos176 a.k.a. Anh Duc Vu
     */
    @Override
    public T pop() throws EmptyStackException {
        if (top == null) {
            throw new EmptyStackException("Stack is empty");
        } else {
            T itemPopped = top.getElement(); // Stores the top of the stack in itemPopped
            if (top.getPrevious() == null) {
                top = null; // Sets the top of the stack to null if the top of the stack is the only node in the stack
            } else {
                top = top.getPrevious(); // Sets the top of the stack to the previous node of the top of the stack
                top.setNext(null); // Sets the pointer of the new top of the stack to null
            }
            numItems--;
            return itemPopped;
        }
    }

    /**
     * Returns the top of the stack without removing it.
     * @return the top of the stack, null if the stack is empty
     * @author Anos176 a.k.a. Anh Duc Vu
     */
    @Override
    public T peek() throws EmptyStackException {
        if (numItems == 0) {
            throw new EmptyStackException("Stack is empty");
        }
        return top.getElement();
    }

    /**
     * Returns the element at the specified index and removes it from the stack.
     * @param k the index of the element to be returned
     * @return the element at the specified index
     * @throws InvalidItemException if the index is invalid (> numItems, < 0)
     * @author Anos176 a.k.a. Anh Duc Vu
     */
    @Override
    public T pop(int k) throws InvalidItemException {
        T itemPopped;
        if (k > numItems || k <= 0) {
            throw new InvalidItemException("Invalid item");
        } else if (k == 1) {
            itemPopped = pop(); // Calls the pop method if the index is 1
        } else {
            DoubleLinkedNode<T> current = top; // Creates a new node to store the top of the stack
            for (int i = 1; i < k - 1; i++) { // Loops through the stack to find the node at the specified index
                current = current.getPrevious(); // Sets the current node to the previous node of the current node
            }
            itemPopped = current.getPrevious().getElement(); // Stores the element of the node before the node at the specified index
            if (current.getPrevious().getPrevious() != null) { // Checks if the node before the node at the specified index is null
                current.getPrevious().getPrevious().setNext(current); // Sets the pointer of the node before the node at the specified index to the current node
                current.setPrevious(current.getPrevious().getPrevious()); // Sets the pointer of the current node to the node before the node at the specified index
            } else {
                current.setPrevious(null); // Sets the pointer of the current node to null if the node before the node at the specified index is null
            }
        }
        numItems--;
        return itemPopped;
    }

    /**
     * Check if the stack is empty.
     * @return true if the stack is empty, false otherwise
     * @author Anos176 a.k.a. Anh Duc Vu
     */
    @Override
    public boolean isEmpty() {
        return numItems == 0;
    }

    /**
     * Returns the number of elements in the stack.
     * @return the number of elements in the stack
     * @author Anos176 a.k.a. Anh Duc Vu
     */
    @Override
    public int size() {
        return numItems;
    }

    /**
     * Return top of the stack as a DoubleLinkedNode.
     * @return top of the stack as a DoubleLinkedNode
     */
    @Override
    public DoubleLinkedNode<T> getTop() {
        return top;
    }

    /**
     * Returns a string of the form “[data1 data2 … datan]”, where data1 is the
     * data item at the top of the stack, and datan is the data item at the bottom of the stack.
     * @return the string representation of the stack
     * @author Anos176 a.k.a. Anh Duc Vu
     */
    public String toString() {
        String result = "[";
        DoubleLinkedNode<T> current = top;
        while (current != null) {
            result += current.getElement();
            current = current.getPrevious();
            if (current != null) {
                result += " ";
            }
        }
        result += "]";
        return result;
    }
}
