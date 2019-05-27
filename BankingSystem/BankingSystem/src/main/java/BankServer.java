
import java.util.*;

/**
 * 
 */
public class BankServer {

    private HashMap<Integer, ArrayList<Account>> users;

    /**
     * Default constructor
     * @param users
     */
    public BankServer(HashMap<Integer, ArrayList<Account>> users) {
        this.users = users;
    }

    /**
     *  @param fromAccount
     * @param toAccount
     * @param sum
     */
    synchronized void transferMoney(Account fromAccount, Account toAccount, int sum) {
        var fromBalance = fromAccount.getBalance();
        var toBalance = toAccount.getBalance();

        fromAccount.setBalance(fromBalance - sum);
        toAccount.setBalance(toBalance + sum);
    }

    /**
     * 
     */
    private void AddMoney() {
        // TODO implement here
    }

    synchronized boolean checkUserBalance(User user, int fromAccountId, int sum){
        Account userAccount = users.get(user.getUsernameId()).get(fromAccountId);

        return userAccount.checkBalance(sum);
    }

    synchronized int checkAccountBalance(User user, int accountId){
        accountId = accountId - 1;
        var account = this.users.get(user.getUsernameId()).get(accountId);

        return account.getBalance();
    }
}