import java.util.Scanner;

public class Main {

    static private StringParcer parcer = new StringParcer();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println(parcer.getResult(sc.nextLine()));

    }
}
