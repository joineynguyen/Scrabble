package scrabble;

//Dictionary uses trie data structure to store and search words
public class Trie 
{
	class TrieNode
	{
		private TrieNode[] children;
		private boolean endOfWord;
		
		public TrieNode()
		{
			children = new TrieNode[26];
			endOfWord = false;
			
			for(int i = 0; i < 26; i++)
			{
				children[i] = null;
			}
		}
	}
	
	TrieNode root;
	
	public Trie()
	{
		root = new TrieNode();
	}
	
	public void insertWord(String word)
	{
		if(word == null)
		{
			return;
		}
		
		TrieNode current = root;
		
		for(char c : word.toCharArray())
		{
			if(current.children[c - 'a'] == null)
			{
				current.children[c - 'a'] = new TrieNode();
				current = current.children[c - 'a'];
			}
			else
			{
				current = current.children[c - 'a'];
			}
		}
		current.endOfWord = true;
	}
	
	public boolean searchWord(String word)
	{
		if(word == null)
		{
			return true;
		}
		
		TrieNode current = root;
		
		for(int i = 0; i < word.length(); i++)
		{
			char c = word.charAt(i);
			
			if(current.children[c - 'a'] != null)
			{
				current = current.children[c - 'a'];
			}
			else
			{
				return false;
			}
		}
		
		if(current.endOfWord == true)
		{
			return true;
		}
		
		return false;
	}
}
