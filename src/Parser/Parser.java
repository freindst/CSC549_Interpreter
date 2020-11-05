package Parser;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser 
{
	private static ArrayList<Statement> theListOfStatements = new ArrayList<Statement>();
	
	public static ArrayList<Statement> getParsedStatements()
	{
		return theListOfStatements;
	}
	
	public static void display()
	{
		for(Statement s : theListOfStatements)
		{
			System.out.println(s);
		}
	}
	
	static RememberStatement parseRemember(String type, String name, String value)
	{
		//parse this string into language objects
		//turn remember syntax into a RememberStatement
		RememberStatement rs = new RememberStatement(type, name, value);
		return rs;
	}
	
	static ResolveStatement parseResolve(String name)
	{
		ResolveStatement rs = new ResolveStatement(name);
		return rs;
	}
	
	public static void parse(String filename)
	{
		try
		{
			Scanner input = new Scanner(new File(System.getProperty("user.dir") + 
					"/src/" + filename));
			String fileContents = "";
			while(input.hasNext())
			{
				fileContents += input.nextLine();
			}
			
			//System.out.println(fileContents);
			String[] theProgramLines = fileContents.split(";");
			for(int i = 0; i < theProgramLines.length; i++)
			{
				parseStatement(theProgramLines[i]);
			}
		}
		catch(Exception e)
		{
			System.err.println("File Not Found!");
		}
	}
	
	static void parseStatement(String s)
	{
		String[] theParts = s.split("\\s+");
		if(theParts[0].equals("remember"))	// "remember int a = 5"
		{
			theListOfStatements.add(Parser.parseRemember(theParts[1], 
					theParts[2], theParts[4]));
		}
		else if(theParts[0].equals("resolve"))	//resolve a;
		{
			theListOfStatements.add(Parser.parseResolve(theParts[1]));
		}
		
	}
}
