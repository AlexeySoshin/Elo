package me.soshin.algo;

import org.junit.Test;

import static org.junit.Assert.*;

public class EloTest {

    @Test
    public void computeStrongerWins() throws Exception {

        int winnerRank = 2400;
        int loserRank = 2000;

        Elo.Result result = Elo.compute(winnerRank, loserRank);

        assertEquals(2402, result.getWinnerRank());
        assertEquals(1997, result.getLoserRank());
    }


    @Test
    public void computeWeakerWins() throws Exception {

        int winnerRank = 2000;
        int loserRank = 2400;

        Elo.Result result = Elo.compute(winnerRank, loserRank);

        assertEquals(2029, result.getWinnerRank());
        assertEquals(2370, result.getLoserRank());
    }

}