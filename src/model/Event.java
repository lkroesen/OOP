package model;

public class Event {
	
private int player;
private int type;
private int minute;
private int outfor;

	public Event(int player, int type, int minute, int outfor) {
		this.player = player;
		this.type = type;
		this.minute = minute;
		this.outfor = outfor;
	}

	public int getPlayer() {
		return player;
	}

	public int getType() {
		return type;
	}

	public int getMinute() {
		return minute;
	}

	public int getOutfor() {
		return outfor;
	}
	
	@Override
	public String toString() {
		return "Event [player=" + player + ", type=" + type + ", minute="
				+ minute + ", outfor=" + outfor + "]";
	}
	
	/**
	 * Input an object to be checked
	 * @param
	 * Input an object
	 * @return
	 * Returns true or false
	 */
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		
		if (obj instanceof Event)
		{
			Event that = (Event) obj;
			if (		this.getMinute() 	== 	that.getMinute() 
					&&	this.getPlayer() 	== 	that.getPlayer()
					&&	this.getType() 		==	that.getType()
					&&	this.getOutfor()	==	that.getOutfor())
				return true;
		}
		
		return false;
		
	}
}
