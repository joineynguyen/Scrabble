package scrabble;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Reads and store words from file filled with legal scrabble words to play
public class Dictionary 
{
	Trie trie;
	
	public Dictionary()
	{
		trie = new Trie();
		try 
		{
			addFileToDictionary();
		}
		catch (FileNotFoundException e) 
		{
			
			e.printStackTrace();
		}
	}
	
	public void addFileToDictionary() throws FileNotFoundException
	{
		File file = new File("C:\\Users\\Joiney\\Desktop\\EclipseProjects\\Scrabble\\file.txt");
		try (Scanner sc = new Scanner(file)) {
			while(sc.hasNextLine())
			{
				String word = sc.nextLine();
				word = word.toLowerCase();
				trie.insertWord(word);
			}
		}
	}
	
	public boolean verifyWord(String word)
	{
		word = word.toLowerCase();
		return trie.searchWord(word);
	}
	
	
}
