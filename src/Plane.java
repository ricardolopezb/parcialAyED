/**
 * @author: Ricardo Lopez Bonaguro
 */

public class Plane implements Comparable<Plane> {

    private int boardingTime;
    private int fuelingTime;
    static int generalCode = 0;
    private int waitingInitialTime;
    private boolean toBoard;
    private String planeCode;
    private int hubWaitingTime;
    private String name;

    public Plane(String name, int boardingTime, int fuelingTime, boolean toBoard){
        generalCode++;
        this.name = name;
        this.planeCode = String.valueOf(generalCode);
        this.boardingTime = boardingTime;
        this.fuelingTime = fuelingTime;
        this.toBoard = toBoard;
        this.waitingInitialTime = 0;
        this.hubWaitingTime = 0;

    }

    public String getName() {
        return name;
    }

    public int getHubWaitingTime() {
        return hubWaitingTime;
    }

    public int getBoardingTime() {
        return boardingTime;
    }

    public int getFuelingTime() {
        return fuelingTime;
    }

    public boolean isToBoard() {
        return toBoard;
    }

    public int compareTo(Plane o) {
        if(this.hubWaitingTime > o.getHubWaitingTime()) return 1;
        if (this.hubWaitingTime == o.getHubWaitingTime()) return 0;
        else{
            return -1;
        }
    }


    public void markQueueExitTime(int currentMinute) {
        this.hubWaitingTime = currentMinute - waitingInitialTime;
    }

    public void markEntranceTime(int currentMin) {
        this.waitingInitialTime = currentMin;
    }

    public String toString(){
        return name + " - Code: " + planeCode + " - Time: " + this.hubWaitingTime + " minutes";
    }
}
