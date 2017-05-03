package me.soshin.algo;

import org.springframework.stereotype.Component;

/**
 * Implementation of Elo algorithm
 * https://en.wikipedia.org/wiki/Elo_rating_system
 */
@Component
public class Elo implements RankingAlgorithm {

    private static final int K = 32;
    private static final int RANK_FACTOR = 400;

    public static final int MIN_RANK = 100;
    public static final int MAX_RANK = 3000;

    private static int transformedRating(final Integer rank) {
        return (int) Math.pow(10, rank/RANK_FACTOR);
    }

    private static double expectedScore(final double first, final double second) {
        return first / (first + second);
    }


    /**
     * Given two ratings, compute how much each would benefit if he wins
     * @param winnerRank
     * @param loserRank
     * @return Pair of updated ratings, for winner and loser both
     */
    public RankingResult compute(final Integer winnerRank, final Integer loserRank) {
        // Transform rating
        final int winnerRating = Elo.transformedRating(winnerRank);
        final int loserRating = Elo.transformedRating(loserRank);

        // Calculate expected win chances
        final double winnerExpected = Elo.expectedScore(winnerRating, loserRating);
        final double loserExpected = Elo.expectedScore(loserRating, winnerRating);

        // Since we already know who winner and losers are
        int winnerUpdated = (int) (winnerRank + K * (1 - winnerExpected));
        int loserUpdated = (int) (loserRank + K * (0 - loserExpected));

        winnerUpdated = Math.min(MAX_RANK, Math.max(MIN_RANK, winnerUpdated));
        loserUpdated = Math.min(MAX_RANK, Math.max(MIN_RANK, loserUpdated));

        return new RankingResult(winnerUpdated, loserUpdated);
    }


}
