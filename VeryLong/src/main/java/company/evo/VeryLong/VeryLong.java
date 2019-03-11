package main.java.company.evo.VeryLong;

import java.util.*;

public class VeryLong {

    private int[] number;
    private String symbol = "+";

    public VeryLong(String str) {

        String num;

        if (str.contains("-")) {
            num = Arrays.asList(str.split("-")).get(1);
            symbol = "-";
        } else {
            num = str;
        }

        this.number = new int[num.length()];
        for (int i = 0; i < num.length(); i++) {
            this.number[i] = Integer.parseInt(String.valueOf(num.charAt(i)));
        }

    }


    public VeryLong(int number) {

        String num = Integer.toString(number);

        if (num.contains("-")) {
            num = Arrays.asList(num.split("-")).get(1);
            this.symbol = "-";
        }

        this.number = new int[num.length()];
        for (int i = 0; i < num.length(); i++) {
            this.number[i] = Integer.parseInt(String.valueOf(num.charAt(i)));
        }
    }

    public VeryLong(long number) {
        String num = Long.toString(number);

        if (num.contains("-")) {
            num = Arrays.asList(num.split("-")).get(1);
            this.symbol = "-";
        }

        this.number = new int[num.length()];
        for (int i = 0; i < num.length(); i++) {
            this.number[i] = Integer.parseInt(String.valueOf(num.charAt(i)));
        }

    }

    /**
     * This fucntion check wether the two VeryLong numbers are equals or not
     *
     * @param other number of class VeryLong
     * @return boolean statement
     */
    public boolean isEqual(VeryLong other) {

        if (this.number.length != other.number.length) {
            return false;
        }


        for (int i = 0; i < this.number.length; i++) {
            if (this.number[i] != other.number[i]) {
                return false;
            }
        }

        return this.symbol.equals(other.symbol);

    }

    /**
     * This function check whether two VeryLong numbers equals or not
     *
     * @param other number of class VeryLong
     * @return boolean statement
     */
    public boolean isNotEqual(VeryLong other) {

        if (this.number.length != other.number.length) {
            return true;
        }

        for (int i = 0; i < this.number.length; i++) {
            if (this.number[i] != other.number[i]) {
                return true;
            }
        }

        return !this.symbol.equals(other.symbol);

    }

    /**
     * Check if the one VeryLong number is bigger then other. Note: if biggerEqual is true, then we will check if
     * one VeryLong number bigger or equal then other
     *
     * @param other number of class VeryLong
     * @param biggerEqual check bigger or equal
     * @return boolean statement
     */
    public boolean bigger(VeryLong other, boolean biggerEqual) {

        if (this.number.length != other.number.length) {

            if (this.symbol.equals("+") & other.symbol.equals("+")) {
                return this.number.length > other.number.length;
            } else if (this.symbol.equals("-") & other.symbol.equals("+")) {
                return false;
            } else if (this.symbol.equals("+") & other.symbol.equals("-")) {
                return true;
            } else {
                return this.number.length < other.number.length;
            }

        } else {

            if (biggerEqual) {
                return this.isEqual(other);
            }

            if (this.symbol.equals("-") & other.symbol.equals("-")) {

                if (checkingLess(other, biggerEqual)) return true;

            }

            if (this.symbol.equals("+") & other.symbol.equals("+")) {

                if (checkingBigger(other, biggerEqual)) return true;

            }

            if (this.symbol.equals("-") & other.symbol.equals("+")) {
                return false;
            } else return this.symbol.equals("+") & other.symbol.equals("-");
        }

    }

