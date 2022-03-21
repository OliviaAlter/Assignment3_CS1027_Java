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
        numItems++;
    }

    @Override
    public T pop() throws EmptyStackException {
        if (top == null) {
            throw new EmptyStackException("Stack is empty");
        } else {
            T itemPopped = top.getElement();
            if (top.getPrevious() == null) {
                top = null;
            } else {
                top = top.getPrevious();
                top.setNext(null);
            }
            numItems--;
            return itemPopped;
        }
    }

    @Override
    public T peek() {
        if (numItems == 0) {
            return null;
        }
        return top.getElement();
    }

    @Override
    public T pop(int k) throws InvalidItemException {
        T itemPopped;
        if (k > numItems || k <= 0) {
            throw new InvalidItemException("Invalid item");
        } else if (k == 1) {
            itemPopped = pop();
        } else {
            DoubleLinkedNode<T> current = top;
            for (int i = 1; i < k; i++) {
                current = current.getPrevious();
            }
            itemPopped = current.getPrevious().getElement();
            if (current.getPrevious().getPrevious() == null) {
                current.setPrevious(current.getPrevious().getPrevious());
                current.getPrevious().getPrevious().setNext(current);
            } else {
                current.setPrevious(null);
            }
        }
        numItems--;
        return itemPopped;
    }

    @Override
    public boolean isEmpty() {
        return numItems == 0;
    }

    @Override
    public int size() {
        return numItems;
    }

    @Override
    public DoubleLinkedNode<T> getTop() {
        return top;
    }

    public String toString() {
        String out = "[]";
        DoubleLinkedNode<T> current = top;
        for (int i = 1; i <= numItems; i++) {
            out = "[" + current.getElement() + "]";
            current = current.getNext();
        }
        return out;
    }
}
