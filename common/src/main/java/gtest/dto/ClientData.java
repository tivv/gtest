package gtest.dto;

/**
* @author Vitalii Tymchyshyn
*/
public class ClientData {
    private long sessionNumber;
    private long startingTransaction;

    public ClientData() {
    }

    public ClientData(long sessionNumber, long startingTransaction) {
        this.sessionNumber = sessionNumber;
        this.startingTransaction = startingTransaction;
    }

    public long getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(long sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public long getStartingTransaction() {
        return startingTransaction;
    }

    public void setStartingTransaction(long startingTransaction) {
        this.startingTransaction = startingTransaction;
    }

    @Override
    public String toString() {
        return "ClientData{" +
                "sessionNumber=" + sessionNumber +
                ", startingTransaction=" + startingTransaction +
                '}';
    }
}
