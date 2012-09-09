package gtest.dao;

import gtest.domain.Player;

import java.math.BigDecimal;

/**
 * @author Vitalii Tymchyshyn
 */
public interface PlayerDao {
    Player getPlayer(String userName);
    void addPlayer(String userName);
}
