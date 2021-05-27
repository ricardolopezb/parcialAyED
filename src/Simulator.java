import java.util.Random;
/**
 * @author: Ricardo Lopez Bonaguro
 */
public class Simulator {
    private final int maxMinute;
    private Airport airport;
    private int currentMin;

    public Simulator(int hours){
        this.airport = new Airport();
        this.currentMin = 1;
        this.maxMinute = hours*60;
    }

    public void run(){

        while(currentMin < maxMinute){
            if(currentMin%5 == 0){
                airport.authTakeOff();
                Plane[] incomingPlanes = simulateIncomingPlanes();
                airport.receiveLandings(incomingPlanes, currentMin);
            }
            airport.work(currentMin);

            currentMin++;
        }
        airport.end();

    }

    private Plane[] simulateIncomingPlanes() {
        Plane[] planes = new Plane[2];
        for (int i = 0; i < 2; i++) {
            int prob = randomNumberInRange(0, 4);
            boolean boarding = false;
            int probBoarding = randomNumberInRange(1,101);
            if (probBoarding <= 65){
                boarding = true;
            }

            switch (prob){
                case 0: planes[i] = new Plane("Golondrina", 30, 20,boarding);
                        break;
                case 1: planes[i] = new Plane("Vencejo", 50, 25, boarding);
                    break;
                case 2: planes[i] = new Plane("Buho", 60, 30, boarding);
                    break;
                case 3: planes[i] = new Plane("Halcon", 90, 35, boarding);
                    break;
            }

        }
        return planes;

    }

    public static int randomNumberInRange(int start, int end){
        Random r = new Random();
        int low = start;
        int high = end;
        return r.nextInt(high-low) + low;
    }


}
