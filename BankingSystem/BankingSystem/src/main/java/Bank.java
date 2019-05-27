
import java.nio.charset.Charset;
import java.util.*;

/**
 * 
 */
public class Bank {


    private HashMap<Integer, ArrayList<Account>> users;

    private Checker checker;

    private BankServer bankServer;


    /**
     * Default constructor
     */
    public Bank() {
        this.users = new HashMap<Integer, ArrayList<Account>>();
        this.checker = new Checker(users);
        this.bankServer = new BankServer(users);
    }

    public void addUserToBank(User user, int numberAccounts){

        ArrayList<Account> accountNumbers = new ArrayList<>();
        for (var i=0; i<numberAccounts;i++){

            var value = getRandomString();
            accountNumbers.add(new Account(value));

        }

        user.setAccounts(accountNumbers);
        user.setBank(this);
        users.put(user.getUsernameId(), accountNumbers);

    }


    /**
     *
     * @param user
     * @param fromAccountId
     * @param toAccountId
     */
    private boolean checkUserAccess(User user, int fromAccountId, int toAccountId) {
        return checker.checkUserAccess(user, fromAccountId, toAccountId);
    }


    public static String getRandomString(){

        byte[] array = new byte[7];
        new Random().nextBytes(array);

        return new String(array, Charset.forName("UTF-8"));

    }

    public boolean processTransaction(User user, int fromAccountId, int toAccountId, int sum) {
        fromAccountId = fromAccountId - 1;
        toAccountId = toAccountId - 1;
        if (this.checkUserAccess(user, fromAccountId, toAccountId) &&
        bankServer.checkUserBalance(user, fromAccountId, sum)){
            var fromAccount = this.users.get(user.getUsernameId()).get(fromAccountId);
            var toAccount = this.users.get(user.getUsernameId()).get(toAccountId);
            bankServer.transferMoney(fromAccount, toAccount, sum);
            return true;
        }
        return false;
    }

    public boolean processUserTransaction(User user1, int fromUserAccountId,
                                   int toUserId, int toUserAccountId, int sum) {

        fromUserAccountId = fromUserAccountId - 1;
        toUserAccountId = toUserAccountId - 1;

        if (this.checkUserAccess(user1.getUsernameId(), fromUserAccountId) &&
            this.checkUserAccess(toUserId, toUserAccountId) &&
            bankServer.checkUserBalance(user1, fromUserAccountId, sum)){

            var fromAccount = this.users.get(user1.getUsernameId()).get(fromUserAccountId);
            var toAccount = this.users.get(toUserId).get(toUserAccountId);

            bankServer.transferMoney(fromAccount, toAccount, sum);
            return true;

        }
        return false;

    }

    private boolean checkUserAccess(int userId, int accountId) {

        return checker.checkUserAccess(userId, accountId);

    }

    public int checkAccountBalance(User user, int accountId){
        return bankServer.checkAccountBalance(user, accountId);
    }

}