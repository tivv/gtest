package gtest.controllers;

import gtest.dao.PlayerDao;
import gtest.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

/**
 * @author Vitalii Tymchyshyn
 */
@Controller
@Transactional
@RequestMapping("/player")
public class PlayerController {
    private PlayerDao playerDao;

	@RequestMapping(value="{name}", method = RequestMethod.GET)
	public @ResponseBody Player getPlayer(@PathVariable String name) {

		return playerDao.getPlayer(name);

	}

    @RequestMapping(value="{name}", method = RequestMethod.PUT)
    public ModelAndView addPlayer(@PathVariable String name) {
        playerDao.addPlayer(name);
        return null;
    }

    @Required @Autowired
    public void setPlayerDao(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }
}
