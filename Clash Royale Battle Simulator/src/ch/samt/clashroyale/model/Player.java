package ch.samt.clashroyale.model;

public class Player {
    private String nickname;
    private Deck deck;

    public Player(String nickname, Deck deck) {
        this.nickname = nickname;
        this.deck = deck;
    }

    public String getNickname() {
        return nickname;
    }

    public Deck getDeck() {
        return deck;
    }
}