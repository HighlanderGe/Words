package wordquizz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

public class WordDatabase {
	private HashMap<String, Word> database;
	private ArrayList<String> dictionary;

	/*Read file from Dictionary*/
	public WordDatabase(String filename){
		try {
			BufferedReader rd = new BufferedReader(new FileReader(filename));
			dictionary = new ArrayList<>();
			database = new HashMap<String, Word>();
			while(true){
				String line = rd.readLine();
				if(line == null) break;
				int a = line.indexOf("-") - 1;
                int b = line.indexOf("-") + 1;
                int c = line.indexOf(">") - 1;
                int d = line.indexOf(">") + 1;
                String wordE = line.substring(0, a);
                String speach = line.substring(b, c);
                String definition = line.substring(d, line.length());
                Word word = new Word(speach, definition);
                database.put(wordE, word);
                dictionary.add(wordE);
                
			}
			rd.close();
			System.out.println("Dictionary was loaded successfully.");
		} catch (IOException e) {
			System.out.println("Error occured while reading database file! File doesn't exists or is not readable!");
			
		} 
	}
	
	public WordDatabase(){
		database = new HashMap<String, Word>();
		dictionary = new ArrayList<String>(); 
	}

	/*add word to dictionary*/
	public void addWords(String word, Word entry){
		if(database.containsKey(word)){
			System.out.println("Dictionary already contains entry: ");
			System.out.println(word + " - " + database.get(word));
			System.out.println("Are you sure you want to add this entry? (y, n): ");
			Scanner sc = new Scanner(System.in);
			String a = sc.nextLine();
			if (a.equals("y")){
				database.put(word, entry);
				dictionary.add(word);
			}
		} else {
			database.put(word, entry);
			dictionary.add(word);
		}
	}
	
	
	/*Writes database to file*/
	public void saveDictionary(){
		 String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		 String name = "dict" + time;
		 File dictionaryFile = new File(name);
		 try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(dictionaryFile));
			Iterator<Entry<String, Word>> it = database.entrySet().iterator();
			while(it.hasNext()){
				Entry<String, Word> temp = it.next();
				String tempS = temp.getKey();
				String tempW = temp.getValue().toString();
				writer.write(tempS + " - " + tempW);
				writer.newLine();
			}
			writer.close();
			System.out.println("Dictionary was saved successfully.");
		} catch (IOException e) {
			System.out.println("Error occured while writing database file to the disk!");
		}
	}

	public void listDictionary() {
		Iterator<Entry<String, Word>> it = database.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, Word> temp = it.next();
			String tempS = temp.getKey();
			String tempW = temp.getValue().toString();
			System.out.println(tempS + " - " + tempW);
		}

		
	}
	public boolean isEmpty(){
		if (dictionary.size() != 0) return false;
		return true;
	}
	
	public void erase(){
		dictionary.clear();
		database.clear();
		System.out.println("Database is empty now.");
	}
	
	public Word getEntry(String word){
		if(database.containsKey(word)){
			
			return database.get(word);
			
		} else {
			
			return null;
		}
	}

	public void removeEntry(String word) {
		dictionary.remove(word);
		database.remove(word);
	}

	public int size() {
		return dictionary.size();
	}

	public String getWord(int rand) {
		return dictionary.get(rand);
	}

	public String getDeffinition(String word) {
		return database.get(word).getDeffinition();
		
	}



}
