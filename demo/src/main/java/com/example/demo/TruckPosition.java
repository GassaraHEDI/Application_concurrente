package com.example.demo;

import java.time.Instant;

public class TruckPosition {
    private int truckId;
    private Instant ts;
    private  Position position;
	public TruckPosition(){}
	public TruckPosition(int truckId, Instant ts, Position position) {
		this.truckId = truckId;
		this.ts = ts;
		this.position = position;
	}
	public int getTruckId() {
		return truckId;
	}
	public void setTruckId(int truckId) {
		this.truckId = truckId;
	}
	public Instant getTs() {
		return ts;
	}
	public void setTs(Instant ts) {
		this.ts = ts;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}


	@Override
	public String toString() {
		return "TruckPosition{" +
				"truckId=" + truckId +
				", ts=" + ts +
				", position=" + position +
				'}';
	}
}
