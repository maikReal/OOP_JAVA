package main.java.company.evo;

public class Transaction{
    private Account senderId;
    private Account receiverId;
    private boolean fraud;
    private float amount;

    Transaction(Account receiverId, Account senderId, boolean fraud, float amount) {

            this.senderId = senderId;

            this.receiverId = receiverId;

            this.fraud = fraud;

            this.amount = amount;





    }

    public float getAmount() {
        return this.amount;
    }

    public Account getReceiverId() {
        return receiverId;
    }

    public Account getSenderId() {
        return senderId;
    }

    public boolean getFraud() {
        return fraud;
    }

    public void setReceiverId(Account receiverId) {
        this.receiverId = receiverId;
    }

    public void setSenderId(Account senderId) {
        this.senderId = senderId;
    }


}
