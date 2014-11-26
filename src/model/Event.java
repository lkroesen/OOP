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

	@Override
	public String toString() {
		return "Event [player=" + player + ", type=" + type + ", minute="
				+ minute + ", outfor=" + outfor + "]";
	}
	
	
}
