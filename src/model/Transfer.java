package model;

public class Transfer {
	
	private int id;
	private int from;
	private int to;
	private int player;
	private int price;
	private int day;

	private static int curId = 0;

	public Transfer(int id, int from, int to, int player, int price, int day) {
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
	
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		
		if (obj instanceof Transfer)
		{
			Transfer that = (Transfer) obj;
			if (		this.getId() 	== 	that.getId() 
					&&	this.getFrom() 	== 	that.getFrom()
					&&	this.getTo() 		==	that.getTo()
					&&	this.getPlayer()	==	that.getPlayer()
					&&	this.getPrice()		==	that.getPrice()
					&&	this.getDay()	==	that.getDay()
					
					)
				return true;
		}
		
		return false;
		
	}
	
	

}
