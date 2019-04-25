public class Main {


    public static void main(String[] args) {
        Tunnel tunnel = new Tunnel();

        ShipsGenerator generator = new ShipsGenerator(5, tunnel);

        Pier pier_banana = new Pier("Banana", tunnel);
        Pier pier_bread = new Pier("Bread", tunnel);
        Pier pier_clothes = new Pier("Clothes", tunnel);


        pier_banana.setDaemon(true);
        pier_bread.setDaemon(true);
        pier_clothes.setDaemon(true);

        pier_banana.start();
        pier_bread.start();
        pier_clothes.start();
        generator.start();

    }
}
