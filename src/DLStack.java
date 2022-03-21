public class DLStack<T> implements DLStackADT<T> {

    private DoubleLinkedNode<T> top;
    private int numItems;

    public DLStack() {
        top = null;
        numItems = 0;
    }

    @Override
    public void push(T dataItem) {
        // Adds the dataItem to the top of the stack.
        if (top == null) {
            top = new DoubleLinkedNode<T>(dataItem);
        } else {
            DoubleLinkedNode<T> newNode = new DoubleLinkedNode<T>(dataItem);
            newNode.setPrevious(top);
            top.setNext(newNode);
            top = newNode;
            newNode.setNext(null);
        }
    }

    @Override
    public T pop() throws EmptyStackException {
        if (top == null) {
            throw new EmptyStackException("Stack is empty");
        } else {
            T dataItem = top.getElement();
            top = top.getPrevious();
            
        }
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public T pop(int k) throws InvalidItemException {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public DoubleLinkedNode<T> getTop() {
        return null;
    }
}
