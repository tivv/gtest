package gtest.dao;

import gtest.dto.Player;
import gtest.exceptions.NotFoundException;

import java.math.BigDecimal;

/**
 * @author Vitalii Tymchyshyn
 */
public interface PlayerDao {
    Player getPlayer(String userName) throws NotFoundException;
    void addPlayer(String userName);
    void addTransaction(String userName, BigDecimal balanceChange) throws NotFoundException;
}
