/*WordQuizz.java*/
package wordquizz;

import java.util.Random;
import java.util.Scanner;

/**
 * The main program of WordQuizz V2.
 * Rewritten using object word in class Word.java, without using hashMaps, only ArrayLists are used.
 * Also in this version isn't used Stanford's acm library. Only java's native library.
 * @author highlander
 *
 */

public class Wordquizz implements WordConstants {
	private static Scanner sc;
	private static WordDatabase database = new WordDatabase();
	private static Random randomGenerator = new Random();
	private static SList success;
	private static SList failure;
	private static SList total;
	
	/*Main method*/
	public static void main(String[] args){
		System.out.println("Welcome to the word quiz program version 2");
		System.out.println("Here you can create your own dictionaries and play words game.");
		System.out.println("Idea of program is to help memorize new words while learning foreign language.");
		System.out.println("This is still console version of program.");
		
		/*Main menu - level one*/
		while(true){
			int selection = getSelection(); 
			if (selection == QUIT) break;
			switch (selection){
			case CREATE_DICTIONARY:
				createDictionary();
				break;
			case USE_OLD_DICTIONARY:
				readDictionary();
				break;
			case UPDATE_DICTIONARY:
				updateDictionary();
				break;
			case SAVE_DICTIONARY:
				database.saveDictionary();
				break;
			case CLEAR_DICTIONARY:
				database.erase();
				break;
			case REMOVE_ENTRY:
				removeEntry();
				break;
			case LIST_DICTIONARY:
				database.listDictionary();
				break;
			case WORD_QUIZ:
				wordquiz();
				break;
			case LIST_GUESSED:
				successList();
				break;
			case LIST_FAILURE:
				failedList();
				break;
			case HELP:
				displayHelp();
				break;
			default: 
				System.out.println("Invalid selection"); 
				break; 
			}
		}
	}

	/*Main method for Wordquiz program game*/
	/**
	 * This method creates 3 Linked lists to keep used words and show score in the end.
	 */
	private static void wordquiz() {
		for (int i = 0; i < 40; ++i) System.out.println();
		int max = database.size() - 1;
		success = new SList();
		failure = new SList();
		total = new SList();
		String guess;
		sc = new Scanner(System.in);
		System.out.println("Random word's deffinition is displayed below. You have to guess the word.");
		if(max > 0){
			while(true){
				int randomInt = randomGenerator.nextInt(max);
				String takeWord = database.getWord(randomInt);
				System.out.println();
				System.out.println(database.getDeffinition(takeWord));
				//System.out.println(takeWord);
				total.insertFront(takeWord);
				System.out.print("Enter your guess: ");
				guess = sc.nextLine();
				guess = guess.toLowerCase();
				if(guess.equals(takeWord.toLowerCase())){
					System.out.println("Congratulations! Your guess is correct!");
					success.insertFront(takeWord);
				} else {
					System.out.println("You failed :(");
					failure.insertFront(takeWord);
				}
				System.out.println();
				System.out.print("Do you want to continue? y or n: ");
				String answer = sc.nextLine();
				if(answer.equals("n"))break;
			}
		} else {
			System.out.println("Error! Dictionary is not loaded or created!");
		}
		System.out.println("Your result is:");
		int t = total.length();
		int s = success.length();
		int f = failure.length();
		System.out.println("Total words played: " + t);
		System.out.println("Guessed correctly: " + s);
		System.out.println("Failed: " + f);
	}

	
	/*Method to remove selected entry from dictionary*/
	private static void removeEntry() {
		sc = new Scanner(System.in);
		System.out.print("Enter the word which should be removed from current dictionary: ");
		String word = sc.nextLine();
		if(database.getEntry(word) != null){
			database.removeEntry(word);
			System.out.print("Entry was deleted.");
		} else {
			System.out.println("Entry not found. Try other word.");
		}
	}


	/*Method to update dictionary*/
	private static void updateDictionary() {
		sc = new Scanner(System.in);
		System.out.print("Enter the word to look up in dictionary: ");
		String word = sc.nextLine();
		if(database.getEntry(word) != null){
			System.out.println("Entry is: " +  word + " - " + database.getEntry(word));
			System.out.print("Enter new deffinition or translation for the word: ");
			String deffinition = sc.nextLine();
			database.getEntry(word).setDeffinition(deffinition);
			
		} else {
			System.out.println("Entry not found. Try other word.");
		}
	}

	/*Create new dictionary and add entries. This method also adds entries to exiting dictionary. */
	private static void createDictionary() {
		System.out.println("You are starting with the new dictionary. Start to enter words and deffinitions.");
		System.out.println("To finnish, do not enter new word, but just hit enter.");
		
		sc = new Scanner(System.in);
		while(true){
			System.out.print("Enter new word : ");
			String word = sc.nextLine();
			if(word.equals("")) break;
			System.out.print("Enter part of the speach : ");
			String partOfSpeach = sc.nextLine();
			System.out.print("Enter deffinition or translation : ");
			String deffinition = sc.nextLine();
			Word wordEntry = new Word(partOfSpeach.toLowerCase(), deffinition.toLowerCase());
			if (database.isEmpty()) {
				database = new WordDatabase();
				database.addWords(word.toLowerCase(), wordEntry);
			} else {
				database.addWords(word.toLowerCase(), wordEntry);
			}
		}
	}
	
/*Method to read dictionary from file*/
	private static void readDictionary() {
		sc = new Scanner(System.in);
		System.out.print("Enter dictionary filename : ");
		String filename = sc.nextLine();
		database = new WordDatabase(filename);
		
	}
/*Main menu values*/
	private static int getSelection() {
		sc = new Scanner(System.in);
		System.out.println(); 
		System.out.println("Please make a selection:");
		System.out.println("1. Create new dictionary, or add words to the loaded dictionary.");
		System.out.println("2. Load dictionary from file.");
		System.out.println("3. Update word deffinition in Dictionary.");
		System.out.println("4. Save dictionary to the file.");
		System.out.println("5. Clear current dictionary");
		System.out.println("6. Remove entry from dictionary");
		System.out.println("7. List all words in current dictionary.");
		System.out.println("8. Play quiz game (Dictionary must be loaded first).");
		System.out.println("9. Load successfully guessed word list.");
		System.out.println("10. Load failed word list.");
		System.out.println("11. Help");
		System.out.println("0. exit");
		System.out.print("Enter your choise: ");
		int choice = sc.nextInt();
		return choice;
	}
	
	private static void failedList() {
		if(failure.isEmpty()){
			System.out.println("List is empty");
		} else {
			String str = failure.toString();
			System.out.println(str);
		}
		
	}

	private static void successList() {
		if(success.isEmpty()){
			System.out.println("List is empty");
		} else {
			String str = success.toString();
			System.out.println(str);
		}
		
	}
	
	private static void displayHelp() {
		System.out.println("This is version 2 of my word quizz program.");
		System.out.println("Program is totaly rewriten using Java standard libraries. version 1 was usind Stanford's acm library.");
		System.out.println("Major change is also in functions. Here is added to dictionary part of the speach. Wordquizz also - can remember failures and successes.");
		System.out.println("Dictionary files are simple text files and are saved in the same directory as the program itself.");
		System.out.println("In the program are used classes - word, SListNode, SList and database.");
		System.out.println("Also Hashmap and ArrayList.");
		System.out.println("Program is fully cross platform.");
		System.out.println("Hope everything is clear and you will enjoy it.");
	}
}
