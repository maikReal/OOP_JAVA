import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static java.lang.Thread.sleep;


/**
 * В конструкторе кольцо инициализируется, то есть создаются все узлы и данные на узлах.
 * В методе {@link RingProcessor#startProcessing()} запускается работа кольца - данные начинают
 * обрабатываться по часовой стрелке. Также производится логгирование в {@link RingProcessor#logs}.
 * Вся работа должна быть потокобезопасной и с обработкой всех возможных исключений. Если необходимо,
 * разрешается создавать собственные классы исключений.
 */

public class RingProcessor {

    private final int nodesAmount;
    private final int dataAmount;

    private final String logs;
    private final Logger logger;
    private List<Node> nodeList = new ArrayList<>();
    private FileHandler fileHandler;
    public int corData = 0;

    private int corId;

    /**
     * Сюда идёт запись времени прохода каждого пакета данных.
     * Используется в {@link RingProcessor#averageTime()} для подсчета среднего времени
     * прохода данных к координатору.
     */

    RingProcessor(int nodesAmount, int dataAmount, String logs) throws IOException {
        this.nodesAmount = nodesAmount;

        this.dataAmount = dataAmount;

        this.logs = logs;

        fileHandler = new FileHandler(this.logs);
        fileHandler.setFormatter(new SimpleFormatter());
        logger = Logger.getLogger("ringLogger");
        logger.setUseParentHandlers(false);
        logger.addHandler(fileHandler);

        this.corId = 0;

        init();
    }

    public int getNodesAmount() {
        return this.nodesAmount;
    }

    public int getDataAmount() {
        return this.dataAmount;
    }

    private void init() {
        this.corId = (int) (Math.random() * this.nodesAmount);

        for (var i = 0; i < this.nodesAmount; i++) {

            var node = new Node(i, corId, this);
            this.nodeList.add(node);

        }

        var nodeCounter = 0;
        for (var i = 0; i < this.dataAmount; i++) {

            var data = new DataPackage(getRandomDestination(), getRandomData());

            if (nodeCounter == this.nodeList.size()) {
                nodeCounter = 0;
            }

            var node = this.nodeList.get(nodeCounter);
            node.setData(data);
            nodeCounter++;

        }
    }

    public void startProcessing() throws InterruptedException {

        System.out.println("Starting Ring Token Simulation");

        var i = 0;
        var counter = 0;
        while (true) {

            if (counter == 0) {
                var numberNode = 0;
                for (var node : this.nodeList) {
                    System.out.println(String.format("Started %d node", i));
                    node.setName("Node " + numberNode);
                    node.setDaemon(true);
                    node.start();
                    i++;
                    numberNode++;
                }
                counter++;

            }
            sleep(3000);
            var simulationString = String.format("Simulation finished on %s percent", this.nodeList.get(this.corId).allData.size() * 100 / this.dataAmount);
            System.out.println(simulationString);
            if (this.corData == this.dataAmount){
                break;
            }

        }

        System.out.println("Finishing simulation...");
    }

    private int getRandomDestination() {

        return (int) (Math.random() * this.nodesAmount);

    }

    private String getRandomData() {

        byte[] array = new byte[7];
        new Random().nextBytes(array);

        return new String(array, Charset.forName("UTF-8"));

    }

    public List<Node> getNodes() {
        return this.nodeList;
    }

    public Logger getLogger() {
        return this.logger;
    }
}
