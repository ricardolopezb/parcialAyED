import structs.StaticQueue;
/**
 * @author: Ricardo Lopez Bonaguro
 */
public class TakeOffHub {
    private StaticQueue<Plane> queue;
    private boolean full;

    public TakeOffHub(){
        this.queue = new StaticQueue();
        this.full = false;
    }


    public boolean isFull() {
        return queue.size() == 2;
    }

    public void dispatch() {
        while(!queue.isEmpty()){
            try {
                queue.dequeue();
            } catch (Exception e){
                e.getMessage();
            }
        }
    }

    public void receivePlane(Plane plane){
        queue.enqueue(plane);

    }

}
