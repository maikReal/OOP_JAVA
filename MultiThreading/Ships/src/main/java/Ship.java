public class Ship extends Thread{

    private int size;
    private String type;
    private int shipCapacity = 0;
    private Tunnel tunnel;
    public boolean onPier = false;

    public Ship(int size, String type, Tunnel tunnel){

        this.size = size;
        this.type = type;
        this.tunnel = tunnel;

    }


    @Override
    public void run(){

        var counter = 0;
        while (!Thread.interrupted()) {
            if (counter == 0) {
                System.out.println(String.format("Appeared ship with %s and size of %d in generator",
                        this.getType(), this.getSize()));
                counter++;
            }

        }


    }

    public String getType(){
        return this.type;
    }

    public int getSize() {
        return this.size;
    }

    public void add(int value){
        this.shipCapacity += value;
    }

    public boolean countCapacity() {
        if (this.shipCapacity >= this.size) {
            return false;
        }
        return true;
    }

    public int getCapacity(){
        return this.shipCapacity;
    }

}
