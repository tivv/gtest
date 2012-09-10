package gtest.dto;

import java.math.BigDecimal;

/**
* @author Vitalii Tymchyshyn
*/
public class TransactionResponse {
    private long transactionId;
    private long balanceVersion;
    private BigDecimal balanceChange;
    private BigDecimal balanceAfterChange;

    public TransactionResponse() {
    }

    public TransactionResponse(long transactionId, long balanceVersion, BigDecimal balanceChange, BigDecimal balanceAfterChange) {
        this.transactionId = transactionId;
        this.balanceVersion = balanceVersion;
        this.balanceChange = balanceChange;
        this.balanceAfterChange = balanceAfterChange;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public long getBalanceVersion() {
        return balanceVersion;
    }

    public void setBalanceVersion(long balanceVersion) {
        this.balanceVersion = balanceVersion;
    }

    public BigDecimal getBalanceChange() {
        return balanceChange;
    }

    public void setBalanceChange(BigDecimal balanceChange) {
        this.balanceChange = balanceChange;
    }

    public BigDecimal getBalanceAfterChange() {
        return balanceAfterChange;
    }

    public void setBalanceAfterChange(BigDecimal balanceAfterChange) {
        this.balanceAfterChange = balanceAfterChange;
    }

    @Override
    public String toString() {
        return "TransactionResponse{" +
                "transactionId=" + transactionId +
                ", balanceVersion=" + balanceVersion +
                ", balanceChange=" + balanceChange +
                ", balanceAfterChange=" + balanceAfterChange +
                '}';
    }
}
