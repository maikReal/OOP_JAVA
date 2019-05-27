import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankSystemTest {

    private static Bank bank;
    private static User user1, user2;

    @BeforeEach
    void prepare(){
        bank = new Bank();

        user1 = new User(1);
        bank.addUserToBank(user1, 3);

        user2 = new User(2);
        bank.addUserToBank(user2, 3);

    }

    @Test
    void testTransactionBetweenAccounts(){

        user1.makeTransactionBetweenAccounts(1,3,500);
        assertEquals(user1.checkBalance(1), 500);
        assertEquals(user1.checkBalance(3), 1500);

        user2.makeTransactionBetweenAccounts(2, 1, 800);
        assertEquals(user2.checkBalance(2), 200);
        assertEquals(user2.checkBalance(1), 1800);


    }

    @Test
    void testUnrealTransactionsAccounts(){

        var respTransactionUser1 = user1.makeTransactionBetweenAccounts(1, 3, 1600);
        assertTrue(!respTransactionUser1);

        var respTransactionUser2 = user2.makeTransactionBetweenAccounts(3, 2, 6200);
        assertTrue(!respTransactionUser2);

    }

    @Test
    void testTransactionBetweenUsers(){

        user1.makeTransactionBetweenUsers(2, 2, 3, 400);
        assertEquals(user1.checkBalance(2), 600);
        assertEquals(user2.checkBalance(3), 1400);

        user2.makeTransactionBetweenUsers(2,1,2,200);
        assertEquals(user2.checkBalance(2), 800);
        assertEquals(user1.checkBalance(2), 800);

    }

    @Test
    void testUnrealTransactionsUsers(){

        var respTransactionUser1 = user1.makeTransactionBetweenUsers(3, 2,1,1001);
        assertTrue(!respTransactionUser1);

        var respTransactionUser2 = user2.makeTransactionBetweenUsers(3, 1,1, 6666);
        assertTrue(!respTransactionUser2);

    }



}