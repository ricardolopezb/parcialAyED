
/**
 * @author: Ricardo Lopez Bonaguro
 */


public class Gate {
    private boolean bridge;
    private boolean boarding;
    private boolean ready;
    private boolean available;
    private int elapsedActivityTime;
    private int activityNeededTime;
    private Plane currentPlane;

    public Gate(boolean bridge){
        this.bridge = bridge;
        this.boarding = false;
        this.available = true;
        this.elapsedActivityTime = 0;
        this.activityNeededTime = 0;
        this.currentPlane = null;
        this.ready = false;
    }

    public boolean isReady() {
        return ready;
    }

    //takes out the plane
    public Plane retrieveReadyPlane(){
        Plane plane = this.currentPlane;
        this.currentPlane = null;
        this.ready = false;
        this.available = true;
        return plane;
    }



    public boolean isAvailable() {
        return available;
    }

    public boolean hasBridge() {
        return bridge;
    }

    //I think the block of code in this method could be far more optimized. However, because of time constraints, I will leave it like this.
    public void receivePlane(Plane plane) {
        this.elapsedActivityTime = 0;
        if(bridge){
            if(plane.isToBoard()){
                this.activityNeededTime = plane.getBoardingTime()/2;
            } else{
                this.activityNeededTime = plane.getFuelingTime();
            }

        } else{
            if(plane.isToBoard()){
                this.activityNeededTime = plane.getBoardingTime()/2;
            } else{
                this.activityNeededTime = plane.getFuelingTime();
            }
        }
        currentPlane = plane;
        this.available = false;
        this.ready = false;





    }
    //called every minute
    public void work() {
        if(this.activityNeededTime == this.elapsedActivityTime){
            this.ready = true;
        } else{
            this.elapsedActivityTime++;
        }
    }
}
