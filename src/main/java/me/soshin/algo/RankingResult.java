package me.soshin.algo;

public class RankingResult {
    private int winnerRank;
    private int loserRank;

    public RankingResult(final int winnerUpdated, final int loserUpdated) {
        this.setWinnerRank(winnerUpdated);
        this.setLoserRank(loserUpdated);
    }

    public int getLoserRank() {
        return this.loserRank;
    }

    public void setLoserRank(final int loserRank) {
        this.loserRank = loserRank;
    }

    public int getWinnerRank() {
        return this.winnerRank;
    }

    public void setWinnerRank(final int winnerRank) {
        this.winnerRank = winnerRank;
    }
}
