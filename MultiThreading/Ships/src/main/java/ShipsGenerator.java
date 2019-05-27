import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;

public class ShipsGenerator extends Thread {

    private int numberShips;
    private Tunnel tunnel;
    private String [] types = new String[]{"Banana", "Bread", "Clothes"};
    private Integer[] size  = new Integer[]{10, 50, 100};

    public ShipsGenerator(int numberShips, Tunnel tunnel){

        this.tunnel = tunnel;
        this.numberShips = numberShips;

    }

    @Override
    public void run(){

        Thread.currentThread().setName("ShipsGenerator");

        int counter = 0;
        while (counter < numberShips){
            try {
                var ship = new Ship(getRandomSize(), getRandomType(), tunnel);
                ship.start();

                tunnel.add(ship);

                counter++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("There are no ships in generator");
    }


    private String getRandomType(){

        int index = ThreadLocalRandom.current().nextInt(3);
        return types[index];

    }



    private Integer getRandomSize(){

        int index = ThreadLocalRandom.current().nextInt(3);
        return size[index];

    }

    public int getNumberShips(){
        return this.numberShips;
    }


}
