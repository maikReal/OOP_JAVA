package test.java.company.evo;


import main.java.company.evo.VeryLong.VeryLong;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VeryLongTest {

//    static String expression = "-937 - 13761376179864981487987";

//    static  String expression = "2347205 * 123 + 233";
    static String expression = "123448134122 >= 123448134";


    @BeforeAll
    private static void setParams () {


    }

    @Test
    void testingAdd(){

        int s = -1234;

        VeryLong a = new VeryLong(s);
        System.out.println(a.toString());

//        getResult(expression);
//        String inputString = "prologistic.com.ua";
//        byte[] byteArray = inputString.getBytes();
//        System.out.println("Строку в массив байт : " + Arrays.toString(byteArray));



    }

    private VeryLong getResult(String expression){

        int indexProb = expression.split(" ")[1].length() == 1 ? expression.indexOf(" ") + 2 : expression.indexOf(" ") + 3;

        int indexEndSlice = expression.indexOf(" ", indexProb + 1);

        if (indexEndSlice == -1){
            return makeOperation(expression);
        }else{
            String subExpr = (String) expression.subSequence(0, indexEndSlice);
            String endExpr = (String) expression.subSequence(indexEndSlice, expression.length());

            // check what should do
            return getResult(makeOperation(subExpr) + endExpr);
        }

//        return null;
    }

    private VeryLong makeOperation(String operation){

        List<String> expr = Arrays.asList(operation.split(" "));

        VeryLong num1 = new VeryLong(expr.get(0));

        VeryLong num2 = new VeryLong(expr.get(2));


        switch (expr.get(1)){
            case "+":
                return num1.add(num2);
            case "-":
                return num1.substraction(num2);
            case "/":
                return num1.division(num2);
            case "*":
                return num1.multiply(num2);
            case "+=":
                num1.add(num2);
                return num1;
            case "-=":
                num1.substraction(num2);
                return num1;
            case "*=":
                return num1.multiply(num2);

//            case "/=":
//
            case "==":
                System.out.println(num1.isEqual(num2));
            case "!=":
                System.out.println(num1.isNotEqual(num2));
            case ">=":
                System.out.println(num1.bigger(num2, true));
            case "<=":
                System.out.println(num1.less(num2, true));
            case ">":
                System.out.println(num1.bigger(num2, false));
            case "<":
                System.out.println(num1.less(num2, false));
        }
        return null;

    }



}