public class Main {
    public static void main(String[] args) {
        int hours = Scanner.getInt("Input amount of hours to simulate: ");
        Simulator simulator = new Simulator(hours);
        simulator.run();
    }



}
