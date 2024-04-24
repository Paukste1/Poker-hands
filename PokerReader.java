import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PokerReader {
    public static int index = 0;
    public static void main(String[] args) {
        String filename = "hands.txt";
        String[] player1Value = new String[5];
        String[] player2Value = new String[5];
        String[] player1Suit = new String[5];
        String[] player2Suit = new String[5];
        String line;
        int winCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            
            
            while ((line = br.readLine()) != null) {
                String[] hand = line.trim().split("\\s+");
                for(int i = 0; i < hand.length; i++){
                    if(i < 5){
                        player1Value[i] = hand[i].substring(0, hand[i].length() - 1);
                        player1Suit[i] = hand[i].substring(hand[i].length() - 1);
                        //System.out.println("p1" + player1Value[i]);
                        //System.out.println("p1" + player1Suit[i]);
                    }
                    else{
                        player2Value[i - 5] = hand[i].substring(0, hand[i].length() - 1);
                        player2Suit[i - 5] = hand[i].substring(hand[i].length() - 1);
                        //System.out.println("p2" + player2Value[i - 5]);
                        //System.out.println("p2" + player2Suit[i - 5]);
                    }
                }
                if((Combinations.evaluate(player1Value, player1Suit, player2Value, player2Suit)) == 1)
                    winCount++;
                //System.out.println(Combinations.evaluate(player1Value, player1Suit, player2Value, player2Suit));
                
             //System.out.println(index++);   
            }
            
            //System.out.println(winCount); 
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
