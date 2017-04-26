package me.soshin.parse;

import me.soshin.algo.Elo;
import me.soshin.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Parses players and games from file
 */
public class PlayersParser {


    private static final String PLAYER_NOT_FOUND_MESSAGE = "Player with id %d not found on line %d, skipping";

    private static final Logger log = LoggerFactory.getLogger(PlayersParser.class);

    private final Map<Integer, Player> players = new HashMap<>();

    public Map<Integer, Player> parse(final InputStream playersInput, final InputStream gamesInput) throws IOException {

        loadPlayers(playersInput);
        loadGames(gamesInput);

        return this.players;
    }

    private void loadGames(final InputStream gamesInput) throws IOException {
        try (Scanner s = new Scanner(gamesInput)) {
            int i = 1;
            while (s.hasNextLine()) {
                final String[] gameData = s.nextLine().split(" ");

                if (gameData.length < 2) {
                    log.error(String.format("Expected to get two player ids, got '%s'", Arrays.toString(gameData)));
                    continue;
                }

                final int winnerId = Integer.parseInt(gameData[0]);
                final int loserId = Integer.parseInt(gameData[1]);

                final Player winner = this.players.get(winnerId);
                final Player loser = this.players.get(loserId);
                if (winner == null) {
                    log.error(String.format(PLAYER_NOT_FOUND_MESSAGE, winnerId, i));
                } else if (loser == null) {
                    log.error(String.format(PLAYER_NOT_FOUND_MESSAGE, loserId, i));
                } else {
                    final Elo.Result result = Elo.compute(winner.getRank(), loser.getRank());
                    winner.setRank(result.getWinnerRank());
                    loser.setRank(result.getLoserRank());

                    winner.addWin(loser);
                    loser.addLose(winner);
                }

                i++;
            }
        }
    }

    private void loadPlayers(final InputStream playersInput) throws IOException {

        this.players.clear();
        try (Scanner s = new Scanner(playersInput)) {
            while (s.hasNextLine()) {
                final String[] playerData = s.nextLine().split(" ");

                if (playerData.length < 2) {
                    log.error(String.format("Expected to get player ID and name, got '%s'", Arrays.toString(playerData)));
                    continue;
                }

                final Integer id;
                try {
                    id = Integer.parseInt(playerData[0]);
                }
                catch (NumberFormatException nfe) {
                    log.error("Expected to get player ID, got '%s'", playerData[0]);
                    continue;
                }

                final String name = playerData[1];

                this.players.put(id, new Player(id, name));
            }
        }
    }

}
