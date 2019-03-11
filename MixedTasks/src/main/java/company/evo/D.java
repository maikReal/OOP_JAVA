package main.java.company.evo;

import java.util.Scanner;


public class D {

    /**
     * @author
     * @version
     * @param n
     * @param k
     * @return <code>True</code> if sucsess
     * @throws
     * @exception
     * @since from what time the function was added
     * @see
     * @deprecated become unuseful and the best don't use it
     */

    public static int safe_position(int n, int k) {
        int g = 0;
        for (int i = 0; i < n; ++i)
            g = (g + k) % (i + 1);
        return g + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        int[] input = new int[2];
        for (int i = 0; i < 2; i++) {
            input[i] = sc.nextInt();
        }

        System.out.println(safe_position(input[0], input[1]));

    }
}

