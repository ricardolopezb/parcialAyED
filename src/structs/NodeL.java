package structs;

public class NodeL<T> {
     T value;
     NodeL<T> next;
    public NodeL(){
        value = null;
        next = null;
    }
    public NodeL(T value) {
        this.value = value;
        next = null;
    }
    public NodeL(T value, NodeL<T> next) {
        this.value = value;
        this.next = next;
    }
    public T getValue() {
        return value;
    }

    public NodeL<T> getNext() {
        return next;
    }

}
