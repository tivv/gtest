package gtest.dao.impl;

import gtest.dto.Player;
import gtest.exceptions.NotFoundException;
import gtest.dao.PlayerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;

import java.math.BigDecimal;

/**
 * @author Vitalii Tymchyshyn
 */
public class PlayerDaoImpl implements PlayerDao{
    private JdbcOperations jdbcOperations;

    @Override
    public Player getPlayer(String userName) throws NotFoundException {
        try {
            return jdbcOperations.queryForObject("select * from player where username = ?",
                    new BeanPropertyRowMapper<Player>(Player.class),
                    userName);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Player " + userName + " was not found");
        }
    }

    @Override
    public void addPlayer(String userName) {
        jdbcOperations.update("merge into player using (values(cast(? AS varchar(255)))) AS vals(x) " +
                "   ON player.username = vals.x" +
                "   WHEN NOT MATCHED THEN INSERT (username) VALUES vals.x", userName);
    }

    @Override
    public void addTransaction(String userName, BigDecimal balanceChange) throws NotFoundException {
        if (1 != jdbcOperations.update("update player set balance_version = balance_version + 1, " +
                "balance = balance + ? where username = ?", balanceChange, userName)) {
            throw new NotFoundException("Player " + userName + " was not found");
        }
    }

    @Required @Autowired
    public void setJdbcOperations(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }
}
