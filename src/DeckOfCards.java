
public class DeckOfCards {

	public static void main(String[] args) {
		
		String[] suit= {"Clubs" , "Diamonds" , "Hearts" , "Spades"};
		String[] rank= {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace" };
		
		int cards[]=new int[52];
		
		  for (int i = 0; i < cards.length; i++)
		      cards[i] = i;

		    for (int i = 0; i < cards.length; i++)                          /* Shuffle the cards */ 
		    { 
		      int index = (int)(Math.random() * cards.length);              /* Generate an index randomly */ 
		      int temp = cards[i];
		      cards[i] = cards[index]; 
		      cards[index] = temp;
		    }

		    /* Display the first four cards */ 
		    for (int i = 0; i < 4; i++) 
		    {
		      String suits = suit[cards[i] / 13];
		      String ranks = rank[cards[i] % 13];
		      System.out.println("Card number " + cards[i] + ": "   + ranks + " of " + suits);
	       }

}
}
