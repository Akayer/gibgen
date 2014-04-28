import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Gibgen {
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		String input = "";
		
		// Continously get tokens from System.in
		while(in.hasNext())
		{
			input = in.next();
			ArrayList<String> strArr = new ArrayList<String>();
			
			// Pass input string to giberize each token and output to console
			System.out.print(giberize(input) + " ");

		}
		}
	
	// Input: 	String from System.in
	// Output: 	StringBuilder with all chars scrambled except beginning and ending
	// 			characters. Doesn't scramble numbers, abbreviations, or punctuation
	public static StringBuilder giberize(String s)
	{
		// Variables used to split string around punc and track punc placement
		ArrayList<Integer> indexOfPunc = new ArrayList<Integer>();
		ArrayList<String> strList = new ArrayList<String>();
		StringBuilder punc = new StringBuilder();
		StringBuilder str = new StringBuilder();
		
	      for (int i=0;i<s.length();i++){
	    	  char c = s.charAt(i);
	    	  
	    	  // If next char is punc, split strings around punc and add to ArrList
	    	  switch (c)
	    	  {
	      		case '.':  
	      			savePuncPos('.', i, strList, indexOfPunc,punc,str);
	      			break;
	      		case '\'':  
	      			savePuncPos('\'', i, strList, indexOfPunc,punc,str);
	      			break;
	      		case ',':  
	      			savePuncPos(',', i, strList, indexOfPunc,punc,str);
	      			break;
	      		case '"':  
	      			savePuncPos('"', i, strList, indexOfPunc,punc,str);
	      			break;
	      		case ';':  
	      			savePuncPos(';', i, strList, indexOfPunc,punc,str);
	      			break;
	      		case ':':  
	      			savePuncPos(':', i, strList, indexOfPunc,punc,str);
	      			break;
	      		case '?':  
	      			savePuncPos('?', i, strList, indexOfPunc,punc,str);
	      			break;
	      		case '!':  
	     			savePuncPos('!', i, strList, indexOfPunc,punc,str);
	      			break;
	      		case '[':  
	      			savePuncPos(']', i, strList, indexOfPunc,punc,str);
	      			break;
	      		case ']':  
	      			savePuncPos(']', i, strList, indexOfPunc,punc,str);
	      			break;
	      		case '{':  
	      			savePuncPos('}', i, strList, indexOfPunc,punc,str);
	      			break;
	      		case '}':  
	      			savePuncPos('}', i, strList, indexOfPunc,punc,str);
	      			break;
	      		case '(':  
	     			savePuncPos('(', i, strList, indexOfPunc,punc,str);
	      			break;
	      		case ')':  
	     			savePuncPos(')', i, strList, indexOfPunc,punc,str);
	      			break;
	      		// While punc isnt detected add chars to StringBuilder
	      		default :
	      			str.append(c);
	      			break;
	    	  }
	    	  
	       }
	      
	      if(str.length() > 0)
	    	  strList.add(str.toString());
	      
	      str.setLength(0);
	      
	      // Scramble all strings in arrList that arent abbrev's or Nums
	      for (String temp : strList)
	      {
	    	   //System.out.println(temp);
	    	  if(isAbbrev(temp) || isNum(temp))
	    		  str.append(temp);
	    	  else
	    	    str.append(scrambleString(temp));
	      }  
	    	  
	    	  
	      // Add the punctuation back into the scrambled string
	      for(int i=0;i<indexOfPunc.size();i++)
	      {
	    	  char c = punc.charAt(i);
	    	  int pos = indexOfPunc.get(i);
	    	  str.insert(pos,c);
	      }
		return str;
		
	}
	
	// Input: 	Parameters to keep track of punctuation
	// Output:	Void; update Lists and strings and keep track of what
	// 			punc was parsed and the position in the string it was
	public static void savePuncPos( char c, int i,
									ArrayList<String> strList,
									ArrayList<Integer> indexOfPunc,
									StringBuilder punc,
									StringBuilder str)
	{
			indexOfPunc.add(i);
			punc.append(c);
			strList.add(str.toString());
			str.setLength(0);
	}
	
	// Input: 	String 
	// Output: 	String with all chars scrambled except beginning and ending
	public static String scrambleString(String s)
	{
		int len = s.length();
		boolean[] b = new boolean[len];
		StringBuilder str = new StringBuilder();
		Random randomGenerator = new Random();
		
		// Return string if know scrambling is possible
		if(len <= 3)
			return s;
		// Add beginning char to StringBuilder
		else
		{
			str.append(s.charAt(0));
		}
		
		// Attempt to random indexes from initial string to StringBuilder
		// while keeping track of indexes of chars already added
		while(str.length() < len - 1)
		{
			
			int randomInt = randomGenerator.nextInt(len-2) + 1;
			if(!b[randomInt])
			{
				str.append(s.charAt(randomInt));
				b[randomInt] = true;
			}
			
		}
		
		// Add final char and return
		str.append(s.charAt(len-1));
		
		
		return str.toString();
	}
	
	// Input: 	String
	// Output: 	Boolean value; True if all chars in string are capitalized
	//			False if any char within string isn't 'A', 'B', 'C', ... 'Z' 
	public static boolean isAbbrev(String s)
	{
		boolean abbrev = true;
		
		for(int i=0;i<s.length();i++)
		{
			if(s.charAt(i) < 'A'  || s.charAt(i) > 'Z')
				abbrev = false;
		}
		
		return abbrev;
	}
	
	// Input: 	String
	// Output: 	Boolean value; True if string is a number value
	//			False if string isn't a number value
	public static boolean isNum(String s)
	{
		  try  
		  {  
		    double d = Double.parseDouble(s);  
		  }  
		  catch(NumberFormatException nfe)  
		  {  
		    return false;  
		  }  
		  return true;
	}
}	