import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Combinations{
    
    public static int evaluate(String[] player1Value, String[] player1Suit, String[] player2Value, String[] player2Suit){

        int p1HandStrength = evaluateHand(player1Value, player1Suit);
        int p2HandStrength = evaluateHand(player2Value, player2Suit);
        if (p1HandStrength > p2HandStrength) {
            return 1; // Player 1 wins
        } else if (p1HandStrength < p2HandStrength) {
            return 0; // Player 2 wins
        } else {
            // If hand strengths are equal, compare individual cards
            return compareHands(player1Value, player2Value);
        }
    }

    private static int[] convertToNumericValues(String[] values) {
        int[] numericValues = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            switch (values[i]) {
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
                    numericValues[i] = Integer.parseInt(values[i]);
                    break;
            }
        }
        return numericValues;
    }

    private static int compareHands(String[] hand1, String[] hand2) {
        int n = hand1.length;
        
        // Convert cards to numeric values for comparison
        int[] numericValues1 = convertToNumericValues(hand1);
        int[] numericValues2 = convertToNumericValues(hand2);
    
        // Sort the numeric values in descending order
        Arrays.sort(numericValues1);
        Arrays.sort(numericValues2);
    
        // Compare hand strengths
        for (int i = n - 1; i >= 0; i--) {
            if (numericValues1[i] > numericValues2[i]) {
                return 1; // Player 1 wins
            } else if (numericValues1[i] < numericValues2[i]) {
                return -1; // Player 2 wins
            }
        }
    
        // If hand strengths are equal, compare the highest value of the hand
        int maxCardValue1 = findMaxGroupValue(numericValues1);
        int maxCardValue2 = findMaxGroupValue(numericValues2);
    
        if (maxCardValue1 > maxCardValue2) {
            return 1; // Player 1 wins
        } else  {
            return -1; // Player 2 wins
        } 
    }
    
    private static int findMaxGroupValue(int[] numericValues) {
        int maxCount = 0;
        int maxValue = -1;
        
        // Count occurrences of each value
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int value : numericValues) {
            int count = frequencyMap.getOrDefault(value, 0) + 1;
            frequencyMap.put(value, count);
            
            // Update maxCount and maxValue
            if (count > maxCount || (count == maxCount && value > maxValue)) {
                maxCount = count;
                maxValue = value;
            }
        }
        
        return maxValue;
    }

    private static int evaluateHand(String[] value, String[] suit){
        int strength = 1;
        int[] numericValues = convertToNumericValues(value); // Array to store numeric values
    
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

        //Is it a three of a kind or full house, or Four of a kind
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