
import java.util.*;

/**
 * 
 */
public class Checker {

    private HashMap<Integer, ArrayList<Account>> users;

    /**
     * Default constructor
     * @param users
     */
    public Checker(HashMap<Integer, ArrayList<Account>> users) {
        this.users = users;
    }


    /**
     *
     * @param user
     * @param fromAccountId
     * @param toAccountId
     */
    boolean checkUserAccess(User user, int fromAccountId, int toAccountId) {
        var userAccounts = this.users.get(user.getUsernameId());

        try{
            var fAccountId = userAccounts.get(fromAccountId);
            var tAccountId = userAccounts.get(toAccountId);
        }catch (IndexOutOfBoundsException e){
            System.out.println("Wrong number of account! You don't have such account.");
            return false;
        }

        return true;

    }

    boolean checkUserAccess(int userId, int accountId) {
        var userAccount = users.get(userId);

        try {
            var account = userAccount.get(accountId);
        } catch (IndexOutOfBoundsException e){

            System.out.println("Wrong number of account! You don't have such account.");
            return false;

        }
        return true;
    }

}