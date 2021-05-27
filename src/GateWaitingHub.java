import structs.StaticQueue;
/**
 * @author: Ricardo Lopez Bonaguro
 */
public class GateWaitingHub {
    private StaticQueue<Plane> toBoard;
    private StaticQueue<Plane> toRefuel;
    private int[] landedPlanesByType;


    public GateWaitingHub(){
        this.toBoard = new StaticQueue<>();
        this.toRefuel = new StaticQueue<>();
        this.landedPlanesByType = new int[4];
        for (int i = 0; i < landedPlanesByType.length; i++) {
            landedPlanesByType[i] = 0;
        }

    }

    public boolean isFull(){
        return (toBoard.size() + toRefuel.size()) == 4;
    }

    public boolean hasOneSpace(){
        return (toBoard.size() + toRefuel.size()) == 3;
    }

    public Plane dispatchRefuelingPlane(int currentMinute) {
        return dispatchQueue(toRefuel, currentMinute);
    }

    public Plane dispatchBoardingPlane(int currentMinute) {
        return dispatchQueue(toBoard, currentMinute);
    }

    private Plane dispatchQueue(StaticQueue<Plane> q, int currentMinute){
        if(!q.isEmpty()){
            try{
                Plane toSend = q.dequeue();
                toSend.markQueueExitTime(currentMinute);
                return toSend;

            } catch (Exception e){
                e.getMessage();
            }
        }
        return null;
    }

    public void receivePlane(Plane plane, int currentMin) {
        if(plane.isToBoard()) {
            toBoard.enqueue(plane);
        } else{
            toRefuel.enqueue(plane);
        }
        plane.markEntranceTime(currentMin);
        countType(plane);
    }

    private void countType(Plane plane) {
        String name = plane.getName();
        switch (name){
            case "Golondrina": landedPlanesByType[0]++;
                                break;
            case "Vencejo": landedPlanesByType[1]++;
                break;
            case "Buho": landedPlanesByType[2]++;
                break;
            case "Halcon": landedPlanesByType[3]++;
                break;
        }


    }

    public int[] getCountByType() {
        return landedPlanesByType;
    }
}
