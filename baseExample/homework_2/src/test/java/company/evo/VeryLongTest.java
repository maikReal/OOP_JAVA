package test.java.company.evo;


import main.java.company.evo.VeryLong.VeryLong;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VeryLongTest {

    private static VeryLong vLong;



    @BeforeAll
    private static void setParams () {

        vLong = new VeryLong("937 + -13761376179864981487987");

    }

    @Test
    void testingF(){
        vLong.getResult();
    }




}