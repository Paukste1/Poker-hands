public class Combinations{
    
    public static int evaluate(String[] player1Value, String[] player1Suit, String[] player2Value, String[] player2Suit){

        int p1HandStrength = evaluateHand(player1Value, player1Suit);
        int p2HandStrength = evaluateHand(player2Value, player2Suit);

        if (p1HandStrength > p2HandStrength)
            return 1;
        else
            return 0;
    }

    private static int evaluateHand(String[] value, String[] suit){
        
    }
}