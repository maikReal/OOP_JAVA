import java.net.UnknownServiceException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class Main {


    public static void main(String[] args) {

        Bank bank = new Bank();

        var user1 = new User(1);
        bank.addUserToBank(user1, 2);
        var user2 = new User(2);
        bank.addUserToBank(user2, 2);
        var user3 = new User(3);
        bank.addUserToBank(user3, 2);

        user1.makeTransactionBetweenAccounts(1, 2, 200);
        System.out.println("User 1 has on his 1 account: " + user1.checkBalance(1));
        System.out.println("User 1 has on his 2 account: " + user1.checkBalance(2));

        user1.makeTransactionBetweenUsers(1, 2, 1, 500);
        System.out.println("User 1 has on his 1 account: " + user1.checkBalance(1));
        System.out.println("User 2 has on his 1 account: " + user2.checkBalance(1));






    }

}
