package gtest.controllers;

import gtest.domain.Player;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * @author Vitalii Tymchyshyn
 */
@Controller
@RequestMapping("/player")
public class PlayerController {

	@RequestMapping(value="{name}", method = RequestMethod.GET)
	public @ResponseBody Player getPlayer(@PathVariable String name) {

		return new Player(name, 1, BigDecimal.valueOf(100.2));

	}
}
