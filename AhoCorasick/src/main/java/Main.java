import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AhoCorasick ac = new AhoCorasick();

        for (Integer i : ac.getAllConjunctions(sc.next(), sc.next())) {
            System.out.println(i);
        }
    }
}
