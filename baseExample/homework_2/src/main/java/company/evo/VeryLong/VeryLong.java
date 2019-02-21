package main.java.company.evo.VeryLong;


import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class VeryLong {


    public static void main(String[] args) {
        int[] data = { 100, 200, 300, 400 };

        ByteBuffer byteBuffer = ByteBuffer.allocate(data.length * 4);
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(data);

        byte[] array = byteBuffer.array();

        for (int i=0; i < array.length; i++)
        {
            System.out.println(i + ": " + array[i]);
        }

    }
}
