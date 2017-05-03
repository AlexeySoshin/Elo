package me.soshin.service;

import me.soshin.model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class SuggestionService {


    private static final int SUGGESTIONS_COUNT = 3;


    /**
     * Chooses x players to compete with
     * Players with closest rating are chosen
     *
     * @param ranked
     * @param currentRank
     * @return
     */
    public List<Player> calculateSuggestions(final List<Player> ranked, int currentRank) {
        final List<Player> suggestions = new ArrayList<>();

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