    /**
     * Auxiliary function for the reduce the number of lines of code
     *
     * @param other number of class VeryLong
     * @param biggerEqual number of class VeryLong
     * @return boolean statement
     */
    private boolean checkingBigger(VeryLong other, boolean biggerEqual) {
        for (int i = 0; i < this.number.length; i++) {
            if (biggerEqual) {
                if (this.number[i] >= other.number[i]) {
                    return true;
                }
            } else {
                if (this.number[i] > other.number[i]) {
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * Check if the one VeryLong number is less then other. Note: if lessEqual is true, then we will check if
     * one VeryLong number less or equal then other
     *
     * @param other number of class VeryLong
     * @param lessEqual check bigger or equal
     * @return boolean statement
     */
    public boolean less(VeryLong other, boolean lessEqual) {

        if (this.number.length != other.number.length) {

            if (this.symbol.equals("+") & other.symbol.equals("+")) {
                return this.number.length < other.number.length;
            } else if (this.symbol.equals("-") & other.symbol.equals("+")) {
                return true;
            } else if (this.symbol.equals("+") & other.symbol.equals("-")) {
                return false;
            } else {
                return this.number.length > other.number.length;
            }

        } else {

            if (lessEqual) {
                return this.isEqual(other);
            }

            if (this.symbol.equals("-") & other.symbol.equals("-")) {

                if (checkingBigger(other, lessEqual)) return true;

            }

            if (this.symbol.equals("+") & other.symbol.equals("+")) {


                if (checkingLess(other, lessEqual)) return true;

            }

            if (this.symbol.equals("-") & other.symbol.equals("+")) {
                return true;
            } else return this.symbol.equals("+") & other.symbol.equals("-");
        }


    }

    /**
     * Auxiliary function for the reduce the number of lines of code
     *
     * @param other number of class VeryLong
     * @param biggerEqual number of class VeryLong
     * @return boolean statement
     */
    private boolean checkingLess(VeryLong other, boolean biggerEqual) {
        for (int i = 0; i < this.number.length; i++) {
            if (biggerEqual) {
                if (this.number[i] <= other.number[i]) {
                    return true;
                }
            } else {
                if (this.number[i] < other.number[i]) {
                    return true;
                }
            }

        }
        return false;
    }


    private int[] convertIntegers(List<Integer> integers) {
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++) {
            ret[i] = iterator.next();
        }
        return ret;
    }

    private List<Integer> toArrayList(int[] arr) {

        List<Integer> primArr = new ArrayList<>();

        for (int i1 : arr) {

            primArr.add(i1);

        }

        return primArr;
    }

    /**
     * Calculate the multiplication of two VeryLong numbers. The main idea - use the column multiplication.
     * We compute the multiplication of big number to every term of the small one, after this we sum it with
     * `biRemainder` and etc. Note: before the sum current product with `bigRemainder`, we check the number of zeros,
     * which we should add to the end of the current product
     *
     * @param bigNum the array of number's term
     * @param smallNum the array of number's term
     * @param bigRemainder the remainder after the product between the `bigNum` and term of `smallNum`
     * @param numberZeros counts of zeros, which we chould add to the end of the current product
     * @return
     */
    private VeryLong calcMultiplication(int[] bigNum, int[] smallNum, List<Integer> bigRemainder, int numberZeros) {

        int mainIndex = bigNum.length - 1;
        int remainder = 0;

        int smallN = smallNum[smallNum.length - 1];

        List<Integer> resRem = new ArrayList<>();

        while (mainIndex >= 0) {


            int res = bigNum[mainIndex] * smallN;

            if (remainder != 0) {

                res += remainder;

            }


            if (mainIndex - 1 == -1 & res > 9) {
                resRem.add(0, res);

            } else {
                remainder = res / 10;
                resRem.add(0, res % 10);
            }

            mainIndex--;

        }

        for (int i = numberZeros; i > 0; i--) {
            resRem.add(0);
        }

        VeryLong num1 = toVeryLong(convertIntegers(resRem), "+");
        VeryLong num2 = toVeryLong(convertIntegers(bigRemainder), "+");


        VeryLong result = num1.add(num2);

        numberZeros += 1;

        if (smallNum.length == 1) {

            return result;

        } else {

            smallNum = Arrays.copyOfRange(smallNum, 0, smallNum.length - 1);
            return calcMultiplication(bigNum, smallNum, toArrayList(result.number), numberZeros);
        }


    }

    /**
     * This function is compute multiplication between current VeryLong number and the other VeryLong number. Here
     * we prepare variables for the function `calcMultiplication`
     *
     * @param num number of class VeryLong
     * @return the multiplication between the current number and other one
     */
    public VeryLong multiply(VeryLong num) {

        List<Integer> bigRemainder = new ArrayList<>();


        if (this.number.length > num.number.length) {

            for (int i = 0; i < this.number.length; i++) {
                bigRemainder.add(0);
            }

            return calcMultiplication(this.number, num.number, bigRemainder, 0);

        } else {

            for (int i = 0; i < num.number.length; i++) {
                bigRemainder.add(0);
            }

            return calcMultiplication(num.number, this.number, bigRemainder, 0);
        }

    }


    public VeryLong division(VeryLong num) {


        return null;
    }

    /**
     * This function is compute the subtraction between the two VeryLong numbers. The main idea is the column subtraction.
     * For the beginning, we checking the signs of this two numbers and then deciding the method of computing the subtraction.
     *
     * @param num number of class VeryLong
     * @return subtraction between two VeryLong numbers
     */
    public VeryLong substraction(VeryLong num) {

        VeryLong res;
        String resSymbol = "+";

        if (this.symbol.equals("-") & num.symbol.equals("-")) {

            num.symbol = "+";
            this.symbol = "+";
            res = num.substraction(this);

            return res;


        }

        if (num.symbol.equals("-")) {

            num.symbol = "+";
            res = this.add(num);
            return res;

        }
        if (this.symbol.equals("-")) {

            num.symbol = "-";
            res = this.add(num);
            res.symbol = "-";

            return res;
        }


        List<int[]> biggestArr = getBiggerNumber(this.number, num.number);

        int[] num1 = biggestArr.get(0);
        int[] num2 = biggestArr.get(1);
        int sizeDiff = Math.abs(num1.length - num2.length);

        int[] thisCopy = this.number.clone();

        resSymbol = this.number.length > num.number.length ? "+" : "-";

        int remainderSub = 0;
        int resSub;

        int mainIndex = num1.length - 1;
        int j = num2.length - 1;

        while (mainIndex > 0) {

            if (j == -1 & remainderSub != 0) {

                resSub = num1[mainIndex] - remainderSub;

                if (resSub < 0) {
                    remainderSub = 1;
                } else {
                    num1[mainIndex] = resSub;
                    break;
                }

                mainIndex--;

            }

            if (j > -1) {
                resSub = num1[mainIndex] - num2[j];

                if (remainderSub != 0) {
                    resSub -= remainderSub;
                }

                if (resSub < 0) {

                    resSub = num1[mainIndex] + 10 - num2[j];
                    num1[mainIndex] = resSub;
                    remainderSub = 1;

                } else {
                    num1[mainIndex] = resSub;
                }
            }
            mainIndex--;
            j--;

            if (mainIndex == sizeDiff - 1) {

            }

        }


        this.number = thisCopy;

        return toVeryLong(num1, resSymbol);
    }

    /**
     * This function computing the sum between the two VeryLong numbers. The principle of such approach is the column adding.
     * For the beginning, we checking the signs og this two numbers and then choosing the way of computing the sum. Depending
     * of signs we will use different functions, for example, if signs `-` and `+`, we will use the subtraction function
     *
     * @param num number of class VeryLong
     * @return the subtraction of two VeryLong numbers
     */
    public VeryLong add(VeryLong num) {

        VeryLong result;

        String sign = "+";

        if ((this.symbol.equals("-") & num.symbol.equals("-")) | (this.symbol.equals("+") & num.symbol.equals("+"))) {
            sign = this.symbol.equals("-") & num.symbol.equals("-") ? "-" : "+";

            int sizeDiff = Math.abs(this.number.length - num.number.length) == 0 ? 0 : Math.abs(this.number.length - num.number.length) - 1;

            List<int[]> biggestArr = getBiggerNumber(this.number, num.number);
            int[] num1 = biggestArr.get(0);
            int[] num2 = biggestArr.get(1);

            int[] thisCopy = this.number.clone();


            int remainder = 0;
            int res;

            int mainIndex = num1.length - 1;
            int j = num2.length - 1;

            while (mainIndex != sizeDiff - 1) {

                if (j == -1 & remainder != 0) {

                    res = num1[mainIndex] + remainder;

                    if (res > 9) {
                        List<Integer> resRemainder = getResRemainderAdd(res, remainder);
                        num1[mainIndex] = resRemainder.get(0);
                        remainder = resRemainder.get(1);
                    } else {
                        num1[mainIndex] = res;
                        break;
                    }

                    mainIndex--;

                    continue;

                }
                if (j == -1 & remainder == 0) {
                    break;
                }

                res = num1[mainIndex] + num2[j];

                List<Integer> resRemainder = getResRemainderAdd(res, remainder);

                num1[mainIndex] = resRemainder.get(0);
                remainder = resRemainder.get(1);
                mainIndex--;
                j--;

            }


            this.number = thisCopy;

            return toVeryLong(num1, sign);

        }

        if (this.symbol.equals("-")) {

            boolean isFirstBigger = this.number.length > num.number.length;

            if (isFirstBigger) {


                this.symbol = "+";
                result = num.substraction(this);

                return result;
            }

        }
        if (num.symbol.equals("-")) {


            num.symbol = "+";
            result = this.substraction(num);

            return result;

        }

        return null;
    }

    private List<Integer> getResRemainderAdd(int res, int remainder) {

        if (remainder != 0) {
            res += remainder;
            remainder = 0;
        }

        if (res > 9) {

            remainder = res / 10;
            res = res % 10;

        }

        List<Integer> output = new ArrayList<>();
        output.add(res);
        output.add(remainder);

        return output;

    }

    /**
     * This function is checking which number is bigger and return it
     *
     * @param num1 array of terms of VeryLong number
     * @param num2 array of terms of VeryLong number
     * @return bigger number
     */
    private List<int[]> getBiggerNumber(int[] num1, int[] num2) {

        int[] number1, number2;
        if (num1.length > num2.length) {

            number1 = num1;
            number2 = num2;

        } else {

            number1 = num2;
            number2 = num1;

        }

        return Arrays.asList(number1, number2);
    }

    @Override
    public String toString() {

        StringBuilder val = new StringBuilder(this.symbol.equals("+") ? "" : "-");

        for (int i : this.number) {

            val.append(i);

        }

        return val.toString();

    }

    private VeryLong toVeryLong(int[] arr, String sign) {

        StringBuilder val = new StringBuilder(sign.equals("+") ? "" : "-");

        for (int i : arr) {
            val.append(i);
        }

        return new VeryLong(val.toString());


    }

    public int toInteger() {

        VeryLong maxInteger = new VeryLong(Integer.toString(Integer.MAX_VALUE));

        if (this.less(maxInteger, true) & this.bigger(maxInteger, true)) {
            return Integer.parseInt(this.toString());
        } else {
            throw new StackOverflowError();
        }


    }

    public long toLong() {

        VeryLong maxLong = new VeryLong(Long.toString(Long.MAX_VALUE));


        if (this.less(maxLong, true) & this.bigger(maxLong, true)) {
            return Long.parseLong(this.toString());
        } else {
            throw new StackOverflowError();
        }

    }


    public boolean toBoolean() {

        for (int i : this.number) {

            if (i != 0) {
                return true;
            }

        }

        return false;

    }

    public void increment() {
        VeryLong incrRes = this.add(new VeryLong("1"));

        this.number = incrRes.number;
        this.symbol = incrRes.symbol;
    }

    public void dicrement() {
        VeryLong incrRes = this.substraction(new VeryLong("1"));

        this.number = incrRes.number;
        this.symbol = incrRes.symbol;
    }


}
