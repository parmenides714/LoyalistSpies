package spies.framework;

import java.util.List;

public interface IOutputDevice
{
	public void showSuit(Player player);

	public void showMessage(String msg);

	public void showCards(List<Card> cards);

	public void showPlayers(List<String> names);

	public void showScore();

	public void showPlayArea(PlayArea playArea);

	public void revealCard(Card card);
}
