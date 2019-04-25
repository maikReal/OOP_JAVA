import java.util.ArrayList;

public class Tunnel extends Thread{

    private int shipCounter;
    private ArrayList<Ship> tunnelStore;
    private int maxShips = 5;
    private int minShips = 0;

    public Tunnel(){

        this.tunnelStore = new ArrayList<Ship>();

    }

    public synchronized boolean add(Ship ship) throws InterruptedException {

        if (this.shipCounter < this.maxShips){
            notifyAll();
            this.tunnelStore.add(ship);
            System.out.println(String.format("Ship with %s and size of %d in tunnel",
                    ship.getType(), ship.getSize()));
            this.shipCounter++;

        } else if (this.shipCounter > this.maxShips){
            System.out.println("There is no space in tunnel");
            wait();
            return false;
        }
        return true;

    }

    public synchronized Ship get(String type) throws InterruptedException {


        if (this.shipCounter > this.minShips) {
            notifyAll();
            for (Ship ship : this.tunnelStore) {

                if (ship.getType().equals(type)) {
                    sleep(1000);
                    this.shipCounter--;
                    this.tunnelStore.remove(ship);
                    System.out.println(String.format("Ship with %s and size of %d was taken from tunnel",
                            ship.getType(), ship.getSize()));
                    return ship;
                }

            }
        }

        System.out.println("There are no ships in tunnel");
        wait();

        return null;


    }


}
