package model;

public class Player {

	private int id;
	private String firstname;
	private String surname;
	private byte number;
	private int type;
	private int position;
	private byte offensiveRating;
	private byte defensiveRating;
	private byte stamina;
	private int price;

	/**
	 * Creates a Player-Object
	 * 
	 * @param id
	 * @param firstname
	 * @param surname
	 * @param jerseyNumber
	 * @param type
	 * @param offensiveScore
	 * @param defensiveScore
	 * @param staminaScore
	 * @param teamId
	 * @param status
	 * @param price
	 */
	public Player(int id, String firstname, String surname, byte jerseyNumber,
			int type, int position, byte offensiveScore, byte defensiveScore,
			byte staminaScore, int price) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.surname = surname;
		this.number = jerseyNumber;
		this.type = type;
		this.offensiveRating = offensiveScore;
		this.defensiveRating = defensiveScore;
		this.stamina = staminaScore;
		this.price = price;
		this.position = position;
	}

	// Getters and Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public short getJerseyNumber() {
		return number;
	}

	public void setJerseyNumber(byte jerseyNumber) {
		this.number = jerseyNumber;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public byte getOffensiveScore() {
		return offensiveRating;
	}

	public void setOffensiveScore(byte offensiveScore) {
		this.offensiveRating = offensiveScore;
	}

	public byte getDefensiveScore() {
		return defensiveRating;
	}

	public void setDefensiveScore(byte defensiveScore) {
		this.defensiveRating = defensiveScore;
	}

	public byte getStaminaScore() {
		return stamina;
	}

	public void setStaminaScore(byte staminaScore) {
		this.stamina = staminaScore;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", firstname=" + firstname + ", surname="
				+ surname + ", jerseyNumber=" + number + ", type=" + type
				+ ", offensiveRating=" + offensiveRating + ", defensiveRating="
				+ defensiveRating + ", staminaRating=" + stamina
				+ ", price=" + price + "]";
	}

	// Getters and Setters end
	
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		
		if (obj instanceof Player)
		{
			Player that = (Player) obj;
			if (		this.getId() 	== 	that.getId() 
					&&	this.getFirstname() 	== 	that.getFirstname()
					&&	this.getSurname() 		==	that.getSurname()
					&&	this.getJerseyNumber()	==	that.getJerseyNumber()
					&&	this.getType()		==	that.getType()
					&&	this.getPosition()	==	that.getPosition()
					&&	this.getOffensiveScore() == that.getOffensiveScore()
					&&	this.getDefensiveScore() == that.getDefensiveScore()
					&&	this.getStaminaScore()	== that.getStaminaScore()
					&&	this.getPrice()			== that.getPrice()
					)
				return true;
		}
		
		return false;
		
	}
	
	/**
	 * Returns the changed values on a player's stats
	 * @param old_player
	 * Input the Old_Player
	 * @param new_player
	 * Input the new Player
	 * @return
	 * returns an array with at 0 the change to offensive, at 1 the change to defensive and at 2 the stamina
	 */
	public static int[] ChangedStats(Player old_player, Player new_player)
	{
		int[] delta = new int[2];
		delta[0] = (int) ( new_player.getOffensiveScore() - old_player.getOffensiveScore() );
		delta[1] = (int) ( new_player.getDefensiveScore() - old_player.getDefensiveScore() );
		delta[2] = (int) ( new_player.getStaminaScore() - old_player.getStaminaScore() );
		
		return delta;
	}
}