package main.java.company.evo.VeryLong;

import java.util.*;

// идея! рекурсивно берем строку и делаем вычисления, также как на пвором курсе по ооп

public class VeryLong {

    private final int SIZE_NUM = 10;
    private final String logicOperators = "==!=<><=>=";
    private int[] number;
    private String symbol = "+";

//    private String number;
    public VeryLong (String str){

        String num;

        if (str.contains("-")){
            num = Arrays.asList(str.split("-")).get(1);
            symbol = "-";
        }else{
            num = str;
        }

        this.number = new int[num.length()];
        for (int i=0; i < num.length(); i++){
            this.number[i] = Integer.parseInt(String.valueOf(num.charAt(i)));
        }

    }

    public VeryLong (int number){

        String num = Integer.toString(number);

        if (num.contains("-")){
            num = Arrays.asList(num.split("-")).get(1);
            this.symbol = "-";
        }

        this.number = new int[num.length()];
        for (int i=0; i < num.length(); i++){
            this.number[i] = Integer.parseInt(String.valueOf(num.charAt(i)));
        }
    }

    public VeryLong (long number){
        String num = Long.toString(number);

        if (num.contains("-")){
            num = Arrays.asList(num.split("-")).get(1);
            this.symbol = "-";
        }

        this.number = new int[num.length()];
        for (int i=0; i < num.length(); i++){
            this.number[i] = Integer.parseInt(String.valueOf(num.charAt(i)));
        }

    }

    public boolean isEqual(VeryLong other){

        if (this.number.length != other.number.length){
            return false;
        }


        for (int i=0; i < this.number.length; i++){
            if (this.number[i] != other.number[i]){
                return false;
            }
        }

        return this.symbol.equals(other.symbol);

    }

    public boolean isNotEqual(VeryLong other){

        if (this.number.length != other.number.length){
            return true;
        }

        for (int i=0; i < this.number.length; i++){
            if (this.number[i] != other.number[i]){
                return true;
            }
        }

        return !this.symbol.equals(other.symbol);

    }

    public boolean bigger (VeryLong other, boolean biggerEqual){

        // if biggerEqual => we chech bigger ro equal

        if (this.number.length != other.number.length){

            if (this.symbol.equals("+") & other.symbol.equals("+")){
                return this.number.length > other.number.length;
            }else if(this.symbol.equals("-") & other.symbol.equals("+")){
                return false;
            } else if(this.symbol.equals("+") & other.symbol.equals("-")){
                return true;
            }else{
                return this.number.length < other.number.length;
            }

        }else {

            if (biggerEqual){
                return this.isEqual(other);
            }

            if (this.symbol.equals("-") & other.symbol.equals("-")) {

                for (int i = 0; i < this.number.length; i++) {
                    if (biggerEqual){
                        if (this.number[i] <= other.number[i]) {
                            return true;
                        }
                    }else{
                        if (this.number[i] < other.number[i]) {
                            return true;
                        }
                    }

                }

            }

            if (this.symbol.equals("+") & other.symbol.equals("+")) {


                for (int i = 0; i < this.number.length; i++) {
                    if (biggerEqual){
                        if (this.number[i] >= other.number[i]) {
                            return true;
                        }
                    }else{
                        if (this.number[i] > other.number[i]) {
                            return true;
                        }
                    }

                }

            }

            if (this.symbol.equals("-") & other.symbol.equals("+")) {
                return false;
            } else return this.symbol.equals("+") & other.symbol.equals("-");
        }

    }

