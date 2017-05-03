package me.soshin.algo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EloTest {

    private final RankingAlgorithm elo = new Elo();

    @Test
    public void computeStrongerWins() throws Exception {

        final int winnerRank = 2400;
        final int loserRank = 2000;

        final RankingResult result = this.elo.compute(winnerRank, loserRank);

        assertEquals(2402, result.getWinnerRank());
        assertEquals(1997, result.getLoserRank());
    }


    @Test
    public void computeWeakerWins() throws Exception {

        final int winnerRank = 2000;
        final int loserRank = 2400;

        final RankingResult result = this.elo.compute(winnerRank, loserRank);

        assertEquals(2029, result.getWinnerRank());
        assertEquals(2370, result.getLoserRank());
    }

}