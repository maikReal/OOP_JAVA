import java.util.*;


public class User {

    private HashMap<Integer, Account> accounts;
    private Bank bank;
    private int usernameId;

    /**
     * Default constructor
     */
    public User(int usernameId) {
        this.usernameId = usernameId;
        this.accounts = new HashMap<>();
    }
//

    /**
     * 
     */
    public boolean makeTransactionBetweenAccounts(int fromAccountId, int toAccountId, int sum) {
        return this.bank.processTransaction(this, fromAccountId, toAccountId, sum);

    }

    public boolean makeTransactionBetweenUsers(int fromUserAccountId,
                                            int toUserId, int toUserAccountId, int sum) {


        return this.bank.processUserTransaction(this, fromUserAccountId, toUserId, toUserAccountId, sum);



    }

    int checkBalance(int accountId){
        return bank.checkAccountBalance(this, accountId);
    }

    int getUsernameId(){
        return this.usernameId;
    }

    void setAccounts(ArrayList<Account> accounts){

        var numberOfAccount = 1;
        for (var account : accounts){
            this.accounts.put(numberOfAccount, account);
            numberOfAccount++;
        }

    }

    void setBank(Bank bank) {
        this.bank = bank;
    }
}