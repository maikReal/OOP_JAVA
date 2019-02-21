package main.java.company.evo.VeryLong;


import java.util.Arrays;
import java.util.List;

public class VeryLong {

    final int SIZE_NUM = 10;
    private List<String> operands, operators;


//    public VeryLong (String str){
//        this.value = str;
//    }



    public VeryLong (String str) throws NullPointerException{

        List splitedStr = Arrays.asList(str.split(" "));

        for (int i=0; i < splitedStr.size(); i += 2){

            this.operators.add(String.valueOf(splitedStr.get(i)));

            if (i+1 == splitedStr.size()){
                break;
            }else {
                this.operands.add(String.valueOf(splitedStr.get(i + 1)));
            }

        }



    }

    public void getResult(){

//        parceString(operation);

        System.out.println(this.operands + " " + this.operators);


    }


}
