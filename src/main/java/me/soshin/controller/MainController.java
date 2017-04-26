package me.soshin.controller;

import me.soshin.dao.PlayerDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Map;


/**
 * Returns the initial template
 */
@Controller
public class MainController {

    @Resource
    PlayerDAO playersDAO;

    @RequestMapping("/")
    public String welcome(final Map<String, Object> model) {
        model.put("players", this.playersDAO.getPlayersRanked());
        return "index";
    }

}
