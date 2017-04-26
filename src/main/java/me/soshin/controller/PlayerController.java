package me.soshin.controller;


import me.soshin.controller.exception.PlayerNotFoundException;
import me.soshin.dao.PlayerDAO;
import me.soshin.model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.xml.ws.Response;

/**
 * Supports AJAX requests
 */
@RestController
public class PlayerController {

    @Resource
    PlayerDAO playerDAO;

    @RequestMapping("/player/{id}")
    public Player getPlayer(@PathVariable Integer id) {

        Player player = playerDAO.getPlayer(id);

        if (player == null) {
            throw new PlayerNotFoundException();
        }

        return player;
    }

}
