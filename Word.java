/*File: Word.java*/
package wordquizz;

/**
 * Word.java class created for Wordquizz program V2.
 * Object Word consists of two strings - part of the speech and definition.
 * This object must be used in HashMap together with string.
 * 
 * @author highlander
 *
 */

public class Word {
	
	private String partOfSpeach;
	private String deffinition;
	
	/*constructors */
	public Word(String partOfSpeachL, String deffinitionL){
		
		partOfSpeach = partOfSpeachL;
		deffinition = deffinitionL;
		
	}
	
	public Word(String deffinitionL){
		
		deffinition = deffinitionL;
		partOfSpeach = null;
	}
	
	//setters
	public void setPartOfSpeach(String partOfSpeachL){
		partOfSpeach = partOfSpeachL;
	}
	
	public void setDeffinition(String deffinitionL){
		deffinition = deffinitionL;
	}
	
	// getters

	public String getDeffinition(){
		return deffinition;
	}
	public String getPartOfSpeach(){
		return partOfSpeach;
	}
	
	//tostring
	public String toString() { 
		return (partOfSpeach + " > " + deffinition); 
		}

}
