public class DataPackage {

    private final int destinationNode;

    private final String data;

    private final long startTime;


    DataPackage(int destinationNode, String data) {
        this.destinationNode = destinationNode;

        this.data = data;


        // Фиксируется время, когда создаётся пакет данных. Необходимо для
        // вычисления времени доставки до узла назначения.
        this.startTime = System.nanoTime();
    }


    public int getDestinationNode() {
        return this.destinationNode;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public String getData() {
        return data;
    }


}
