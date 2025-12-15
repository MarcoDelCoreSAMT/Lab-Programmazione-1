package ch.samt.clashroyale.battle;

import ch.samt.clashroyale.model.*;

public class BattleEngine {
    public void playCard(Card card){

        System.out.println("Carta giocata: " + card.getName());

        if (card instanceof TroopCard){
            TroopCard troopCard = (TroopCard) card;
            System.out.println("Troop --> HP: " + troopCard.getHitPoints() + ", Danno: " + troopCard.getDamage());
        }
        else if (card instanceof SpellCard){
            SpellCard spellCard = (SpellCard) card;
            System.out.println("Spell --> HP ad area: " + spellCard.getAreaDamage() + ", Raggio: " + spellCard.getRadius());
        }
    }
}
