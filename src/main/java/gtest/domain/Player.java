package gtest.domain;

import java.math.BigDecimal;

/**
 * @author Vitalii Tymchyshyn
 */
public class Player {
    private String userName;
    private long balanceVersion;
    private BigDecimal balance;

    public Player() {
    }

    public Player(String userName, long balanceVersion, BigDecimal balance) {
        this.userName = userName;
        this.balanceVersion = balanceVersion;
        this.balance = balance;
    }

    public String getUserName() {
        return userName;
    }

    public long getBalanceVersion() {
        return balanceVersion;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
