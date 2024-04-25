import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Combinations{
    
    public static int evaluate(String[] player1Value, String[] player1Suit, String[] player2Value, String[] player2Suit){

        int p1HandStrength = evaluateHand(player1Value, player1Suit);
        int p2HandStrength = evaluateHand(player2Value, player2Suit);
        //System.out.println(p1HandStrength);
        //System.out.println(p2HandStrength);
        if (p1HandStrength > p2HandStrength)
            return 1;
        else
            return 0;
    }

    private static int evaluateHand(String[] value, String[] suit){
        int strength = 1;
        int[] numericValues = new int[value.length]; // Array to store numeric values
    
        // Convert string values to integers
        for (int i = 0; i < value.length; i++) {
            switch (value[i]) {
                case "T":
                    numericValues[i] = 10;
                    break;
                case "J":
                    numericValues[i] = 11;
                    break;
                case "Q":
                    numericValues[i] = 12;
                    break;
                case "K":
                    numericValues[i] = 13;
                    break;
                case "A":
                    numericValues[i] = 14;
                    break;
                default:
                    numericValues[i] = Integer.parseInt(value[i]);
                    break;
            }
        }
        Arrays.sort(numericValues);
        Arrays.sort(suit);

        boolean hasStraight = false;
        boolean hasFlush = false;
        boolean hasStraightFlush = false;
        boolean hasRoyalFlush = false;
        
        boolean hasFourOfAKind = false;
        boolean hasThreeOfAKind = false;
        boolean hasTwoPair = false;
        boolean hasPair = false;

        //is it a three of a kind or full house, or Four of a kind
        Map <Integer, Integer> freqMap = new HashMap<>();
        for(int values:numericValues){
            freqMap.put(values, freqMap.getOrDefault(values, 0) + 1);
        }
        

        for(int count:freqMap.values()){
            if (count == 4) {
                hasFourOfAKind = true;
            }   else if (count == 3) {
                hasThreeOfAKind = true;
                }   else if(hasPair){
                        hasTwoPair = true;
                    }
                        else if(count == 2){
                            hasPair = true;
                        }
        }
        if (hasFourOfAKind) {
            strength = 8;
        }   else if(hasThreeOfAKind && hasPair){
                strength = 7;
            }   else if (hasThreeOfAKind){
                strength = 4;
                }

        //Is it a straight or a flush, or a straight flush, or a royal flush?
        //Is it a straight?
        if (numericValues[0] == numericValues[4] - 4) {
            if (freqMap.size() == 5) {
                hasStraight = true;
            }
        }

        //Is it a flush?
        if(suit[0].equals(suit[4])){
            hasFlush = true;
        }  

        //Is it a straight flush?
        if(hasStraight && hasFlush){
            hasStraightFlush = true;
        }

        //Is it a royal flush?
        if(hasStraightFlush && numericValues[0] == 10){
            hasRoyalFlush = true;
        }


        if (hasStraight) {
            strength = 5;
        }   
        if (hasFlush) {
            strength = 6;
        }   
        if(hasStraightFlush){
            strength = 9;
        }   
        if(hasRoyalFlush){
            strength = 10;
            return strength;
        }

        // part for two pairs, one pair and high card
        if(hasTwoPair){
            strength = 3;
        }   else if(hasPair){
            strength = 2;
            }   else{
                strength = 1;
                }   
        

        
        return strength;
        
    }
}