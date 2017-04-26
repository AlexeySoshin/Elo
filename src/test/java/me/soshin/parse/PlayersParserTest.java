package me.soshin.parse;

import me.soshin.model.Player;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PlayersParserTest {



    @Test
    public void parseGoodInput() throws Exception {

        final PlayersParser parser = new PlayersParser();

        final Map<Integer, Player> players = parser.parse(this.getClass().getResourceAsStream("/good/players.data"), this.getClass().getResourceAsStream("/good/games.data"));

        assertEquals(40, players.size());

        assertEquals(2, players.get(1).getWins().size());
    }

    @Test
    public void parseGamesNoSuchPlayer() throws IOException {
        final PlayersParser parser = new PlayersParser();

        final Map<Integer, Player> players = parser.parse(this.getClass().getResourceAsStream("/good/players.data"), this.getClass().getResourceAsStream("/bad/games.noSuchPlayer.data"));


    }

    @Test
    public void parsePlayersNoId() throws IOException {
        final PlayersParser parser = new PlayersParser();

        final Map<Integer, Player> players = parser.parse(this.getClass().getResourceAsStream("/bad/players.noId.data"), this.getClass().getResourceAsStream("/empty/games.data"));

    }


    @Test
    public void parsePlayersNoName() throws IOException {
        final PlayersParser parser = new PlayersParser();

        final Map<Integer, Player> players = parser.parse(this.getClass().getResourceAsStream("/bad/players.noName.data"), this.getClass().getResourceAsStream("/empty/games.data"));

    }

}