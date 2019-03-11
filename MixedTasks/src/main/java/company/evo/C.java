package main.java.company.evo;//package company;
//
import java.util.Scanner;

public class C {


    public static int countIndexes(int lengthA, int[] arrayA, int lengthB, int[] arrayB, int k) {
        int lastJ = 0, res = 0;

        for(int i = lengthB - 1; i >= 0; i--) {
            int b = arrayB[i];

            for(int j = lastJ; j < lengthA; j++) {
                int sum = b + arrayA[j];
                if(sum == k) {
                    res++;
                }
                else if(sum > k) {
                    lastJ = j;
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int size1 = sc.nextInt();
        int[] array1 = new int[size1];

        for (int i = 0; i < size1; i++) {
            array1[i] = sc.nextInt();
        }

        int size2 = sc.nextInt();
        int[] array2 = new int[size2];

        for (int i = 0; i < size2; i++) {
            array2[i] = sc.nextInt();
        }

        int k = sc.nextInt();

        System.out.println(countIndexes(array1.length, array1, array2.length, array2, k));


    }
}
