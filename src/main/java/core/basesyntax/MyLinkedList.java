package core.basesyntax;

import java.util.List;

public class MyLinkedList<T> implements MyLinkedListInterface<T> {
    private int size;
    private Node<T> first;
    private Node<T> last;

    @Override
    public void add(T value) {
        linkLast(value);
    }

    @Override
    public void add(T value, int index) {
        checkPositionIndex(index);
        if (index == size) {
            linkLast(value);
        } else {
            linkBefore(value,node(index));
        }

    }

    @Override
    public void addAll(List<T> list) {
        for (T comp : list) {
            add(comp);
        }
    }

    @Override
    public T get(int index) {
        checkElementIndex(index);
        return node(index).getValue();
    }

    @Override
    public T set(T value, int index) {
        checkElementIndex(index);
        Node<T> rest = node(index);
        T res = rest.getValue();
        rest.setValue(value);
        return res;
    }

    @Override
    public T remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));

    }

    @Override
    public boolean remove(T object) {
        for (Node<T> x = first; x != null; x = x.getNext()) {
            if (object == null) {
                if (x.getValue() == null) {
                    unlink(x);
                    return true;
                }
            } else {
                if (object.equals(x.getValue())) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T unlink(Node<T> x) {
        final T element = x.getValue();
        final Node<T> next = x.getNext();
        final Node<T> prev = x.getPrev();

        if (prev == null) {
            first = next;
        } else {
            prev.setNext(next);
        }

        if (next == null) {
            last = prev;
        } else {
            next.setPrev(prev);
        }

        size--;
        return element;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    void linkLast(T e) {
        final Node<T> l = last;
        final Node<T> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.setNext(newNode);
        }
        size++;

    }

    void linkBefore(T e, Node<T> succ) {
        final Node<T> pred = succ.getPrev();
        final Node<T> newNode = new Node<>(pred, e, succ);
        succ.setPrev(newNode);
        if (pred == null) {
            first = newNode;
        } else {
            pred.setNext(newNode);
        }
        size++;
    }

    public Node<T> node(int index) {
        if (index < size / 2) {
            Node<T> current = first;
            for (int i = 0;i < index;i++) {
                current = current.getNext();
            }
            return current;

        } else {
            Node<T> current = last;
            for (int i = size - 1;i > index;i--) {
                current = current.getPrev();
            }
            return current;

        }

    }

}
