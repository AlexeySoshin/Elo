package me.soshin.controller;


import me.soshin.controller.exception.PlayerNotFoundException;
import me.soshin.dao.PlayerDAO;
import me.soshin.model.Player;
import me.soshin.service.SuggestionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * Suggests players to play with
 */
@RestController
public class SuggestionController {


    @Resource
    PlayerDAO playerDAO;

    @Resource
    SuggestionService suggestionService;

    @RequestMapping("/suggest/{id}")
    public List<Player> suggestedPlayers(@PathVariable("id") final Integer id) {

        final Player currentPlayer = this.playerDAO.getPlayer(id);

        if (currentPlayer == null) {
            throw new PlayerNotFoundException();
        }

        final List<Player> ranked = this.playerDAO.getPlayersRanked();

        final int currentRank = ranked.indexOf(currentPlayer);

        // Player is not ranked yet, no suggestions
        if (currentRank == -1) {
            return Collections.emptyList();
        }

        return this.suggestionService.calculateSuggestions(ranked, currentRank);
    }


}
