package studio7;

public class HockeyPlayer {

	private String name;
	private final int jersey;
	private int games;
	private int goals;
	private int assists;

	public HockeyPlayer(String name, int jersey) {
		this.name = name;
		this.jersey = jersey;
		this.games = this.goals = this.assists = 0;
	}

	public void recordGame(int goals, int assists) {
		this.games++;
		this.goals += goals;
		this.assists += assists;
	}

	public int getPoints() {
		return this.goals + this.assists;
	}

	public String getName() {
		return name;
	}

	public int getJersey() {
		return jersey;
	}

	public int getGames() {
		return games;
	}

	public int getGoals() {
		return goals;
	}

	public int getAssists() {
		return assists;
	}

}
