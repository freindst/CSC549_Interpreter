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
		Expression re = Parser.parseExpression(value);
		RememberStatement rs = new RememberStatement(type, name, re);
		return rs;
	}
	
	static ResolveExpression parseResolve(String name)
	{
		ResolveExpression rs = new ResolveExpression(name);
		return rs;
	}
	
	static DoMathExpression parseDoMath(String name)
	{
		DoMathExpression e = new DoMathExpression(name);
		return e;
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
			System.err.println(e.getStackTrace());
			System.err.println("File Not Found!");
		}
	}
	
	static void parseStatement(String s)
	{
		System.out.println(s);
		String[] theParts = s.split("\\s+");
		if(theParts[0].equals("remember"))	// "remember int a = 5"
		{
			String str = "";
			for(int i = 4; i < theParts.length; i++ )
			{
				str = str + " " + theParts[i];
			}
			str = str.trim();
			theListOfStatements.add(Parser.parseRemember(theParts[1], 
					theParts[2], str));
		}
		
	}
	
	static Expression parseExpression(String e)
	{
		if (e.startsWith(DoMathExpression.identifier))
		{
			return Parser.parseDoMath(e);
		}
		else
		{
			return Parser.parseResolve(e);
		}
	}
}
