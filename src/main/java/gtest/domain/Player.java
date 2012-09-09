package gtest.domain;

import java.math.BigDecimal;

/**
 * @author Vitalii Tymchyshyn
 */
public class Player {
    private String username;
    private long balanceVersion;
    private BigDecimal balance;

    public Player() {
    }

    public Player(String username, long balanceVersion, BigDecimal balance) {
        this.username = username;
        this.balanceVersion = balanceVersion;
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getBalanceVersion() {
        return balanceVersion;
    }

    public void setBalanceVersion(long balanceVersion) {
        this.balanceVersion = balanceVersion;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
