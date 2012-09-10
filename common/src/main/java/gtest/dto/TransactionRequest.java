package gtest.dto;

import java.math.BigDecimal;

/**
* @author Vitalii Tymchyshyn
*/
public class TransactionRequest {
    private String username;
    private long transactionId;
    private BigDecimal balanceChange;

    public TransactionRequest() {
    }

    public TransactionRequest(String username, long transactionId, BigDecimal balanceChange) {
        this.username = username;
        this.transactionId = transactionId;
        this.balanceChange = balanceChange;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getBalanceChange() {
        return balanceChange;
    }

    public void setBalanceChange(BigDecimal balanceChange) {
        this.balanceChange = balanceChange;
    }

    @Override
    public String toString() {
        return "TransactionRequest{" +
                "username='" + username + '\'' +
                ", transactionId=" + transactionId +
                ", balanceChange=" + balanceChange +
                '}';
    }
}
