package me.soshin.dao;


import me.soshin.model.Player;
import me.soshin.parse.PlayersParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Maintains players: loads them from a file and sorts them
 */
@Repository
public class PlayerDAO {


    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value(value = "classpath:games.data")
    private Resource gamesSource;

    @Value(value = "classpath:players.data")
    private Resource playersSource;

    private final PlayersParser playersParser = new PlayersParser();

    private Map<Integer, Player> players = new ConcurrentHashMap<>();

    private final PlayerComparator comparator = new PlayerComparator();

    // Holds the players sorted by rating
    // Index is their rank
    private List<Player> ranked = new ArrayList<>();

    @PostConstruct
    private void load() throws IOException {
        load(this.playersSource.getInputStream(), this.gamesSource.getInputStream());
    }

    /**
     * Attempts to load players and games from provided files
     * @param players
     * @param games
     * @throws IOException
     */
    public void load(InputStream players, InputStream games) throws IOException {

        this.players = this.playersParser.parse(players, games);
        log.info(String.format("Players loaded: %d", this.players.size()));

        ranked = new ArrayList<>(this.players.values());

        Collections.sort(ranked, this.comparator);

        log.info("Players ranked");
    }


    /**
     * Player by id
     * @param id
     * @return
     */
    public Player getPlayer(final Integer id) {
        return this.players.get(id);
    }

    /**
     * Returns a copy of player rankings, so the consumers won't be able to modify the internal state
     * @return A copy of player rankings
     */
    public List<Player> getPlayersRanked() {
        return new ArrayList<>(this.ranked);
    }

    /**
     * Sorts players by rating, wins (descending) and loses (ascending)
     */
    private class PlayerComparator implements Comparator<Player> {

        @Override
        public int compare(final Player p1, final Player p2) {

            // Rank descending
            int result = p2.getRank().compareTo(p1.getRank());

            if (result != 0) {
                return result;
            }

            // Wins descending
            result = p2.getWins().size() - p1.getWins().size();

            if (result != 0) {
                return result;
            }

            // Loses ascending
            return p1.getLoses().size() - p2.getLoses().size();
        }
    }
}
