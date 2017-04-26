package me.soshin.model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    public static final Integer DEFAULT_RANK = 200;

    private final Integer id;
    private final String name;
    private Integer rank = DEFAULT_RANK;

    // Names of winners and losers
    // In real life those would be also models
    private List<String> wins = new ArrayList<>();
    private List<String> loses = new ArrayList<>();

    public Player(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public List<String> getWins() {
        return wins;
    }

    public List<String> getLoses() {
        return loses;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rank=" + rank +
                ", wins=" + wins.size() +
                ", loses=" + loses.size() +
                '}';
    }

    public void addWin(Player loser) {
        wins.add(loser.getName());
    }

    public void addLose(Player winner) {
        loses.add(winner.getName());
    }
}
