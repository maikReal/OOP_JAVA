import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Node extends Thread {

    private final int nodeId;

    private final int corId;
    public List<DataPackage> allData;
    private List<Node> nodes;
    private int packageCounter = 0;
    private BufferStack<DataPackage> bufferStack = new BufferStack<>();
    private Logger logger;
    private RingProcessor topology;

    Node(int nodeId, int corId, RingProcessor topology) {
        this.nodeId = nodeId;
        this.corId = corId;
        this.topology = topology;
        this.nodes = topology.getNodes();

        if (nodeId == corId)
            allData = new ArrayList<>();

        logger = topology.getLogger();
    }

    public long getId() {
        return this.nodeId;
    }

    public long getCorId() {
        return this.corId;
    }

    public synchronized DataPackage getData() throws InterruptedException {
        var buffer = getBuffer();
        if (this.packageCounter < 3 && buffer.size() != 0) {
            notifyAll();
            this.packageCounter--;
            sleep(1000);
            return (DataPackage) buffer.pop();
        } else {
            wait();
            return null;
        }

    }

    public synchronized void setData(DataPackage dataPackage) {
        notifyAll();

        var buffer = getBuffer();
        buffer.push(dataPackage);
        this.packageCounter++;
    }

    public BufferStack<DataPackage> getBuffer() {
        return this.bufferStack;
    }

    /**
     * Начало работы узла. То есть из Node.bufferStack берётся пакет с данными
     * и отправляется на обработку, после чего передаётся следующему узлу.
     * Тут заключена логика, согласно которой обрабатываться может только 3 пакета данных одновременно.
     */
    @Override
    public void run() {


        while (true) {
            var nodeIndex = this.nodeId + 1;


            if (this.nodeId + 1 == this.nodes.size()) {
                nodeIndex = 0;
            }

            try {
                var data = getData();

                if (data != null) {


                    if (data.getDestinationNode() == this.nodeId) {

                        var delayTime = System.nanoTime() - data.getStartTime();
                        this.logger.info(String.format("Get data from node %s, set to coordinator %s. Delay in network: %s",
                                Thread.currentThread().getName(), this.nodes.get(this.corId).getName(), delayTime));

                        this.nodes.get(this.corId).allData.add(data);
                        this.topology.corData++;
                    } else {

                        this.logger.info(String.format("Get data from node %s, set to %s",
                                Thread.currentThread().getName(), this.nodes.get(nodeIndex).getName()));

                        this.nodes.get(nodeIndex).setData(data);

                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }


    }


}


