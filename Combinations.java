import java.util.Arrays;
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


        //is it a flush?
        if(suit[0].equals(suit[4])){
            strength = 6;
            System.out.println(strength);
            //is it a straight flush?
            if(numericValues[0] == numericValues[4] && numericValues[1] != numericValues[3]){
                strength = 9;
                System.out.println(strength);
                //is it a royal flush?
                if(numericValues[0] == 10){
                    strength = 10;
                    System.out.println(strength);
                    return strength;
                }

            }
        }

        
        return strength;
    }
}