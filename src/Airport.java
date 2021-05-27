import structs.OrderedDynamicList;

/**
 * @author: Ricardo Lopez Bonaguro
 */


public class Airport {
    private TakeOffHub takeOffHub;
    private GateWaitingHub gateWaitingHub;
    private Gate[] gates;
    private int gonePlanes;
    private int[] landedPlanesByType;
    private String[] aircraftNameList;
    OrderedDynamicList<Plane> planesGoneToGate;

    public Airport(){
        this.takeOffHub = new TakeOffHub();
        this.gateWaitingHub = new GateWaitingHub();
        this.gates = new Gate[4];
        this.aircraftNameList = new String[]{"Golondrina", "Vencejo", "Buho", "Halcon"};
        for (int i = 0; i < gates.length; i++) {
            if(i%2 == 0){
                gates[i] = new Gate(true);
            } else{
                gates[i] = new Gate(false);
            }
        }
        this.gonePlanes = 0;
        this.planesGoneToGate = new OrderedDynamicList<>();
    }

    //Displays results
    public void end(){
        showLandedPlanesByType();
        showLongestWaitingPlane();
        showGonePlanes();

    }

    private void showLongestWaitingPlane() {
        planesGoneToGate.goTo(planesGoneToGate.size()-1);
        System.out.println("\nPlane with the longest waiting time:");
        System.out.println("\t"+ planesGoneToGate.getActual().toString());
    }

    private void showLandedPlanesByType() {
        int[] counts = gateWaitingHub.getCountByType();
        System.out.println("\nLanded Planes:");
        for (int i = 0; i < counts.length; i++) {
            System.out.println("\t" + aircraftNameList[i] + ": "+ counts[i]+ " planes");
        }
    }

    private void showGonePlanes(){
        System.out.println("\nPlanes that did not land: " + this.gonePlanes + " planes");
    }

    public void authTakeOff() {
        takeOffHub.dispatch();
        while(!takeOffHub.isFull()){
            Plane readyPlane = getReadyPlane();
            if(readyPlane != null){
                takeOffHub.receivePlane(readyPlane);
            } else{
                break;
            }

        }
    }

    //gets planes that are ready
    private Plane getReadyPlane() {
        for (int i = 0; i < gates.length; i++) {
            if(gates[i].isReady()){
                return gates[i].retrieveReadyPlane();
            }
        }
        return null;
    }

    public void receiveLandings(Plane[] incomingPlanes, int currentMin) {
        if(gateWaitingHub.isFull()){
            this.gonePlanes += 2;
        } else if(gateWaitingHub.hasOneSpace()){
            this.gonePlanes++;
            int randNum = Simulator.randomNumberInRange(0,2);
            Plane planeToBeReceived = incomingPlanes[randNum];
            gateWaitingHub.receivePlane(planeToBeReceived, currentMin);
        } else{
            gateWaitingHub.receivePlane(incomingPlanes[0], currentMin);
            gateWaitingHub.receivePlane(incomingPlanes[1], currentMin);
        }

    }

    public void sendPlaneToGate(int currentMinute, int index){
        Plane toSend;
        if(gates[index].hasBridge()){
            toSend = gateWaitingHub.dispatchBoardingPlane(currentMinute);
        } else{
            toSend = gateWaitingHub.dispatchRefuelingPlane(currentMinute);
        }
        if(toSend != null){
            gates[index].receivePlane(toSend);
            planesGoneToGate.add(toSend);
        }

    }

    public int checkGateAvailability() {
        for (int i = 0; i < gates.length; i++) {
            if(gates[i].isAvailable()){
                return i;
            }
        }
        return -1;
    }

    public void work(int currentMin) {
        int result = checkGateAvailability();
        if(result >= 0){
            sendPlaneToGate(currentMin, result);
        }
        gatesWork();
    }

    private void gatesWork() {
        for (int i = 0; i < gates.length; i++) {
            if(!gates[i].isAvailable() && !gates[i].isReady()){
                gates[i].work();
            }
        }
    }
}
