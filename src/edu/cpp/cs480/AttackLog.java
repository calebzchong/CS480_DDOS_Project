package edu.cpp.cs480;

public class AttackLog {
	public String description;
	public String fullLog;
	public String type;
	public String probability;
	public String action;
	public int numRequests;
	
	public AttackLog( String description ){
		this.description = description;
	}
}
