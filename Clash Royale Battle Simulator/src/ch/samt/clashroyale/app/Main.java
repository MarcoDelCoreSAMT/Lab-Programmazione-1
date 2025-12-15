// Esercizio: 6.7 Clash Royale Battle Simulator
// Autore: Marco Del Core

package ch.samt.clashroyale.app;

import ch.samt.clashroyale.battle.BattleEngine;
import ch.samt.clashroyale.model.*;

public class Main {
    public static void main(String[] args) {
        TroopCard arcierePirotecnico = new TroopCard("Arciere pirotecnico", 3, 12, 334,115);
        TroopCard principeNero = new TroopCard("Principe nero", 4, 11, 1240,190);
        SpellCard furia = new SpellCard("Furia", 2, 10, 179, 3);

        Deck deck = new Deck();
        deck.addCard(arcierePirotecnico);
        deck.addCard(principeNero);
        deck.addCard(furia);

        Player p = new Player("Dorian", deck);

        BattleEngine engine = new BattleEngine();

        System.out.println("=== Deck 1 di "+ p.getNickname() + " ===\n");

        for (Card c : deck.getCards()) {
            System.out.println(c);
            engine.playCard(c);
            System.out.println();
        }
    }
}