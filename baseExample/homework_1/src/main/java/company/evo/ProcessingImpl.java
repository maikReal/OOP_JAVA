package main.java.company.evo;

import main.java.company.evo.interfaces.Processing;

public class ProcessingImpl implements Processing {
    @Override
    public boolean transfer(Transaction transaction) {

        if (!transaction.getFraud()){
            return false;
        }

        float amount = transaction.getSenderId().getBalance() - transaction.getAmount();

        transaction.getSenderId().setBalance(transaction.getSenderId().getBalance() - amount);
        transaction.getReceiverId().setBalance(transaction.getReceiverId().getBalance() + amount);

        return true;
    }
}
