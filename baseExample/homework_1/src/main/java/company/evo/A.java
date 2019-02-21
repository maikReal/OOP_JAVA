package main.java.company.evo;
import java.util.*;



public class A {



    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        int size = Integer.parseInt(sc.next());

        int[] array1 = new int[size];
        int[] array2 = new int[size];

        for (int i = 0; i < size; i++) {
            array1[i] = sc.nextInt();
        }

        for (int i = 0; i < size; i++) {
            array2[i] = sc.nextInt();
        }


        int x=0, y=0;

        int a_res = 0;
        int max = 0;
        max = array1[0] + array2[0];
        for (int i = 0; i < size; i++) {

            if (array1[i] > array1[a_res]) {
                a_res = i;
            }

            if (array1[a_res] + array2[i] > max) {
                max = array1[a_res] + array2[i];
                x = a_res;
                y = i;
            }
        }

        System.out.println(x + " " + y);

    }

}
