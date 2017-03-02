package edu.cpp.cs480;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogEntry {
	private String line;
	private Date time;
	private String duration;
	private String srcIP;
	private String destIP;
	private String rawLog;
	
//	public LogEntry( String line, String date, String time, String duration, String srcIP, String destIP) throws ParseException{
//		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//		this.time = format.parse(date + " " + time);
//		this.line = line;
//		this.duration = duration;
//		this.srcIP = srcIP;
//		this.destIP = destIP;
//	}
//	
//	public LogEntry( String[] log) throws ParseException{
//		this.line = log[0];
//		
//		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//		this.time = format.parse(log[1] + " " + log[2]);
//		this.duration = log[3];
//		this.srcIP = log[7];
//		this.destIP = log[8];
//	}
	
	public LogEntry( String str ) throws ParseException{
		String[] log = str.split(" ");
		this.rawLog = str;
		this.line = log[0];
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		this.time = format.parse(log[1] + " " + log[2]);
		this.duration = log[3];
		this.srcIP = log[7];
		this.destIP = log[8];
	}
	
	String getLine(){
		return line;
	}

	Date getTime(){
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
	
	public boolean equals( LogEntry l){
		return ( l != null && 
				 this.srcIP == l.srcIP &&
				 this.destIP == l.destIP);
	}
	
	@Override
	public String toString(){
		return "Line: " + line + "\n" +
				"Time: " + time + "\n" +
				"Duration: " + duration + "\n" +
				"srcIP: " + srcIP + "\n" +
				"destIP: " + destIP + "\n";
	}

	public String getRawLog() {
		return rawLog;
	}

}
