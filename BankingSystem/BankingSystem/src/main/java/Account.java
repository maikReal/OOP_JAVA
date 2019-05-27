
import java.util.*;

/**
 * 
 */
public class Account {

    private String personalAccountNumber;
    private int balance = 1000;

    /**
     * Default constructor
     */
    public Account(String personalAccountNumber) {
        this.personalAccountNumber = personalAccountNumber;
    }


    /**
     *
     * @param sum
     */
    boolean checkBalance(int sum) {
        return sum <= balance;
    }

    synchronized int getBalance(){
        return this.balance;
    }
    synchronized void setBalance(int balance){
        this.balance = balance;
    }

}