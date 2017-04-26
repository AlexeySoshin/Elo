package me.soshin.algo;

/**
 * Implementation of Elo algorithm
 * https://en.wikipedia.org/wiki/Elo_rating_system
 */
public class Elo {

    private static final int K = 32;
    private static final int RANK_FACTOR = 400;

    public static final int MIN_RANK = 100;
    public static final int MAX_RANK = 3000;

    private static int transformedRating(Integer rank) {
        return (int) Math.pow(10, rank/RANK_FACTOR);
    }

    private static double expectedScore(double first, double second) {
        return first / (first + second);
    }


    /**
     * Given two ratings, compute how much each would benefit if he wins
     * @param winnerRank
     * @param loserRank
     * @return Pair of updated ratings, for winner and loser both
     */
    public static Elo.Result compute(Integer winnerRank, Integer loserRank) {
        // Transform rating
        int winnerRating = Elo.transformedRating(winnerRank);
        int loserRating = Elo.transformedRating(loserRank);

        // Calculate expected win chances
        double winnerExpected = Elo.expectedScore(winnerRating, loserRating);
        double loserExpected = Elo.expectedScore(loserRating, winnerRating);

        // Since we already know who winner and losers are
        int winnerUpdated = (int) (winnerRank + K * (1 - winnerExpected));
        int loserUpdated = (int) (loserRank + K * (0 - loserExpected));

        winnerUpdated = Math.min(MAX_RANK, Math.max(MIN_RANK, winnerUpdated));
        loserUpdated = Math.min(MAX_RANK, Math.max(MIN_RANK, loserUpdated));

        return new Result(winnerUpdated, loserUpdated);
    }

    public static class Result {
        private int winnerRank;
        private int loserRank;

        public Result(int winnerUpdated, int loserUpdated) {
            this.setWinnerRank(winnerUpdated);
            this.setLoserRank(loserUpdated);
        }

        public int getLoserRank() {
            return loserRank;
        }

        public void setLoserRank(int loserRank) {
            this.loserRank = loserRank;
        }

        public int getWinnerRank() {
            return winnerRank;
        }

        public void setWinnerRank(int winnerRank) {
            this.winnerRank = winnerRank;
        }
    }
}
