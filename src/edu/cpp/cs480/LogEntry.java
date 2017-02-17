package edu.cpp.cs480;

public class LogEntry {
	private String line;
	private String date;
	private String time;
	private String duration;
	private String srcIP;
	private String destIP;
	
	LogEntry( String line, String date, String time, String duration, String srcIP, String destIP){
		this.line = line;
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.srcIP = srcIP;
		this.destIP = destIP;
	}
	
	LogEntry( String[] log){
		this.line = log[0];
		this.date = log[1];
		this.time = log[2];
		this.duration = log[3];
		this.srcIP = log[7];
		this.destIP = log[8];
	}
	
	String getLine(){
		return line;
	}
	String getDate(){
		return date;
	}
	String getTime(){
		return time;
	}
	String getDuration(){
		return duration;
	}
	String getSrcIP(){
		return srcIP;
	}
	String getDestIP(){
		return destIP;
	}
	
	
}