    public boolean less (VeryLong other, boolean biggerEqual){

        // if biggerEqual => we chech less ro equal

        if (this.number.length != other.number.length){

            if (this.symbol.equals("+") & other.symbol.equals("+")){
                return this.number.length < other.number.length;
            }else if(this.symbol.equals("-") & other.symbol.equals("+")){
                return true;
            } else if(this.symbol.equals("+") & other.symbol.equals("-")){
                return false;
            }else{
                return this.number.length > other.number.length;
            }

        }else {

            if (biggerEqual){
                return this.isEqual(other);
            }

            if (this.symbol.equals("-") & other.symbol.equals("-")) {

                for (int i = 0; i < this.number.length; i++) {
                    if (biggerEqual){
                        if (this.number[i] >= other.number[i]) {
                            return true;
                        }
                    }else{
                        if (this.number[i] > other.number[i]) {
                            return true;
                        }
                    }

                }

            }

            if (this.symbol.equals("+") & other.symbol.equals("+")) {


                for (int i = 0; i < this.number.length; i++) {
                    if (biggerEqual){
                        if (this.number[i] <= other.number[i]) {
                            return true;
                        }
                    }else{
                        if (this.number[i] < other.number[i]) {
                            return true;
                        }
                    }

                }

            }

            if (this.symbol.equals("-") & other.symbol.equals("+")) {
                return true;
            } else return this.symbol.equals("+") & other.symbol.equals("-");
        }


    }

