package main.java.company.evo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class B {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(br.readLine());

        String inp = br.readLine();
        int x1 = Integer.parseInt(Arrays.asList(inp.split(" ")).get(0));
        int y1 = Integer.parseInt(Arrays.asList(inp.split(" ")).get(1));

        int basX1 = x1, basY1 = y1;

        int summary = 0;
        while (count-1 > 0) {

            String el = br.readLine();

            int x2 = Integer.parseInt(Arrays.asList(el.split(" ")).get(0));
            int y2 = Integer.parseInt(Arrays.asList(el.split(" ")).get(1));
            summary += (x1 + x2)*(y2 - y1);

            x1 = x2;
            y1 = y2;

            count -= 1;

        }

            summary += (x1 + basX1)*(basY1 - y1);

        System.out.println((float) Math.abs(summary)/2);


    }
}
