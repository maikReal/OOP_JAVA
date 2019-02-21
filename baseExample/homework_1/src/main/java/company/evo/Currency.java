package main.java.company.evo;

public class Currency {

    int idCurrency;

    Currency(int idCurrency){
        this.idCurrency = idCurrency;
    }

    public int getID() {
        return idCurrency;
    }

    public void setIdCurrency(int idCurrency){
        this.idCurrency = idCurrency;
    }
}
