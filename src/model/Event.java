package model;

public class Event {
	
private int player;
private EventType type;
private int minute;
private int outfor;

	public Event(int player, EventType type, int minute, int outfor) {
		this.player = player;
		this.type = type;
		this.minute = minute;
		this.outfor = outfor;
	}

	public int getPlayer() {
		return player;
	}

	public EventType getType() {
		return type;
	}

	public int getMinute() {
		return minute;
	}
	
}
