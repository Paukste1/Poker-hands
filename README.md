# Poker-hands

## The process
First step was to figure our a way to approach the task. There were a few factors I had to take into account:
- To store all the hand combinations to memory, or taking them line by line in order to compare them;
- How to determine combinations of the given hand; (Observed that only 3 combinations include the suits and all 10 include the values)
- How to quantize the combination, so that they would be comparable;
- How to determine the winner if the hands are of the same rank, but different high card in the combination or in hand;

### Flush
I noticed that only 3 combinations are determined by the suit of the cards, the other 7 are determined by the values and their combinations. So by using the `Array.sort()` function I've created a simple way to determine if the hand contains a straight, flush, straight flush and the royal flush combinations.

### Multiple cards
Then I decided on creating a solution for determining the Three of a kind, Full house and Four of a kind combinations. To solve this I have used a more advanced method. I have incorporated a Card frequency HashMap to store the values of the cards and their count. After that was done I used a FOR loop which contained `count:freqMap.values()` which went through the Hashmap and to the values which were the counts of each value of the card. 
**Now I understand that the naming was a little confusing...**

