
public class Player implements Comparable {

	private BowlingGame game;
	private String name;

	public BowlingGame getGame() {
		return game;
	}

	public void setGame(BowlingGame game) {
		this.game = game;
	}
	
	public Player(String name) {
		this.setName(name);
		game = new BowlingGame(10);
	}
	public Player(String name, BowlingGame game) {
		this.setName(name);
		this.game = game;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		int score1 = this.getGame().getSum();
		int score2 = ((Player) arg0).getGame().getSum();
		if(score1 > score2)
			return 1;
		if(score1 < score2)
			return -1;
		return 0;
	}

	@Override
	public String toString() {
		return  name + " "+game.toString();
	}
}
