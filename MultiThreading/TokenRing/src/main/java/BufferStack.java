import java.util.Stack;

/**
 * Расширение класса Stack для внедрения потокобезопасной логики. Если на классе
 * берётся лок, то все остальные потоки, желающие сделать какое-то действие с
 * BufferStack, ставятся на ожидание.
 */
public class BufferStack<T> extends Stack {

    private boolean lock = false;

    public void lock(Node node) {
    }

    public void unlock() {
        this.lock = false;
    }

}
