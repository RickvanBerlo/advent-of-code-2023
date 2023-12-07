package task7;

import java.util.List;

public class Hand {
    public char[] cards;
    public Integer type;
    public Integer value;

    public Hand(char[] cards, Integer type, Integer value){
        this.cards = cards;
        this.type = type;
        this.value = value;
    }

    public Boolean isStronger(Hand hand, List<Character> strength){
        if(type > hand.type){
            return true;
        } else if( type == hand.type){
            for(int i = 0; i < cards.length; i++){
                int indexCurrent = strength.indexOf(cards[i]);
                int indexHand = strength.indexOf(hand.cards[i]);
                if(indexCurrent > indexHand){
                    return true;
                } else if(indexCurrent < indexHand){
                    return false;
                }
            }
        } else {
            return false;
        }

        //can not be
        System.out.println("invalid false!!!!");
        return false;
    }
    
}
