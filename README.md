LoyalistSpies
=============

Java Based Card Game called Loyalist Spies

Game Rules
----------
Before starting the game a target score should be choosen. *Suggested Target Scores*

At the beginning of the game randomly give each player an ace. Which suit their ace belongs to should be kept secret at all times.

Next deal the remainder of the deck, including Jokers. Any player who ends the game with a Joker is a Traitor. Traitors earn points by ensuring that missions fail.

Of the dealt cards, players pick 5 cards to keep with the remainder being shuffled into the Success Deck.

Players then randomly determine a Mission by flipping the top card of the Success deck and referencing the mission guide. Players take turns determining the mission, following a clockwise order.

Follow the instructions on the mission guide in regards to mission set up.

Play three cards face down from the Success Deck onto the mission, then each player plays a card from their hand onto the mission, also face down. Shuffle these cards and then determine the total value of these cards (Jacks, Queens and Kings are 11, 12 and 13 respectively. Jokers are worth 0.) Players may not play a Joker from their hand, it may however end up being played on the mission if it was in the Success deck.

The total value of cards played for the mission is then compared to the covert value of the mission, if it exceeds the covert value the mission is exposed (failed) and set aside along with all the cards played on it. If it is less than the covert value then the mission is successful and its success condition is resolved and then set aside along with all the cards played on it..

If a player ever has fewer than 2 cards left in their hand, after mission resolution. Then they should draw cards from the Success deck until they have 2 cards in their hand.

Repeat this process until the success deck runs out of cards.

Then each player reveals their Ace and any jokers they may have left in their hand. Proceed to score the round.

Once the round has been scored start a new round and continue until a player beats the target score.

###Scoring###
Determine the most common suit among missions. That player is Compromised. If there is a tie for most common suit, then find the most common suit among failed missions. If there is still a tie, then all tied players are compromised.
Traitors cannot be Compromised.
If a player does not have the Traitor card at the end of the game, and is not compromised, they are Loyal.


Combine the total VP value of successful missions, loyal players receive this many points.
For each mission belonging to a players suit, they get a point.
For each mission belonging to the compromised players suit, they get a point.
For each failed mission, each traitor gets a point.
If the majority of missions were failed, the traitors gets 10 points.
If the majority of failed missions were of the compromised players suit, they get 15 points.

###Mission Guide###

<table>
  <caption>Card Definitions</caption>
  <tr>
    <td>Card</td>
    <td>Covert Value</td>
    <td>VP</td>
  </tr>
  <tr>
    <td>2-5</td>
    <td>50</td>
    <td>2</td
  </tr>
   <tr>
    <td>6-8</td>
    <td>40</td>
    <td>3</td
  </tr>
   <tr>
    <td>9</td>
    <td>35</td>
    <td>4</td
  </tr>
   <tr>
    <td>10</td>
    <td>30</td>
    <td>5</td
  </tr>
</table>

<table>
  <caption>Suit Definitions</caption>
  <tr>
    <td>Suit</td>
    <td>Covert Modifier</td>
    <td>VP Modifier</td>
    <td>Mission Effect</td>
  </tr>
  <tr>
    <td>Clubs</td>
    <td>-3</td>
    <td>+2</td>
    <td>Each player draws a card on failure</td>
  </tr>
  <tr>
    <td>Diamonds</td>
    <td>0</td>
    <td>0</td>
    <td>Each player draws 2 cards on success</td>
  </tr>
  <tr>
    <td>Spades</td>
    <td>+1</td>
    <td>0</td>
    <td>Each player draws a card</td>
  </tr>
  <tr>
    <td>Hearts</td>
    <td>0</td>
    <td>-1</td>
    <td>Each player draws a card on failure</td>
  </tr>
</table>

<table>
  <caption>Face Cards</caption>
  <tr>
    <td>Card</td>
    <td>Effect</td>
  <tr>
  <tr>
    <td>Jack</td>
    <td>When a player reveals a jack as part of a mission reveal they may look at any other players suit card in secret. If it matches the suit of the jack they must reveal their own suit to that player. As a result of this the target player's suit is revealed to all players. This is instead of determining a mission.</td>
  <tr>
  <tr>
    <td>Queen</td>
    <td>When a player reveals a queen as part of a mission reveal they may look at the top three cards of the mission deck and rearrange them. This is instead of determining a mission.</td>
  <tr>
  <tr>
    <td>King</td>
    <td>When a player reveals a king as part of a mission reveal they immediately reveal the next card and use its value to determine the mission parameters but increase the missions VP by 2. If the next card is queen or jack then it's effect replaces the king's effect. If the next card is a king, then the +2 VP bonus stacks, and the player reveals another card.</td>
  <tr>
</table>
