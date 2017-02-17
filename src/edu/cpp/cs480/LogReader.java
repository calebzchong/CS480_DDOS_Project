package edu.cpp.cs480;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LogReader {
	
	
	public static String loadLog( String path ){
		Path p = Paths.get(path);
		List<String> log = new ArrayList<String>();
		try {
			log = Files.readAllLines(p);
			parseLog(log);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// use hashtable for frequency 
	// if count equals 1000, check following entries for same srcIP
	private static void parseLog( List<String> log) {
		ArrayList<LogEntry> logEntries = new ArrayList<LogEntry>(); 
		for( String str : log ){
			LogEntry entry = new LogEntry( str.split(" ") );
			logEntries.add(entry);
		}
	}
	
}