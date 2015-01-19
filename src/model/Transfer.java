package model;

public class Transfer {
	
	private int id;
	private int from;
	private int to;
	private int player;
	private int price;
	private int day;

	private static int curId = 0;

	public Transfer(int from, int to, int player, int price, int day) {
		this.id = Transfer.curId;
		curId++;

		this.from = from;
		this.to = to;
		this.player = player;
		this.price = price;
		this.day = day;
	}
	
	public int getId(){
		return id;
	}

	public int getFrom() {
		return from;
	}

	public int getTo() {
		return to;
	}

	public int getPlayer() {
		return player;
	}

	public int getPrice() {
		return price;
	}

	public int getDay() {
		return day;
	}

	@Override
	public String toString() {
		return "Transfer [id=" + id +", from=" + from + ", to=" + to + ", player=" + player
				+ ", price=" + price + ", day=" + day + "]";
	}
	
	@Override
	public boolean equals(Object obj)
	{		
		if (obj instanceof Transfer)
		{
			Transfer that = (Transfer) obj;
			
			// ID defines a transfer
			if (this.id == that.id)
				return true;
		}
		return false;
	}
	
	

}
