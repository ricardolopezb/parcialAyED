package structs;

public class DynamicList<T> implements List<T> {
    private NodeL<T> head, window, sentinel;
    private int size;
    public DynamicList(){
        head = new NodeL<T>();
        sentinel = new NodeL<T>();
        head.next = sentinel;
        window = head;
        size = 0;
    }
    @Override
    public T getActual() {
        return window.value;
    }
    @Override
    public int getActualPosition() {
        int pos = 0;
        if (!isVoid()) {
            NodeL<T> aux = head;
            for (; aux.next != window; pos++) aux = aux.next;
        }
        return pos;
    }
    @Override
    public boolean isVoid() {
        return head.next == sentinel;
    }
    @Override
    public boolean listStart(){
        return head.next == window;
    }
    @Override
    public boolean listEnd() {
        return window.next == sentinel;
    }
    @Override
    public GeneralList<T> clone() {
        return null;
    }
    @Override
    public void insertPrev(T obj) {
        if (!isVoid()) {
            goBack();
        }
        insertNext(obj);
    }
    @Override
    public void insertNext(T obj) {
        window.next = new NodeL<>(obj, window.next);
        window = window.next;
        size++;
    }
    @Override
    public void goNext() {
        if(window.next == sentinel) throw new IndexOutOfBoundsException("Reached the end of this List");
        window = window.next;
    }
    @Override
    public void goPrev() {
        if(window == head.next) throw new IndexOutOfBoundsException("Reached the beginning of this List");
        goBack();
    }
    private void goBack(){
        NodeL<T> aux = head;
        while(window != aux.next){
            aux = aux.next;
        }
        window = aux;
    }
    @Override
    public void goTo(int index) {
        window = head.next;
        for(int i = 0; i < index; i++){
            window = window.next;
        }
    }
    @Override
    public void remove() {
        if(isVoid()) throw new NullPointerException("This List is empty");
        goBack();
        window.next = window.next.next;
        window = window.next;
        if(window == sentinel) goBack();
        size--;
    }
    @Override
    public int size() {
        return size;
    }
}
