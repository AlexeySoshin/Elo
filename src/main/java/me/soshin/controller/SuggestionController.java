package me.soshin.controller;


import me.soshin.controller.exception.PlayerNotFoundException;
import me.soshin.dao.PlayerDAO;
import me.soshin.model.Player;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Suggests players to play with
 */
@RestController
public class SuggestionController {


    private static final int SUGGESTIONS_COUNT = 3;

    @Resource
    PlayerDAO playerDAO;

    @RequestMapping("/suggest/{id}")
    public List<Player> suggestedPlayers(@PathVariable("id") Integer id) {

        Player currentPlayer = playerDAO.getPlayer(id);

        if (currentPlayer == null) {
            throw new PlayerNotFoundException();
        }

        List<Player> ranked = playerDAO.getPlayersRanked();

        int currentRank = ranked.indexOf(currentPlayer);

        // Player is not ranked yet, no suggestions
        if (currentRank == -1) {
            return Collections.emptyList();
        }

        return calculateSuggestions(ranked, currentRank);
    }

    /**
     * Chooses x players to compete with
     * Players with closest rating are chosen
     *
     * @param ranked
     * @param currentRank
     * @return
     */
    private List<Player> calculateSuggestions(List<Player> ranked, int currentRank) {
        List<Player> suggestions = new ArrayList<>();

        int total = SUGGESTIONS_COUNT;

        if (currentRank >= 0 && currentRank < ranked.size()) {
            // remove current player, cannot play himself
            ranked.remove(currentRank);

            // Put players closest in rank as suggestions
            while (total > 0 && ranked.size() > 0) {
                currentRank = Math.min(currentRank, ranked.size() - 1);
                suggestions.add(ranked.remove(currentRank));
                total--;
            }
        }

        return suggestions;
    }
}
