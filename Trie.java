import java.util.*;
import java.io.*;


public class Trie {
	

	Node root=null;
	 private class Node
	{
		char data;
		boolean end;
		HashMap<Character,Node> lookup;

		Node(char data)
		{
			this.data=data;
			end=false;
			lookup=new HashMap<Character,Node>();

		}

		
				//assuming null strings r not passed
				public void addWord(String word)
				{
					//exit
					if(word.length()==0)
					{
						this.end=true;
						//System.out.println("end of "+this.data+" set to "+this.end);
						return;
					}
					
					char first=word.charAt(0);
					Node node=this.lookup.getOrDefault(first,new Node(first));
					this.lookup.put(first,node);
					//System.out.println("call made to "+node.data+" to add "+word.substring(1));
					node.addWord(word.substring(1));
					
					
				}
				
				
				//assuming null strings r not passed	
				public boolean isValid (String word)
				{
					if(word.length()==0)
					{
						//System.out.println("end position I'm at "+this.data);
						return this.end==true;

					}

					else
					{
						char first=word.charAt(0);
						Node node= this.lookup.getOrDefault(first,null); 
						if(node==null)
							return false;

						else
						{
							//System.out.println("Call to "+node.data+" --> "+word.substring(1));
							//System.out.println(node.data+".."+node.lookup.keySet()+" end "+node.end);
							return node.isValid(word.substring(1));
						}


					}

				}
				
				
				//assuming valid prefix and valid words are not null
				private boolean isValidPrefix(String word)
				{
					if(word.length()==0)
					{
						return true;
					}

					else
					{
						char first=word.charAt(0);
						Node node= this.lookup.getOrDefault(first,null); 
						if(node==null)
							return false;
						else
							return node.isValidPrefix(word.substring(1));



					}



				}




	}
	

	Trie()
	{
		root=new Node(' ');

	}
	
	

	
	public static void main(String[] args)
	{
		//New obj.  of Trie created with root initialized
		Trie T=new Trie();
	
		//create a dictionary of the below words
		String[] words = {"antarctica","ant","apple","many","my","lie","beauty","ant","Sun"};
		for(String word : words)
		{
			T.root.addWord(word);
		}
		
		//check if the below words are valid
		String[] valid = {"ant","beauty","Sunshine"};
		//
		System.out.println("valid word :");
		for(String word : valid)
		{
			boolean result=T.root.isValid(word);
			System.out.println(word+" is "+result);
			
		}
		
		//check if the below words are valid prefix
		System.out.println("valid prefix :");
		String[] validPrefix = {"ant","li","beau","love"};
		for(String word : validPrefix)
		{
			boolean result=T.root.isValidPrefix(word);
			System.out.println(word+" valid prefix result "+result);
			
		}
		
		
		
	}
	
	/*
	 * Alternatively ,This function inputs a file object ,
	 * processes it to replace certain symbols 
	 * and creates a string using StringBuilder which can be used to create a Trie
	 */
	private String readFile(File words,StringBuilder s)
	{
		try {
			Scanner read =  new Scanner(words);
			
			while(read.hasNextLine())
			{
				s.append(read.nextLine().trim());
			}
			
			
			String readWords = s.toString();
			
			System.out.println("words read "+readWords);
			
			String processed=readWords.replaceAll("[-,;'./()]", "");
			
			System.out.println("words after replacement "+processed);
					
			read.close();
			
			return processed;
			
			
		} catch (Exception e) {
			
			System.out.println(e.toString());
			return "";
		}
		
		
	}
	



}
