import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        RingProcessor processor = new RingProcessor(10, 3, "logInfo.log");

        processor.startProcessing();
    }

}
