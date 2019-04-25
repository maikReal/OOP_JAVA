public class Pier extends Thread{

    private String type;
    private Tunnel tunnel;

    public Pier(String type, Tunnel tunnel){

        this.type = type;
        this.tunnel = tunnel;

    }

    @Override
    public void run(){

        Thread.currentThread().setName("Pier");

        while(true){

            try {
                Thread.sleep(2000); // задержка, чтобы корабль не постоянно проверял наличия кораблей в туннеле
                Ship ship = tunnel.get(type);
                if(ship!=null) {

                    while (ship.countCapacity()) {
                        Thread.sleep(1000);
                        ship.add(10);

                        System.out.println(String.format("Ship with %s and size of %d loaded of %d",
                                ship.getType(), ship.getSize(), ship.getCapacity()));
                    }
                    ship.interrupt();
                    System.out.println("Ship disappeared");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(String.format("There are no ships on pier %s", this.type));

        }

    }
}