    private int[] convertIntegers(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++)
        {
            ret[i] = iterator.next();
        }
        return ret;
    }

    private List<Integer> toArrayList(int[] arr){

        List<Integer> primArr = new ArrayList<>();

        for (int i1 : arr) {

            primArr.add(i1);

        }

        return primArr;
    }


    private VeryLong calcMultiplication(int[] bigNum, int[] smallNum, List<Integer> bigRemainder, int numberZeros){

        int mainIndex = bigNum.length-1;
        int remainder = 0;

        int smallN = smallNum[smallNum.length-1];

        List<Integer> resRem = new ArrayList<>();

        while (mainIndex >= 0) {



            int res = bigNum[mainIndex] * smallN;

            if (remainder != 0) {

                res += remainder;

            }


            if (mainIndex-1 == -1 & res > 9){
                resRem.add(0, res);

            }else{
                remainder = res/10;
                resRem.add(0, res%10);
            }

            mainIndex--;

        }

        for (int i = numberZeros;i > 0; i--){
            resRem.add(0);
        }

//        System.out.println(resRem);

        VeryLong num1 = toVeryLong(convertIntegers(resRem), "+");
        VeryLong num2 = toVeryLong(convertIntegers(bigRemainder), "+");




        VeryLong result = num1.add(num2);

        numberZeros += 1;

        if (smallNum.length == 1){

            return result;

        }else{

            smallNum = Arrays.copyOfRange(smallNum, 0, smallNum.length-1);
            return calcMultiplication(bigNum, smallNum, toArrayList(result.number), numberZeros);
        }



    }



    public VeryLong multiply(VeryLong num) {

        if (this.number.length > num.number.length){

            List<Integer> bigRemainder = new ArrayList<>();

            for (int i=0; i < this.number.length; i++){
                bigRemainder.add(0);
            }

            return calcMultiplication(this.number, num.number, bigRemainder, 0);

        }

        return null;
    }

    public VeryLong division(VeryLong num) {



        return null;
    }

    public VeryLong substraction(VeryLong num) {

        VeryLong res;
        String resSymbol = "+";

        if (this.symbol.equals("-") & num.symbol.equals("-")){

            String val = "";

            for (int i : this.number){
                val += Integer.toString(i);
            }

            num.symbol = "+";
            res = num.substraction(new VeryLong(val));

            System.out.println(num.symbol);
            return res;


        }

        if (num.symbol.equals("-")){

            String val = "";

            for (int i : num.number){

                val += Integer.toString(i);

            }

            res = this.add(new VeryLong(val));
            return res;

        }
        if (this.symbol.equals("-")){

            res = this.add(new VeryLong(num.toString()));
            res.symbol = "-";

            return res;
        }



        List<int[]> biggestArr = getBiggerNumber(this.number, num.number);

        int[] num1 = biggestArr.get(0);
        int[] num2 = biggestArr.get(1);
        int sizeDiff = Math.abs(num1.length - num2.length);

        resSymbol = this.number.length > num.number.length ? "+" : "-";

        int remainderSub = 0;
        int resSub;

        int mainIndex = num1.length-1;
        int j = num2.length-1;

        while (mainIndex != sizeDiff-1){

            if (j == -1 & remainderSub != 0){

                resSub = num1[mainIndex] - remainderSub;

                if (resSub < 0){
                    remainderSub = 1;
                }else{
                    num1[mainIndex] = resSub;
                    break;
                }

                mainIndex--;

            }

            resSub = num1[mainIndex] - num2[j];

            if (remainderSub != 0){
                resSub -= remainderSub;
            }

            if (resSub < 0) {

                resSub = num1[mainIndex] + 10 - num2[j];
                remainderSub = 1;

            }else{
                num1[mainIndex] = resSub;
            }

            mainIndex--;
            j--;

        }

        return toVeryLong(num1, resSymbol);
    }

    public VeryLong add(VeryLong num) {

        VeryLong result;

        if (this.symbol.equals("-")){

            boolean isFirstBigger = this.number.length > num.number.length;

            if (isFirstBigger){

                String val = "";

                for (int i : this.number){

                    val += Integer.toString(i);

                }

                result = num.substraction(new VeryLong(val));

                return result;
            }

        }
        if (num.symbol.equals("-")){
//            boolean isFirstBigger = this.number.length > num.number.length;
            String val = "";
//
            for (int i : num.number){

                val += Integer.toString(i);

            }

            result = this.substraction(new VeryLong(val));

            return result;

        }

        String sign;

        if (this.symbol.equals("-") & num.symbol.equals("-")) {
            sign = "-";
        }else{
            sign = "+";
        }

            int sizeDiff = Math.abs(this.number.length - num.number.length) == 0 ? 0 : Math.abs(this.number.length - num.number.length)-1;

            List<int[]> biggestArr= getBiggerNumber(this.number, num.number);
            int[] num1 = biggestArr.get(0);
            int[] num2 = biggestArr.get(1);


            int remainder = 0;
            int res;

            int mainIndex = num1.length-1;
            int j = num2.length-1;

                while (mainIndex != sizeDiff-1){

                    if (j == -1 & remainder != 0){

                        res = num1[mainIndex] + remainder;

                        if (res > 9){
                            List<Integer> resRemainder = getResRemainderAdd(res, remainder);
                            num1[mainIndex] = resRemainder.get(0);
                            remainder = resRemainder.get(1);
                        }else{
                            num1[mainIndex] = res;
                            break;
                        }

                        mainIndex--;

                        continue;

                    }
                    if (j == -1 & remainder == 0){
                        break;
                    }

                    res = num1[mainIndex]+num2[j];

                    List<Integer> resRemainder = getResRemainderAdd(res, remainder);

                    num1[mainIndex] = resRemainder.get(0);
                    remainder = resRemainder.get(1);
                    mainIndex--;
                    j--;

                }




        return toVeryLong(num1, sign);
    }

    private List<Integer> getResRemainderAdd (int res, int remainder){

        if (remainder != 0){
            res += remainder;
            remainder = 0;
        }

        if (res > 9){

            remainder = res/10;
            res = res%10;

        }

        List<Integer> output = new ArrayList<>();
        output.add(res);
        output.add(remainder);

        return output;

    }

    private List<int[]> getBiggerNumber(int[] num1, int[] num2){

        int[] number1, number2;
        if (num1.length > num2.length){

            number1 = num1;
            number2 = num2;

        }else{

            number1 = num2;
            number2 = num1;

        }

        return Arrays.asList(number1, number2);
    }

    @Override
    public String toString(){

        String val = this.symbol.equals("+") ? "" : "-";

        for (int i : this.number){

            val += Integer.toString(i);

        }

        return val;

    }

    private VeryLong toVeryLong(int[] arr, String sign){

        String val = sign == "+" ? "" : "-";

        for (int i : arr){
            val += Integer.toString(i);
        }

        return new VeryLong(val);


    }

    public int toInteger(){

        VeryLong maxInteger = new VeryLong(Integer.toString(Integer.MAX_VALUE));

        if (this.less(maxInteger, true) & this.bigger(maxInteger, true)){
            return Integer.parseInt(this.toString());
        }else{
            throw new StackOverflowError();
        }


    }

    public long toLong(){

        VeryLong maxLong = new VeryLong(Long.toString(Long.MAX_VALUE));


        if (this.less(maxLong, true) & this.bigger(maxLong, true)){
            return Long.parseLong(this.toString());
        }else{
            throw new StackOverflowError();
        }

    }


}
