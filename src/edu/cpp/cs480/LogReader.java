package edu.cpp.cs480;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LogReader {

	public static List<AttackLog> loadLog( String path ) throws Exception{
		ArrayList<LogEntry> logEntries = new ArrayList<LogEntry>();
		BufferedReader br = new BufferedReader( new FileReader(path) );
		String str;
		while ( (str = br.readLine()) != null) {
			LogEntry entry = new LogEntry( str );
			logEntries.add(entry);
		}
		br.close();
		return parseLog(logEntries);
	}

	// use a moving count of number of the requests to detect potential DOS attacks
	private static List<AttackLog> parseLog( List<LogEntry> logEntries) {
		final long WINDOW_LENGTH = 1000 * 60 * 1; //1 minutes
		final long DOS_THRESHOLD_HIGH = 500;
		final long DOS_THRESHOLD_MEDIUM = 400;
		final long DOS_THRESHOLD_LOW = 300;
		List<AttackLog> detectedAttacks = new ArrayList<AttackLog>();
		List<LogEntry> window = new LinkedList<LogEntry>();
		AttackLog attack = null;
		for( int i = 0; i < logEntries.size(); i++){
			window.add( logEntries.get(i) );
			Date initialD = window.get(0).getTime(); // GAS GAS GAS
			Date finalD = logEntries.get(i).getTime();
			if ( attack != null && window.size() < attack.numRequests ){
				detectedAttacks.add(attack);
				attack = null;
				window.clear();
			} else {


				if ( window.size() >= DOS_THRESHOLD_LOW ){
					//Continue if the attack goes past threshold. Needs tweaking
					//				if( finalD.getTime() - initialD.getTime()  > WINDOW_LENGTH )
					//				{
					//					continue;
					//				}

					attack = new AttackLog( "(D)DOS Detected at " + initialD );
					StringBuilder sb = new StringBuilder();

					//Categorize the strength and action for the attack
					if(window.size() > DOS_THRESHOLD_HIGH)
					{
						attack.probability = "High";
						attack.action = "Flag";
					}
					else if(window.size() > DOS_THRESHOLD_MEDIUM)
					{
						attack.probability = "Medium";
						attack.action = "Flag";
					}
					else {
						attack.probability = "Low";
						attack.action = "Monitor";
					}
					for ( int j = 0; j < window.size(); j++){
						sb.append( window.get(j).getRawLog() + "\n" );
					}
					attack.numRequests = window.size();
					attack.fullLog = sb.toString();
					attack.type = "DDOS";

				}
				while( !window.isEmpty() && ( finalD.getTime() - initialD.getTime()  > WINDOW_LENGTH || finalD.getTime() - initialD.getTime() < 0 )){
					window.remove(0);
					initialD = window.get(0).getTime();
				}
			}
		}
		return detectedAttacks;
	}

	//	public static List<AttackLog> loadLog( String path ) throws Exception{
	//		ArrayList<LogEntry> logEntries = new ArrayList<LogEntry>();
	//		BufferedReader br = new BufferedReader( new FileReader(path) );
	//		String str;
	//		while ( (str = br.readLine()) != null) {
	//			LogEntry entry = new LogEntry( str );
	//			logEntries.add(entry);
	//		}
	//		br.close();
	//		return parseLog(logEntries);
	//	}
	//
	//	// use a moving count of number of the requests to detect potential DOS attacks
	//	private static List<AttackLog> parseLog( List<LogEntry> logEntries) {
	//		final long WINDOW_LENGTH = 1000 * 60 * 1; //1 minutes
	//		final long DOS_THRESHHOLD = 300;
	//		List<AttackLog> detectedAttacks = new ArrayList<AttackLog>();
	//		Queue<LogEntry> window = new LinkedList<LogEntry>();
	//		for( int i = 0; i < logEntries.size(); i++){
	//			window.add( logEntries.get(i) );
	//			Date initialD = window.peek().getTime(); // GAS GAS GAS
	//			Date finalD = logEntries.get(i).getTime();
	//			if ( window.size() >= DOS_THRESHHOLD){
	//				AttackLog attack = new AttackLog( "(D)DOS Detected at " + initialD );
	//				StringBuilder sb = new StringBuilder();
	//				while ( !window.isEmpty() ){
	//					sb.append( window.poll().getRawLog() + "\n" );
	//				}
	//				attack.fullLog = sb.toString();
	//				detectedAttacks.add(attack);
	//			} else {
	//				while( finalD.getTime() - initialD.getTime()  > WINDOW_LENGTH ){
	//					window.poll();
	//					initialD = window.peek().getTime();
	//				}
	//			}
	//		}
	//		return detectedAttacks;
	//	}

}