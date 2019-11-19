package com.parquet.dao;

public class Team {
	String teamName;
	int teamNumber;
	
	public Team(String teamName, int teamNumber) {
		super();
		this.teamName = teamName;
		this.teamNumber = teamNumber;
	}
	
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public int getTeamNumber() {
		return teamNumber;
	}
	public void setTeamNumber(int teamNumber) {
		this.teamNumber = teamNumber;
	}
}
